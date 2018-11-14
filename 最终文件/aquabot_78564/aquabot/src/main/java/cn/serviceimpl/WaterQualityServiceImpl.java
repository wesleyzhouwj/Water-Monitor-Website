package cn.serviceimpl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import cn.dao.IWaterQualityDao;
import cn.entity.WaterQuality;
import cn.service.IWaterQualityService;
/*import com.qitoon.framework.core.dao.ICrudDao;
import com.qitoon.framework.core.service.CrudServiceImpl;
import com.xuxj.app.record.api.dao.IWaterQualityDao;
import com.xuxj.app.record.api.dao.entity.WaterQuality;
import com.xuxj.app.record.api.service.inteface.IWaterQualityService;*/


@Service
public class WaterQualityServiceImpl implements IWaterQualityService{
	@Autowired
	private IWaterQualityDao waterQualityDao;

	public List<WaterQuality> getLatestData(String mac) {
		//return null;
		return waterQualityDao.getLatestData(mac);
	}

	public List<WaterQuality> getAvgData(String mac) {
		return waterQualityDao.getAvgData(mac);
	}

	public Boolean clear() {
		waterQualityDao.clear();
		return true;
	}

	public Boolean save(WaterQuality water) throws DataAccessException {
		return waterQualityDao.save(water);
	}

	public List<Long> checkNewData(WaterQuality water) throws DataAccessException {
		// TODO Auto-generated method stub
		List<Long> result = waterQualityDao.checkNewData(water);
		/*if (result.size() ==0) {
			
			//不存在就添加
			save(water);
			
		}else  {
			if (result.size() >1) {
				
			}else {
				
			}
			//存在当天数据 jian
			//List<PayOrder> newlis = payorderservice.selectPayOrder();
			for (int i = 0; i < result.size(); i++) {
				WaterQuality temw = result.get(i);
				System.out.println("geng***"+temw.getCreateTime()+"id="+temw.getId());
				water.setId(temw.getId());
				waterQualityDao.updateData(water);
			}
			//waterQualityDao.updateData(water);
		}*/
		return result;
	}

	public Boolean deleteDataWithMac(String macid) throws DataAccessException {
		waterQualityDao.deleteDataWithid(macid);
		return true;
	}

	public Boolean updateData(WaterQuality water) throws DataAccessException {
		boolean result= waterQualityDao.updateData(water);
		return result;
	}

	public Boolean insertAlldata(List<WaterQuality> waterlist) throws Exception {
		waterQualityDao.insertAlldata(waterlist);
		return true;
	}

	//批量删除
	public Boolean deleteDataWithidArr(List<Long> idlist) throws DataAccessException {
		boolean result = waterQualityDao.deleteDataWithidArr(idlist);
		return result;
	}

	public long maxid() throws Exception {
		
		return waterQualityDao.maxid();
	}

	public Integer goodCount(String mac) throws Exception {
		
		return waterQualityDao.goodCount(mac);
	}


	

}