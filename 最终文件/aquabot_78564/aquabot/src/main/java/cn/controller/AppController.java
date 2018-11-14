package cn.controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
//import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
//import com.mysql.fabric.xmlrpc.base.Data;

import cn.entity.BaseResponse;
import cn.entity.HistoryCV;
import cn.entity.LatestSTCV;
import cn.entity.WaterQuality;
import cn.entity.WaterqtNew;
import cn.service.WaterqtNService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import cn.utils.DateUtils;
@RestController
@RequestMapping("/app")

public class AppController {

	@Autowired
	private WaterqtNService waterqtNService;
	
	/*@ResponseBody
	@RequestMapping(value="/insertOneLevel", method = RequestMethod.POST)
	@ApiOperation(value = "添加第一等级数据", httpMethod = "POST", notes = "添加第一等级数据")
	public BaseResponse<Boolean> insertOneLevel(HttpServletRequest request) throws Exception{
		
		String reString =request.getParameter("oneLevelData");
		String createTime =request.getParameter("screateTime");
		WaterqtNew waterqtNew =JSON.parseObject(reString,WaterqtNew.class);
		waterqtNew.setCreateTime(createTime);
		//waterqtNew.setCreateDate(DateUtils.getCurrentDateForOneLevel());
		System.out.println("restr="+reString+"water.mac="+waterqtNew.getCreateTime());
		int result = waterqtNService.insertOneLevel(waterqtNew);
		
		return BaseResponse.successWithData("200", "success", null);
		Map<String, String> map = userservice.getBadLocation();
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", map);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";
		//return sb;
	}*/
	
	
	@RequestMapping(value="/insertSecondLevel", method = RequestMethod.POST)
	@ApiOperation(value = "添加第一等级数据", httpMethod = "POST", notes = "添加第一等级数据")
	public BaseResponse<Boolean> insertSecondLevel(HttpServletRequest request) throws Exception
	{
		
		String reString =request.getParameter("oneLevelData");
		String screateTime =request.getParameter("screateTime");
		WaterqtNew waterqtNew =JSON.parseObject(reString,WaterqtNew.class);
		waterqtNew.setScreateTime(screateTime);
		waterqtNew.setSlevel("2");
		System.out.println("restr="+reString+"water.mac="+waterqtNew.getScreateTime());
		java.util.List<WaterqtNew> result = waterqtNService.checkOneLevel(waterqtNew);
		Boolean finalr =false;
		if (result.size()>0) 
		{
			finalr =true;
			//插入二级数据
			WaterqtNew transedw =waterStatus(waterqtNew);
			int secondResult = waterqtNService.insertOneLevel(transedw);
			if (secondResult==1) {
				//插入二级数据成功
				return BaseResponse.successWithData("200", "insertSecondDataSuccess", null);
			}else {
				//插入数据失败
				return BaseResponse.successWithData("500", "insertSecondDataFail", null);
			}
			
		}else {
			WaterqtNew oneLevelwater =new WaterqtNew();
			oneLevelwater.setLat(waterqtNew.getLat());
			oneLevelwater.setLng(waterqtNew.getLng());
			oneLevelwater.setMac(waterqtNew.getMac());
			oneLevelwater.setSlevel("1");
			
			String createT =screateTime.substring(0, 10);
			oneLevelwater.setCreateTime(createT);
			
			int insertResult=waterqtNService.insertOneLevel(oneLevelwater);
			if (insertResult==1) {
				//insert onelevel success
				//插入二级数据
				WaterqtNew transedw2 =waterStatus(waterqtNew);
				int secondResult = waterqtNService.insertOneLevel(transedw2);
				
				
				if (secondResult==1) {
					//插入二级数据成功
					
					return BaseResponse.successWithData("200", "insertSecondDataSuccess", null);
				}else {
					//插入数据失败
					return BaseResponse.successWithData("500", "insertSecondDataFail", null);
				}
				
			}else {
				//dg add insert oneLevel fail
				return BaseResponse.successWithData("500", "insertOneLevelDataFail", null);
			}
		}
		//return finalr;
		/*Map<String, String> map = userservice.getBadLocation();
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", map);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";*/
		//return sb;
	}
	
	
	@RequestMapping(value="/searchAllOneLevel", method = RequestMethod.GET)
	@ApiOperation(value = "搜索并更新所有一级数据的status", httpMethod = "GET", notes = "搜索并更新所有一级数据的status")
	//public BaseResponse<List<WaterqtNew>> searchAllOneLevel(HttpServletRequest request) throws Exception
	public String searchAllOneLevel(HttpServletRequest request) throws Exception
	{
		List<WaterqtNew> list =waterqtNService.searchAllOneLeveldata();
		
		JSONObject object=new JSONObject();
		
		if (list.size()>0) 
		{//获取一级数据成功
			for (int i = 0; i < list.size(); i++) 
			{
				WaterqtNew tWaterqtNew =(WaterqtNew)list.get(i);
				List<Integer> secondlist =waterqtNService.searchDayStatusForSecond(tWaterqtNew);
				int tts =0;
				if (secondlist.contains(1)) 
				{
					tts =1;
				}else 
				{
					//水质好
					tts = 2;
				}
				
				//包含1：水质为坏，2：好
				int upRes= waterqtNService.updateOneLevelstatus(tts, tWaterqtNew.getId());
				if (upRes==1) {
					//更新成功
					
				}else {
					//更新1级水质数据状态失败
				}
				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
			     ParsePosition bpos = new ParsePosition(0);
			     Date bstrtodate = formatter2.parse(tWaterqtNew.getCreateTime(), bpos);
			     Long finalback =bstrtodate.getTime();
				tWaterqtNew.setCreateTime(finalback.toString());
			}
			
			object.put("data", list);
			object.toString();
			String string ="callbackAlluser"+"("+object+")";
			return string;
			//return BaseResponse.successWithData("200", "searchSuccess", string);
		}else 
		{
			//获取一级数据失败
			//return BaseResponse.successWithData("500", "searchFailed", list);
			object.put("data", list);
			object.toString();
			String string ="callbackAlluser"+"("+object+")";
			return string;
		}
	}
	
	
	
	
	//判断水质好坏
		public WaterqtNew waterStatus(WaterqtNew waterQuality) {
				 List<Integer> statuslist = new ArrayList<Integer>();
				 System.out.println("ph="+waterQuality.getPh()+"cl="+waterQuality.getCl()+"tds="+waterQuality.getTds()+"orp="+waterQuality.getOrp());
					Integer status = 0;
					String tph = (String)waterQuality.getPh();
					String tcl = (String)waterQuality.getCl();
					String ttds = (String)waterQuality.getTds();
					String torp = (String)waterQuality.getOrp();
					double ph = Double.valueOf(tph);
					double cl = Double.valueOf(tcl);
					double tds = Double.valueOf(ttds);
					double orp = Double.valueOf(torp);
					
					//status 1:bad  2:good
					if (ph<=7.8 && ph>=7 && cl>=0.2 && cl <=1) {
						//好
						
						statuslist.add(2);
					}else {
						statuslist.add(1);
					}
					
					
					if (tds >1800) {
						statuslist.add(1);
					}else {
						statuslist.add(2);
					}
					
					if (orp <650) {
						statuslist.add(1);
					}else {
						statuslist.add(2);
					}
					if (statuslist.contains(1)) {
						//一个是bad  就更新水质状态为bad
						status = 1;
					}else {
						status = 2;
					}
					waterQuality.setStatus((long)status);
					
					return waterQuality;
			}
		
