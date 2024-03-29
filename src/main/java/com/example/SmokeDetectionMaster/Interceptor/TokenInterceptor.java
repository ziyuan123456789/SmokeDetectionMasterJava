package com.example.SmokeDetectionMaster.Interceptor;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NotLogin;
import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedAdminRole;
import com.example.SmokeDetectionMaster.Service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.lang.reflect.Method;
/**
 * @author wsh
 */
@Component
@Order(1)
@Slf4j
public class TokenInterceptor implements HandlerInterceptor  {
    @Value("${jwt.secretKey}")
    String hs512Key;

    @Autowired
    RedisService redisService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            NotLogin annotation = method.getAnnotation(NotLogin.class);
            NeedAdminRole annotationAdminRole = method.getAnnotation(NeedAdminRole.class);
            if (annotation == null) {
                log.warn("需要登陆验证");
                String token = request.getHeader("Authorization");
                if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
                    log.error("没有token");
                    response.sendError(403, "未携带鉴权信息");
                    return false;
                }

                try {
                    Claims claims = Jwts.parserBuilder()
                            .setSigningKey(hs512Key)
                            .build()
                            .parseClaimsJws(token.replace("Bearer ", ""))
                            .getBody();
                    if (redisService.isBlacklisted(token.replace("Bearer ", ""))) {
                        log.warn("Token已被加入黑名单");
                        response.sendError(403, "Token无效");
                        return false;
                    }

                    String role = (String) claims.get("role");
                    if (annotationAdminRole != null && !"1".equals(role)) {
                        log.warn("越权行为发生");
                        response.sendError(403, "宁不配");
                        return false;
                    }
                } catch (ExpiredJwtException e) {
                    log.error("Token过期: " + e);
                    response.sendError(401, "Token已过期");
                    return false;
                } catch (JwtException e) {
                    log.error("鉴权信息异常: " + e);
                    response.sendError(403, "鉴权信息被篡改");
                    return false;
                }
            } else {
                log.info("无需权限验证");
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
