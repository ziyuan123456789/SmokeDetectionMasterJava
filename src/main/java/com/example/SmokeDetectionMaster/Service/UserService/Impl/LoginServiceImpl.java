package com.example.SmokeDetectionMaster.Service.UserService.Impl;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Mapper.User.LoginMapper;
import com.example.SmokeDetectionMaster.Service.UserService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public User checkLogin(String username, String password, String role) {
        return loginMapper.checkLogin(username,password,role);
    }

}
