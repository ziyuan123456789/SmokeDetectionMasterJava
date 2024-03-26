package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedAdminRole;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "用户管理")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@Slf4j
@RequestMapping("/userControl")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserTerritoryService userTerritoryService;

    @NeedAdminRole
    @GetMapping("/getUserTerritories")
    public Result<?> getUserTerritories(Integer id) {
        try {
            List<UserTerritoryVo> territories = userTerritoryService.getUserTerritories(id);
            return new Result<>(true, ResponseMessage.SUCCESS, territories);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }
    @RequestMapping("/{id}")
    public Result getUserById(Integer id) {
        return new Result<>(true,ResponseMessage.SUCCESS,userService.findById(id));
    }
    //选择没被封尽的
    @NeedAdminRole
    @GetMapping("/getAllActiveUsers")
    public Result<?> getAllActiveUsers() {
        return new Result<>(true, ResponseMessage.SUCCESS,userService.findAllActiveUsers());
    }
    //选择全部
    @NeedAdminRole
    @GetMapping("/getAllUsers")
    public Result<?> getAllUsers() {
        return new Result<>(true, ResponseMessage.SUCCESS,userService.findAllUsers());
    }

    @GetMapping("/ban")
    public Result<Void> banUser(int id) {
        userService.banUser(id);
        return new Result<>(true, ResponseMessage.SUCCESS, null);
    }

    @GetMapping("/unban")
    public Result<Void> unbanUser(int id) {
        userService.unbanUser(id);
        return new Result<>(true, ResponseMessage.SUCCESS, null);
    }

    @GetMapping("/update")
    public Result<Void> updateUserInfo(int id, String username, String telephone) {
        userService.updateUserInfo(id, username, telephone);
        return new Result<>(true, ResponseMessage.SUCCESS, null);
    }

    @GetMapping("/updatePassword")
    public Result<Void> updateUserPassword(String password, HttpServletRequest request) {
        Claims claims = jwtUtil.parseJwt(request);
        Integer userId = Integer.parseInt(claims.get("id", String.class));
        userService.updateUserPassword(userId, password);
        return new Result<>(true, ResponseMessage.SUCCESS, null);
    }

}
