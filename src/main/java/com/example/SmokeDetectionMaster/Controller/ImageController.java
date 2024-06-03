package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedAdminRole;
import com.example.SmokeDetectionMaster.Annotations.NeedRole.NotLogin;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Image.ImageService;
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


    @Autowired
    private ImageService imageService;


    @GetMapping("/deleteImage")
    public Result<?> deleteFileInPath(Integer territoryId, String year, String day, String name) {
        if(imageService.deleteFile(territoryId, year, day, name)){
            return new Result<>(true, ResponseMessage.SUCCESS, "删除成功");
        }else{
            return new Result<>(false, ResponseMessage.FAILURE, "删除失败");
        }

    }

    @GetMapping("/likeImage")
    public Result<?> likeImage(String name) {
        if(imageService.likeImage(name)){
            return new Result<>(true, ResponseMessage.SUCCESS, "收藏成功");
        }else{
            return new Result<>(false, ResponseMessage.FAILURE, "收藏失败");
        }

    }



    @GetMapping("/getfolders")
    public Result<?> listFolders(HttpServletRequest request) {
        List<Map<String, Object>> territoriesData = imageService.listFolders(request);
        return new Result<>(true, "Success", territoriesData);
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
                    .anyMatch(userTerritoryVo -> userTerritoryVo.getTerritoryId().equals(territoryId)) ? listFilesInPath(territoryId, year, day)
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
//    管理员推送截图
    @NeedAdminRole
    @GetMapping("/pushImage")
    public Result<?> pushImage(String picName) {
        if(imageService.pushImage(picName)>=1){
            return new Result<>(true, ResponseMessage.SUCCESS, "推送成功");
        }else {
            return new Result<>(false, ResponseMessage.FAILURE, "推送失败");
        }

    }



    @GetMapping("/getImportantScreenshotsForUser")
    public Result<?> getImportantScreenshotsForUser(HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        Integer userId = Integer.parseInt(claims.get("id", String.class));
        return new Result<>(true, ResponseMessage.SUCCESS, imageService.getImportantScreenshotsForUser(userId));


    }




}
