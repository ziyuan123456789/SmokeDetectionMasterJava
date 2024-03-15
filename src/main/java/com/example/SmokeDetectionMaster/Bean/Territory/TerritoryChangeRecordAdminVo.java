package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class TerritoryChangeRecordAdminVo {
    private Integer changeRequestId;
    private Integer requestedTerritoryId;
    private LocalDate requestDate;
    private String territoryName;
    private String action;
    private String requestStatus;
    private String username;
    private Integer userId;
}
