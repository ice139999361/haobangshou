package com.hbs.common.manager.systemconfig;

import java.util.List;



import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.SystemConfigDao;
import com.hbs.domain.common.pojo.SystemConfig;

/**
 * 系统参数配置类
 * @author yangzj
 * 对外提供系统参数获取，以静态方式调用
 */

public class SystemConfigMgr {
	
    private static final String SYSTEMCONFIGDAO ="systemConfigDao";
    
    /**
     * 增加系统配置信息
     *
     * @param systemConfig
     * 	@deprecated
     */
    public static void insertSystemConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.insertSystemConfig(systemConfig);
    
    }

    /**
     * 删除系统配置项目，以configName为key
     * @param configName
     * @deprecated
     */
    public static void deleteSystemConfig(String configName) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.deleteSystemConfig(configName);
    }

   /**
    * 修改系统配置项目
    * @param systemConfig
    * @deprecated
    */
    public static void updateSystemConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.updateSystemConfig(systemConfig);
    }

    /**
     * 批量修改系统配置
     * @param systemConfigs
     * @deprecated
     */
    public static void updateSimsConfig(List<SystemConfig> systemConfigs) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
        if(systemConfigs!=null && systemConfigs.size()>0){
            for(SystemConfig systemConfig : systemConfigs ){
            	systemConfigDao.updateSystemConfig(systemConfig);
            }
        }
    }


   /**
    * 查询系统配置，以configName为key,如果发生异常，则异常抛出，由调用方捕获
    * @param configName
    * @return
    */
    public static SystemConfig findSystemConfig(String configName) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
        return systemConfigDao.findSystemConfig(configName);
    }

    /**
     * 查询系统配置，返回列表
     * @param systemConfig
     * @return
     * @deprecated 
     */
    
    public static List<SystemConfig> listSimsConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
        return systemConfigDao.listSystemConfig(systemConfig);
    }

    
}
