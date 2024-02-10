package com.example.chapter6_wzy.Bean.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wsh
 */
@Data
public class Students {
    private int sid;
    private String sname;
    private  int tid;
    private int isvalid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createtime;
    private String password;
    private String classgroup;
}
