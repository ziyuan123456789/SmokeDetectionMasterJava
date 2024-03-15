package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class UserTerritoryVO {
    private Integer id;
    private Integer territoryId;
    private String territoryName;
    private Integer hardwareSettingId;
    private Integer territoryConfigurationId;
    private Double storageSize;
    private Double confidenceLevel;
    private String hardwareName;
    private String action;
}
