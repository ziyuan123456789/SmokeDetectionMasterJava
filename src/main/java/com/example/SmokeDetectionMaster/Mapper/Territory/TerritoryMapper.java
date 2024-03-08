package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.ShowTerritory;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
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
    List<ShowTerritory> findAllLegal();

    @Select("SELECT * FROM territory WHERE TerritoryId = #{id}")
    Territory findById(int id);

    @Insert("INSERT INTO territory(TerritoryName, HardwareSettingId, TerritoryConfigurationId,StorageSize) VALUES(#{territoryName}, #{hardwareSettingId}, #{territoryConfigurationId},#{storageSize})")
    @Options(useGeneratedKeys = true, keyProperty = "territoryId")
    Integer insert(Territory territory);

    @Update("UPDATE territory SET TerritoryName = #{territoryName}, HardwareSettingId = #{hardwareSettingId}, TerritoryConfigurationId = #{territoryConfigurationId} WHERE TerritoryId = #{territoryId}")
    void update(Territory territory);

    @Delete("DELETE FROM territory WHERE TerritoryId = #{id}")
    Integer delete(int id);


}
