package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.03
 */
@RestController
@CrossOrigin
@RequestMapping("/images")
@Slf4j
public class ImageController {
    @Value("${pictureHome}")
    String baseDirectory;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TerritoryService territoryService;
    @Autowired
    UserTerritoryService userTerritoryService;




    @GetMapping("/getfolders")
    public Result<?> listFolders(HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        String role =claims.get("role", String.class);
        //xxx:这段代码确实很蠢,但是我也不知道为什么传递integer.class会报错
        Integer userId =Integer.parseInt(claims.get("id",String.class));
        List<Map<String, Object>> territoriesData = new ArrayList<>();
        //xxx:如果是Admin
        if ("1".equals(role)) {
            //xxx:找出所有已有辖区
            List<TerritoryAdminVo> territories = territoryService.findAllUserTerritories();
            for (TerritoryAdminVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getTerritoryId());
                territoryData.put("name", territory.getTerritoryName());
                //xxx:依照辖区id去指定文件夹找
                territoryData.put("child", listFilesByTerritory(territory.getTerritoryId()));
                territoriesData.add(territoryData);
            }
        } else { // 普通用户
            List<UserTerritoryVo> territories = userTerritoryService.getUserTerritories(userId);
            for (UserTerritoryVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getId());
                territoryData.put("name", territory.getTerritoryName());
                territoryData.put("child", listFilesByTerritory(territory.getId()));
                territoriesData.add(territoryData);
            }
        }

        return new Result<>(true, ResponseMessage.SUCCESS,territoriesData);
    }

    private List<Map<String, Object>> listFilesByTerritory(Integer territoryId) {
        List<Map<String, Object>> yearsData = new ArrayList<>();
        Path territoryPath = Paths.get(baseDirectory + territoryId);
        try {
            Files.walk(territoryPath, 1)
                    .filter(path -> !path.equals(territoryPath))
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        Map<String, Object> yearData = new HashMap<>();
                        String year = path.getFileName().toString();
                        yearData.put("year", year);
                        //xxx:两层深度,去寻找id/年/月-日,收集起来
                        yearData.put("index", listFilesInDirectory(path));
                        yearsData.add(yearData);
                    });
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return yearsData;
    }

    private List<String> listFilesInDirectory(Path directoryPath) {
        List<String> fileNames = new ArrayList<>();
        try {
            Files.walk(directoryPath, 2)
                    .filter(path -> !path.equals(directoryPath))
                    .forEach(path -> {
                        fileNames.add(path.getFileName().toString());
                    });
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return fileNames;
    }

    @GetMapping("/getPics")
    public Result<?> getPics(HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        String role =claims.get("role", String.class);
        Integer userId =Integer.parseInt(claims.get("id",String.class));
        List<Map<String, Object>> territoriesData = new ArrayList<>();
        //xxx:如果是Admin
        if ("1".equals(role)) {
            //xxx:找出所有已有辖区
            List<TerritoryAdminVo> territories = territoryService.findAllUserTerritories();
            for (TerritoryAdminVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getTerritoryId());
                territoryData.put("name", territory.getTerritoryName());
                //xxx:依照辖区id去指定文件夹找
                territoryData.put("child", listFilesByTerritory(territory.getTerritoryId()));
                territoriesData.add(territoryData);
            }
        } else { // 普通用户
            List<UserTerritoryVo> territories = userTerritoryService.getUserTerritories(userId);
            for (UserTerritoryVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getId());
                territoryData.put("name", territory.getTerritoryName());
                territoryData.put("child", listFilesByTerritory(territory.getId()));
                territoriesData.add(territoryData);
            }
        }

        return new Result<>(true, ResponseMessage.SUCCESS,territoriesData);
    }







}
