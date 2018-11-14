package cn.dao;
/*import com.xuxj.app.record.api.dao.entity.WaterQuality;
import com.qitoon.framework.core.dao.ICrudDao;*/

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlProvider;

import cn.entity.WaterQuality;
@Mapper
public interface IWaterQualityDao{
 
	public List<WaterQuality> getLatestData(@Param(value="mac") String mac);
	
	public List<WaterQuality> getAvgData(@Param(value="mac") String mac);
	
	public long clear();
	
	@Select("SELECT MAX(id) FROM water_quality")
	public long maxid() throws Exception;
	/*String mac,
	String lat,
	String lng,
	String address,
	String cl,
	String orp,
	String ph,
	String tds,
	String temperature)*/
	 @Insert("insert into water_quality (id,mac,lat,lng,address,cl,orp,ph,tds,temperature,createTime,status) values (#{id},#{mac},#{lat},#{lng},#{address},#{cl},#{orp},#{ph},#{tds},#{temperature},#{createTime},#{status})")
   // @InsertProvider(type=sqlprivatebean.class,method="saveWater")
	 public Boolean save(WaterQuality water ) throws DataAccessException;
	 
	 /*��������Ƿ���ڣ����ھ͸��£������ھͲ�������*/
	 
	 @SelectProvider(type=sqlprivatebean.class,method="checkNewDatasql")
	 //@Select("SELECT * FROM water_quality WHERE mac = 'fdc70dde-61ae-cbb6-d4af-cf363605cb1' AND create_time  < '2018-07-07 14:32:00' AND create_time  > '2018-07-06 14:32:00'")
	 public List<Long> checkNewData(WaterQuality water) throws DataAccessException;
	 
	 @SelectProvider(type=sqlprivatebean.class,method="deleteDataWithid")
	 public Boolean deleteDataWithid(@Param(value="id") String macid) throws DataAccessException;
	 
	 //批量删除
	 @SelectProvider(type=sqlprivatebean.class,method="deleteDataWithidArr")
	 public Boolean deleteDataWithidArr(List<Long> idlist) throws DataAccessException;
	 
	 @SelectProvider(type=sqlprivatebean.class,method="updateDatawithid")
	// @Update("UPDATE water_quality SET mac ='dfefdfe-gegdf-cecx1',lat ='1202',lng ='207',address ='de广东省深圳市南山区高科技中心1',cl ='2342',orp ='2342',ph ='12.2',tds ='2342',temperature ='12332',createTime ='2018-02-05 12:45:46' WHERE id =2")
	 public Boolean updateData(WaterQuality water) throws DataAccessException;
	 
	 @SelectProvider(type=sqlprivatebean.class,method="insertAlldata")
	 //@Select("select * from water_quality")
	 public Boolean insertAlldata(List<WaterQuality> waterlist) throws Exception;
	 
	 @SelectProvider(type=sqlprivatebean.class,method="goodCount")
	 public Integer goodCount(String mac) throws Exception;
	 
	 

	
	 
	 
}