package com.example.SmokeDetectionMaster.Service.Territory.Impl;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;
import com.example.SmokeDetectionMaster.Mapper.Territory.UserTerritoryMapper;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Service
public class UserTerritoryServiceImpl implements UserTerritoryService {

    @Autowired
    private UserTerritoryMapper userTerritoryMapper;

    @Override
    public Map<String,Object> getAvailableTerritories(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("currentCount",userTerritoryMapper.countUserAndPendingTerritories(userId));
        map.put("availableTerritories",userTerritoryMapper.findAvailableTerritories());
        return map;
    }

    public void requestTerritoryChanges(int userId, List<Integer> territoryIds) throws Exception {
        Integer currentCount = userTerritoryMapper.countUserAndPendingTerritories(userId);
        if (currentCount + territoryIds.size() > 4) {
            throw new Exception("超过单一用户四辖区的限制");
        }

        List<TerritoryUserVo> availableTerritories = userTerritoryMapper.findAvailableTerritories();
        List<Integer> availableTerritoryIds = availableTerritories.stream().map(TerritoryUserVo::getTerritoryId).collect(Collectors.toList());

        for (Integer territoryId : territoryIds) {
            if (!availableTerritoryIds.contains(territoryId)) {
                throw new Exception("辖区ID为"+territoryId+"的辖区已经被他人抢注");
            }
            userTerritoryMapper.insertTerritoryChangeRequest(userId, territoryId);
        }
    }
}
