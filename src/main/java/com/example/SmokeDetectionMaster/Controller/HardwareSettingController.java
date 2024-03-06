package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Bean.HardwareSetting.HardwareSetting;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.HardwareSetting.HardwareSettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "硬件管理")
@CrossOrigin
@RestController
@RequestMapping("/HardwareSetting")
public class HardwareSettingController {
    private final HardwareSettingService hardwareSettingService;

    @Autowired
    public HardwareSettingController(HardwareSettingService hardwareSettingService) {
        this.hardwareSettingService = hardwareSettingService;
    }

    @RequestMapping("/getAll")
    public Result<?> getAll() {
        return new Result<>(true, ResponseMessage.SUCCESS,hardwareSettingService.findAll());
    }

    @RequestMapping("/getById")
    public Result<?> getById(Integer id) {
        return new Result<>(true, ResponseMessage.SUCCESS,hardwareSettingService.findById(id));
    }

    @RequestMapping("/create")
    public Result<?> create(HardwareSetting hardwareSetting) {
        return new Result<>(true, ResponseMessage.SUCCESS,hardwareSettingService.save(hardwareSetting));
    }

    @RequestMapping("/update")
    public Result<?> update(HardwareSetting hardwareSetting) {
        return new Result<>(true, ResponseMessage.SUCCESS,hardwareSettingService.update(hardwareSetting));
    }

    @RequestMapping("/delete")
    public Result<?> delete(Integer id) {
        return new Result<>(true, ResponseMessage.SUCCESS,hardwareSettingService.delete(id));
    }
}
