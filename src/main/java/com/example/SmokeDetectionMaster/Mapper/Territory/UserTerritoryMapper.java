package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRequest;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Mapper
public interface UserTerritoryMapper {
    @Select("SELECT t.*,h.`HardwareName`,tc.`Action`\n" +
            "FROM territory t\n" +
            "LEFT JOIN hardwaresetting as h \n" +
            "ON t.`HardwareSettingId`=h.`HardwareSettingId`  \n" +
            "LEFT JOIN territoryconfiguration as tc \n" +
            "ON t.`TerritoryConfigurationId`=tc.`TerritoryConfigurationId`\n" +
            "WHERE NOT EXISTS (\n" +
            "  SELECT 1 \n" +
            "  FROM userterritory ut \n" +
            "  WHERE ut.TerritoryId = t.TerritoryId \n" +
            ")\n" +
            "AND NOT EXISTS (\n" +
            "  SELECT 1 \n" +
            "  FROM territorychangerequest tcr \n" +
            "  WHERE tcr.RequestedTerritoryId = t.TerritoryId \n" +
            "    AND tcr.RequestStatus = 'pending'\n" +
            ") LIMIT 100")
    List<TerritoryUserVo> findAvailableTerritories();

    @Insert("INSERT INTO territorychangerequest (UserId, TerritoryId, RequestStatus) VALUES (#{userId}, #{territoryId}, 'pending')")
    void insertTerritoryChangeRequest(@Param("userId") int userId, @Param("territoryId") int territoryId);

    @Select("SELECT SUM(count) FROM (" +
            "SELECT COUNT(*) as count FROM userterritory WHERE UserId = #{userId} " +
            "UNION ALL " +
            "SELECT COUNT(*) as count FROM territorychangerequest WHERE UserId = #{userId} AND RequestStatus = 'pending'" +
            ") AS combinedCounts")
    Integer countUserAndPendingTerritories(int userId);

    @Insert("<script>" +
            "INSERT INTO territorychangerequest (UserId, RequestedTerritoryId, RequestStatus,territoryConfigurationId,Remarks) VALUES " +
            "<foreach collection='requests' item='request' separator=','>" +
            "(#{request.userId}, #{request.requestedTerritoryId}, #{request.requestStatus},#{request.territoryConfigurationId},#{request.remarks})" +
            "</foreach>" +
            "</script>")
    void batchInsertTerritoryChangeRequests(@Param("requests") List<TerritoryChangeRequest> requests);

    @Select("SELECT tc.ChangeRequestId,tc.RequestedTerritoryId,tc.RequestDate,tc.ApprovalDate,t.`TerritoryName`,tc.`RequestStatus`,u.`Username` ,tcf.`Action` FROM territorychangerequest as tc left join  territory as t on t.`TerritoryId`=tc.`RequestedTerritoryId` left join `user` as u on u.`UserID`=tc.`ApproverId` left JOIN territoryconfiguration as tcf on tc.`territoryConfigurationId`=tcf.`TerritoryConfigurationId` WHERE tc.`UserId`= #{userId} ORDER BY tc.`RequestDate`  DESC")
    List<TerritoryChangeRecordUserVo> getApproveState(Integer userID);

    @Select("SELECT ut.id, t.*, hs.`HardwareName`, tcf.`Action` FROM userterritory as ut " +
            "LEFT JOIN territory as t ON t.`TerritoryId` = ut.`TerritoryId` " +
            "LEFT JOIN hardwaresetting as hs ON hs.`HardwareSettingId` = t.`HardwareSettingId` " +
            "LEFT JOIN territoryconfiguration as tcf ON tcf.`TerritoryConfigurationId` = t.`TerritoryConfigurationId` " +
            "WHERE ut.`UserId` = #{userId}")
    List<UserTerritoryVo> findUserTerritories(Integer userId);
    @Delete("DELETE FROM userterritory WHERE id = #{id}")
    Integer deleteUserTerritory(Integer id);
}
