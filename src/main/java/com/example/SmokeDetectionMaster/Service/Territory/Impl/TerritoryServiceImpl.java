package com.example.SmokeDetectionMaster.Service.Territory.Impl;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryReviewResultDto;
import com.example.SmokeDetectionMaster.Mapper.Territory.TerritoryMapper;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<TerritoryAdminVo> findAllUserTerritories() {
        return territoryMapper.findAllUserTerritories();
    }

    @Override
    public List<TerritoryAdminVo> findAvailableTerritories() {
        return territoryMapper.findAllLegal();
    }

    @Override
    public List<TerritoryChangeRecordAdminVo> findAllTerritoriesApply() {
        return territoryMapper.findAllTerritoriesApply();
    }

    @Override
    @Transactional
    public Integer updateTerritoryChange(TerritoryReviewResultDto territoryReviewResultDto) {
        if (territoryReviewResultDto.getApprovalOutcome()) {
            territoryReviewResultDto.setRequestStatus("agree");
            Integer res = territoryMapper.updateTerritoryChange(territoryReviewResultDto);
            Integer resAdd = territoryMapper.addUserTerritory(territoryReviewResultDto);
            if (res == 1 && resAdd == 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            territoryReviewResultDto.setRequestStatus("refuse");

            return territoryMapper.updateTerritoryChange(territoryReviewResultDto);
        }

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
