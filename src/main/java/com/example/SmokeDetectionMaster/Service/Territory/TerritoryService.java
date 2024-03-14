package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryReviewResultDto;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration.BaseCRUDService;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface TerritoryService extends BaseCRUDService<Territory> {
    //xxx:寻找所有可用的辖区
    List<TerritoryAdminVo> findAvailableTerritories();

    List<TerritoryChangeRecordAdminVo> findAllTerritoriesApply();


    Integer updateTerritoryChange(TerritoryReviewResultDto territoryReviewResultDto);



}
