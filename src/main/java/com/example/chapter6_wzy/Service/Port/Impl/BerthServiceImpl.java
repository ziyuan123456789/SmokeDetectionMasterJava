package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Union.BerthInfoWithPortComp;
import com.example.chapter6_wzy.Mapper.Port.BerthMapper;
import com.example.chapter6_wzy.Service.Port.BerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BerthServiceImpl implements BerthService {
    @Autowired
    BerthMapper berthMapper;
    @Override
    public List<BerthInfoWithPortComp> getAllBerthInfo() {
        return berthMapper.getAllBerthInfo();
    }
}
