package com.example.SmokeDetectionMaster.Controller.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.TerritoryConfiguration.TerritoryConfiguration;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryConfiguration.TerritoryConfigurationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "辖区管理")
@CrossOrigin
@RestController
@RequestMapping("/TerritoryConfiguration")
@Slf4j
public class TerritoryConfigurationController {
    @Autowired
    private TerritoryConfigurationService territoryConfigurationService;
    @RequestMapping("/getAll")
    public Result<?> getAllTerritory(){
        return new Result<>(true, ResponseMessage.SUCCESS,territoryConfigurationService.findAll());
    }

    @RequestMapping("/getById")
    public Result<?> getById(int id) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryConfigurationService.findById(id));
    }

    @RequestMapping("/create")
    public Result<?> create(TerritoryConfiguration territoryConfiguration) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryConfigurationService.save(territoryConfiguration));
    }

    @RequestMapping("/update")
    public Result<?> update(TerritoryConfiguration territoryConfiguration) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryConfigurationService.update(territoryConfiguration));
    }
    @RequestMapping("/delete")
    public Result<?> delete( int id) {
        System.out.println(id);
        return new Result<>(true,ResponseMessage.SUCCESS,territoryConfigurationService.delete(id));
    }

}
