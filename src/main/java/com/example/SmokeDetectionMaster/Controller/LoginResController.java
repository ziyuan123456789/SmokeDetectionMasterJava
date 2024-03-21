package com.example.SmokeDetectionMaster.Controller;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NotLogin;
import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserRegDto;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.RedisService;
import com.example.SmokeDetectionMaster.Service.UserService.LoginService;
import com.example.SmokeDetectionMaster.Service.UserService.UserService;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @Autowired
    RedisService redisService;


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
        if (user != null) {
            String userIdAsString = String.valueOf(user.getUserID());
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", userIdAsString);
            claims.put("username", username);
            claims.put("role", role);
            long expirationTime = 6 * 60 * 60 * 1000;
            String access_token = Jwts.builder()
                    .setSubject(userIdAsString)
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(SignatureAlgorithm.HS512, hs512Key)
                    .compact();
            String refresh_token = Jwts.builder()
                    .setSubject(userIdAsString)
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime*2))
                    .signWith(SignatureAlgorithm.HS512, hs512Key)
                    .compact();

                Map<String, Object> loginMap = Map.of(
                        "id", user.getUserID(),
                        "username", username,
                        "role", role,
                        "token", access_token,
                        "refresh_token", refresh_token
                );
                return new Result<>(true, "success", loginMap);
        } else {
            return new Result<>(false, "登录失败，用户名或密码错误", null);
        }
    }
    @NotLogin
    @RequestMapping("/reNew")
    public Result<?> reNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
            log.error("reNew接口没有token");
            response.sendError(403, "未携带鉴权信息");
            return new Result<>(false, "未携带鉴权信息", null);
        } else {
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(hs512Key)
                        .build()
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody();

                if (redisService.isBlacklisted(token.replace("Bearer ", ""))) {
                    log.error("令牌在黑名单中");
                    response.sendError(403, "Token在黑名单中");
                    return new Result<>(false, "Token在黑名单中", null);
                }

                String userIdAsString = claims.getSubject();
                String username = claims.get("username", String.class);
                String role = claims.get("role", String.class);
                long expirationTime = 6 * 60 * 60 * 1000;

                String access_token = Jwts.builder()
                        .setSubject(userIdAsString)
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                        .signWith(SignatureAlgorithm.HS512, hs512Key)
                        .compact();

                String refresh_token = Jwts.builder()
                        .setSubject(userIdAsString)
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 2))
                        .signWith(SignatureAlgorithm.HS512, hs512Key)
                        .compact();

                redisService.addToBlacklist(token.replace("Bearer ", ""), expirationTime * 2);

                Map<String, Object> loginMap = Map.of(
                        "id", userIdAsString,
                        "username", username,
                        "role", role,
                        "token", access_token,
                        "refresh_token", refresh_token
                );

                return new Result<>(true, "success", loginMap);
            } catch (ExpiredJwtException e) {
                log.error("Token过期: " + e);
                response.sendError(403, "Token已过期");
            } catch (JwtException e) {
                log.error("鉴权信息异常: " + e);
                response.sendError(403, "鉴权信息被篡改");
            }
        }
        return new Result<>(false, "鉴权失败", null);
    }

}


