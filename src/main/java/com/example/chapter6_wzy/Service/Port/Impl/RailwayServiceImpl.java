package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Union.RailwayInfoWithPort;
import com.example.chapter6_wzy.Mapper.Port.RailwayMapper;
import com.example.chapter6_wzy.Service.Port.RailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author wsh
 */
@Service
public class RailwayServiceImpl implements RailwayService {
    @Autowired
    RailwayMapper railwayMapper;
    @Override
    public List<RailwayInfoWithPort> selectAllRailwayInfo() {
        return railwayMapper.selectAllRailwayInfo();
    }
}
