package com.hbs.common.manager.waittask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.waittask.dao.WaitTaskConfigInfoDao;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;

/**
 * 待办配置初始化
 * 
 * @author yangzj
 * 
 */
public class WaitTaskConfigInit {
	/**
	 * 待办配置缓存MAP
	 */
	private static Map<String, WaitTaskConfigInfo> hm = null;

	/**
	 * 日志对象
	 */
	private static final Logger logger = Logger
			.getLogger(WaitTaskConfigInit.class);

	private static final String WAITTASKCONFIGINFODAO = "waitTaskConfigInfoDao";

	/**
	 * 私有构造函数，禁止外部实例化
	 */
	private WaitTaskConfigInit(){
		
	}
	
	/**
	 * 加载待办配置数据库数据，缓存数据
	 */
	@SuppressWarnings("unchecked")
	private static void init() {
		logger.info("进入待办配置缓存数据加载!");
		try {
			WaitTaskConfigInfoDao waitTaskConfigInfoDao = (WaitTaskConfigInfoDao) BeanLocator
					.getInstance().getBean(WAITTASKCONFIGINFODAO);
			if (null != waitTaskConfigInfoDao) {
				List<WaitTaskConfigInfo> list = waitTaskConfigInfoDao
						.listWaitTaskConfigInfo();
				if (null != list && list.size() > 0) {
					Map<String, WaitTaskConfigInfo> tempMap = new HashMap<String, WaitTaskConfigInfo>();
					for (WaitTaskConfigInfo info : list) {
						tempMap.put(info.getConfigId(), info);
					}
					hm = tempMap;
				} else {
					logger.error("进入待办配置缓存数据加载!,数据库中无数据，请配置待办数据!");
				}

			} else {
				logger.error("进入待办配置缓存数据加载!,无法获取待办获取实例，请查看spring配置!");
			}
		} catch (Exception e) {
			logger.error("待办配置缓存数据加载失败! ", e);
		}
	}
	
	
	
	/**
	 * 根据configid，获取待办的配置项目
	 * @param configId
	 * @return
	 */
	public static WaitTaskConfigInfo getWaitTaskConfigInfo(String configId){
		if(null == hm){
			init();
		}		
		return hm.get(configId);
	}

}
