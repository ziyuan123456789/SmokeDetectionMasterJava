package com.example.SmokeDetectionMaster.Service.Image;

import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
@Service
@Slf4j
public class ImageService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private UserTerritoryService userTerritoryService;
    @Value("${pictureHome}")
    String baseDirectory;

    public List<Map<String, Object>> listFolders(HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        String role = claims.get("role", String.class);
        Integer userId = Integer.parseInt(claims.get("id", String.class));
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
        } else {
            List<UserTerritoryVo> territories = userTerritoryService.getUserTerritories(userId);
            for (UserTerritoryVo territory : territories) {
                Map<String, Object> territoryData = new HashMap<>();
                territoryData.put("id", territory.getId());
                territoryData.put("name", territory.getTerritoryName());
                territoryData.put("child", listFilesByTerritory(territory.getId()));
                territoriesData.add(territoryData);
            }
        }

        return territoriesData;
    }


    private List<Map<String, Object>> listFilesByTerritory(Integer territoryId) {
        List<Map<String, Object>> yearsData = new ArrayList<>();
        Path territoryPath = Paths.get(baseDirectory + territoryId);
        if (!Files.exists(territoryPath)) {
            log.error("当前的pictureHome没有创建完整的辖区文件夹，请检查,但是可以继续使用,有问题的辖区id为"+territoryId.toString());
        }
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
            Files.walk(directoryPath, 1)
                    .filter(path -> !path.equals(directoryPath))
                    .filter(Files::isDirectory)
                    .forEach(path -> fileNames.add(path.getFileName().toString()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return fileNames;
    }

}
