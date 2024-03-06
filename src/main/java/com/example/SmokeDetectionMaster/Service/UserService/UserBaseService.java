package com.example.SmokeDetectionMaster.Service.UserService;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserBaseService {
    int saveUser(User user);
    User getUserById(Integer id);
    List<User> getAllUsers();
    int updateUser(User user);
    int deleteUser(Integer id);
}
