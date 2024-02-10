package com.example.chapter6_wzy.Controller;

import com.example.chapter6_wzy.Annotations.CheckParams.CheckDate;
import com.example.chapter6_wzy.Annotations.CheckParams.EnableParameterCheck;
import com.example.chapter6_wzy.Annotations.NeedRole.NeedLogin;
import com.example.chapter6_wzy.Bean.Homework.Experiment;
import com.example.chapter6_wzy.Bean.Homework.Union.ClassInfoWithClassGroupAndStudents;
import com.example.chapter6_wzy.Bean.Utils.Result;
import com.example.chapter6_wzy.Service.Homework.ClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wsh
 */
@Api(tags = "作业管理")
@CrossOrigin
@RestController
@Validated

public class HomeworkController {
    @Autowired
    ClassService classService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Value("${zipcache}")
    String zipcachehome;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/getClassInfoByStudentsId")
    public Result<?> getClassInfoByStudentsId(Integer id) {
        List<ClassInfoWithClassGroupAndStudents> c = classService.getClassInfoByStudentsId(id);
        return new Result<>(true, "success", c);
    }

    @RequestMapping("/getHomeworkInfoByClassId")
    public Result<?> getHomeworkInfoByClassId(String studentid,String classid)  {
        return new Result<>(true, "success",classService.getHomeworkInfoByClassId(studentid,classid));
    }


    @CrossOrigin("*")
    @Nullable
    @RequestMapping(value = "/upload")
    public Result upLoadFile(Integer stuId,Integer taskId,@RequestParam("file") MultipartFile file) {
        String oldFileName = file.getOriginalFilename();
        if (StringUtils.isBlank(oldFileName)) {
            return new Result<>(false, "fail", "没有收到文件");
        }
        String filePath = "/";
        String rootPath = System.getProperty("user.dir")+"\\filehome";
        File fileDir = new File(rootPath + filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "-"+oldFileName;
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(fileDir, newFileName))) {
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
            return new Result<>(false, "fail", "文件服务器异常");
        }
        Integer result = classService.checkInsertOrUpdate(stuId, taskId);
        if (result == null) {
            logger.warn("没有记录应该新建一条");
            if (classService.insertTaskByStuIdTaskId(stuId, taskId, newFileName) != 1) {
                return new Result<>(false, "fail", "插入失败");
            }
        } else {
            logger.warn("加条新的呗");
            if (classService.updateTaskByTaskId(result, newFileName) != 1) {
                return new Result<>(false, "fail", "修改失败");
            } else {
                return new Result<>(true, "success", "修改成功");
            }
        }


        return new Result<>(true, "success", null);
    }
    @EnableParameterCheck
    @RequestMapping("filter")
    public Result<?> filter(@RequestParam("date") @CheckDate String dateString, String taskid)  {
        return  new Result<>(true,"",classService.UpdateHomework(taskid));
    }



    @RequestMapping("updateTest")
    public Result<?> uploadTest(@RequestParam("file") MultipartFile file) {
        String oldFileName = file.getOriginalFilename();
        if (StringUtils.isBlank(oldFileName)) {
            return new Result<>(false, "fail", "没有收到文件");
        }
        String filePath = "/";
        String rootPath = System.getProperty("user.dir")+"\\filehome";
        File fileDir = new File(rootPath + filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        String newFileName = UUID.randomUUID().toString().replace("-", "") + "-"+oldFileName;

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(fileDir, newFileName))) {
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
            return new Result<>(false, "fail", "文件服务器异常");
        }
        return new Result<>(true, "success", null);
    }


    @RequestMapping("getExperimentById")
    @NeedLogin
    public Result<?> getExperimentById( HttpServletRequest request, String homeworkId)  {
        String studentId= (String) request.getAttribute("id");
        List<Experiment> res;
        if(Boolean.TRUE.equals(redisTemplate.hasKey("Experiment" + studentId))){
            return new Result<>(true,"存在缓存,给你缓存",redisTemplate.opsForValue().get("Experiment" + studentId));
        }else{
            res=classService.selectExperimentByhomeworkidAndstudentid(studentId,homeworkId);
            logger.warn(res.toString());
            redisTemplate.opsForValue().set("Experiment" + studentId,res);
            return new Result<>(true,"success",res);
        }
    }

    @RequestMapping("/redistest")
    @NeedLogin
    public Result<?> redistest(HttpServletRequest request, @RequestBody Map<String, Object> requestBody) {
        Integer id = (Integer) requestBody.get("id");
        String studentId = (String) request.getAttribute("id");
        Map<String, Object> getMap = (Map<String, Object>) requestBody.get("getMap");
        ObjectMapper objectMapper = new ObjectMapper();
        Experiment javaBean = objectMapper.convertValue(getMap, Experiment.class);
        System.out.println(javaBean.toString());
        List<Experiment> res = (List<Experiment>) redisTemplate.opsForValue().get("Experiment" + studentId);
        res.set(id - 1, javaBean);
        redisTemplate.opsForValue().set("Experiment" + studentId, res);
        return new Result<>(true, "success", res);

    }








}





