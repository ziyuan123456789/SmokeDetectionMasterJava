package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.Comp_info;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYear;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYearAndId;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface CompanyMapper {
    @Select("select * from comp_info")
    List<Comp_info>selectAllCompanyInfo();

    @Select("SELECT sc.Date as date,sum(sc.Cargo_thp) as total FROM m_thp_comp as sc where sc.Comp_ID=#{comId} and SUBSTR(sc.Date, 1, 4) =#{year} GROUP BY sc.Date")
    List<CompanyThroughput_Info_ByYearAndId>getCompanyThroughputByComIdAndYear(String comId, Integer year);
    @Select("SELECT  ci.`Comp_name` as comname, SUM(mc.`Cargo_thp`) as total\n" +
            "FROM m_thp_comp AS mc\n" +
            "LEFT JOIN comp_info AS ci ON ci.`Comp_ID` = mc.`Comp_ID` \n" +
            "WHERE SUBSTR(mc.`Date`, 1, 4) = #{year}\n" +
            "GROUP BY ci.`Comp_name`")
    List<CompanyThroughput_Info_ByYear>getCompThpByYear(Integer year);


}
