package cn.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.Auserdao;
import cn.dao.IWaterQualityDao;
import cn.entity.Auser;
import cn.entity.WaterQuality;
import cn.service.AuserService;
@Service
public class Auserserviceimpl implements AuserService {

	
	@Autowired
	private Auserdao auserdao;
	public List<Auser> userList() {
	List<Auser> temulist=auserdao.userList();
		return temulist;
	}

	public List<WaterQuality> waterList(String mac) {
		// TODO Auto-generated method stub
		List<WaterQuality> waterLiss = auserdao.waterList(mac);
		return waterLiss;
	}

	public Map<String, String> goodMapForAllData() {
		
		return auserdao.goodMapForAllData();
	}

	public Map<String, String> getBadLocation() {
		
		return auserdao.getBadLocation();
	}

}
