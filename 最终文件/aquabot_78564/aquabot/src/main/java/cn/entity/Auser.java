package cn.entity;

import java.io.Serializable;

public class Auser implements Serializable{
	private int u_id;
	private String u_lat;
	private String u_lng;
	
	private String u_mac;
	private String u_status;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_lat() {
		return u_lat;
	}
	public void setU_lat(String u_lat) {
		this.u_lat = u_lat;
	}
	public String getU_lng() {
		return u_lng;
	}
	public void setU_lng(String u_lng) {
		this.u_lng = u_lng;
	}
	public String getU_mac() {
		return u_mac;
	}
	public void setU_mac(String u_mac) {
		this.u_mac = u_mac;
	}
	public String getU_status() {
		return u_status;
	}
	public void setU_status(String u_status) {
		this.u_status = u_status;
	}

}
