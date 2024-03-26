package com.example.SmokeDetectionMaster.Service.UserService;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserAdminViewDto;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserService {
    boolean checkIsReg(String userName);

    UserAdminViewDto findById(int userId);

    List<UserAdminViewDto> findAllActiveUsers();

    // 获取所有用户列表
    List<UserAdminViewDto> findAllUsers();

    // 根据用户ID封禁用户
    void banUser(int userId);

    // 根据用户ID解封用户
    void unbanUser(int userId);

    // 创建新用户
    Integer createUser(User user);


    // 更新用户信息
    void updateUserInfo(int userId, String username, String telephone);

    // 更新用户密码
    void updateUserPassword(int userId, String password);
}
