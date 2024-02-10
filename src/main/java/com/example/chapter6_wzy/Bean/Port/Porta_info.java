package com.example.chapter6_wzy.Bean.Port;


import jakarta.persistence.Column;
import org.springframework.data.annotation.Id;


import java.io.Serializable;


/**
 * @author wsh
 */
public class Porta_info {
	@Id
	private int id;
	private Integer p_a_code;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getP_a_code() {
		return p_a_code;
	}

	public void setP_a_code(Integer p_a_code) {
		this.p_a_code = p_a_code;
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
}
