package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserTerritoryService {
    List<Territory> getAvailableTerritories();
    void requestTerritoryChanges(int userId, List<Integer> territoryIds) throws Exception;
}