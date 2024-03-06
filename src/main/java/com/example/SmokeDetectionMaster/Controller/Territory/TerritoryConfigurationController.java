package com.example.SmokeDetectionMaster.Controller.Territory;

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

}
