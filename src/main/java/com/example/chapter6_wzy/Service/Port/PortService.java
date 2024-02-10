package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Port_info;
import com.example.chapter6_wzy.Bean.Port.Union.PortInfoWithPorta;

import java.util.List;

/**
 * @author wsh
 */
public interface PortService {
    List<Port_info> selectAllPortInfo();

    List<PortInfoWithPorta> selectPortInfoWithPorta();
}
