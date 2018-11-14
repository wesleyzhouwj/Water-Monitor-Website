package cn.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.entity.Config;
@Mapper
public interface ConfigDao {
	@InsertProvider(type = sqlprivatebean.class, method = "saveAll")
	void saveAll(@Param("list") Collection<Config> configs) throws Exception;
}
