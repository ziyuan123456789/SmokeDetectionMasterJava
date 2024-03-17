package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
public class ImageController {
    @Value("${pictureHome}")
    String baseDirectory;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    TerritoryService territoryService;

    // 列出所有可用的月份-日期文件夹
    @GetMapping("/folders")
    public Result<List<Map<String, Object>>> listFolders(HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        String role = (String) claims.get("role");
//        Integer userId = (Integer)claims.get("id");

        List<Map<String, Object>> territoriesData = new ArrayList<>();

        if ("1".equals(role)) {
            List<TerritoryAdminVo> territories = territoryService.findAllUserTerritories();
            for (TerritoryAdminVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getTerritoryId());
                territoryData.put("name", territory.getTerritoryName());
                territoryData.put("child", listFilesByTerritory(territory.getTerritoryId()));
                territoriesData.add(territoryData);
            }
        } else { // 普通用户
//            List<TerritoryAdminVo> territories = findUserTerritories(userId);
//            for (TerritoryAdminVo territory : territories) {
//                Map<String, Object> territoryData = new HashMap<>();
//                territoryData.put("id", territory.getId());
//                territoryData.put("name", territory.getName());
//                territoryData.put("child", listFilesByTerritory(territory.getId()));
//                territoriesData.add(territoryData);
//            }
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
                        yearData.put("index", listFilesInDirectory(path, 2));
                        yearsData.add(yearData);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return yearsData;
    }

    private List<String> listFilesInDirectory(Path directoryPath, int maxDepth) {
        List<String> fileNames = new ArrayList<>();
        try {
            Files.walk(directoryPath, maxDepth)
                    .filter(path -> !path.equals(directoryPath))
                    .forEach(path -> {
                        fileNames.add(path.getFileName().toString());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }



}
