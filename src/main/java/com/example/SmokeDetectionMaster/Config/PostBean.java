package com.example.SmokeDetectionMaster.Config;

import com.example.SmokeDetectionMaster.Controller.DataAnalysisController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author ziyuan
 * @since 2024.05
 */
@Component
public class PostBean  {
    @PostConstruct
    public void init() {
        System.out.println("PostBean init");
    }


}
