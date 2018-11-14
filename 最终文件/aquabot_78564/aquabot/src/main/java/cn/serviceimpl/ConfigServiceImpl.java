package cn.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.ConfigDao;
import cn.entity.Config;
import cn.service.ConfigService;
@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	ConfigDao configDao;
	public void saveAll(Collection<Config> configs) throws Exception {
		// TODO Auto-generated method stub
		configDao.saveAll(configs);
		
	}

}
