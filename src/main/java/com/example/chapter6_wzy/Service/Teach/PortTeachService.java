package com.example.chapter6_wzy.Service.Teach;

import com.example.chapter6_wzy.Bean.Teach.Port_info_teach;

import java.util.List;

/**
 * @author wsh
 */
public interface PortTeachService {
    List<Port_info_teach> selectAllPortInfo();
}
