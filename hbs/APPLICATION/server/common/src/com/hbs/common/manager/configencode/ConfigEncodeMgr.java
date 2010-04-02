package com.hbs.common.manager.configencode;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.ConfigEncodeDao;
import com.hbs.domain.common.pojo.ConfigEncode;

/**
 * ϵͳ�ֵ�����ֻ�ṩfind��list��̬����
 * @author yangzj
 *
 */
public class ConfigEncodeMgr {
	
	private static final Logger logger = Logger.getLogger(ConfigEncodeMgr.class);
	private static final String CONFIGENCODEDAO ="configEncodeDao";
	
	/**
	 * �����ֵ��
	 */
	private static Map<String, List<ConfigEncode>> cacheMap = null;
	
	static{
		if (null ==cacheMap){//����Ϊnull ����Ҫ��ʼ������
			cacheMap = new HashMap<String, List<ConfigEncode>>();
			init();
			logger.info("�����ֵ��ɹ�!");
		}
	}
	
	/**
	 * ��ֹʵ����
	 */
	private ConfigEncodeMgr() {
				
	}
	
	private static void init(){
		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
		List<ConfigEncode> ret_ConfigEncode = configEncodeDao.listConfigEncode(new ConfigEncode());
		String oldEncodeType = "";
		List<ConfigEncode> listEncode = null;
		for(ConfigEncode encode : ret_ConfigEncode){
			String curEncodeType = encode.getEncodeType();
			if(!(oldEncodeType.equals(curEncodeType))){
				listEncode = new ArrayList<ConfigEncode>();
				listEncode.add(encode);
				cacheMap.put(curEncodeType, listEncode);
				oldEncodeType = curEncodeType;
			}else{
				listEncode.add(encode);
			}
		}
//		Iterator<Map.Entry<String,List<ConfigEncode>>> iter = cacheMap.entrySet().iterator();
//		while (iter.hasNext()) { 
//			Map.Entry<String,List<ConfigEncode>> entry = iter.next(); 		     
//		    List<ConfigEncode> list = entry.getValue();
//		    for(ConfigEncode encode : list){
//		    	System.out.println(encode.toString());
//		    }
//		}
	}
	/**
	 * ����������ѯ�����(encodeType , encodeKey)
	 
	 * @return
	 */
	public static ConfigEncode getConfigEncode(ConfigEncode ceParam){
		ConfigEncode ret_ConfigEncode = null;
//		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
//		ConfigEncode ret_ConfigEncode = configEncodeDao.findConfigEncode(ceParam);
		String encodeType = ceParam.getEncodeType();
		String encodeKey = ceParam.getEncodeKey();
		if(null != cacheMap){
			List<ConfigEncode> list = cacheMap.get(encodeType);
			if(null != list){
				for( ConfigEncode code : list){
					if(encodeKey.equals(code.getEncodeKey())){
						ret_ConfigEncode = code;
						break;
					}
				}
			}else{
				logger.info("getConfigEncode(ConfigEncode ceParam) ʧ��,�ֵ�������������Ĳ���Ϊ��" + ceParam.toString());
			}
		}else{
			logger.info("getConfigEncode(ConfigEncode ceParam) ʧ��,����Ϊnull������Ĳ���Ϊ��" + ceParam.toString());
		}
		return ret_ConfigEncode;
	}
	/**
	 * ���ݱ������Ͳ�ѯ�����(encodeType )
	 
	 * @return
	 */
	public static List<ConfigEncode> getListConfigEncode(ConfigEncode ceParam){			
//		ConfigEncodeDao configEncodeDao = (ConfigEncodeDao) BeanLocator.getInstance().getBean(CONFIGENCODEDAO);
//		List<ConfigEncode> ret_ConfigEncode = configEncodeDao.listConfigEncode(ceParam);
		List<ConfigEncode> ret_ConfigEncode = null;
		if(null != cacheMap){
			ret_ConfigEncode = cacheMap.get(ceParam.getEncodeType());
		}else{
			logger.info("getListConfigEncode(ConfigEncode ceParam) ʧ��,����Ϊnull������Ĳ���Ϊ��" + ceParam.toString());
		}
		return ret_ConfigEncode;
	}
}
