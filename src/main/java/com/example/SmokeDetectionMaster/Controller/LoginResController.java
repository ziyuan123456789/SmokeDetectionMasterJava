package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NotLogin;
import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserRegDto;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.UserService.LoginService;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wsh
 */
@Api(tags = "登陆管理")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@Slf4j
public class LoginResController {
    @Autowired
    LoginService loginService;

    @Value("${jwt.secretKey}")
    String hs512Key;

    @Autowired
    UserService userService;

    @RequestMapping("reg")
    @NotLogin
    public Result<?> createUser(User user) {
        if(user.isComplete()) {
            if(userService.checkIsReg(user.getUsername())){
                return new Result<>(false, "账户已经存在",null);
            }else{
                try{
                    return new Result<>(true, ResponseMessage.SUCCESS,null);
                }catch (Exception e){
                    e.printStackTrace();
                    return new Result<>(false, ResponseMessage.ERROR,null);
                }

            }
        }else{
            return new Result<>(false, "参数不全",null);
        }


    }
    @NotLogin
    @RequestMapping("/checkLogin")
    public Result<?> login(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        log.info("username: " + username + " password: " + password + " role: " + role);
        User user = loginService.checkLogin(username, password, role);
//        if("wzy1".equals(username)){
//            username="wzy1";
//            role="0";
//            user=new User(2,"wzy1","0");
//        }
        if (user != null) {
            String userIdAsString = String.valueOf(user.getUserID());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userIdAsString);
            claims.put("username", username);
            claims.put("role", role);
            long expirationTime = 6 * 60 * 60 * 1000;
            String token = Jwts.builder()
                    .setSubject(userIdAsString)
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(SignatureAlgorithm.HS512, hs512Key)
                    .compact();
            try {
                Map<String, Object> loginMap = Map.of(
                        "id", user.getUserID(),
                        "username", username,
                        "role", role,
                        "token", token
                );
                return new Result<>(true, "success", loginMap);

            } catch (Exception e) {
                log.error("Token验证失败", e);
                return new Result<>(false, "Token验证失败", null);
            }
        } else {
            return new Result<>(false, "登录失败，用户名或密码错误", null);
        }
    }
}


