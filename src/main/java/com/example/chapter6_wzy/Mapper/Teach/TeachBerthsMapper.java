package com.example.chapter6_wzy.Mapper.Teach;

import com.example.chapter6_wzy.Bean.Teach.Union.BerthTeachInfoWithCargoType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface TeachBerthsMapper {
    @Select("SELECT tb.*,tct.`cargoType` as type FROM teach_berth AS tb \n" +
            "LEFT JOIN teach_cargo_type AS tct ON tb.cargotypeid=tct.`cargoTypeID`  where portid=#{portId}")
    List<BerthTeachInfoWithCargoType> getBerthsByPort(int portId);
}
