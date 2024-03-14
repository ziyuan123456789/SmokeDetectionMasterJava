package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryReviewResultDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Mapper
public interface TerritoryMapper {
    @Select("SELECT t.*,h.`HardwareName`,tc.`Action` FROM territory as t LEFT JOIN hardwaresetting as h ON t.`HardwareSettingId`=h.`HardwareSettingId`  LEFT JOIN territoryconfiguration as tc ON t.`TerritoryConfigurationId`=tc.`TerritoryConfigurationId`")
    List<TerritoryAdminVo> findAllLegal();

    @Select("SELECT * FROM territory WHERE TerritoryId = #{id}")
    Territory findById(int id);

    @Insert("INSERT INTO territory(TerritoryName, HardwareSettingId, TerritoryConfigurationId,StorageSize,ConfidenceLevel) VALUES(#{territoryName}, #{hardwareSettingId}, #{territoryConfigurationId},#{storageSize},#{confidenceLevel})")
    @Options(useGeneratedKeys = true, keyProperty = "territoryId")
    Integer insert(Territory territory);

    @Update("UPDATE territory SET TerritoryName = #{territoryName}, HardwareSettingId = #{hardwareSettingId}, TerritoryConfigurationId = #{territoryConfigurationId} ,StorageSize=#{storageSize},ConfidenceLevel=#{confidenceLevel} WHERE TerritoryId = #{territoryId}")
    void update(Territory territory);

    @Delete("DELETE FROM territory WHERE TerritoryId = #{id}")
    Integer delete(int id);

    @Select("SELECT tc.ChangeRequestId,tc.RequestedTerritoryId,tc.RequestDate,tc.ApprovalDate,t.`TerritoryName`,tc.`RequestStatus`,u.`Username`,u.UserID FROM territorychangerequest as tc left join  territory as t on t.`TerritoryId`=tc.`RequestedTerritoryId` left join `user` as u on u.`UserID`=tc.`UserId`")
    List<TerritoryChangeRecordAdminVo> findAllTerritoriesApply();

    @Update("UPDATE territorychangerequest SET RequestStatus = #{requestStatus}, ApprovalDate = #{approvalDate}, ApproverId = #{approverId}, Remarks = #{remarks} WHERE ChangeRequestId = #{changeRequestId}")
    Integer updateTerritoryChange(TerritoryReviewResultDto territoryReviewResultDto);

    @Insert("INSERT INTO userterritory(UserId, TerritoryId) VALUES(#{userId}, #{requestedTerritoryId})")
    Integer addUserTerritory(TerritoryReviewResultDto territoryReviewResultDto);

}
