package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Union.RailwayInfoWithPort;

import java.util.List;

public interface RailwayService {
    List<RailwayInfoWithPort> selectAllRailwayInfo();
}
