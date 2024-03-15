package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class TerritoryChangeRecordUserVo {
    private Integer changeRequestId;
    private Integer requestedTerritoryId;
    private LocalDate requestDate;
    private LocalDate approvalDate;
    private String territoryName;
    private String requestStatus;
    private String username;
    private String action;
}
