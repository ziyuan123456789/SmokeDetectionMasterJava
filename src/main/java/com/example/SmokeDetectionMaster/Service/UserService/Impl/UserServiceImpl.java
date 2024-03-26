package com.example.SmokeDetectionMaster.Service.UserService.Impl;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserAdminViewDto;
import com.example.SmokeDetectionMaster.Mapper.User.UserMapper;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean checkIsReg(String userName) {
        Integer count = userMapper.checkIsReg(userName);
        return count != null && count > 0;

    }

    @Override
    public UserAdminViewDto findById(int userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<UserAdminViewDto> findAllActiveUsers() {
        return userMapper.findAllActiveUsers();
    }

    @Override
    public List<UserAdminViewDto> findAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    @Transactional
    public void banUser(int userId) {
        userMapper.banUser(userId);
    }

    @Override
    @Transactional
    public void unbanUser(int userId) {
        userMapper.unbanUser(userId);
    }

    @Override
    @Transactional
    public Integer createUser(User user) {
        return userMapper.createUser(user);
    }


    @Override
    @Transactional
    public void updateUserInfo(int userId, String username, String telephone) {
        userMapper.updateUserInfo(userId, username, telephone);
    }

    @Override
    @Transactional
    public void updateUserPassword(int userId, String password) {
        userMapper.updateUserPassword(userId, password);
    }


}