		@RequestMapping(value="/searchAllUser", method = RequestMethod.GET)
		@ApiOperation(value = "搜索所有一级数据", httpMethod = "GET", notes = "搜索所有一级数据")
		public BaseResponse<List<WaterqtNew>> searchAllUser() {
			List<WaterqtNew> list =waterqtNService.searchAllOneLeveldata();
			if (list.size() >0) {
				//success
				return BaseResponse.successWithData("200", "success", list);
			}else {
				return BaseResponse.successWithData("500", "fail", null);
			}
		}
		
		@RequestMapping(value="/getUserInfo", method = RequestMethod.POST)
		@ApiOperation(value = "⦁	获取用户详细信息", httpMethod = "POST", notes = "⦁获取用户详细信息")
		@ApiImplicitParams({
			@ApiImplicitParam(name = "mac", value = "mac地址", required = false, paramType = "query", dataType = "string")
		})
		public String getUserInfo(String mac) {
			
			
			
			List<WaterqtNew> list =waterqtNService.getUserInfo(mac);
			
			JSONObject object=new JSONObject();
			
			
			if (list.size() >0) {
				//success
				for (int i = 0; i < list.size(); i++) {
					
					if (i==0) {
						WaterqtNew tNew =list.get(i);
						SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					     ParsePosition bpos = new ParsePosition(0);
					     Date bstrtodate = formatter2.parse(tNew.getScreateTime(), bpos);
					     Long finalback =bstrtodate.getTime();
						tNew.setScreateTime(finalback.toString());
						
						object.put("lat", tNew.getLat());
						object.put("lng", tNew.getLng());
						object.put("address", tNew.getAddress());
						object.put("mac", tNew.getMac());
						object.put("tds", tNew.getTds());
						object.put("cl", tNew.getCl());
						object.put("ph", tNew.getPh());
						object.put("orp", tNew.getOrp());
						object.put("screateTime", tNew.getScreateTime());
					}else {
						WaterqtNew lWaterqtNew = list.get(i);
						
						SimpleDateFormat lwformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					     ParsePosition lwpos = new ParsePosition(0);
					     Date lwtrtodate = lwformatter.parse(lWaterqtNew.getScreateTime(), lwpos);
					     Long lwfinalback =lwtrtodate.getTime();
						lWaterqtNew.setScreateTime(lwfinalback.toString());
					}
					
						
				}
				
				object.put("data", list);
//				object.toString();
//				String string ="callbackUserInfo"+"("+object+")";
//				return string;
				return object.toString();
//				return BaseResponse.successWithData("200", "success", list);
			}else {
				object.put("data", list);
//				object.toString();
//				String string ="callbackUserInfo"+"("+object+")";
//				return string;
				return object.toString();
//				return BaseResponse.successWithData("500", "fail", null);
			}
		}
		
