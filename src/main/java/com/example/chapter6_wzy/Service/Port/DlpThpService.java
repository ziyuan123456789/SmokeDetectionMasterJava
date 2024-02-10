package com.example.chapter6_wzy.Service.Port;

import com.example.chapter6_wzy.Bean.Port.M_thp_dlp;

import java.util.List;

/**
 * @author wsh
 */
public interface DlpThpService {
    List<M_thp_dlp> getDlpThpByYear(Integer year);
    List<M_thp_dlp> findAllThp();
}
