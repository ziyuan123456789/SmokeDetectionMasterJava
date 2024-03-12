package com.example.SmokeDetectionMaster.Service.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface BaseTerritoryService {
    List<TerritoryAdminVo> findAll();
    Territory findById(int id);
    Territory save(Territory territory);
    Territory update(Territory territory);
    Integer delete(int id);
}
