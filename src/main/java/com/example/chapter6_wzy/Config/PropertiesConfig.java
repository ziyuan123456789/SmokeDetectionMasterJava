package com.example.chapter6_wzy.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author wsh
 */
@Configuration
public class PropertiesConfig {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        propertiesConfig = this;
    }

    public static String getSecretKey() {
        return propertiesConfig.env.getProperty("jwt.secretKey");
    }
}





