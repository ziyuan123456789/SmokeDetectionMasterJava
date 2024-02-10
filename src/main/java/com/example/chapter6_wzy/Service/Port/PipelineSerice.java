package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Union.PipelineInfoWithPortCom;

import java.util.List;

public interface PipelineSerice {
    List<PipelineInfoWithPortCom> selectAllPipelineInfo();
}
