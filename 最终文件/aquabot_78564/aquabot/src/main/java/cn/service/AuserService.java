package cn.service;

import java.util.List;
import java.util.Map;

import cn.entity.Auser;
import cn.entity.WaterQuality;

public interface AuserService {
	public List<Auser> userList();
	
	public List<WaterQuality> waterList(String mac);
	
	public Map<String, String> goodMapForAllData();
	public Map<String, String> getBadLocation();
}
