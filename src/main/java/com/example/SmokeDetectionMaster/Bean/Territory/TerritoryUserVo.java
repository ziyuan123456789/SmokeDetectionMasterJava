package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class TerritoryUserVo {
    private Integer territoryId;
    private String territoryName;
    private Integer hardwareSettingId;
    private Integer territoryConfigurationId;
    private String hardwareName;
    private Double storageSize;
}
