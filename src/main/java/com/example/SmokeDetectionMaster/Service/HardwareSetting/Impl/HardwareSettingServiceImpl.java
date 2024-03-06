package com.example.SmokeDetectionMaster.Service.HardwareSetting.Impl;

import com.example.SmokeDetectionMaster.Bean.HardwareSetting.HardwareSetting;
import com.example.SmokeDetectionMaster.Mapper.HardwareSetting.HardwareSettingMapper;
import com.example.SmokeDetectionMaster.Service.HardwareSetting.HardwareSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Service
public class HardwareSettingServiceImpl implements HardwareSettingService {
    private final HardwareSettingMapper hardwareSettingMapper;

    @Autowired
    public HardwareSettingServiceImpl(HardwareSettingMapper hardwareSettingMapper) {
        this.hardwareSettingMapper = hardwareSettingMapper;
    }

    @Override
    public List<HardwareSetting> findAll() {
        return hardwareSettingMapper.findAll();
    }

    @Override
    public HardwareSetting findById(Integer id) {
        return hardwareSettingMapper.findById(id);
    }

    @Override
    public HardwareSetting save(HardwareSetting hardwareSetting) {
        hardwareSettingMapper.insert(hardwareSetting);
        return hardwareSetting;
    }


    @Override
    public Integer update(HardwareSetting hardwareSetting) {
        return hardwareSettingMapper.update(hardwareSetting);
    }

    @Override
    public Integer delete(Integer id) {
        return hardwareSettingMapper.delete(id);
    }
}