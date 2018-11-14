package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import cn.entity.HistoryCV;
import cn.entity.WaterqtNew;
@Mapper
public interface HistoryCVDao {
	
	@SelectProvider(type=sqlprivatebean.class,method="searchHistoryCV")
	public List<HistoryCV> searchHistoryCV() throws Exception;
}
