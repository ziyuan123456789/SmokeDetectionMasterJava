package com.example.chapter6_wzy.Bean.Port;


import org.springframework.data.annotation.Id;

public class M_thp_dlp {
	@Id
	private Integer date;
	private Float cargo_thp;
	private Float f_t_thp;
	private Float d_t_thp;
	private Float conr_thp;
	private Float f_t_conr;
	private Float d_t_conr;
	private Float coal;
	private Float crude;
	private Float p_oil;
	private Float ironstone;
	private Float steel;
	private Float grain;
	private Float machineryE;
	private Float chemicals;
	private Float conr_weight;
	private Float ro_thp;
	private Float pger_thp;
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Float getCargo_thp() {	
		return FormatData(cargo_thp);
	}
	public void setCargo_thp(Float cargo_thp) {
		this.cargo_thp = cargo_thp;
	}
	public Float getF_t_thp() {
		return FormatData(f_t_thp);
	}
	public void setF_t_thp(Float f_t_thp) {
		this.f_t_thp = f_t_thp;
	}
	public Float getD_t_thp() {
		return FormatData(d_t_thp);
	}
	public void setD_t_thp(Float d_t_thp) {
		this.d_t_thp = d_t_thp;
	}
	public Float getConr_thp() {
		return FormatData(conr_thp);
	}
	public void setConr_thp(Float conr_thp) {
		this.conr_thp = conr_thp;
	}
	public Float getF_t_conr() {
		return FormatData(f_t_conr);
	}
	public void setF_t_conr(Float f_t_conr) {
		this.f_t_conr = f_t_conr;
	}
	public Float getD_t_conr() {
		return FormatData(d_t_conr);
	}
	public void setD_t_conr(Float d_t_conr) {
		this.d_t_conr = d_t_conr;
	}
	public Float getCoal() {
		return FormatData(coal);
	}
	public void setCoal(Float coal) {
		this.coal = coal;
	}
	public Float getCrude() {
		return FormatData(crude);
	}
	public void setCrude(Float crude) {
		this.crude = crude;
	}
	public Float getP_oil() {
		return FormatData(p_oil);
	}
	public void setP_oil(Float p_oil) {
		this.p_oil = p_oil;
	}
	public Float getIronstone() {
		return FormatData(ironstone);
	}
	public void setIronstone(Float ironstone) {
		this.ironstone = ironstone;
	}
	public Float getSteel() {
		return FormatData(steel);
	}
	public void setSteel(Float steel) {
		this.steel = steel;
	}
	public Float getGrain() {
		return FormatData(grain);
	}
	public void setGrain(Float grain) {
		this.grain = grain;
	}
	public Float getMachineryE() {
		return FormatData(machineryE);
	}
	public void setMachineryE(Float machineryE) {
		this.machineryE = machineryE;
	}
	public Float getChemicals() {
		return FormatData(chemicals);
	}
	public void setChemicals(Float chemicals) {
		this.chemicals = chemicals;
	}
	public Float getConr_weight() {
		return FormatData(conr_weight);
	}
	public void setConr_weight(Float conr_weight) {
		this.conr_weight = conr_weight;
	}
	public Float getRo_thp() {
		return FormatData(ro_thp);
	}
	public void setRo_thp(Float ro_thp) {
		this.ro_thp = ro_thp;
	}
	public Float getPger_thp() {
		return FormatData(pger_thp);
	}
	public void setPger_thp(Float pger_thp) {
		this.pger_thp = pger_thp;
	}
	
	public float FormatData(Float f)
	{
		return (float)(Math.round(f*10))/10;
	}

}
