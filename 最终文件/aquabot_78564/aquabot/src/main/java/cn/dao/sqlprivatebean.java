package cn.dao;

import java.sql.Date;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.bm.Lang;

import cn.entity.Config;
import cn.entity.WaterQuality;
import cn.entity.WaterqtNew;
import net.bytebuddy.asm.Advice.Return;
import net.sf.jsqlparser.expression.LongValue;

public class sqlprivatebean {
	
	public String saveWater(WaterQuality waterQuality) {
		//waterQuality = waterStatus(waterQuality);
		//String sql = "insert into water_quality SET mac ='"+water.getMac()+"',lat ='"+water.getLat()+"',lng ='"+water.getLng()+"',address ='"+water.getAddress()+"',cl ='"+water.getCl()+"',orp ='"+water.getOrp()+"',ph ='"+water.getPh()+"',tds ='"+water.getTds()+"',temperature ='"+water.getTemperature()+"',createTime ='"+water.getCreateTime()+"',status ='"+water.getStatus()+"' WHERE id ="+water.getId();
		
		//String sql = "insert into water_quality SET mac ='"+water.getMac()+"',lat ='"+water.getLat()+"',lng ='"+water.getLng()+"',address ='"+water.getAddress()+"',cl ='"+water.getCl()+"',orp ='"+water.getOrp()+"',ph ='"+water.getPh()+"',tds ='"+water.getTds()+"',temperature ='"+water.getTemperature()+"',createTime ='"+water.getCreateTime()+"',status ='"+water.getStatus()+"',id ="+water.getId();

		
		String sql= "insert into water_quality (id,mac,lat,lng,address,cl,orp,ph,tds,temperature,createTime,status) values (#{id},#{newWater.getMac()},#{lat},#{lng},#{address},#{cl},#{orp},#{ph},#{tds},#{temperature},#{createTime},status)";

		//String sql= "insert into water_quality (id,mac,lat,lng,address,cl,orp,ph,tds,temperature,createTime,status) values (#{id},#{mac},#{lat},#{lng},#{address},#{cl},#{orp},#{ph},#{tds},#{temperature},#{createTime},#{status})";
		System.out.println("save***sql="+sql);
		//sql = "SELECT MAX(id) FROM water_quality";
		return sql;
	}
	
	public String checkNewDatasql(WaterQuality water)throws Exception {
		String mac = water.getMac();
		//String createtime = nwater.getCreateTime();
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		String dateString = formatter.format(water.getCreateTime());
		Integer belowH = Integer.parseInt(dateString)/4;
		Integer upH = belowH+1;
		
		SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd HH");
		String dayStr = dayformatter.format(water.getCreateTime());
		StringBuffer substr=new StringBuffer(60);
		substr =substr.append(dayStr).append(":00:00");
		
		System.out.println("6677*"+substr+water.getMac());
		String finalBelowStr ="";
		String finalUpStr = "";
		
		Integer fbh = 4*belowH;
		Integer fuh = 4*upH;
		if (fbh <10) {
			finalBelowStr = substr.replace(11, 13, "0"+fbh.toString()).toString();
		}else {
			
			finalBelowStr = substr.replace(11, 13, fbh.toString()).toString();
		}
		
		if (fuh <10) {
			finalUpStr = substr.replace(11, 13, "0"+fuh.toString()).toString();
		}else {
			
			finalUpStr = substr.replace(11, 13, fuh.toString()).toString();
		}
		
		
		//return "SELECT * FROM water_quality WHERE mac = "+water.getMac()+" AND createTime < " +finalUpStr.toString()+"";
		return "SELECT id FROM water_quality WHERE mac = '"+water.getMac()+"' AND createTime  < '"+finalUpStr+"' AND createTime  >'"+finalBelowStr+"'";
		
		
		
	}
	
	public String deleteDataWithid(String macid) {
		return "DELETE FROM water_quality WHERE id ="+ Integer.parseInt(macid);
		//String sql = "DELETE FROM water_quality WHERE id in (48)";
		//return sql;
	}
	
	public String deleteDataWithidArr(List<Long> idList) {
		System.out.println("sys idlist="+idList);
		String sql = "DELETE FROM water_quality WHERE id in (2,3)";
		System.out.println("syssql="+sql);
		return sql;
	}
	
	
	
