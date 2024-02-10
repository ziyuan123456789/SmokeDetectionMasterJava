package com.example.chapter6_wzy.Mapper.Port;

import com.example.chapter6_wzy.Bean.Port.M_thp_dlp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wsh
 */
@Mapper
public interface M_thp_dlpMapper {
    @Select("SELECT * FROM m_thp_dlp WHERE FLOOR(date/100) = #{year}")
    List<M_thp_dlp> findThpByYear(Integer year);
    @Select("SELECT * FROM m_thp_dlp ")
    List<M_thp_dlp> findAllThp();
}
