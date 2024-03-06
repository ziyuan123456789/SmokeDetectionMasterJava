package com.example.SmokeDetectionMaster.Bean.User.Dto;

import lombok.Data;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Data
public class UserRegDto {
    private Integer userID;
    private String username;
    private String password;
    private String telephone;
    public boolean isComplete(){
        return userID != null && username != null && password != null && telephone != null;
    }

}
