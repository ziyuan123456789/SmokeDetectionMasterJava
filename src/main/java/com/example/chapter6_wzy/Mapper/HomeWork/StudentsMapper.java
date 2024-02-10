package com.example.chapter6_wzy.Mapper.HomeWork;

import com.example.chapter6_wzy.Bean.Homework.Experiment;
import com.example.chapter6_wzy.Bean.Homework.Union.ClassInfoWithClassGroupAndStudents;
import com.example.chapter6_wzy.Bean.Homework.Union.HomeworkInfoWithComplete;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface StudentsMapper {
    @Select("SELECT s.sid,s.sname,cg.groupname,c.id as keyclassid,c.classid,c.classname FROM students as s     \n" +
            "left join classgroup as cg on cg.id=s.classgroup\n" +
            "left join class as c on  c.master=cg.id\n" +
            "where s.sid=#{id} ")
    List<ClassInfoWithClassGroupAndStudents> getClassInfoByStudentsId(Integer id);
    @Select("SELECT h.*, sct.score, CASE \n" +
            "    WHEN h.endtime > NOW() AND sct.score IS NULL AND sct.homeworkid IS NULL THEN '作业进行中' \n" +
            "    WHEN sct.homeworkid IS NOT NULL THEN '作业已提交' \n" +
            "    WHEN sct.score IS NOT NULL THEN '作业已提交' \n" +
            "    WHEN h.endtime < NOW() THEN '作业已经过期' \n" +
            "    ELSE '' END AS state \n" +
            "FROM homework AS h \n" +
            "LEFT JOIN studentcompletedtask AS sct ON sct.homeworkid=h.id AND sct.studentid=#{studentid}\n" +
            "where classid =#{classid}")
    List<HomeworkInfoWithComplete> getHomeworkInfoByClassId(String studentid,String classid);
    @Insert("INSERT INTO studentcompletedtask (homeworkid,studentid,filehome) VALUES (#{taskId},#{stuId},#{filename})")
    int insertTaskByStuIdTaskId(Integer stuId, Integer taskId,String filename);
    @Select("select sct.id from studentcompletedtask as sct LEFT JOIN homework as h on h.id=sct.homeworkid  where sct.studentid=#{stuId} and sct.homeworkid=#{taskId}")
    Integer checkInsertOrUpdate(Integer stuId, Integer taskId);
    @Update("update  studentcompletedtask set filehome=#{newFileName} where id=#{i}")
    int updateTaskByTaskId(int i, String newFileName);

    @Select("SELECT numqc,numit,qqct1,qqct2,bst from experiment  where homeworkid=#{homeworkid} and studentid=#{studentid}")
    List<Experiment>selectExperimentByhomeworkidAndstudentid(String homeworkid,String studentid);
    @Delete("delete from experiment where id=#{id}")
    int deleteExperiment(String id);

}
