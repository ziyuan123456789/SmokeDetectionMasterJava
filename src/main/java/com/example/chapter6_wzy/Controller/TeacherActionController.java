package com.example.chapter6_wzy.Controller;

import com.example.chapter6_wzy.Annotations.NeedRole.NeedLogin;
import com.example.chapter6_wzy.Annotations.NeedRole.NeedTeacherRole;
import com.example.chapter6_wzy.Annotations.ResourceClean.CleanupResources;
import com.example.chapter6_wzy.Bean.Homework.Union.StudentsInfoWithWorkWithTask;
import com.example.chapter6_wzy.Bean.Utils.Result;
import com.example.chapter6_wzy.Service.Homework.TeacherActionService;
import com.example.chapter6_wzy.WebSocket.DownloadSocket;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * @author wsh
 */
@Api(tags = "收作业")
@CrossOrigin
@RestController
@Slf4j
public class TeacherActionController {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    Lock lock = new ReentrantLock();
    @Autowired
    TeacherActionService teacherActionService;
    @Autowired
    DownloadSocket downloadSocket;
    @Value("${zipcache}")
    String zipCache;
    @Value("${fileroot}")
    String fileRoot;
    String zipFileName;
    public String getZipFileName() {
        return zipFileName;
    }

    @RequestMapping("/getClassInfoByTeacherId")
    public Result<?> getClassInfoByTeacherId(Integer teacherId) {
        return new Result<>(true, "success", teacherActionService.getClassInfoByTeacherId(teacherId));
    }
    @RequestMapping("/getClassesListByTeacherIdClassGroupId")
    public Result<?> getClassesListByTeacherIdClassGroupId(Integer classGroupId,Integer teacherId) {
        return new Result<>(true, "success", teacherActionService.getClassesListByTeacherIdClassGroupId(classGroupId, teacherId));
    }
    @RequestMapping("/getHomeworkListByClassId")
    public Result<?> getHomeworkListByClassId(String classId) {
        return new Result<>(true, "success", teacherActionService.getHomeworkListByClassId(classId));
    }
    @RequestMapping("/getFinishedHomeworkListByHomeworkId")
    public Result<?> getFinishedHomeworkListByHomeworkId(Integer homeworkId) {
        return new Result<>(true, "success", teacherActionService.getFinishedHomeworkListByHomeworkId(homeworkId));
    }
    @NeedLogin
    @GetMapping("/downloadfileByTaskId")
    public ResponseEntity<Resource> downloadfileByTaskId(Integer taskid) {
        Map<String,Object> resultMap=teacherActionService.downloadfileByTaskId(taskid);
        return ResponseEntity.ok()
                .headers((HttpHeaders) resultMap.get("headers"))
                .body((Resource) resultMap.get("resource"));
    }
    @NeedLogin
    @NeedTeacherRole
    @GetMapping("/files")
    @CleanupResources("D://cacheHome//")
    public ResponseEntity<Resource> downloadFiles(HttpServletRequest request,Integer homeworkId) throws IOException, InterruptedException, ExecutionException, ExecutionException {
        System.out.println(request.getAttribute("id"));
        List<StudentsInfoWithWorkWithTask> res=teacherActionService.getFinishedHomeworkListByHomeworkId(homeworkId);
        if(res==null||res.size()==0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        zipFileName = "download.zip";
        Path zipFilePath = Paths.get(zipCache, zipFileName);
        CountDownLatch latch = new CountDownLatch(1);
        Future<Void> future = executorService.submit(() -> {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath.toFile()))) {
                for (StudentsInfoWithWorkWithTask re : res) {
                    String newfilepath=fileRoot + re.getFilehome();
                    System.out.println(newfilepath);
                    addToZipFile(newfilepath, zipOutputStream);
                }
                zipOutputStream.finish();
            } catch (IOException e) {
                log.error("io错误");
            } finally {
                latch.countDown();
            }
            return null;
        });
        latch.await();
        future.get();
        Resource resource = new FileSystemResource(zipFilePath.toFile());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", zipFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    private void addToZipFile(String filePath, ZipOutputStream zipOutputStream) throws IOException {
            File file = new File(filePath);
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                long totalBytesRead = 0;
                long totalBytes = file.length();
                int updateThreshold = 10;
                int updateCounter = 0;
                while ((length = fileInputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                    totalBytesRead += length;
                    double progress = (double) totalBytesRead / totalBytes * 100;
                    updateCounter++;
                    if (updateCounter >= updateThreshold) {
                        String userId = "wzy";
                        downloadSocket.sendProgressUpdate(userId, "" +(int)progress);
                        updateCounter = 0;
                    }
                }
            }
        }

    }





