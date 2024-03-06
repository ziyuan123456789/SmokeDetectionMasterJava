package com.example.SmokeDetectionMaster.Aop.Checker;
import com.example.SmokeDetectionMaster.Annotations.CheckParams.CheckDate;
import org.apache.commons.lang3.time.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * @author wsh
 */
@Aspect
@Component
@Slf4j
public class ValidDateAspect {
    @Around("pointcut()")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                Annotation annotation = parameterAnnotations[i][j];
                if (annotation instanceof CheckDate) {
                    String[] possibleFormats = {"yyyy-MM-dd", "yyyy/MM/dd", "MM/dd/yyyy","yyyyMMdd"};
                    Date date;
                    try {
                        date = DateUtils.parseDate((String) args[i], possibleFormats);
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                        Date date = sdf.parse((String) args[i]);
                        if (date.before(new Date())) {
                            System.out.println("日期早于今天");
                            args[i] = "早于今天";
                        } else {
                            System.out.println("日期晚于今天");
                            args[i] = "晚于今天";
                        }
                    }catch (Exception e){
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
                    }

                }
            }
        }
        return proceedingJoinPoint.proceed(args);
    }
    @Pointcut("@annotation(com.example.SmokeDetectionMaster.Annotations.CheckParams.EnableParameterCheck))")
    public void pointcut(){
    }


}

