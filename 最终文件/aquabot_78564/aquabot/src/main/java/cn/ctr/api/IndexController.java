package cn.ctr.api;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



import cn.entity.Atestlist;
import cn.entity.Auser;


import cn.entity.BaseResponse;

import cn.entity.WaterQuality;
import cn.entity.WaterqtNew;
import cn.entity.student;
import cn.service.AuserService;
import cn.service.ConfigService;
import cn.service.IWaterQualityService;
import cn.service.WaterqtNService;
import cn.utils.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.spring.web.json.Json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
//import org.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.apache.catalina.util.RequestUtil;
import org.pojava.datetime.DateTime;

@Controller
@RequestMapping("/admin/water_quality")
public class IndexController<V> {
	@Autowired
	IWaterQualityService waterQualityService;
	
	@Autowired
	AuserService userservice;
	
	
	
	@Autowired
	private ConfigService configservice;
	
	@Autowired
	private WaterqtNService waterqtNService;
	
	@ResponseBody
	@RequestMapping(value="/helloworld",method=RequestMethod.GET)
	public ResponseEntity helloworld() {
		System.out.println("hellodd");
		//对象转json
		Auser newus = new Auser();
		newus.setU_id(6);
		newus.setU_lat("456");
		String strjson = JSON.toJSONString(newus);
		System.out.println("strjson="+strjson);
		
		
		String json="{\"u_id\":34,\"u_lat\":\"24\"}";
		 //反序列化
		 Auser userInfo=JSON.parseObject(json,Auser.class);
		 System.out.println("userinfo.uid="+userInfo.getU_id()+"+userinf="+userInfo);

		
		
		return ResponseEntity.ok("hello 13");
	}
	
