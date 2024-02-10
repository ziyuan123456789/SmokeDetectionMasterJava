package com.example.chapter6_wzy.Service.Teach.Impl;

import com.example.chapter6_wzy.Bean.Teach.Port_info_teach;
import com.example.chapter6_wzy.Mapper.Teach.TeachPortMapper;
import com.example.chapter6_wzy.Service.Teach.PortTeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsh
 */
@Service
public class PortTeachServiceImpl implements PortTeachService {
    @Autowired
    TeachPortMapper teachPortMapper;
    @Override
    public List<Port_info_teach> selectAllPortInfo() {
        return teachPortMapper.selectAllPortInfo();
    }
}
