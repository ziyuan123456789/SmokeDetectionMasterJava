package com.example.SmokeDetectionMaster.Service.UserService.Impl;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Mapper.User.UserMapper;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
        System.out.println(userName);
        Integer count = userMapper.checkIsReg(userName);
        return count != null && count > 0;

    }
    @Override
    public int saveUser(User user) {
        user.setSalt("123");
        user.setRole("0");
        user.setEnabled("1");
        user.setRegTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return 0;
    }


}
