package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Data
public class TerritoryRequestDto {
    private Integer userId;
    private List<Integer> territoryIds;
    private String remarks;
    private Integer territoryConfigurationId;
}
