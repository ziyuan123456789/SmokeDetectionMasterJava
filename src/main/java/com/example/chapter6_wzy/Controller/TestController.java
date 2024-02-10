package com.example.chapter6_wzy.Controller;

import com.example.chapter6_wzy.Mapper.HomeWork.StudentsMapper;
import com.example.chapter6_wzy.Service.Homework.ClassService;
import com.example.chapter6_wzy.WebSocket.DownloadSocket;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wsh
 */
@Api(tags = "测试")
@CrossOrigin
@RestController
@Slf4j
public class TestController {
    @Autowired
    DownloadSocket downloadSocket;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ClassService classService;
    @Autowired
    StudentsMapper studentsMapper;
    Boolean mark = true;

    @RequestMapping("/websockettest")
    public ModelAndView websockettest() {
        studentsMapper.deleteExperiment("1");
        downloadSocket.sendProgressUpdate("wzy", "Progress: 50%");

        return new ModelAndView("websockettest");
    }


}





