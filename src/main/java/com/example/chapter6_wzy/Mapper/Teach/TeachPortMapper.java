package com.example.chapter6_wzy.Mapper.Teach;

import com.example.chapter6_wzy.Bean.Teach.Port_info_teach;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface TeachPortMapper {
    @Select("select * from teach_port")
    List<Port_info_teach> selectAllPortInfo();

}
