package com.example.SmokeDetectionMaster.Service.Territory.Impl;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRequest;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;
import com.example.SmokeDetectionMaster.Exception.TerritoryLimitExceededException;
import com.example.SmokeDetectionMaster.Mapper.Territory.UserTerritoryMapper;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Service
@Slf4j
public class UserTerritoryServiceImpl implements UserTerritoryService {

    @Autowired
    private UserTerritoryMapper userTerritoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TerritoryChangeRecordUserVo> getApproveState(Integer userID) {
        return userTerritoryMapper.getApproveState(userID);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String,Object> getAvailableTerritories(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        Integer allowNums = userTerritoryMapper.countUserAndPendingTerritories(userId);
        map.put("currentCount", 4 - allowNums);
        if (allowNums == null || allowNums < 0 || allowNums > 4) {
            map.put("currentCount", -1);
        }
        map.put("availableTerritories",userTerritoryMapper.findAvailableTerritories());
        log.info(map.toString());
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void requestTerritoryChanges(int userId, List<Integer> territoryIds) throws TerritoryLimitExceededException {
        Integer currentCount = userTerritoryMapper.countUserAndPendingTerritories(userId);
        if (currentCount == null) {
            currentCount = 0;
        }
        if (currentCount + territoryIds.size() > 4) {
            throw new TerritoryLimitExceededException("超过单一用户四辖区的限制");
        }
        List<Integer> availableTerritoryIds = userTerritoryMapper.findAvailableTerritories().stream()
                .map(TerritoryUserVo::getTerritoryId)
                .collect(Collectors.toList());
        List<TerritoryChangeRequest> requests = territoryIds.stream()
                .filter(availableTerritoryIds::contains)
                .map(id -> new TerritoryChangeRequest(userId, id, "pending"))
                .collect(Collectors.toList());

        if (requests.size() != territoryIds.size()) {
            throw new TerritoryLimitExceededException("存在无效的辖区ID请求");
        }

        if (!requests.isEmpty()) {
            userTerritoryMapper.batchInsertTerritoryChangeRequests(requests);
        }
    }
}
