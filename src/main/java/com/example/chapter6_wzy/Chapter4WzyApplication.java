package com.example.chapter6_wzy;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@Slf4j
public class Chapter4WzyApplication {

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
        ApplicationContext applicationContext = SpringApplication.run(Chapter4WzyApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.warn("Bye!");
            Process process = null;
            BufferedReader reader = null;
            try {
                process = Runtime.getRuntime().exec( "D:/Redis-x64-3.0.504/redis-cli.exe"+" flushall");
                reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println("result: " + line);
                    line = reader.readLine();
                }
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    throw new RuntimeException("执行命令失败");
                }
            } catch (IOException e) {
                throw new RuntimeException("执行命令异常", e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("关闭异常");
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    log.error("不是,关闭一下程序也给老子抛异常?");
                }
                if (process != null) {
                    process.destroy();
                }
            }

        }));

    }
    @PreDestroy
    public void goodBye() throws Exception {

    }

}
