package com.example.SmokeDetectionMaster.Bean.Territory;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class TerritoryReviewResultDto {
    //主键
    private Integer changeRequestId;
    //审批状态
    private String requestStatus;
    //需要的辖区id
    private String requestedTerritoryId;
    //审批结束时间
    private LocalDate approvalDate=LocalDate.now();
    //审批者id
    private Integer approverId;
    //拒绝原因
    private String remarks;
    //用户id
    private Integer userId;
    //是否同意
    private Boolean approvalOutcome;

}
