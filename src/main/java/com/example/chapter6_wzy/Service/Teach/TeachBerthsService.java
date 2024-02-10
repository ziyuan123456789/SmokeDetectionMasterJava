package com.example.chapter6_wzy.Service.Teach;

import com.example.chapter6_wzy.Bean.Teach.Union.BerthTeachInfoWithCargoType;

import java.util.List;

/**
 * @author wsh
 */
public interface TeachBerthsService {
    List<BerthTeachInfoWithCargoType> getBerthsByPort(int portId);
}
