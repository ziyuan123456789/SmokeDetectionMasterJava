package com.example.SmokeDetectionMaster.Controller.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "辖区管理")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/territory")
@Slf4j
public class TerritoryController {
    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private UserTerritoryService userTerritoryService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/availableTerritories")
    public Result<?> getAvailableTerritories(HttpServletRequest request) {
        try {
            Claims claims =jwtUtil.parseJwt(request);
            Integer userID=Integer.parseInt((String) claims.get("id"));
            System.out.println(userID);
            Map<String, Object> territories = userTerritoryService.getAvailableTerritories(userID);
            return new Result<>(true,ResponseMessage.SUCCESS,territories);
        } catch (Exception e) {
            return new Result<>(false,ResponseMessage.SUCCESS,e.getMessage());
        }
    }

    @GetMapping("/requestChanges")
    public Result<?> requestTerritoryChanges(TerritoryRequestDto request) {
        try {
            userTerritoryService.requestTerritoryChanges(request.getUserId(), request.getTerritoryIds());
            return new Result<>(true,ResponseMessage.SUCCESS,"更新成功");
        } catch (Exception e) {
            return new Result<>(false,ResponseMessage.ERROR,e.getMessage());
        }
    }

    //xxx:CRUD
    @GetMapping("/getAllTerritory")
    public Result<?> getAllTerritory(){
        return new Result<>(true, ResponseMessage.SUCCESS,territoryService.findAvailableTerritories());
    }

    @GetMapping("/getTerritoryById")
    public Result<?> getById(int id) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.findById(id));
    }

    @GetMapping("/createTerritory")
    public Result<?> create(Territory territory) {
        System.out.println(territory.toString());
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.save(territory));
    }

    @GetMapping("/updateTerritory")
    public Result<?> update(Territory territory) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.update(territory));
    }

    @GetMapping("/deleteTerritory")
    public Result<?> delete( int id) {
        if(territoryService.delete(id)==0){
            return  new Result<>(false,ResponseMessage.FAILURE,"删除失败");
        }else{
            return new Result<>(true,ResponseMessage.SUCCESS,null);
        }

    }


}
