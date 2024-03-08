package com.example.SmokeDetectionMaster.Bean.Territory;

/**
 * @author ziyuan
 * @since 2024.02
 */

import lombok.Data;

@Data
public class Territory {
    private Integer territoryId;
    private String territoryName;
    private Integer hardwareSettingId;
    private Integer territoryConfigurationId;
    private Double storageSize;
}
