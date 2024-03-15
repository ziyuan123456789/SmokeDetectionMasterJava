package com.example.SmokeDetectionMaster.Bean.Territory;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
/**
 * @author ziyuan
 * @since 2024.02
 */
@Data
public class TerritoryChangeRequest {
    private int changeRequestId;
    private int userId;
    private int requestedTerritoryId;
    private Timestamp requestDate;
    private Timestamp approvalDate;
    private Integer approverId;
    private String remarks;
    private String requestStatus;
    private Integer territoryConfigurationId;


    public TerritoryChangeRequest(int userId, Integer id, String pending,String remarks,Integer territoryConfigurationId) {
        this.userId = userId;
        this.requestedTerritoryId=id;
        this.requestStatus=pending;
        this.remarks=remarks;
        this.territoryConfigurationId = territoryConfigurationId;

    }
}
