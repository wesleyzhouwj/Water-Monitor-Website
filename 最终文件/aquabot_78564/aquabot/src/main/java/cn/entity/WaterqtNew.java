package cn.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class WaterqtNew {
	 @ApiModelProperty(value = "id")
	   private int id; //
	   @ApiModelProperty(value = "设备蓝牙编码")
	   private String mac; //
	   @ApiModelProperty(value = "经度")
	   private String lng; //经度
	   @ApiModelProperty(value = "纬度")
	   private String lat; //纬度
	   @ApiModelProperty(value = "地址")
	   private String address; //ַ
	   @ApiModelProperty(value = "tds")
	   private String tds; //tds
	   @ApiModelProperty(value = "cl")
	   private String cl; //cl
	   @ApiModelProperty(value = "phֵ")
	   private String ph; //phֵ
	   @ApiModelProperty(value = "orp")
	   private String orp; //mV
	   @ApiModelProperty(value = "温度")
	   private String temperature; //
	   @ApiModelProperty(value = "设备记录时间")
	   private String createTime; //设备记录时间
	   @ApiModelProperty(value = "数据存储到服务器时间")
	   private String createDate; //数据存储到服务器时间
	   
	   @ApiModelProperty(value = "数据好坏，1：good,2:bad")
	   private Long status;
	   
	   @ApiModelProperty(value = "数据等级，1 >2")
	   private String slevel;
	   
	   @ApiModelProperty(value = "二级数据设备创建时间")
	   private String screateTime; //


	public String getScreateTime() {
		return screateTime;
	}

	public void setScreateTime(String screateTime) {
		this.screateTime = screateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getOrp() {
		return orp;
	}

	public void setOrp(String orp) {
		this.orp = orp;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getSlevel() {
		return slevel;
	}

	public void setSlevel(String slevel) {
		this.slevel = slevel;
	}
}
