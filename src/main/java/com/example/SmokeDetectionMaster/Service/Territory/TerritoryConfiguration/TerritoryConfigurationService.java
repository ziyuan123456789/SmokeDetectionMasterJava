package com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.TerritoryConfiguration.TerritoryConfiguration;
import com.example.SmokeDetectionMaster.Mapper.Territory.TerritoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Service
public class TerritoryConfigurationService implements BaseCRUDService<TerritoryConfiguration>{
    @Autowired
    TerritoryMapper territoryMapper;

    @Override
    public List<TerritoryConfiguration> findAll() {
        return null;
    }

    @Override
    public TerritoryConfiguration findById(int id) {
        return null;
    }

    @Override
    public TerritoryConfiguration save(Territory territory) {
        return null;
    }

    @Override
    public TerritoryConfiguration update(Territory territory) {
        return null;
    }

    @Override
    public Integer delete(int id) {
        return null;
    }
}
