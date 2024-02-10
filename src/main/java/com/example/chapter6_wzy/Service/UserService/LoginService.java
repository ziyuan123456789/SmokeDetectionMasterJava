package com.example.chapter6_wzy.Service.UserService;

import com.example.chapter6_wzy.Bean.Smoke.User;

/**
 * @author wsh
 */
public interface LoginService {
    User checkLogin(String username, String password, String role);

}
