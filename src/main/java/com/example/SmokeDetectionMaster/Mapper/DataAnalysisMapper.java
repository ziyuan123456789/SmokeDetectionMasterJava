package com.example.SmokeDetectionMaster.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.05
 */
@Mapper
public interface DataAnalysisMapper {

    @Select("SELECT EXTRACT(YEAR FROM SmokeStartTime) AS year, EXTRACT(MONTH FROM SmokeStartTime) AS month, COUNT(*) AS count FROM smokingrecord GROUP BY year, month")
    List<Map<String, Object>> countMonthlySmokingRecords();

    @Select("SELECT u.Username, COUNT(ut.TerritoryId) AS territoryCount FROM user u JOIN userterritory ut ON u.UserID = ut.UserId GROUP BY u.UserID ORDER BY territoryCount DESC")
    List<Map<String, Object>> findUsersWithMostTerritories();

    @Select("SELECT t.TerritoryName, COUNT(ut.UserId) AS userCount FROM territory t JOIN userterritory ut ON t.TerritoryId = ut.TerritoryId GROUP BY t.TerritoryId ORDER BY userCount DESC")
    List<Map<String, Object>> findTerritoriesWithMostUsers();
}
