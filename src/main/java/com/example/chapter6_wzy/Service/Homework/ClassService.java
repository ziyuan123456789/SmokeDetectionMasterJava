package com.example.chapter6_wzy.Service.Homework;

import com.example.chapter6_wzy.Bean.Homework.Experiment;
import com.example.chapter6_wzy.Bean.Homework.Union.ClassInfoWithClassGroupAndStudents;
import com.example.chapter6_wzy.Bean.Homework.Union.HomeworkInfoWithComplete;

import java.util.List;

/**
 * @author wsh
 */
public interface ClassService {
    List<ClassInfoWithClassGroupAndStudents> getClassInfoByStudentsId(Integer id);

    List<HomeworkInfoWithComplete> getHomeworkInfoByClassId(String studentid,String classid);

    int insertTaskByStuIdTaskId(Integer stuId, Integer taskId,String filename);
    Integer checkInsertOrUpdate(Integer stuId, Integer taskId);

    int updateTaskByTaskId(int i, String newFileName);

    List<Experiment> selectExperimentByhomeworkidAndstudentid(String studentId, String homeworkid);
    List<Experiment> UpdateHomework(String studentId);
}
