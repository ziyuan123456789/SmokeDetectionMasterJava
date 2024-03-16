package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ziyuan
 * @since 2024.03
 */
@RestController
@RequestMapping("/images")
public class ImageController {
    @Value("${pictureHome}")
    String pictureHome;

    @Autowired
    JwtUtil jwtUtil;

    private final String baseDirectory = "/path_to_your_images_folder/";

    // 列出所有可用的月份-日期文件夹
    @GetMapping("/folders")
    public ResponseEntity<List<String>> listFolders(int territoryId, HttpServletRequest request){
        Claims claims =jwtUtil.parseJwt(request);
        if(claims.get("role").equals("1")){

        }

        String directoryPath = baseDirectory + territoryId;

        File territoryDirectory = new File(directoryPath);
        if (!territoryDirectory.exists() || !territoryDirectory.isDirectory()) {
            return ResponseEntity.notFound().build();
        }

        // 过滤出所有的文件夹
        String[] folders = territoryDirectory.list((current, name) -> new File(current, name).isDirectory());

        return ResponseEntity.ok(Arrays.asList(folders));
    }

    // 获取特定月份-日期文件夹下的所有图片
    @GetMapping("/images/{territoryId}/{folder}")
    public ResponseEntity<List<String>> listImagesInFolder(
            @PathVariable("territoryId") int territoryId,
            @PathVariable("folder") String folder) {

        String folderPath = baseDirectory + "territory_" + territoryId + "/" + folder;

        File dateDirectory = new File(folderPath);
        if (!dateDirectory.exists() || !dateDirectory.isDirectory()) {
            return ResponseEntity.notFound().build();
        }

        // 过滤出所有的图片文件
        File[] files = dateDirectory.listFiles((dir, name) -> name.endsWith(".jpg"));

        // 转换为可访问的URL路径
        List<String> urls = Arrays.stream(files)
                .map(file -> convertToFileURL(territoryId, folder, file.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(urls);
    }

    private String convertToFileURL(int territoryId, String folder, String fileName) {
        // 注意：这里的路径转换需要确保和静态资源映射一致
        return "http://localhost:8080/images/territory_" + territoryId + "/" + folder + "/" + fileName;
    }
}
