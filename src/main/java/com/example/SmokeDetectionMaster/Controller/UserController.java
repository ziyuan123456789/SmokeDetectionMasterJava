package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "用户管理")
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/userControl")
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @RequestMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    @RequestMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
//        user.setId(id);
//        return ResponseEntity.ok(userService.updateUser(user));
//    }
//
//    @RequestMapping("/{id}")
//    public void deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//    }
}
