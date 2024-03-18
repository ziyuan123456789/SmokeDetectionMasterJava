package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NotLogin;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    @GetMapping("/getPics")
    public Result<?> getPics(HttpServletRequest request, Integer territoryId, String year, String day) {
        Claims claims = jwtUtil.parseJwt(request);
        String role = claims.get("role", String.class);
        Integer userId = Integer.parseInt(claims.get("id", String.class));
        if ("1".equals(role)) {
            //xxx:咱们对管理员还是无条件信任的
            return listFilesInPath(territoryId, year, day);
        } else {
            return userTerritoryService.getUserTerritories(userId)
                    .stream()
                    .anyMatch(userTerritoryVo -> userTerritoryVo.getId().equals(territoryId)) ? listFilesInPath(territoryId, year, day)
                    : new Result<>(false, ResponseMessage.FAILURE, "该用户没有该区域权限");
        }
    }

    private Result<?> listFilesInPath(Integer territoryId, String year, String day) {
        Path territoryPath = Paths.get(baseDirectory, territoryId.toString(), year, day);
        if (Files.exists(territoryPath)) {
            try {
                List<String> fileNames = Files.list(territoryPath)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
                return new Result<>(true, ResponseMessage.SUCCESS, fileNames);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return new Result<>(false, ResponseMessage.FAILURE, e.getMessage());
            }
        } else {
            return new Result<>(false, ResponseMessage.FAILURE, "不存在这个路径");
        }


    }

    @GetMapping("/images/{territoryId}/{year}/{day}/{imageName}")
    @ResponseBody
    @NotLogin
    public ResponseEntity<Resource> getImage(@PathVariable String territoryId,
                                             @PathVariable String year,
                                             @PathVariable String day,
                                             @PathVariable String imageName) {
        Path imagePath = Paths.get(baseDirectory, territoryId, year,day, imageName);

        try {
            Resource image = new UrlResource(imagePath.toUri());
            if (image.exists() || image.isReadable()) {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                        .body(image);
            } else {
                return ResponseEntity
                        .notFound()
                        .build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }




}
