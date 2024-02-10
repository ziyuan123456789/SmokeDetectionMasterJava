package com.example.chapter6_wzy.Bean.Teach.Union;





/**
 * @author wsh
 */
public class BerthTeachInfoWithCargoType {



	private int berthid;
	private int cargoTypeID;
	private int tonnage;
	private int number;
	private int portid;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	public int getBerthid() {
		return berthid;
	}

	public void setBerthid(int berthid) {
		this.berthid = berthid;
	}

	public int getCargoTypeID() {
		return cargoTypeID;
	}

	public void setCargoTypeID(int cargoTypeID) {
		this.cargoTypeID = cargoTypeID;
	}

	public int getTonnage() {
		return tonnage;
	}

	public void setTonnage(int tonnage) {
		this.tonnage = tonnage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPortid() {
		return portid;
	}

	public void setPortid(int portid) {
		this.portid = portid;
	}
}
