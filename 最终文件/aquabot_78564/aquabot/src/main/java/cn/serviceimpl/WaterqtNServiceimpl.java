package cn.serviceimpl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

//import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.HistoryCVDao;
import cn.dao.WaterqtNewDao;
import cn.entity.HistoryCV;
import cn.entity.LatestSTCV;
import cn.entity.WaterqtNew;
import cn.service.WaterqtNService;
@Service
public class WaterqtNServiceimpl implements WaterqtNService {
	@Autowired
	WaterqtNewDao waterqtNewDao;
	
	@Autowired
	HistoryCVDao historyCVDao;
	
	public int insertOneLevel(WaterqtNew waterN) throws Exception {
		int result = waterqtNewDao.insertOneLevel(waterN);
		return result;
	}
	public List<WaterqtNew> checkOneLevel(WaterqtNew waterqtNew) {
		
		return waterqtNewDao.checkOneLevel(waterqtNew);
	}
	public List<WaterqtNew> searchAllOneLeveldata() {
		List<WaterqtNew> list = waterqtNewDao.searchAllOneLeveldata();
		return list;
	}
	public int updateOneLevelstatus(int status, int tid) {
		int result =waterqtNewDao.updateOneLevelstatus(status, tid);
		return result;
	}
	public List<Integer> searchDayStatusForSecond(WaterqtNew waterqtNew) {
		List<Integer> list =waterqtNewDao.searchDayStatusForSecond(waterqtNew);
		return list;
	}
	public List<WaterqtNew> getUserInfo(String mac) {

		List<WaterqtNew> list = waterqtNewDao.getUserInfo(mac);
		return list;
	}
	public List<LatestSTCV> getLatestStatusCV() {
		List<LatestSTCV> list = (List<LatestSTCV>) waterqtNewDao.getLatestStatusCV();
		//JSONObject object = waterqtNewDao.getLatestStatusCV();
		//return object;
		return list;
	}
	public List<WaterqtNew> areaUserLocation() throws Exception {
		List<WaterqtNew> list =waterqtNewDao.areaUserLocation();
		return list;
	}
	public List<HistoryCV> searchHistoryCV() throws Exception {
		List<HistoryCV> list =historyCVDao.searchHistoryCV();
		return list;
	}
	public WaterqtNew getLatestUserinfo() {
		WaterqtNew tNew =waterqtNewDao.getLatestUserinfo();
		return tNew;
	}

	
	

}
