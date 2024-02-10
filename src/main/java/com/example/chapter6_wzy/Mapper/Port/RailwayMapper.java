package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Union.RailwayInfoWithPort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RailwayMapper {
    @Select("SELECT pi.`P_A_name`,ri.`Comp_name`,ri.`Railway_L`,\n" +
            "ri.`Load_unload_L`,ri.`Railway_DATC`,ri.`R_time` \n" +
            "FROM railway_info as ri \n" +
            "LEFT JOIN port_info as pi on pi.`P_A_code`=ri.`P_A_code`")
    List<RailwayInfoWithPort>selectAllRailwayInfo();

}
