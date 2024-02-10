package com.example.chapter6_wzy.Bean.Homework.Union;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wsh
 */
@Data
public class HomeworkInfoWithComplete {
    private int id;
    private String homeworkname;
    private String classid;
    private String reload;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endtime;
    private  String score;
    private String state;
}
