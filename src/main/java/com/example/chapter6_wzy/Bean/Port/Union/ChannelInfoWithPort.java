package com.example.chapter6_wzy.Bean.Port.Union;

import org.springframework.data.annotation.Id;

/**
 * @author wsh
 */
public class ChannelInfoWithPort {

    private String chan_name;
    private String chan_S_P;
    private String chan_F_P;
    private Float chan_L;
    private String tons_by_tide;
    private String tons_no_tide;
    private Integer chan_1_2;
    private String chan_dep;
    private String chan_wid;
    private String tide_time;
    private String tide_level;
    private Integer chan_sedi;
    private String  p_a_name;
    private Integer r_time;


    public String getChan_name() {
        return chan_name;
    }

    public void setChan_name(String chan_name) {
        this.chan_name = chan_name;
    }

    public String getChan_S_P() {
        return chan_S_P;
    }

    public void setChan_S_P(String chan_S_P) {
        this.chan_S_P = chan_S_P;
    }

    public String getChan_F_P() {
        return chan_F_P;
    }

    public void setChan_F_P(String chan_F_P) {
        this.chan_F_P = chan_F_P;
    }

    public Float getChan_L() {
        return chan_L;
    }

    public void setChan_L(Float chan_L) {
        this.chan_L = chan_L;
    }

    public String getTons_by_tide() {
        return tons_by_tide;
    }

    public void setTons_by_tide(String tons_by_tide) {
        this.tons_by_tide = tons_by_tide;
    }

    public String getTons_no_tide() {
        return tons_no_tide;
    }

    public void setTons_no_tide(String tons_no_tide) {
        this.tons_no_tide = tons_no_tide;
    }

    public Integer getChan_1_2() {
        return chan_1_2;
    }

    public void setChan_1_2(Integer chan_1_2) {
        this.chan_1_2 = chan_1_2;
    }

    public String getChan_dep() {
        return chan_dep;
    }

    public void setChan_dep(String chan_dep) {
        this.chan_dep = chan_dep;
    }

    public String getChan_wid() {
        return chan_wid;
    }

    public void setChan_wid(String chan_wid) {
        this.chan_wid = chan_wid;
    }

    public String getTide_time() {
        return tide_time;
    }

    public void setTide_time(String tide_time) {
        this.tide_time = tide_time;
    }

    public String getTide_level() {
        return tide_level;
    }

    public void setTide_level(String tide_level) {
        this.tide_level = tide_level;
    }

    public Integer getChan_sedi() {
        return chan_sedi;
    }

    public void setChan_sedi(Integer chan_sedi) {
        this.chan_sedi = chan_sedi;
    }

    public String getP_a_name() {
        return p_a_name;
    }

    public void setP_a_name(String p_a_name) {
        this.p_a_name = p_a_name;
    }

    public Integer getR_time() {
        return r_time;
    }

    public void setR_time(Integer r_time) {
        this.r_time = r_time;
    }


}
