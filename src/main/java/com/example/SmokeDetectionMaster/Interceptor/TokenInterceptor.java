package com.example.SmokeDetectionMaster.Interceptor;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedLogin;
import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedTeacherRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
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
    //Todo: token拦截器,没有或者非法token返回401,合法拿出id,role等扔给后续servlet
public class TokenInterceptor implements HandlerInterceptor  {
    @Value("${jwt.secretKey}")
    String hs512Key;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Method method = ((HandlerMethod) handler).getMethod();
//            Object controller = handlerMethod.getBean();
//            log.info("来自"+controller.getClass().getSimpleName()+"控制器");
//            String methodName = handlerMethod.getMethod().getName();
//            log.info("执行"+ methodName+"方法");
//            NeedLogin annotation = method.getAnnotation(NeedLogin .class);
//            NeedTeacherRole annotationTeacherRole = method.getAnnotation(NeedTeacherRole .class);
            //看看有没有需要登录的注解
//            if (annotation != null) {
//                log.warn("需要登陆验证");
//                String token = request.getHeader("Authorization");
//                if (StringUtils.isBlank(token) || !token.startsWith("Bearer ")) {
//                    log.error("没有token");
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                    return false;
//                }
//                String username;
//                String role;
//                String id;
//                try {
//                    Claims claims = Jwts.parserBuilder()
//                            .setSigningKey(hs512Key)
//                            .build()
//                            .parseClaimsJws(token.replace("Bearer ", ""))
//                            .getBody();
//                    username = (String) claims.get("username");
//                    role = (String) claims.get("role");
//                    id = (String) claims.get("id");
//                    if (annotationTeacherRole != null) {
//                        if (!"1".equals(role)) {
//                            log.warn("不是教师触发拦截");
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "宁不配");
//                            return false;
//                        }
//                    }
//                    log.info(username);
//                    log.info(role);
//                    log.warn(id);
//                } catch (JwtException e) {
//                    log.error(e.toString());
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                    return false;
//                }
//                request.setAttribute("username", username);
//                request.setAttribute("role", role);
//                request.setAttribute("id", id);
//                return true;
//            }
//        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
