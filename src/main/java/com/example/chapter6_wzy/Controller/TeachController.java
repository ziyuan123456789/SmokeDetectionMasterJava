package com.example.chapter6_wzy.Controller;

import com.example.chapter6_wzy.Bean.Utils.Result;
import com.example.chapter6_wzy.Service.Teach.PortTeachService;
import com.example.chapter6_wzy.Service.Teach.TeachBerthsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
/**
 * @author wsh
 */
@Api(tags = "教学")
@RestController
public class TeachController {
    @Autowired
    PortTeachService portTeachService;
    @Autowired
    TeachBerthsService teachBerthsService;

    @RequestMapping("/selectAllTeachInfo")
    public Result<?> selectAllPortInfo() {
        return new Result<>(true,"success",portTeachService.selectAllPortInfo());
    }
    @RequestMapping("/getBerthsByPort")
    public Result<?> getBerthsByPort(int portId) {
        return new Result<>(true,"success",teachBerthsService.getBerthsByPort(portId));
    }


}
