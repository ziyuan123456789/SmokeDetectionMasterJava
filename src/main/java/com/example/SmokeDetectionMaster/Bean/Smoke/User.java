package com.example.SmokeDetectionMaster.Bean.Smoke;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ziyuan
 * @since 2024.01
 */
@Data
public class User {
    private Integer userID;
    private String username;
    private String role;
    private String password;
    private String salt;
    private String telephone;
    private LocalDateTime regTime;
    private String enabled;

    public User(Integer userID, String username, String role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
    }
    public boolean isComplete(){
        return  username != null && telephone !=null && password !=null;}
}


