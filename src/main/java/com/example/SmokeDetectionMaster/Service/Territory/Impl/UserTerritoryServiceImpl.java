package com.example.SmokeDetectionMaster.Service.Territory.Impl;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRequest;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import com.example.SmokeDetectionMaster.Exception.TerritoryLimitExceededException;
import com.example.SmokeDetectionMaster.Mapper.Territory.UserTerritoryMapper;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${fastApiUrl}")
    String fastApiUrl;

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
    public void requestTerritoryChanges(TerritoryRequestDto request) throws TerritoryLimitExceededException {
        List <Integer> territoryIds=request.getTerritoryIds();
        Integer userId=request.getUserId();
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
                .map(id -> new TerritoryChangeRequest(userId, id, "pending",request.getRemarks(),request.getTerritoryConfigurationId()))
                .collect(Collectors.toList());

        if (requests.size() != territoryIds.size()) {
            throw new TerritoryLimitExceededException("存在无效的辖区ID请求");
        }

        if (!requests.isEmpty()) {
            userTerritoryMapper.batchInsertTerritoryChangeRequests(requests);
        }
    }

    @Override
    public List<UserTerritoryVo> getUserTerritories(Integer userId) {
        return userTerritoryMapper.findUserTerritories(userId);
    }

    @Override
    public Integer deleteUserTerritory(Integer id) {
        return userTerritoryMapper.deleteUserTerritory( id);
    }

    @Override
    public Boolean changeConfidenceLevel(Integer territoryId,Double level) {
        if(level < 0.05 || level > 0.95){
            return false;
        }
        if(userTerritoryMapper.changeConfidenceLevel(territoryId,level)!=1){
            return false;
        }
        String baseUrl = fastApiUrl+"/changeUserTerritoryConfidenceLevel";
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("territoryId", territoryId)
                .queryParam("confidenceLevel", level)
                .build()
                .toUri();

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(uri, Map.class);
            if (response.getStatusCodeValue() == 403 ||
                    (response.getBody() != null && "false".equals(response.getBody().get("success")))) {
                return false;
            }
            return true;
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 403) {
                return false;
            }
            throw e;
        }
    }
}
