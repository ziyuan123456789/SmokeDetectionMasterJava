package com.example.SmokeDetectionMaster.Controller.Territory;

import com.example.SmokeDetectionMaster.Annotations.NeedRole.NeedAdminRole;
import com.example.SmokeDetectionMaster.Bean.Territory.Territory;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordAdminVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryChangeRecordUserVo;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryRequestDto;
import com.example.SmokeDetectionMaster.Bean.Territory.TerritoryReviewResultDto;
import com.example.SmokeDetectionMaster.Bean.Territory.UserTerritoryVo;
import com.example.SmokeDetectionMaster.Bean.Utils.JwtUtil;
import com.example.SmokeDetectionMaster.Bean.Utils.ResponseMessage;
import com.example.SmokeDetectionMaster.Bean.Utils.Result;
import com.example.SmokeDetectionMaster.Service.Territory.TerritoryService;
import com.example.SmokeDetectionMaster.Service.Territory.UserTerritoryService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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




    @GetMapping("/deleteUserTerritory")
    public Result<?> deleteUserTerritory(Integer id) {
        try {
            return new Result<>(true, ResponseMessage.SUCCESS, userTerritoryService.deleteUserTerritory(id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }

    @GetMapping("/getUserTerritories")
    public Result<?> getUserTerritories(HttpServletRequest request) {
        try {
            Claims claims = jwtUtil.parseJwt(request);
            List<UserTerritoryVo> territories = userTerritoryService.getUserTerritories(Integer.parseInt((String) claims.get("id")));
            return new Result<>(true, ResponseMessage.SUCCESS, territories);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }


    //xxx:管理员更新辖区申请请求
    @NeedAdminRole
    @GetMapping("/updateTerritoryChange")
    public Result<?> updateTerritoryChange(TerritoryReviewResultDto territoryReviewResultDto,HttpServletRequest request) {
        try {
            Claims claims = jwtUtil.parseJwt(request);
            territoryReviewResultDto.setApproverId(Integer.parseInt((String) claims.get("id")));
            return new Result<>(true, ResponseMessage.SUCCESS, territoryService.updateTerritoryChange(territoryReviewResultDto));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }

    //xxx:寻找所有处在未审核状态的申请
    @NeedAdminRole
    @GetMapping("/findAllTerritoriesApply")
    public Result<?> findAllTerritoriesApply() {
        try {
            List<TerritoryChangeRecordAdminVo> territories = territoryService.findAllTerritoriesApply();
            return new Result<>(true, ResponseMessage.SUCCESS, territories);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }

    //xxx:获取当前用户的申请记录
    @GetMapping("/getApproveState")
    public Result<?> getApproveState(HttpServletRequest request) {
        try {
            Claims claims = jwtUtil.parseJwt(request);
            List<TerritoryChangeRecordUserVo> territories = userTerritoryService.getApproveState(Integer.parseInt((String) claims.get("id")));
            return new Result<>(true, ResponseMessage.SUCCESS, territories);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false, ResponseMessage.SUCCESS, e.getMessage());
        }
    }

    //xxx:寻找所有可用的辖区(没有用户申请并且也没有被占有)UserId从Token里拿
    @GetMapping("/availableTerritories")
    public Result<?> getAvailableTerritories(HttpServletRequest request) {
        try {
            Claims claims =jwtUtil.parseJwt(request);
            Map<String, Object> territories = userTerritoryService.getAvailableTerritories(Integer.parseInt((String) claims.get("id")));
            return new Result<>(true,ResponseMessage.SUCCESS,territories);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result<>(false,ResponseMessage.SUCCESS,e.getMessage());
        }
    }

    //xxx:进行辖区申请(每次都检查是否超过4辖区限制,然后再看看申请的辖区是否已经有人申请了)
    @GetMapping("/requestChanges")
    public Result<?> requestTerritoryChanges(TerritoryRequestDto request) {
        try {
            userTerritoryService.requestTerritoryChanges(request);
            return new Result<>(true,ResponseMessage.SUCCESS,"更新成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
