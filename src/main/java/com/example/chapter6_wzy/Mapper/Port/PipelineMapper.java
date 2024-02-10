package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Union.PipelineInfoWithPortCom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface PipelineMapper {
    @Select("SELECT pi.`P_A_name`,\n" +
            "  ci.`Comp_name`,\n" +
            "  pip.Pipe_L,\n" +
            "  pip.Pipe_DATC,\n" +
            "  pip.Crude_pipe_L,\n" +
            "  pip.Crude_pipe_DATC,\n" +
            "  pip.P_oil_L,\n" +
            "  pip.P_oil_DATC,\n" +
            "  pip.Liq_gas_pipe_L,\n" +
            "  pip.Liq_gas_pipe_DATC,\n" +
            "  pip.Liq_pet_pipe_L,\n" +
            "  pip.Liq_pet_pipe_DATC,\n" +
            "  pip.Chemical_pipe_L,\n" +
            "  pip.Chemical_pipe_DATC,\n" +
            "  pip.Other_pipe,\n" +
            "  pip.Other_pipe_DATC,\n" +
            "  pip.R_time from pipeline_info as pip \n" +
            "LEFT JOIN port_info as pi on pip.`P_A_code`=pi.`P_A_code`\n" +
            "LEFT JOIN comp_info as ci on ci.`Comp_ID`=pip.`Comp_ID`\n" +
            "ORDER BY  pip.`ID` ")
    List<PipelineInfoWithPortCom> selectAllPipelineInfo();
}
