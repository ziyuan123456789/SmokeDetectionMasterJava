package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;

import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserTerritoryService {
    Map<String,Object> getAvailableTerritories(Integer userID);
    void requestTerritoryChanges(int userId, List<Integer> territoryIds) throws Exception;
}