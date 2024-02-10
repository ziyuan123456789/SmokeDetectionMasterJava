package com.example.chapter6_wzy.Bean.Port.Union;

import org.springframework.data.annotation.Id;

/**
 * @author wsh
 */
public class RailwayInfoWithPort {
    private String  p_a_name;
    private String  Comp_name;
    private Integer railway_L;
    private Integer load_unload_L;
    private Integer railway_DATC;
    private String r_time;

    public String getP_a_name() {
        return p_a_name;
    }

    public void setP_a_name(String p_a_name) {
        this.p_a_name = p_a_name;
    }

    public String getComp_name() {
        return Comp_name;
    }

    public void setComp_name(String comp_name) {
        Comp_name = comp_name;
    }

    public Integer getRailway_L() {
        return railway_L;
    }

    public void setRailway_L(Integer railway_L) {
        this.railway_L = railway_L;
    }

    public Integer getLoad_unload_L() {
        return load_unload_L;
    }

    public void setLoad_unload_L(Integer load_unload_L) {
        this.load_unload_L = load_unload_L;
    }

    public Integer getRailway_DATC() {
        return railway_DATC;
    }

    public void setRailway_DATC(Integer railway_DATC) {
        this.railway_DATC = railway_DATC;
    }

    public String getR_time() {
        return r_time;
    }

    public void setR_time(String r_time) {
        this.r_time = r_time;
    }
}
