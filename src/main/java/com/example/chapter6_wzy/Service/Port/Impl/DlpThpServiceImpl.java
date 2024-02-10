package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.M_thp_dlp;
import com.example.chapter6_wzy.Mapper.Port.M_thp_dlpMapper;
import com.example.chapter6_wzy.Service.Port.DlpThpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DlpThpServiceImpl implements DlpThpService {
    @Autowired
    M_thp_dlpMapper mThpDlpMapper;
    @Override
    public List<M_thp_dlp> getDlpThpByYear(Integer year) {
        List<M_thp_dlp> list = mThpDlpMapper.findThpByYear(year);
        M_thp_dlp sum = new M_thp_dlp();
        sum.setCargo_thp(0.0f);
        sum.setChemicals(0.0f);
        sum.setCoal(0.0f);
        sum.setConr_thp(0.0f);
        sum.setConr_weight(0.0f);
        sum.setCrude(0.0f);
        sum.setD_t_conr(0.0f);
        sum.setD_t_thp(0.0f);
        sum.setF_t_conr(0.0f);
        sum.setF_t_thp(0.0f);
        sum.setGrain(0.0f);
        sum.setIronstone(0.0f);
        sum.setMachineryE(0.0f);
        sum.setP_oil(0.0f);
        sum.setPger_thp(0.0f);
        sum.setRo_thp(0.0f);
        sum.setSteel(0.0f);
        for (M_thp_dlp mThpDlp : list) {
            sum.setCargo_thp(sum.getCargo_thp() + mThpDlp.getCargo_thp());
            sum.setChemicals(sum.getChemicals() + mThpDlp.getChemicals());
            sum.setCoal(sum.getCoal() + mThpDlp.getCoal());
            sum.setConr_thp(sum.getConr_thp() + mThpDlp.getConr_thp());
            sum.setConr_weight(sum.getConr_weight() + mThpDlp.getConr_weight());
            sum.setCrude(sum.getCrude() + mThpDlp.getCrude());
            sum.setD_t_conr(sum.getD_t_conr() + mThpDlp.getD_t_conr());
            sum.setD_t_thp(sum.getD_t_thp() + mThpDlp.getD_t_thp());
            sum.setF_t_conr(sum.getF_t_conr() + mThpDlp.getF_t_conr());
            sum.setF_t_thp(sum.getF_t_thp() + mThpDlp.getF_t_thp());
            sum.setGrain(sum.getGrain() + mThpDlp.getGrain());
            sum.setIronstone(sum.getIronstone() + mThpDlp.getIronstone());
            sum.setMachineryE(sum.getMachineryE() + mThpDlp.getMachineryE());
            sum.setP_oil(sum.getP_oil() + mThpDlp.getP_oil());
            sum.setPger_thp(sum.getPger_thp() + mThpDlp.getPger_thp());
            sum.setRo_thp(sum.getRo_thp() + mThpDlp.getRo_thp());
            sum.setSteel(sum.getSteel() + mThpDlp.getSteel());
        }
        sum.setDate(year);
        list.add(sum);
        return list;
    }

    @Override
    public List<M_thp_dlp> findAllThp() {
        return mThpDlpMapper.findAllThp();
    }
}
