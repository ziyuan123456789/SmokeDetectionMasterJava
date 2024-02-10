package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.Comp_info;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYear;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYearAndId;

import java.util.List;

public interface CompanyService {
    List<Comp_info> selectAllCompanyInfo();
    List<CompanyThroughput_Info_ByYearAndId>getCompanyThroughputByComIdAndYear(String comId, Integer year);
    List<CompanyThroughput_Info_ByYear>getCompThpByYear(Integer year);
}
