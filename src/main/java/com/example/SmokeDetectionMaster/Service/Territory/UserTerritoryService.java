package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;

import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserTerritoryService {
    List<TerritoryChangeRecordUserVo>getApproveState(Integer userID);
    Map<String,Object> getAvailableTerritories(Integer userID);
    void requestTerritoryChanges(TerritoryRequestDto request) throws Exception;

    List<UserTerritoryVo> getUserTerritories(Integer userId);

    Integer deleteUserTerritory(Integer id);

    Boolean changeConfidenceLevel(Integer territoryId,Double level);
}