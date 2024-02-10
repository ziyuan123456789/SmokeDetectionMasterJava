package com.example.chapter6_wzy.Bean.Port;


import org.springframework.data.annotation.Id;

public class Anchorage_info {
	@Id

	private Integer id;

	private String anc_name;

	private String anc_app;

	private String anc_dep;
	
	private Float anc_a;
	
	private Integer mooring_buoy_N;
	
	private Integer anc_ton;
	
	private Integer anc_ber_N;
	
	private String anc_sedi;
	
	private String p_s_code;
	
	private String p_s_names;
	
	private Integer r_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnc_name() {
		return anc_name;
	}

	public void setAnc_name(String anc_name) {
		this.anc_name = anc_name;
	}

	public String getAnc_app() {
		return anc_app;
	}

	public void setAnc_app(String anc_app) {
		this.anc_app = anc_app;
	}

	public String getAnc_dep() {
		return anc_dep;
	}

	public void setAnc_dep(String anc_dep) {
		this.anc_dep = anc_dep;
	}

	public Float getAnc_a() {
		return anc_a;
	}

	public void setAnc_a(Float anc_a) {
		this.anc_a = anc_a;
	}

	public Integer getMooring_buoy_N() {
		return mooring_buoy_N;
	}

	public void setMooring_buoy_N(Integer mooring_buoy_N) {
		this.mooring_buoy_N = mooring_buoy_N;
	}

	public Integer getAnc_ton() {
		return anc_ton;
	}

	public void setAnc_ton(Integer anc_ton) {
		this.anc_ton = anc_ton;
	}

	public Integer getAnc_ber_N() {
		return anc_ber_N;
	}

	public void setAnc_ber_N(Integer anc_ber_N) {
		this.anc_ber_N = anc_ber_N;
	}

	public String getAnc_sedi() {
		return anc_sedi;
	}

	public void setAnc_sedi(String anc_sedi) {
		this.anc_sedi = anc_sedi;
	}

	public String getP_s_code() {
		return p_s_code;
	}

	public void setP_s_code(String p_s_code) {
		this.p_s_code = p_s_code;
	}

	public Integer getR_time() {
		return r_time;
	}

	public void setR_time(Integer r_time) {
		this.r_time = r_time;
	}

	public String getP_s_names() {
		return p_s_names;
	}

	public void setP_s_names(String p_s_names) {
		this.p_s_names = p_s_names;
	}

	
}
