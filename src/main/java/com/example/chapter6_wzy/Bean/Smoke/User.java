package com.example.chapter6_wzy.Bean.Smoke;

import lombok.Data;

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
    private String territoryID;
    private String telephone;
}
