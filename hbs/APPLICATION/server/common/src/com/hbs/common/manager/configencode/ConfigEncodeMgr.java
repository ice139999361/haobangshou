package com.hbs.common.manager.configencode;

import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.ConfigEncodeDao;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * 系统字典表管理，只提供find和list静态方法
 * @author yangzj
 *
 */
public class ConfigEncodeMgr {
	private static final String CONFIGENCODEDAO ="configEncodeDao";
	
	/**
	 * 根据主键查询编码表(encodeType , encodeKey)
	 
	 * @return
	 */
	public static ConfigEncode getConfigEncode(ConfigEncode ceParam){
		
		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
		ConfigEncode ret_ConfigEncode = configEncodeDao.findConfigEncode(ceParam);
		return ret_ConfigEncode;
	}
	/**
	 * 根据编码类型查询编码表(encodeType )
	 
	 * @return
	 */
	public static List<ConfigEncode> getListConfigEncode(ConfigEncode ceParam){			
		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
		List<ConfigEncode> ret_ConfigEncode = configEncodeDao.listConfigEncode(ceParam);
		return ret_ConfigEncode;
	}
}
