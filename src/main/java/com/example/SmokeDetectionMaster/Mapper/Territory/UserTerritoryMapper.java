package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
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
    @Select("SELECT t.* FROM territory t " +
            "LEFT JOIN userterritory ut ON t.TerritoryId = ut.TerritoryId " +
            "WHERE ut.UserId IS NULL AND t.TerritoryId NOT IN " +
            "(SELECT TerritoryId FROM territorychangerequest WHERE RequestStatus = 'pending')")
    List<Territory> findAvailableTerritories();

    @Insert("INSERT INTO territorychangerequest (UserId, TerritoryId, RequestStatus) VALUES (#{userId}, #{territoryId}, 'pending')")
    void insertTerritoryChangeRequest(@Param("userId") int userId, @Param("territoryId") int territoryId);

    @Select("SELECT COUNT(*) FROM userterritory WHERE UserId = #{userId} " +
            "UNION ALL " +
            "SELECT COUNT(*) FROM territorychangerequest WHERE UserId = #{userId} AND RequestStatus = 'pending'")
    int countUserAndPendingTerritories(int userId);
}
