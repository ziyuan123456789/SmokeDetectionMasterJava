package com.example.chapter6_wzy.Bean.Port;


import org.springframework.data.annotation.Id;

public class Berth_info {
	@Id
	public Integer id;

	public Integer p_a_code;

	private String berth_name;


	private String comp_id;
	
	private Integer berth_type;
	
	private Integer cargo_type;
	
	private Float design_dep;
	
	private Float maintain_dep;
	
	private Float berth_L;
	
	private Integer design_ber_ton;
	
	private Float bulk_genral_DATC;
	
	private Float conr_DATC;
	

	
	private Float pger_DATC;
	
	private Integer r_time;

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

	public String getBerth_name() {
		return berth_name;
	}

	public void setBerth_name(String berth_name) {
		this.berth_name = berth_name;
	}

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public Integer getBerth_type() {
		return berth_type;
	}

	public void setBerth_type(Integer berth_type) {
		this.berth_type = berth_type;
	}

	public Integer getCargo_type() {
		return cargo_type;
	}

	public void setCargo_type(Integer cargo_type) {
		this.cargo_type = cargo_type;
	}

	public Float getDesign_dep() {
		return design_dep;
	}

	public void setDesign_dep(Float design_dep) {
		this.design_dep = design_dep;
	}

	public Float getMaintain_dep() {
		return maintain_dep;
	}

	public void setMaintain_dep(Float maintain_dep) {
		this.maintain_dep = maintain_dep;
	}

	public Float getBerth_L() {
		return berth_L;
	}

	public void setBerth_L(Float berth_L) {
		this.berth_L = berth_L;
	}

	public Integer getDesign_ber_ton() {
		return design_ber_ton;
	}

	public void setDesign_ber_ton(Integer design_ber_ton) {
		this.design_ber_ton = design_ber_ton;
	}

	public Float getBulk_genral_DATC() {
		return bulk_genral_DATC;
	}

	public void setBulk_genral_DATC(Float bulk_genral_DATC) {
		this.bulk_genral_DATC = bulk_genral_DATC;
	}

	public Float getConr_DATC() {
		return conr_DATC;
	}

	public void setConr_DATC(Float conr_DATC) {
		this.conr_DATC = conr_DATC;
	}



	public Float getPger_DATC() {
		return pger_DATC;
	}

	public void setPger_DATC(Float pger_DATC) {
		this.pger_DATC = pger_DATC;
	}

	public Integer getR_time() {
		return r_time;
	}

	public void setR_time(Integer r_time) {
		this.r_time = r_time;
	}
}
