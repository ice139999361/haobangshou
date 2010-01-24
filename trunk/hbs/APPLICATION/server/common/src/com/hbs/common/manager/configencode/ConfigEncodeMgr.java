package com.hbs.common.manager.configencode;

import java.util.List;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.ConfigEncodeDao;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * ϵͳ�ֵ�����ֻ�ṩfind��list��̬����
 * @author yangzj
 *
 */
public class ConfigEncodeMgr {
	private static final String CONFIGENCODEDAO ="configEncodeDao";
	
	/**
	 * ����������ѯ�����(encodeType , encodeKey)
	 
	 * @return
	 */
	public static ConfigEncode getConfigEncode(ConfigEncode ceParam){
		
		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
		ConfigEncode ret_ConfigEncode = configEncodeDao.findConfigEncode(ceParam);
		return ret_ConfigEncode;
	}
	/**
	 * ���ݱ������Ͳ�ѯ�����(encodeType )
	 
	 * @return
	 */
	public static List<ConfigEncode> getListConfigEncode(ConfigEncode ceParam){			
		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
		List<ConfigEncode> ret_ConfigEncode = configEncodeDao.listConfigEncode(ceParam);
		return ret_ConfigEncode;
	}
}