		@RequestMapping(value="/getLatestStatusCV", method = RequestMethod.GET)
		@ApiOperation(value = "⦁	获取所有用户最新的水质好坏概率", httpMethod = "GET", notes = "⦁获取所有用户最新的水质好坏概率")
		public String getLatestStatusCV() {
			List<LatestSTCV> list =waterqtNService.getLatestStatusCV();
			JSONObject object=new JSONObject();
			if (list.size() >0) {
				//success
				
				object.put("data", list);
				object.toString();
				return "callbacklatestCV"+"("+object+")";
				//return BaseResponse.successWithData("200", "success", list);
			}else {
				object.put("data", list);
				object.toString();
				return "callbacklatestCV"+"("+object+")";
				//return BaseResponse.successWithData("500", "fail", null);
			}
		}
		
		@RequestMapping(value="/areaUserLocation", method = RequestMethod.GET)
		@ApiOperation(value = "⦁	返回最新监测的水质为差的经纬度", httpMethod = "GET", notes = "⦁返回最新监测的水质为差的经纬度")
		public String areaUserLocation() throws Exception {
			List<WaterqtNew> list =waterqtNService.areaUserLocation();
			JSONObject object=new JSONObject();
			if (list.size() >0) {
				//success
				for (int i = 0; i < list.size(); i++) {
					WaterqtNew lWaterqtNew = list.get(i);
					
					SimpleDateFormat lwformatter = new SimpleDateFormat("yyyy-MM-dd");
				     ParsePosition lwpos = new ParsePosition(0);
				     Date lwtrtodate = lwformatter.parse(lWaterqtNew.getCreateTime(), lwpos);
				     Long lwfinalback =lwtrtodate.getTime();
					lWaterqtNew.setCreateTime(lwfinalback.toString());
				}
				
				object.put("data", list);
				object.toString();
				return "callbackbadLocation"+"("+object+")";
				//return BaseResponse.successWithData("200", "success", list);
			}else {
				object.toString();
				return "callbackbadLocation"+"("+object+")";
				//return BaseResponse.successWithData("500", "fail", null);
			}
		}
		
		@RequestMapping(value="/historyCV", method = RequestMethod.GET)
		@ApiOperation(value = "⦁	季节图表", httpMethod = "GET", notes = "⦁季节图表")
		public String historyCV() throws Exception {
			List<HistoryCV> list =waterqtNService.searchHistoryCV();
			
			JSONObject object=new JSONObject();
			if (list.size() >0) {
				//success
				for (int i = 0; i < list.size(); i++) {
					HistoryCV historyCV =list.get(i);
					
					SimpleDateFormat lwformatter = new SimpleDateFormat("yyyy-MM-dd");
				     ParsePosition lwpos = new ParsePosition(0);
				     Date lwtrtodate = lwformatter.parse(historyCV.getCreateTime(), lwpos);
				     Long lwfinalback =lwtrtodate.getTime();
					historyCV.setCreateTime(lwfinalback.toString());
				}
				object.put("data", list);
				object.toString();
				String string ="callbackpie"+"("+object+")";
				return string;
				//return BaseResponse.successWithData("200", "success", list);
			}else {
				object.toString();
				String string ="callbackpie"+"("+object+")";
				
				return string;
				//return BaseResponse.successWithData("500", "fail", null);
			}
		}
		
}
