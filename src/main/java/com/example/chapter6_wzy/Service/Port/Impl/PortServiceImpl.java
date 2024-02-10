package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Port_info;
import com.example.chapter6_wzy.Bean.Port.Union.PortInfoWithPorta;
import com.example.chapter6_wzy.Mapper.Port.PortMapper;
import com.example.chapter6_wzy.Service.Port.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
/**
 * @author wsh
 */
public class PortServiceImpl implements PortService {
    @Autowired
    PortMapper portMapper;
    @Override
    public List<Port_info> selectAllPortInfo() {
        return portMapper.selectAllPortInfo();
    }

    @Override
    public List<PortInfoWithPorta> selectPortInfoWithPorta() {
        return portMapper.selectPortInfoWithPorta();
    }


}
