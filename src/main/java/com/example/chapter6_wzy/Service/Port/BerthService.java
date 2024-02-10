package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Union.BerthInfoWithPortComp;

import java.util.List;

/**
 * @author wsh
 */
public interface BerthService {
    List<BerthInfoWithPortComp> getAllBerthInfo();
}
