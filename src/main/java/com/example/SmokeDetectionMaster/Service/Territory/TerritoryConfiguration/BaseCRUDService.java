package com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface BaseCRUDService<T> {
    List<T> findAll();
    T findById(int id);
    T save(Territory territory);
    T update(Territory territory);
    Integer delete(int id);
}
