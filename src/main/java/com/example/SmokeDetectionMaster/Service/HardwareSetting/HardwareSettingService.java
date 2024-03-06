package com.example.SmokeDetectionMaster.Service.HardwareSetting;

import com.example.SmokeDetectionMaster.Bean.HardwareSetting.HardwareSetting;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface HardwareSettingService {
    List<HardwareSetting> findAll();
    HardwareSetting findById(Integer id);
    HardwareSetting save(HardwareSetting hardwareSetting);
    Integer update(HardwareSetting hardwareSetting);
    Integer delete(Integer id);
}
