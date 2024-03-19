package com.example.SmokeDetectionMaster.Service.UserService;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserAdminViewDto;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
public interface UserBaseService {
    int saveUser(User user);
    UserAdminViewDto getUserById(Integer id);
    List<UserAdminViewDto> getAllUsers();
    int updateUser(User user);
    int deleteUser(Integer id);
}
