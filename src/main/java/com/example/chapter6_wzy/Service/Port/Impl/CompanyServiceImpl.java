package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Comp_info;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYear;
import com.example.chapter6_wzy.Bean.Port.CompanyThroughput_Info_ByYearAndId;
import com.example.chapter6_wzy.Mapper.Port.CompanyMapper;
import com.example.chapter6_wzy.Service.Port.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;
    @Override
    public List<Comp_info> selectAllCompanyInfo() {
        return companyMapper.selectAllCompanyInfo();
    }

    @Override
    public List<CompanyThroughput_Info_ByYearAndId> getCompanyThroughputByComIdAndYear(String comId, Integer year) {
        return companyMapper.getCompanyThroughputByComIdAndYear(comId,year);
    }

    @Override
    public List<CompanyThroughput_Info_ByYear> getCompThpByYear(Integer year) {
        return companyMapper.getCompThpByYear(year);
    }
}
