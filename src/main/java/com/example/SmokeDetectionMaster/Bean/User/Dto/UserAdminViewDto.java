package com.example.SmokeDetectionMaster.Bean.User.Dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Data
public class UserAdminViewDto {
    private Integer userID;
    private String username;
    private String role;
    private String telephone;
    private LocalDate regTime;
    private String Enabled;
}
