package com.hbs.common.manager.systemconfig;

import java.util.List;



import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.common.dao.SystemConfigDao;
import com.hbs.domain.common.pojo.SystemConfig;

/**
 * ϵͳ����������
 * @author yangzj
 * �����ṩϵͳ������ȡ���Ծ�̬��ʽ����
 */

public class SystemConfigMgr {
	
    private static final String SYSTEMCONFIGDAO ="systemConfigDao";
    
    /**
     * ����ϵͳ������Ϣ
     *
     * @param systemConfig
     * 	@deprecated
     */
    public static void insertSystemConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.insertSystemConfig(systemConfig);
    
    }

    /**
     * ɾ��ϵͳ������Ŀ����configNameΪkey
     * @param configName
     * @deprecated
     */
    public static void deleteSystemConfig(String configName) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.deleteSystemConfig(configName);
    }

   /**
    * �޸�ϵͳ������Ŀ
    * @param systemConfig
    * @deprecated
    */
    public static void updateSystemConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
    	systemConfigDao.updateSystemConfig(systemConfig);
    }

    /**
     * �����޸�ϵͳ����
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
    * ��ѯϵͳ���ã���configNameΪkey,��������쳣�����쳣�׳����ɵ��÷�����
    * @param configName
    * @return
    */
    public static SystemConfig findSystemConfig(String configName) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
        return systemConfigDao.findSystemConfig(configName);
    }

    /**
     * ��ѯϵͳ���ã������б�
     * @param systemConfig
     * @return
     * @deprecated 
     */
    
    public static List<SystemConfig> listSimsConfig(SystemConfig systemConfig) {
    	SystemConfigDao systemConfigDao = (SystemConfigDao) BeanLocator.getInstance().getBean(SYSTEMCONFIGDAO);
        return systemConfigDao.listSystemConfig(systemConfig);
    }

    
}
