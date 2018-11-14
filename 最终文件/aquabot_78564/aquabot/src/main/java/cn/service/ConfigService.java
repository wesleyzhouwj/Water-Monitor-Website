package cn.service;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import cn.entity.Config;

public interface ConfigService {
	void saveAll(@Param("list") Collection<Config> configs) throws Exception;
}
