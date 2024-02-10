package com.example.chapter6_wzy.Service.Port.Impl;

import com.example.chapter6_wzy.Bean.Port.Anchorage_info;
import com.example.chapter6_wzy.Bean.Port.Port_info;
import com.example.chapter6_wzy.Service.Port.AnchorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsh
 */
@Service
public class AnchorageServiceImpl implements AnchorageService {
    @Autowired
    JdbcTemplate jt;

    @Override
    public List<Anchorage_info> getAnchorageList() {
        List<Anchorage_info> list = jt.query("select * from Anchorage_info",
                new BeanPropertyRowMapper<Anchorage_info>(Anchorage_info.class));
        List<Port_info> list2 = jt.query("select * from Port_info",
                new BeanPropertyRowMapper<Port_info>(Port_info.class));
        for (Anchorage_info anchorageInfo : list) {
            StringBuilder p_s_names = new StringBuilder();
            String p_s_code = anchorageInfo.getP_s_code();// 获取服务港区的代码
            String[] p_s_codes = p_s_code.trim().split(";");
            for (String code : p_s_codes) {
                for (Port_info p : list2) {
                    if (p.getP_a_code().toString().equals(code)) {
                        if (p_s_names.length() == 0) {
                            p_s_names.append(p.getP_a_name());
                        } else {
                            p_s_names.append(";").append(p.getP_a_name());
                        }
                    }

                }
            }
            anchorageInfo.setP_s_names(p_s_names.toString());
        }
        return list;
}}
