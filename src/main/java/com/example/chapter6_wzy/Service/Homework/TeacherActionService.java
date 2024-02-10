package com.example.chapter6_wzy.Service.Homework;

import com.example.chapter6_wzy.Bean.Homework.ClassGroup;
import com.example.chapter6_wzy.Bean.Homework.Classes;
import com.example.chapter6_wzy.Bean.Homework.Homework;
import com.example.chapter6_wzy.Bean.Homework.Union.StudentsInfoWithWorkWithTask;

import java.util.List;
import java.util.Map;

/**
 * @author wsh
 */
public interface TeacherActionService {
   List<ClassGroup> getClassInfoByTeacherId(Integer teacherId);
   List<Classes> getClassesListByTeacherIdClassGroupId(Integer classGroupId, Integer teacherId);

   List<Homework> getHomeworkListByClassId(String classId);
   List<StudentsInfoWithWorkWithTask> getFinishedHomeworkListByHomeworkId(Integer homeworkId);
   Map<String,Object>downloadfileByTaskId(Integer taskid);
}
