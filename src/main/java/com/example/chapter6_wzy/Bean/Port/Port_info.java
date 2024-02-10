package com.example.chapter6_wzy.Bean.Port;


import nonapi.io.github.classgraph.json.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Port_info {
	
	@Id
	private Integer id;
	private Integer p_a_code;
	private String  p_a_name;

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

	public String getP_a_name() {
		return p_a_name;
	}

	public void setP_a_name(String p_a_name) {
		this.p_a_name = p_a_name;
	}
}
