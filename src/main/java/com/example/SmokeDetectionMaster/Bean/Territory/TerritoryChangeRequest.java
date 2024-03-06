package com.example.SmokeDetectionMaster.Bean.Territory;
import lombok.Data;
import java.util.Date;
/**
 * @author ziyuan
 * @since 2024.02
 */
@Data
public class TerritoryChangeRequest {
    private Integer changeRequestId;
    private Integer userId; // 对应UserId字段
    private Integer currentTerritoryId; // 对应CurrentTerritoryId字段，用户当前的辖区ID
    private Integer requestedTerritoryId; // 对应RequestedTerritoryId字段，用户请求的辖区ID
    private String requestStatus; // 对应RequestStatus字段，请求的状态（如"pending"）
    private Date requestDate; // 对应RequestDate字段，请求提交的日期
    private Date approvalDate; // 对应ApprovalDate字段，请求被批准的日期
    private Integer approverId; // 对应ApproverId字段，批准请求的管理员ID
    private String remarks; // 对应Remarks字段，附加备注

}
