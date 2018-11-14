package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;

//import java.awt.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.json.JSONObject;

import cn.entity.LatestSTCV;
import cn.entity.WaterqtNew;

public interface WaterqtNewDao {
	//@SelectProvider(type=sqlprivatebean.class,method="insertOneLevel")
	@InsertProvider(type=sqlprivatebean.class,method="insertOneLevel")
	public int insertOneLevel(WaterqtNew waterN);
	
	@SelectProvider(type=sqlprivatebean.class,method="checkOneLevel")
	public List<WaterqtNew> checkOneLevel(WaterqtNew waterqtNew);
	
	//查询一级数据 返回mac createTime
	@SelectProvider(type=sqlprivatebean.class,method="searchAllOneLeveldata")
	public List<WaterqtNew> searchAllOneLeveldata();
	
	//更新一级数据 水质状态+createDate
	@UpdateProvider(type=sqlprivatebean.class,method="updateOneLevelstatus")
	public int updateOneLevelstatus(int status,int tid);

	//查询某一天的二级数据 如果有水质为bad 当天水质就更新为bad
	@SelectProvider(type=sqlprivatebean.class,method="searchDayStatusForSecond")
	public List<Integer> searchDayStatusForSecond(WaterqtNew waterqtNew);
	//记录总用户水质好坏概率

	//获取用户详细信息
	@SelectProvider(type=sqlprivatebean.class,method="getUserInfo")
	public List<WaterqtNew> getUserInfo(String mac);
	
	//lat,lng,address,mac,tds,cl,ph,orp,MAX(screateTime) AS screateTime
	@SelectProvider(type=sqlprivatebean.class,method="getLatestUserinfo")
	@ResultType(WaterqtNew.class)
	@Results(value={@Result(property="lat",column="lat"),@Result(property="lng",column="lng"),@Result(property="address",column="address"),@Result(property="mac",column="mac"),@Result(property="tds",column="tds"),@Result(property="cl",column="cl"),@Result(property="ph",column="ph"),@Result(property="orp",column="orp"),@Result(property="screateTime",column="screateTime")}
			)
	public WaterqtNew getLatestUserinfo();

	//获取所有用户最新的水质好坏概率
	@SelectProvider(type=sqlprivatebean.class,method="getLatestStatusCV")
	@ResultType(LatestSTCV.class)
	@Results(value={@Result(property="total",column="total"),@Result(property="goodCount",column="goodCount"),@Result(property="badCount",column="badCount")}
	)
	public List<LatestSTCV> getLatestStatusCV();
	
	/*@Results({@Result(property="total",column="total"),@Result(property="goodCount",column="goodCount"),@Result(property="badCount",column="badCount")}
			)*/
	//查询一级数据最近时间
	
	
	//获取区域图 监测水质为差的经纬度
	@SelectProvider(type=sqlprivatebean.class,method="areaUserLocation")
	public List<WaterqtNew> areaUserLocation();
	
	
	
	
}
