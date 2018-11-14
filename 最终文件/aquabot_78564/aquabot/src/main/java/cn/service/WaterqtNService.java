package cn.service;

import java.util.List;

import org.json.JSONObject;

import cn.entity.HistoryCV;
import cn.entity.LatestSTCV;

//import java.awt.List;

import cn.entity.WaterqtNew;

public interface WaterqtNService {
	public int insertOneLevel(WaterqtNew waterN)throws Exception;
	
	public List<WaterqtNew> checkOneLevel(WaterqtNew waterqtNew);
	
	//查询一级数据 返回mac createTime
		public List<WaterqtNew> searchAllOneLeveldata();
		
	//更新一级数据 水质状态+createDate
	public int updateOneLevelstatus(int status,int tid);
	
	public List<Integer> searchDayStatusForSecond(WaterqtNew waterqtNew);

	//获取用户详细信息
	public List<WaterqtNew> getUserInfo(String mac);
	//获取所有用户最新的水质好坏概率
	public List<LatestSTCV> getLatestStatusCV();
	
	public List<WaterqtNew> areaUserLocation() throws Exception;
	
	public List<HistoryCV> searchHistoryCV() throws Exception;
	
	public WaterqtNew getLatestUserinfo();
}
