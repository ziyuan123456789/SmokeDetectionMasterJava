package com.example.SmokeDetectionMaster.Controller.Territory;

import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Api(tags = "辖区管理")
@CrossOrigin
@RestController
@RequestMapping("/territory")
@Slf4j
public class TerritoryController {
    @Autowired
    private TerritoryService territoryService;

    @Autowired
    private UserTerritoryService userTerritoryService;

    @GetMapping("/availableTerritories")
    public Result<?> getAvailableTerritories() {
        try {
            List<Territory> territories = userTerritoryService.getAvailableTerritories();
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
    @RequestMapping("/getAllTerritory")
    public Result<?> getAllTerritory(){
        return new Result<>(true, ResponseMessage.SUCCESS,territoryService.findAvailableTerritories());
    }

    @RequestMapping("/getTerritoryById")
    public Result<?> getById(int id) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.findById(id));
    }

    @RequestMapping("/createTerritory")
    public Result<?> create(Territory territory) {
        System.out.println(territory.toString());
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.save(territory));
    }
    @RequestMapping("/updateTerritory")
    public Result<?> update(Territory territory) {
        return new Result<>(true,ResponseMessage.SUCCESS,territoryService.update(territory));
    }
    @RequestMapping("/deleteTerritory")
    public Result<?> delete( int id) {
        System.out.println(id);
        if(territoryService.delete(id)==0){
            return  new Result<>(false,ResponseMessage.FAILURE,"删除失败");
        }else{
            return new Result<>(true,ResponseMessage.SUCCESS,null);
        }

    }


}
