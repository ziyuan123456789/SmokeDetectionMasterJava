package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedAdminRole;
import com.example.SmokeDetectionMaster.Mapper.DataAnalysisMapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.05
 */
@Api(tags = "数据分析接口")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/DataAnalysis")
@Slf4j
public class DataAnalysisController {
    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @GetMapping("/smoking/monthly")
    public List<Map<String, Object>> getMonthlySmokingStatistics() {
        return dataAnalysisMapper.countMonthlySmokingRecords();
    }

    @GetMapping("/user/territories")
    public List<Map<String, Object>> getUsersWithMostTerritories() {
        return dataAnalysisMapper.findUsersWithMostTerritories();
    }

    @GetMapping("/user/smoking")
    public List<Map<String, Object>> findTerritoriesWithMostUsers() {
        return dataAnalysisMapper.findTerritoriesWithMostUsers();
    }

}
