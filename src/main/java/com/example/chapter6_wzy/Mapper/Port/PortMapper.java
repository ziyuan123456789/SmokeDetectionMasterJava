package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Port_info;
import com.example.chapter6_wzy.Bean.Port.Union.PortInfoWithPorta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface PortMapper {

    @Select("SELECT * FROM port_info")
    List<Port_info> selectAllPortInfo();
    @Select("SELECT p1.`P_A_code`,p1.`P_A_name`, \n" +
            "p2.`totalArea`,p2.`LandArea`,p2.`WaterArea`,p2.`NumberOfBerths`,\n" +
            "p2.`LengthNCL`,p2.`R_time` FROM port_info as p1\n" +
            "LEFT JOIN porta_info as p2 ON p1.`P_A_code`=p2.`P_A_code` ")
    List<PortInfoWithPorta> selectPortInfoWithPorta();
}
