package com.example.SmokeDetectionMaster.Mapper.HardwareSetting;

import com.example.SmokeDetectionMaster.Bean.HardwareSetting.HardwareSetting;
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
public interface HardwareSettingMapper {

    @Select("SELECT * FROM hardwaresetting")
    List<HardwareSetting> findAll();

    @Select("SELECT * FROM hardwaresetting WHERE HardwareSettingId = #{id}")
    HardwareSetting findById(Integer id);

    @Insert("INSERT INTO hardwaresetting (HardwareName, StorageSize) VALUES (#{hardwareName}, #{storageSize})")
    @Options(useGeneratedKeys = true, keyProperty = "hardwareSettingId")
    Integer insert(HardwareSetting hardwareSetting);

    @Update("UPDATE hardwaresetting SET HardwareName = #{hardwareName}, StorageSize = #{storageSize} WHERE HardwareSettingId = #{hardwareSettingId}")
    Integer update(HardwareSetting hardwareSetting);

    @Delete("DELETE FROM hardwaresetting WHERE HardwareSettingId = #{id}")
    Integer delete(Integer id);
}