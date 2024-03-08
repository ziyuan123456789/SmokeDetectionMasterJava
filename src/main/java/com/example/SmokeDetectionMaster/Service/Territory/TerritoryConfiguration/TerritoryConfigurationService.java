package com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.TerritoryConfiguration.TerritoryConfiguration;
import com.example.SmokeDetectionMaster.Mapper.Territory.TerritoryConfigurationMapper;
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
    TerritoryConfigurationMapper territoryConfigurationMapper;

    @Override
    public List<TerritoryConfiguration> findAll() {
        return territoryConfigurationMapper.selectAll();
    }

    @Override
    public TerritoryConfiguration findById(int id) {
        return territoryConfigurationMapper.selectById(id);
    }

    @Override
    public TerritoryConfiguration save(TerritoryConfiguration territoryConfiguration) {
        territoryConfigurationMapper.insert(territoryConfiguration);
        return territoryConfiguration;
    }

    @Override
    public TerritoryConfiguration update(TerritoryConfiguration territoryConfiguration) {
        territoryConfigurationMapper.update(territoryConfiguration);
        return  territoryConfiguration;
    }


    @Override
    public Integer delete(int id) {
        return territoryConfigurationMapper.deleteById(id);
    }
}
