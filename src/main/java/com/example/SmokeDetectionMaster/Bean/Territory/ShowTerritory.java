package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Data
public class ShowTerritory {
    private Integer territoryId;
    private String territoryName;
    private Integer hardwareSettingId;
    private Integer territoryConfigurationId;
    private String hardwareName;
    private Double storageSize;
    private String action;
}
