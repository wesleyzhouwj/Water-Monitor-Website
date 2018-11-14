package cn.service;
import java.util.List;
//import com.github.pagehelper.PageInfo;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import cn.entity.WaterQuality;
//import com.qitoon.framework.core.service.ICrudService;
//import com.xuxj.app.record.api.dao.entity.WaterQuality;
public interface IWaterQualityService{
     
	public List<WaterQuality> getLatestData(String mac);
	
	public List<WaterQuality> getAvgData(String mac);
	
	public Boolean clear();
	public Boolean save(WaterQuality water ) throws DataAccessException;
	
	/*��������Ƿ���ڣ����ھ͸��£������ھͲ�������*/
	 public List<Long> checkNewData(WaterQuality water) throws DataAccessException;
	 
	 public Boolean deleteDataWithMac(@Param(value="mac") String mac) throws DataAccessException;
	 
	 public Boolean deleteDataWithidArr(List<Long> idlist) throws DataAccessException;
	 
	 public Boolean updateData(WaterQuality water) throws DataAccessException;
	
	 public Boolean insertAlldata(List<WaterQuality> waterlist) throws Exception;
	 
	 //查找最大id
	 public long maxid() throws Exception;
	 
	 public Integer goodCount(String mac)throws Exception;
	 
}