	@RequestMapping(value="/saveWaterQualityForm", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name="mac",value="设备地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lat",value="纬度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lng",value="经度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="address",value="地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="cl",value="余氯",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="orp",value="单位mV",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="ph",value="ph值",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="tds",value="单位ppm",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="temperature",value="温度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="createTime",value="数据传入时间",required=true,paramType="form",dataType="string")
	})
	@ApiOperation(value = "保存水质信息", httpMethod = "POST", notes = "保存水质信息")
	public BaseResponse<Boolean> saveLevelInfo(String mac,
			String lat,
			String lng,
			String address,
			String cl,
			String orp,
			String ph,
			String tds,
			String temperature,
			String createTime) {
		WaterQuality waterQuality = new WaterQuality();
		waterQuality.setMac(mac);
		waterQuality.setLat(lat);
		waterQuality.setLng(lng);
		waterQuality.setAddress(address);
		waterQuality.setCl(cl);
		waterQuality.setOrp(orp);
		waterQuality.setPh(ph);
		waterQuality.setTds(tds);
		waterQuality.setTemperature(temperature);
		if (createTime !=null) {
			waterQuality.setCreateTime(DateUtils.strToDateLong(createTime));
		}else {
			waterQuality.setCreateTime(new DateTime(DateUtils.getCurrentDate()).toDate());
		}
		
		return BaseResponse.successWithData("200","success", waterQualityService.save(waterQuality));
	}
	
	
	@RequestMapping(value="/getLatestData", method = RequestMethod.GET)
	@ApiOperation(value = "获取最新水质信息", httpMethod = "GET", notes = "获取最新水质信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mac", value = "mac地址", required = false, paramType = "query", dataType = "string")
	})
	public BaseResponse<List<WaterQuality>> getLatestData(String mac) {
		return BaseResponse.successWithData("200","success",waterQualityService.getLatestData(mac));
	}
	

	@RequestMapping(value="/getAvgData", method = RequestMethod.GET)
	@ApiOperation(value = "获取某一设备水质信息", httpMethod = "GET", notes = "获取某一设备水质信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mac", value = "mac地址", required = false, paramType = "query", dataType = "string")
	})
	public BaseResponse<List<WaterQuality>> getAvgData(String mac) {
		
		return BaseResponse.successWithData("200","success",waterQualityService.getAvgData(mac));
	}
	
	@RequestMapping(value="/clear", method = RequestMethod.GET)
	@ApiOperation(value = "清空数据", httpMethod = "GET", notes = "清空数据")
	public BaseResponse<Boolean> clear() {
		
		return BaseResponse.successWithData("200","success",waterQualityService.clear());
	}
	
	@RequestMapping(value="/checkdata", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name="mac",value="设备地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lat",value="纬度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lng",value="经度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="address",value="地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="cl",value="余氯",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="orp",value="单位mV",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="ph",value="ph值",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="tds",value="单位ppm",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="temperature",value="温度",required=true,paramType="form",dataType="string")
	})
	@ApiOperation(value = "检查数据", httpMethod = "POST", notes = "检查数据")
	public BaseResponse<Boolean> checkNewdata(String mac,
			String lat,
			String lng,
			String address,
			String cl,
			String orp,
			String ph,
			String tds,
			String temperature,
			String createTime) throws Exception {
		WaterQuality waterQuality = new WaterQuality();
		waterQuality.setMac(mac);
		waterQuality.setLat(lat);
		waterQuality.setLng(lng);
		waterQuality.setAddress(address);
		waterQuality.setCl(cl);
		waterQuality.setOrp(orp);
		waterQuality.setPh(ph);
		waterQuality.setTds(tds);
		waterQuality.setTemperature(temperature);
		if (createTime =="") {
			
			waterQuality.setCreateTime(new DateTime(DateUtils.getCurrentDate()).toDate());
		}else {
			waterQuality.setCreateTime(DateUtils.strToDateLong(createTime));
		}
		List<Long> waList = waterQualityService.checkNewData(waterQuality);
		if (waList.size()>0) {
			//更新数据
			long nwaid = waList.get(0);
			boolean deleteresult = waterQualityService.deleteDataWithidArr(waList);
			
			waterQuality.setId(nwaid);
			boolean insertResult = waterQualityService.save(waterQuality);
			return BaseResponse.successWithData("200", "update success", null);
		}else {
			//先查询最大的id 然后+1赋值给新增数据
			long maxid = waterQualityService.maxid();
			//插入数据
			waterQuality.setId(maxid+1);
			boolean insertResult = waterQualityService.save(waterQuality);
			return BaseResponse.successWithData("200", "insert success", null);
			
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/checkdata2", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name="mac",value="设备地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lat",value="纬度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lng",value="经度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="address",value="地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="cl",value="余氯",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="orp",value="单位mV",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="ph",value="ph值",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="tds",value="单位ppm",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="temperature",value="温度",required=true,paramType="form",dataType="string")
	})
	@ApiOperation(value = "检查数据", httpMethod = "POST", notes = "检查数据")
	public BaseResponse<Boolean> checkNewdata2(WaterQuality waterQuality,String createTime) throws Exception {
		
		waterQuality.setCreateTime(DateUtils.strToDateLong(createTime));
		System.out.println("checkdata createtime**="+waterQuality.getCreateTime());
		//return BaseResponse.successWithData(waterQualityService.checkNewData(waterQuality));
		
		List<Long> waList = waterQualityService.checkNewData(waterQuality);
		System.out.println("tem alistid ="+waList+"tostring="+waList.toString()+"array="+waList.toArray());
		if (waList.size()>0) {
			//更新数据
			long nwaid = waList.get(0);
			//nwaid = 48;
			//boolean deleteresult = waterQualityService.deleteDataWithidArr(waList);
			for (int i = 0; i < waList.size(); i++) {
				waterQualityService.deleteDataWithMac(waList.get(i).toString());
			}
			//boolean deleteresult = waterQualityService.deleteDataWithMac("2");
			waterQuality.setId(nwaid);
			waterQuality = waterStatus(waterQuality);
			boolean insertResult = waterQualityService.save(waterQuality);
			return BaseResponse.successWithData("200", "update success", null);
		}else {
			//先查询最大的id 然后+1赋值给新增数据
			long maxid = waterQualityService.maxid();
			//插入数据
			waterQuality.setId(maxid+1);
			waterQuality = waterStatus(waterQuality);
			System.out.println("water.status**="+waterQuality.getStatus());
			boolean insertResult = waterQualityService.save(waterQuality);
			return BaseResponse.successWithData("200", "insert success", null);
			
		}
	}
	
	
	@RequestMapping(value="/deletedata", method = RequestMethod.GET)
	@ApiOperation(value = "删除记录", httpMethod = "GET", notes = "删除记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id地址", required = false, paramType = "query", dataType = "string")
	})
	public BaseResponse<Boolean> deletedatawithid(String id) {
		System.out.println("getid**77="+id);
		return BaseResponse.successWithData("200","succ", waterQualityService.deleteDataWithMac(id));
	}
	
	
	@RequestMapping(value="/insertAllData", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name="mac",value="设备地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lat",value="纬度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="lng",value="经度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="address",value="地址",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="cl",value="余氯",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="orp",value="单位mV",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="ph",value="ph值",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="tds",value="单位ppm",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="temperature",value="温度",required=true,paramType="form",dataType="string"),
		@ApiImplicitParam(name="createTime",value="创建时间",required=true,paramType="form",dataType="string")
	})
	
	public BaseResponse<Boolean> insertalldata() throws Exception {
		List<WaterQuality> list =new ArrayList();
		for (int i = 0; i < 5; i++) {
			WaterQuality waterQuality= new WaterQuality();
			waterQuality.setMac("3fdse44");
			waterQuality.setLat("12345432");
			waterQuality.setLng("12345432");
			waterQuality.setAddress("深圳第"+i);
			waterQuality.setCl("12345432");
			waterQuality.setOrp("12345432");
			waterQuality.setPh("12345432");
			waterQuality.setTds("12345432");
			waterQuality.setTemperature("12345432");
			String createTime = "2018-02-01 12:34:00";
			waterQuality.setCreateTime(DateUtils.strToDateLong(createTime));
			list.add(waterQuality);
		}
		System.out.println("indexController insertall**="+list);
		return null;
		//return BaseResponse.successWithData(waterQualityService.insertAlldata(list));
	}
	
	
	@RequestMapping(value="/testhtml",method = RequestMethod.GET)
	public String testhtml() {
		return "login";
		
	}
	
	@RequestMapping(value="/testFordb",method = RequestMethod.GET)
	public String testfordb() {
		return "testForDB";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserlist",method=RequestMethod.GET)
	public List<Auser> userList(){
		List<Auser> userLiss=userservice.userList();
		return userLiss;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserlist2",method=RequestMethod.GET)
	public String userList2(){
		JSONObject object=new JSONObject();
		object.put("name", "mmvv");
		object.put("age", 100);
		
		JSONArray list = new JSONArray();
		
		
		object.put("message", list);
		object.toString();
		String sb ="";
		sb+="callback"+"("+object+")";
		return sb;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/waterlist", method = RequestMethod.POST)
	@ApiOperation(value = "水质记录", httpMethod = "POST", notes = "水质记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mac", value = "mac地址", required = false, paramType = "query", dataType = "string")
	})
	public List<WaterQuality> waterList(String mac){
		List<WaterQuality> waterLiss=userservice.waterList(mac);
		
		return waterLiss;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/waterlist2", method = RequestMethod.GET)
	@ApiOperation(value = "水质记录", httpMethod = "GET", notes = "水质记录")
	public String waterll(String mac) {
		/*JSONObject object=new JSONObject();
		object.put("name", "mmvv");
		object.put("age", 100);*/
		
		List<WaterQuality> waterLiss=userservice.waterList(mac);
		System.out.println("未转换前返回="+waterLiss);
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", waterLiss);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";
		System.out.println("sb="+sb);
		
		String trs = waterLiss.toString();
		System.out.println("转成text="+trs);
		
		
		
		return sb;
	}
	/**
	 11      * 定义分割常量 
	 12      * #用于list中每个元素间的分割
	 13      * |用于map中每一个kv对间的分割
	 14      * =用于map中key与value间的分割
	 15      */
	   
	@ResponseBody
	@RequestMapping(value="/waterlist3", method = RequestMethod.POST)
	@ApiOperation(value = "水质记录", httpMethod = "POST", notes = "水质记录")
	public boolean waterList3(HttpServletRequest request) throws Exception{
		/*String mac = water.getMac();
		System.out.println("mac="+mac+"ph="+water.getPh());
		List<WaterQuality> waterLiss=userservice.waterList(mac);*/
		System.out.println("testlist="+request.getParameter("listr").length());
		String tes = request.getParameter("listr");
		System.out.println("string**="+tes);
		
		String temstr = "[\n  {\n    \"u_id\" : \"101\",\n    \"u_mac\" : \"45\",\n    \"u_lat\" : \"33\",\n    \"u_lng\" : \"33\",\n    \"u_status\" : \"3\"\n  },\n  {\n    \"u_id\" : \"101\",\n    \"u_mac\" : \"45\",\n    \"u_lat\" : \"33\",\n    \"u_lng\" : \"33\",\n    \"u_status\" : \"3\"\n  },\n  {\n    \"u_id\" : \"101\",\n    \"u_mac\" : \"45\",\n    \"u_lat\" : \"33\",\n    \"u_lng\" : \"33\",\n    \"u_status\" : \"3\"\n  }\n]";
		JSONArray jsonArray = JSONArray.parseArray(tes);
		
		//ArrayList<Auser> userlist= (ArrayList<Auser>) JSONArray.parseArray(tes, Auser.class);
		/*List<Auser> userlist = JSONArray.parseArray(tes, Auser.class);
		System.out.println("jsonarr[0]="+jsonArray.get(0)+"userlist="+userlist+"userlist[0].umac="+userlist.get(1).getU_lat());*/
		List<WaterQuality> waterlist = jsonArray.parseArray(tes, WaterQuality.class);
		long maxid = waterQualityService.maxid();
		for (int i = 0; i < waterlist.size(); i++) {
			waterlist.get(i).setId(maxid+1);
		}
		boolean insertresult = waterQualityService.insertAlldata(waterlist);
		
		
		return insertresult;
		
	}
	
	//判断水质好坏
	public WaterQuality waterStatus(WaterQuality waterQuality) {
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
	
	@ResponseBody
	@RequestMapping(value="/getGoodmap", method = RequestMethod.GET)
	@ApiOperation(value = "水质为优概率", httpMethod = "GET", notes = "水质为优概率")
	public String goodCountMap() throws Exception{
		//String currenttime =DateUtils.getCurrentDate();
		Map<String, String> map =userservice.goodMapForAllData();
		System.out.println("mymap"+map);
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", map);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";
		return sb;
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllUserMess", method = RequestMethod.POST)
	@ApiOperation(value = "所有用户信息", httpMethod = "POST", notes = "所有用户信息")
	public String getAllUserMess() throws Exception{
		List<Auser> list = userservice.userList();
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", list);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";
		return sb;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBadLocation", method = RequestMethod.GET)
	@ApiOperation(value = "获取水质差的设备经纬度", httpMethod = "GET", notes = "获取水质差的设备经纬度")
	public String getBadLocation() throws Exception{
		Map<String, String> map = userservice.getBadLocation();
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", map);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";
		return sb;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/insertOneLevel", method = RequestMethod.POST)
	@ApiOperation(value = "添加第一等级数据", httpMethod = "POST", notes = "添加第一等级数据")
	public int insertOneLevel(HttpServletRequest request) throws Exception{
		
		String reString =request.getParameter("oneLevelData");
		String createTime =request.getParameter("createTime");
		WaterqtNew waterqtNew =JSON.parseObject(reString,WaterqtNew.class);
		waterqtNew.setCreateTime(createTime);
		System.out.println("restr="+reString+"water.mac="+waterqtNew.getCreateTime());
		int result = waterqtNService.insertOneLevel(waterqtNew);
		
		return result;
		/*Map<String, String> map = userservice.getBadLocation();
		JSONObject waterjson = new JSONObject();
		waterjson.put("userList", map);
		waterjson.toString();
		String sb ="";
		sb+="callback"+"("+waterjson+")";*/
		//return sb;
	}
	
	
}
