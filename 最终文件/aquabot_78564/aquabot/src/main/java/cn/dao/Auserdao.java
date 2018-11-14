package cn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import cn.entity.Auser;
import cn.entity.WaterQuality;

public interface Auserdao {
	@Select("select * from auser")
	public List<Auser> userList();
	
	@Select("select * from water_quality where mac = #{mac}")
	public List<WaterQuality> waterList(String mac);
	
	@SelectProvider(type=sqlprivatebean.class,method="goodMapForAllData")
	public Map<String, String> goodMapForAllData();
	
	@SelectProvider(type=sqlprivatebean.class,method="getBadLocation")
	public Map<String, String> getBadLocation();
	

}
