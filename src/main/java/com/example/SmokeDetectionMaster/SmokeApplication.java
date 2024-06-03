package com.example.SmokeDetectionMaster;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author wsh
 */
@SpringBootApplication
@Slf4j
public class SmokeApplication {
    @PostConstruct
    public void init() {
        log.warn("  __  __        _____            _             \n" +
                " |  \\/  |      / ____|          (_)            \n" +
                " | \\  / |_   _| (___  _ __  _ __ _ _ __   __ _ \n" +
                " | |\\/| | | | |\\___ \\| '_ \\| '__| | '_ \\ / _` |\n" +
                " | |  | | |_| |____) | |_) | |  | | | | | (_| |\n" +
                " |_|  |_|\\__, |_____/| .__/|_|  |_|_| |_|\\__, |\n" +
                "          __/ |      | |                  __/ |\n" +
                "         |___/       |_|                 |___/ ");
    }

    public static void main(String[] args) {


        ApplicationContext applicationContext = SpringApplication.run(SmokeApplication.class, args);


    }
    @PreDestroy
    public void goodBye() throws Exception {

    }

}
