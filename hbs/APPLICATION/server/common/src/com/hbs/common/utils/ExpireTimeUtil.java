/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.common.utils;

import java.util.Date;

import com.hbs.common.manager.systemconfig.SystemConfigMgr;
import com.hbs.domain.common.pojo.SystemConfig;

/**
 * @author Administrator
 *
 */
public class ExpireTimeUtil {
	/**
	 * 获取提醒待办过期时间
	 * @return
	 */
	public static Date getExpireTime(String type){		
		String strL = "5";
		SystemConfig config = SystemConfigMgr.findSystemConfig(type);
		if(null != config){
			strL = config.getConfigValue();
		}
		return DateUtils.getNeedDate(new Date(), strL, true);
	}
}