	/*String mac,
	String lat,
	String lng,
	String address,
	String cl,
	String orp,
	String ph,
	String tds,
	String temperature)*/
	public String updateDatawithid(WaterQuality water) {
		SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dayStr = dayformatter.format(water.getCreateTime());
		System.out.println("sql privatebean updatawithid = "+dayStr);
		
		String sql = "UPDATE water_quality SET mac ='"+water.getMac()+"',lat ='"+water.getLat()+"',lng ='"+water.getLng()+"',address ='"+water.getAddress()+"',cl ='"+water.getCl()+"',orp ='"+water.getOrp()+"',ph ='"+water.getPh()+"',tds ='"+water.getTds()+"',temperature ='"+water.getTemperature()+"',createTime ='"+dayStr+"' WHERE id ="+water.getId();
		System.out.println("updatesql**="+sql);
		return sql;
	}
	
	
	public String insertAlldata(List<WaterQuality> waterlist) {  
        List<WaterQuality> list = waterlist; 
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO water_quality ");  
        sb.append("(id, mac,lat,lng,address,cl,orp,ph,tds,temperature,createTime) ");  
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id}, #'{'list[{0}].mac}, #'{'list[{0}].lat}, #'{'list[{0}].lng}, #'{'list[{0}].address},"+ " #'{'list[{0}].cl}, #'{'list[{0}].orp},"+ " #'{'list[{0}].ph}, #'{'list[{0}].tds}, #'{'list[{0}].temperature}, #'{'list[{0}].createTime})");  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }  
        System.out.println("sql insertalldata**="+sb.toString());
        return sb.toString();  
    }  
	
	 public String saveAll(Map map) {
	        List<Config> configs = (List<Config>) map.get("list");
	        StringBuilder sb = new StringBuilder();
	        sb.append("insert into config");
	        sb.append("(cron)");
	        sb.append("values");
	        MessageFormat mf = new MessageFormat("#'{'list[{0}].cron'}'");
	        for (int i = 0; i < configs.size(); i++) {
	            sb.append("(");
	            sb.append(mf.format(new Object[]{i}));
	            sb.append(")");

	            if (i < configs.size() - 1) {
	                sb.append(",");
	            }
	        }

	        return sb.toString();
	    }
	 //sSELECT * FROM (SELECT * FROM water_quality WHERE createTime BETWEEN '2018-02-10 01:01:00' AND '2018-02-11 13:01:00')a WHERE STATUS =2;
	 public String goodCount(String mac) {
		 
		 String sql = "SELECT COUNT(*) FROM water_quality WHERE mac = #{mac} AND STATUS = 2";
		 return sql;
		
	}
	 
	 public String goodMapForAllData() {
		 String mysql = "SELECT COUNT(DISTINCT auser.u_id) AS total,\r\n" + 
		 		"sum(case when water_quality.`status`=1 then 1 else 0 end)/count(*)goodCV\r\n" + 
		 		"from water_quality,auser";
		 return mysql;
		
	}
	 
	 public String getBadLocation() {
		String mysql = "SELECT DISTINCT auser.u_lat,auser.u_lng FROM auser,water_quality WHERE auser.u_mac=water_quality.mac AND water_quality.`status` =1";
		return mysql;
	}
	 //app 接口重写
	public String insertOneLevel() {
		String mysql = "insert into water_qtnew (id,mac,lat,lng,address,cl,orp,ph,tds,temperature,createTime,status,slevel,screateTime) values (DEFAULT,#{mac},#{lat},#{lng},#{address},#{cl},#{orp},#{ph},#{tds},#{temperature},#{createTime},#{status},#{slevel},#{screateTime})";
		
		System.out.println("mysql="+mysql);
		return mysql;
		
	}
	 
	public String checkOneLevel(WaterqtNew waterqtNew) {
		String maa =waterqtNew.getMac();
		String createT =waterqtNew.getScreateTime();
		createT=createT.substring(0, 10);
		String mysql = "SELECT * FROM water_qtnew WHERE mac = '"+maa+"' AND slevel = 1 AND createTime LIKE '"+createT+"%"+"'";
		//String mysql ="SELECT * FROM water_qtnew WHERE mac = 'dfefdfe-gegdf-cecx1' AND slevel = 1 AND createTime LIKE '2018-02-10%'";
		System.out.println("mysql="+mysql+"maa="+maa+"crt="+createT);
		return mysql;
				
				
	}
	
	public String searchAllOneLeveldata() {
		String mysql = "SELECT * FROM water_qtnew WHERE slevel=1";
		return mysql;
	}
	
	public String updateOneLevelstatus(int status,int tid) {
		String mysql = "UPDATE water_qtnew SET `status` = "+status+" WHERE id = "+tid;
		return mysql;
	}
	
	public String searchDayStatusForSecond(WaterqtNew waterqtNew) {
		
		String mysql ="SELECT `status` FROM water_qtnew WHERE mac='"+waterqtNew.getMac()+"' AND slevel = 2 AND screateTime LIKE '"+waterqtNew.getCreateTime()+"%'";
		return mysql;
		
	}

	public String getUserInfo(String mac) {
		//SELECT * FROM water_qtnew WHERE mac = 'dfefdfe-gegdf-cecx1' AND slevel = 2;
		
		String mysql = "SELECT * FROM water_qtnew WHERE mac = '"+mac+"' AND slevel = 2 ORDER BY screateTime DESC";
		return mysql;
	}
	
	//返回最新数据
	public String getLatestUserinfo(String mac) {
		String mysql = "SELECT lat,lng,address,mac,tds,cl,ph,orp,MAX(screateTime) AS screateTime FROM `water_qtnew` WHERE slevel = 2 AND mac ='"+mac+"'";
		return mysql;
	}
	
	public String getLatestStatusCV() {
		//SELECT * FROM water_qtnew WHERE mac = 'dfefdfe-gegdf-cecx1' AND slevel = 2;
		String mysql = "select \r\n" + 
				"count(*) as total,\r\n" + 
				"sum(case when STATUS=1 then 1 else 0 end) badCount,\r\n" + 
				"sum(case when STATUS=2 then 1 else 0 end) goodCount\r\n" + 
				"FROM (SELECT mac,lat,lng,`status`,createTime,slevel FROM water_qtnew WHERE slevel =1 GROUP BY mac)nw WHERE slevel =1";
		return mysql;
	}
	
	public String areaUserLocation() {
		String mysql ="SELECT lat,lng,mac,MAX(createTime) AS createTime FROM water_qtnew WHERE slevel = 1 AND `status` = 1 GROUP BY mac";
		return mysql;
	}
	
	public String searchHistoryCV() {
		String mysql ="select * from historyCV";
		return mysql;
	}

}
