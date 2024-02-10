package com.example.chapter6_wzy.Service.Homework.Impl;

import com.example.chapter6_wzy.Bean.Homework.Experiment;
import com.example.chapter6_wzy.Bean.Homework.Union.ClassInfoWithClassGroupAndStudents;
import com.example.chapter6_wzy.Bean.Homework.Union.HomeworkInfoWithComplete;
import com.example.chapter6_wzy.Mapper.HomeWork.StudentsMapper;
import com.example.chapter6_wzy.Service.Homework.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    StudentsMapper studentsMapper;
    @Override
    public List<ClassInfoWithClassGroupAndStudents> getClassInfoByStudentsId(Integer id) {
        return studentsMapper.getClassInfoByStudentsId(id);
    }

    @Override
    public List<HomeworkInfoWithComplete> getHomeworkInfoByClassId(String studentid,String classid) {
        return studentsMapper.getHomeworkInfoByClassId(studentid, classid);
    }

    @Override
    public int insertTaskByStuIdTaskId(Integer stuId, Integer taskId,String filename) {
        return studentsMapper.insertTaskByStuIdTaskId(stuId,taskId,filename);
    }

    @Override
    public Integer checkInsertOrUpdate(Integer stuId, Integer taskId) {
        return studentsMapper.checkInsertOrUpdate(stuId,taskId);
    }

    @Override
    public int updateTaskByTaskId(int i, String newFileName) {
        return studentsMapper.updateTaskByTaskId(i,newFileName);
    }

    @Override
    public List<Experiment> selectExperimentByhomeworkidAndstudentid(String studentId, String homeworkid) {
        return studentsMapper.selectExperimentByhomeworkidAndstudentid(studentId,homeworkid);
    }

    @Override
    public List<Experiment> UpdateHomework(String studentId) {
        return null;
    }
}
