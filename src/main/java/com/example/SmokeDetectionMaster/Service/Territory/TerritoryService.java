package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.ShowTerritory;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration.BaseCRUDService;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface TerritoryService extends BaseCRUDService<Territory> {
    List<ShowTerritory> findAvailableTerritories();
    void requestTerritoryChange(TerritoryRequestDto request) throws Exception;


}