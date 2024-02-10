package com.example.chapter6_wzy.Service.Teach.Impl;

import com.example.chapter6_wzy.Bean.Teach.Union.BerthTeachInfoWithCargoType;
import com.example.chapter6_wzy.Mapper.Teach.TeachBerthsMapper;
import com.example.chapter6_wzy.Service.Teach.TeachBerthsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author wsh
 */
@Service
public class TeachBerthsServiceImpl implements TeachBerthsService {
    @Autowired
    TeachBerthsMapper teachBerthsMapper;
    @Override
    public List<BerthTeachInfoWithCargoType> getBerthsByPort(int portId) {
        return teachBerthsMapper.getBerthsByPort(portId);
    }
}
