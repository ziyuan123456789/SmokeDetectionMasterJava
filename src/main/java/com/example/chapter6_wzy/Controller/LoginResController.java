package com.example.chapter6_wzy.Controller;

import com.example.chapter6_wzy.Bean.Smoke.User;
import com.example.chapter6_wzy.Bean.Utils.Result;
import com.example.chapter6_wzy.Service.UserService.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@CrossOrigin
@RestController
@Slf4j
public class LoginResController {
    @Autowired
    LoginService loginService;

    @Value("${jwt.secretKey}")
    String hs512Key;

    @RequestMapping("/checkLogin")
    public Result<?> login(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        log.info("username: " + username + " password: " + password + " role: " + role);
        User user = loginService.checkLogin(username, password, role);
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

            // 验证Token合法性
            try {
                Claims parsedClaims = Jwts.parserBuilder()
                        .setSigningKey(hs512Key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                System.out.println(parsedClaims.get("id"));

                // 如果无异常抛出，说明Token合法
                Map<String, Object> loginMap = Map.of(
                        "id", user.getUserID(),
                        "username", username,
                        "role", role,
                        "token", token
                );
                System.out.println("token是"+token);
                System.out.println("密钥是"+hs512Key);
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


