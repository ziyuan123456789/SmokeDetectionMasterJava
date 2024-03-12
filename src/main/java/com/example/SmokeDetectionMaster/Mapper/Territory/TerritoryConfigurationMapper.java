package com.example.SmokeDetectionMaster.Mapper.Territory;

import com.example.SmokeDetectionMaster.Bean.TerritoryConfiguration.TerritoryConfiguration;
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
public interface TerritoryConfigurationMapper {

    @Select("SELECT * FROM TerritoryConfiguration")
    List<TerritoryConfiguration> selectAll();

    @Select("SELECT * FROM TerritoryConfiguration WHERE TerritoryConfigurationId = #{territoryConfigurationId}")
    TerritoryConfiguration selectById( int territoryConfigurationId);

    @Insert("INSERT INTO TerritoryConfiguration (Action) VALUES (#{action})")
    @Options(useGeneratedKeys = true, keyProperty = "territoryConfigurationId")
    int insert(TerritoryConfiguration territoryConfiguration);

    @Update("UPDATE TerritoryConfiguration SET Action = #{action} WHERE TerritoryConfigurationId = #{territoryConfigurationId}")
    int update(TerritoryConfiguration territoryConfiguration);

    @Delete("DELETE FROM TerritoryConfiguration WHERE TerritoryConfigurationId = #{territoryConfigurationId}")
    int deleteById(int territoryConfigurationId);
}