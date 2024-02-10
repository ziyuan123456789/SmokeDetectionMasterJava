package com.example.chapter6_wzy.Service.Homework.Impl;

import com.example.chapter6_wzy.Bean.Homework.ClassGroup;
import com.example.chapter6_wzy.Bean.Homework.Classes;
import com.example.chapter6_wzy.Bean.Homework.Homework;
import com.example.chapter6_wzy.Bean.Homework.Union.StudentsInfoWithWorkWithTask;
import com.example.chapter6_wzy.Mapper.HomeWork.TeacherActionMapper;
import com.example.chapter6_wzy.Service.Homework.TeacherActionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wsh
 */
@Service
public class TeacherActionServiceImpl implements TeacherActionService {
    @Autowired
    TeacherActionMapper teacherActionMapper;
    @Value("${fileroot}")
    String fileroot;
    @Override
    public List<ClassGroup> getClassInfoByTeacherId(Integer teacherId) {
        return teacherActionMapper.getClassInfoByTeacherId(teacherId);
    }

    @Override
    public List<Classes> getClassesListByTeacherIdClassGroupId(Integer classGroupId, Integer teacherId) {
        return teacherActionMapper.getClassesListByTeacherIdClassGroupId(classGroupId,teacherId);
    }

    @Override
    public List<Homework> getHomeworkListByClassId(String classId) {
        return teacherActionMapper.getHomeworkListByClassId(classId);
    }

    @Override
    public List<StudentsInfoWithWorkWithTask> getFinishedHomeworkListByHomeworkId(Integer homeworkId) {
        return teacherActionMapper.getFinishedHomeworkListByHomeworkId(homeworkId);
    }

    @Override
    public Map<String, Object> downloadfileByTaskId(Integer taskid) {
        String localFileName=teacherActionMapper.getFileNameByTaskId(taskid);
        if (StringUtils.isEmpty(localFileName)){
            return null;
        }
        String filePath = fileroot+localFileName;
        Resource resource = new FileSystemResource(filePath);
        String[] parts = Objects.requireNonNull(resource.getFilename()).split("-");
        String fileName = parts[1];
        if (StringUtils.isEmpty(fileName)) {
            fileName = "file.ext";
        }
        HttpHeaders headers = new HttpHeaders();
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        headers.setContentDispositionFormData("attachment", encodedFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("headers",headers);
        resultMap.put("resource",resource);
        return resultMap;
    }
}
