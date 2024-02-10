package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Union.PipelineInfoWithPortCom;
import com.example.chapter6_wzy.Mapper.Port.PipelineMapper;
import com.example.chapter6_wzy.Service.Port.PipelineSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PipelineServiceImpl implements PipelineSerice {
    @Autowired
    PipelineMapper pipelineMapper;
    @Override
    public List<PipelineInfoWithPortCom> selectAllPipelineInfo() {
        return pipelineMapper.selectAllPipelineInfo();
    }
}
