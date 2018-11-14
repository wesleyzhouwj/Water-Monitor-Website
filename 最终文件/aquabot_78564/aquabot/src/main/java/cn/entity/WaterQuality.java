package cn.entity;

import java.util.Date;

import org.apache.commons.codec.language.bm.Lang;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class WaterQuality {
    
   @ApiModelProperty(value = "id")
   private Long id; //
   @ApiModelProperty(value = "�豸����")
   private String mac; //�豸����
   @ApiModelProperty(value = "����")
   private String lng; //����
   @ApiModelProperty(value = "γ��")
   private String lat; //γ��
   @ApiModelProperty(value = "��ַ")
   private String address; //��ַ
   @ApiModelProperty(value = "��λppm")
   private String tds; //ppm
   @ApiModelProperty(value = "���ȣ�mg/L��")
   private String cl; //���ȣ�mg/L��
   @ApiModelProperty(value = "phֵ")
   private String ph; //phֵ
   @ApiModelProperty(value = "��λmV")
   private String orp; //mV
   @ApiModelProperty(value = "�¶�")
   private String temperature; //�¶�
   @ApiModelProperty(value = "����ʱ��")
   private Date createTime; //����ʱ��
   @ApiModelProperty(value = "��������")
   private Date createDate; //��������
   
   private Long status;
	
   public Long getStatus() {
	return status;
}

public void setStatus(Long status) {
	this.status = status;
}

public Long getId(){  
       return id;  
   }
     
  public void setId(Long id){  
    this.id = id;  
   }  
   public String getMac(){  
       return mac;  
   }
     
  public void setMac(String mac){  
    this.mac = mac;  
   }  
   public String getLng(){  
       return lng;  
   }
     
  public void setLng(String lng){  
    this.lng = lng;  
   }  
   public String getLat(){  
       return lat;  
   }
     
  public void setLat(String lat){  
    this.lat = lat;  
   }  
   public String getAddress(){  
       return address;  
   }
     
  public void setAddress(String address){  
    this.address = address;  
   }  
   public String getTds(){  
       return tds;  
   }
     
  public void setTds(String tds){  
    this.tds = tds;  
   }  
   public String getCl(){  
       return cl;  
   }
     
  public void setCl(String cl){  
    this.cl = cl;  
   }  
   public String getPh(){  
       return ph;  
   }
     
  public void setPh(String ph){  
    this.ph = ph;  
   }  
   public String getOrp(){  
       return orp;  
   }
     
  public void setOrp(String orp){  
    this.orp = orp;  
   }  
   public String getTemperature(){  
       return temperature;  
   }
     
  public void setTemperature(String temperature){  
    this.temperature = temperature;  
   }  
  
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   public Date getCreateTime(){  
       return createTime;  
   }
     
  public void setCreateTime(Date createTime){   
    this.createTime = createTime;  
   }  
  
  @DateTimeFormat(pattern="yyyy-MM-dd")
  @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
   public Date getCreateDate(){  
       return createDate;  
   }
     
  public void setCreateDate(Date createDate){  
    this.createDate = createDate;  
   }  
}
