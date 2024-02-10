package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Union.ChannelInfoWithPort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelMapper {
    @Select("SELECT ci.`Chan_name`,ci.Chan_S_P,ci.Chan_F_P,ci.Chan_L,ci.Tons_by_tide,ci.Tons_no_tide,\n" +
            "ci.Chan_1_2,ci.Chan_dep,ci.Chan_wid,ci.Tide_time,\n" +
            "ci.Tide_level,ci.Chan_sedi,pi.P_A_name,ci.R_time\n" +
            "FROM channel_info as ci \n" +
            "LEFT JOIN port_info as pi on pi.`P_A_code`=ci.`P_S_code`")
    List<ChannelInfoWithPort>getAllChannelInfo();

}
