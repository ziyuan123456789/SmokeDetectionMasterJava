package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Union.BerthInfoWithPortComp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface BerthMapper {
    @Select("SELECT bi.`ID`,pi.`P_A_name`,bi.`Berth_name`,ci.`Comp_name`,bi.`Berth_type`,bi.`Cargo_Type`, \n" +
            "bi.`Design_dep`,bi.`Maintain_dep`,bi.`Berth_L`,bi.`Design_ber_ton`,bi.`Bulk_genral_DATC`,\n" +
            "bi.`Conr_DATC`,bi.`Pger_DATC`,bi.`R_time`\n" +
            "FROM berth_info as bi\n" +
            "LEFT JOIN port_info as pi on pi.`P_A_code`=bi.`P_A_code`\n" +
            "LEFT JOIN comp_info as ci on ci.`Comp_ID`=bi.`Comp_ID`\n" +
            "ORDER BY bi.`ID`")
    List<BerthInfoWithPortComp> getAllBerthInfo();
}
