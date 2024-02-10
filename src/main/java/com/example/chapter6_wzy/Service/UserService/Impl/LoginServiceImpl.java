package com.example.chapter6_wzy.Service.UserService.Impl;

import com.example.chapter6_wzy.Bean.Smoke.User;
import com.example.chapter6_wzy.Mapper.User.LoginMapper;
import com.example.chapter6_wzy.Service.UserService.LoginService;
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
