package com.example.chapter6_wzy.Bean.Port;


import org.springframework.data.annotation.Id;

public class Railway_info {
	
	@Id
	private Integer id;
	private Integer p_a_code;
	private String comp_ID;
	private Integer railway_L;
	private Integer load_unload_L;
	private Integer railway_DATC;
	private String r_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getP_a_code() {
		return p_a_code;
	}

	public void setP_a_code(Integer p_a_code) {
		this.p_a_code = p_a_code;
	}

	public String getComp_ID() {
		return comp_ID;
	}

	public void setComp_ID(String comp_ID) {
		this.comp_ID = comp_ID;
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
