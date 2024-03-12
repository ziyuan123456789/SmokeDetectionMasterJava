package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryUserVo;
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

    @Select("SELECT COUNT(*) FROM userterritory WHERE UserId = #{userId} " +
            "UNION ALL " +
            "SELECT COUNT(*) FROM territorychangerequest WHERE UserId = #{userId} AND RequestStatus = 'pending'")
    Integer countUserAndPendingTerritories(int userId);
}
