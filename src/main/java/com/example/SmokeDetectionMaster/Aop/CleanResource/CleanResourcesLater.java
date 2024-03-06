package com.example.SmokeDetectionMaster.Aop.CleanResource;

import com.example.SmokeDetectionMaster.Annotations.ResourceClean.CleanupResources;
import com.example.SmokeDetectionMaster.Utils.Md5;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wsh
 */
@Aspect
@Component
@Slf4j
public class CleanResourcesLater {
    @Value("${zipcache}")
    String zipcachehome;
    @After("pointcut()")
    public void after(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CleanupResources cleanupResources = method.getAnnotation(CleanupResources.class);
        if (cleanupResources != null) {
            Class<?> targetClass = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Method getField = targetClass.getDeclaredMethod("getZipFileName");
            getField.setAccessible(true);
            Object result = getField.invoke(joinPoint.getThis());
            System.out.println((String) result);
            String value = cleanupResources.value();
            System.out.println("注解内容：" + value);
            if(!"".equals(value)){
                Md5.clean(value);

            }
        }
    }
    @Pointcut("@annotation(com.example.SmokeDetectionMaster.Annotations.ResourceClean.CleanupResources))")
    public void pointcut(){
    }
}
