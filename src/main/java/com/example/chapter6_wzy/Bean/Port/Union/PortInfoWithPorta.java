package com.example.chapter6_wzy.Bean.Port.Union;

import nonapi.io.github.classgraph.json.Id;
import jakarta.persistence.Column;
/**
 * @author wsh
 */
public class PortInfoWithPorta {
    public Integer getP_a_code() {
        return p_a_code;
    }

    public void setP_a_code(Integer p_a_code) {
        this.p_a_code = p_a_code;
    }

    public String getP_a_name() {
        return p_a_name;
    }

    public void setP_a_name(String p_a_name) {
        this.p_a_name = p_a_name;
    }

    public float getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(float totalArea) {
        this.totalArea = totalArea;
    }

    public float getLandArea() {
        return landArea;
    }

    public void setLandArea(float landArea) {
        this.landArea = landArea;
    }

    public float getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(float waterArea) {
        this.waterArea = waterArea;
    }

    public int getNumberOfBerths() {
        return numberOfBerths;
    }

    public void setNumberOfBerths(int numberOfBerths) {
        this.numberOfBerths = numberOfBerths;
    }

    public int getLengthNCL() {
        return lengthNCL;
    }

    public void setLengthNCL(int lengthNCL) {
        this.lengthNCL = lengthNCL;
    }

    public int getR_time() {
        return r_time;
    }

    public void setR_time(int r_time) {
        this.r_time = r_time;
    }

    private Integer p_a_code;
    private String  p_a_name;

    @Column(name="totalArea")
    private float totalArea;
    @Column(name="LandArea")
    private float landArea;
    @Column(name="WaterArea")
    private float waterArea;
    @Column(name="NumberOfBerths")
    private int numberOfBerths;
    @Column(name="LengthNCL")
    private int lengthNCL;
    @Column(name="R_time")
    private int r_time;
}
