package com.example.SmokeDetectionMaster.Service.Territory.Impl;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Mapper.Territory.TerritoryMapper;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */

@Service
public class TerritoryServiceImpl implements TerritoryService {

    @Autowired
    private TerritoryMapper territoryMapper;

    @Override
    public List<TerritoryAdminVo> findAvailableTerritories() {
        return territoryMapper.findAllLegal();
    }

    @Override
    public void requestTerritoryChange(TerritoryRequestDto requestDto) throws Exception {


    }



    @Override
    public List<Territory> findAll() {
        return null;
    }

    @Override
    public Territory findById(int id) {
        return territoryMapper.findById(id);
    }

    @Override
    public Territory save(Territory territory) {
        territoryMapper.insert(territory);
        return territory;
    }


    @Override
    public Territory update(Territory territory) {
        territoryMapper.update(territory);
        return territory;
    }

    @Override
    public Integer delete(int id) {
        return territoryMapper.delete(id);
    }
}
