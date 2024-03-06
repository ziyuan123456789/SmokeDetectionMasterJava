package com.example.SmokeDetectionMaster.Service.UserService;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;

/**
 * @author wsh
 */
public interface LoginService {
    User checkLogin(String username, String password, String role);

}
