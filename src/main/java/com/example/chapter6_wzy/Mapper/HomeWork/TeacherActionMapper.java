package com.example.chapter6_wzy.Mapper.HomeWork;

import com.example.chapter6_wzy.Bean.Homework.ClassGroup;
import com.example.chapter6_wzy.Bean.Homework.Classes;
import com.example.chapter6_wzy.Bean.Homework.Homework;
import com.example.chapter6_wzy.Bean.Homework.Union.StudentsInfoWithWorkWithTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface TeacherActionMapper {
    @Select("SELECT cg.id,cg.groupname FROM class as c \n" +
            "LEFT JOIN classgroup as cg ON c.master=cg.id \n" +
            "LEFT JOIN teachers as t ON  t.tid=c.belongteacher\n" +
            "WHERE t.tid=#{teacherId}\n" +
            "GROUP BY cg.groupname,cg.id\n" +
            "ORDER BY cg.id ASC;")
    List<ClassGroup> getClassInfoByTeacherId(Integer teacherId);
    @Select("SELECT c.* FROM class as c\n" +
            "left join teachers as t on c.belongteacher=t.tid\n" +
            "WHERE c.master=#{classGroupId} AND t.tid=#{teacherId}")
    List<Classes> getClassesListByTeacherIdClassGroupId(Integer classGroupId,Integer teacherId);

    @Select("SELECT * FROM homework WHERE classid=#{classId}")
    List<Homework> getHomeworkListByClassId(String classId);
    @Select("SELECT sct.*,s.sname FROM studentcompletedtask as sct\n" +
            "left JOIN students as s on s.sid=sct.studentid \n" +
            "left join homework as h on h.id=sct.homeworkid\n" +
            "where h.id=#{homeworkId}")
    List<StudentsInfoWithWorkWithTask> getFinishedHomeworkListByHomeworkId(Integer homeworkId);
    @Select("SELECT filehome FROM studentcompletedtask WHERE id=#{taskid} ")
    String getFileNameByTaskId(Integer taskid);
}
