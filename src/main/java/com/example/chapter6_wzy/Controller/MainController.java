package com.example.chapter6_wzy.Controller;
import com.example.chapter6_wzy.Annotations.NeedRole.NeedLogin;
import com.example.chapter6_wzy.Bean.Port.M_thp_dlp;
import com.example.chapter6_wzy.Bean.Utils.Result;
import com.example.chapter6_wzy.Mapper.Port.M_thp_dlpMapper;
import com.example.chapter6_wzy.Service.Port.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wsh
 */
@Api(tags = "主查询")
@RestController
public class MainController {
    @Autowired
    PortService portService;
    @Autowired
    RailwayService railwayService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ChannelService channelService;
    @Autowired
    AnchorageService anchorageService;
    @Autowired
    BerthService berthService;
    @Autowired
    PipelineSerice pipelineSerice;
    @Autowired
    JdbcTemplate jt;
    @Autowired
    M_thp_dlpMapper mThpDlpMapper;
    @CrossOrigin("*")
    @RequestMapping("/selectAllPortInfo")
    public Result<?> selectAllPortInfo() {
        return new Result<>(true,"success",portService.selectAllPortInfo());
    }
    @CrossOrigin("*")
    @NeedLogin
    @RequestMapping("/selectAllInfo")
    public Result<?> selectAllInfo() {
        Map<String,Object>resultmap=Map.of(
                "port", portService.selectPortInfoWithPorta(),
                "railway",railwayService.selectAllRailwayInfo(),
                "company",companyService.selectAllCompanyInfo(),
                "channel",channelService.getAllChannelInfo(),
                "anchorage",anchorageService.getAnchorageList(),
                "berth",berthService.getAllBerthInfo(),
                "pipeline",pipelineSerice.selectAllPipelineInfo()
        );
        return new Result<>(true,"success",resultmap);
    }

    @CrossOrigin("*")
    @RequestMapping("/getCompanyThroughput")
    public Result<?> getCompanyThroughput() {
        String sql = "select distinct floor(date/100) as date from m_thp_comp";
        Map<String,Object>resultmap=Map.of(
                "company",companyService.selectAllCompanyInfo(),
                "year", jt.queryForList(sql) );
        return new Result<>(true,"success",resultmap);
    }

    @CrossOrigin("*")
    @RequestMapping("/getCompanyThroughputByComIdAndYear")
    public Result<?> getCompanyThroughputByComIdAndYear(String comid,Integer year) {

        return new Result<>(true,"success",companyService.getCompanyThroughputByComIdAndYear(comid,year));
    }
    @CrossOrigin("*")
    @RequestMapping("/getAllDlpThpYear")
    public Result<?> getAllDlpThpYear() {
        String sql = "select distinct floor(date/100) as date from m_thp_dlp";
        return new Result<>(true,"success", jt.queryForList(sql) );
    }


    @CrossOrigin("*")
    @RequestMapping(path = "/getDlpThpByYear")
    public Result<?> getDlpThpByYear(@RequestParam Integer year) {
        return new Result<>(true,"success",mThpDlpMapper.findThpByYear(year));
    }

    @CrossOrigin("*")
    @RequestMapping(path = "/getDlpThroughputYear")
    public Result<?> getDlpThroughputYear() {
        List<M_thp_dlp> dataList = mThpDlpMapper.findAllThp();
        Map<Integer, Map<Integer, List<M_thp_dlp>>> map = new HashMap<>();
        for (M_thp_dlp data : dataList) {
            int year = data.getDate() / 100;
            int month = data.getDate() % 100;
            if (!map.containsKey(year)) {
                map.put(year, new HashMap<>());
            }
            Map<Integer, List<M_thp_dlp>> yearMap = map.get(year);
            if (!yearMap.containsKey(month)) {
                yearMap.put(month, new ArrayList<>());
            }
            yearMap.get(month).add(data);
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, List<M_thp_dlp>>> entry : map.entrySet()) {
            int year = entry.getKey();
            Map<Integer, List<M_thp_dlp>> yearMap = entry.getValue();
            Map<String, Object> yearDataMap = new HashMap<>();
            yearDataMap.put("year", year);
            List<Map<String, Object>> monthDataList = new ArrayList<>();
            for (Map.Entry<Integer, List<M_thp_dlp>> yearEntry : yearMap.entrySet()) {
                int month = yearEntry.getKey();
                List<M_thp_dlp> monthData = yearEntry.getValue();
                Map<String, Object> monthDataMap = new HashMap<>();
                monthDataMap.put("month", month);
                monthDataMap.put("data", monthData);
                monthDataList.add(monthDataMap);
            }
            yearDataMap.put("monthData", monthDataList);
            resultList.add(yearDataMap);
        }

        return new Result<>(true, "success", resultList);
    }
    @CrossOrigin("*")
    @RequestMapping(path = "/getCompThpByYear")
    public Result<?> getCompThpByYear(Integer year) {
        return new Result<>(true,"success",companyService.getCompThpByYear(year));
    }




}
