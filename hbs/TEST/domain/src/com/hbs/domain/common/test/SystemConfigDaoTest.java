package com.hbs.domain.common.test;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.SystemConfigDao;
import com.hbs.domain.common.pojo.SystemConfig;

/**
 * test SystemConfigDao.
 * @author sims autoCoder1.0
 *
 */
public class SystemConfigDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-SystemConfig.xml)
        return new String[] { "classpath:/applicationContext-test-systemConfig.xml", "classpath:/applicationContext-test-systemConfig.xml" }; 
    } 
    
    /**
     * test insert.
     */
//    public void testInsertSystemConfig() {
//        SystemConfigDao systemConfigDao = (SystemConfigDao)this.getApplicationContext().getBean("systemConfigDao");
//        SystemConfig systemConfig = new SystemConfig();
//        systemConfig.setConfigName("yangzj");
//        systemConfig.setConfigValue("杨宗军");
//        systemConfig.setValueType(1);
//        systemConfig.setValueRange("1");
//        systemConfig.setConfigDesc("1");
//        systemConfig.setVisibaleFlag("1");
//        systemConfig.setConfigRegion("1");
//        systemConfig.setSubSystem("1");
//        systemConfig.setLastUpdate(new Date());
//        systemConfigDao.insertSystemConfig(systemConfig);
//        System.out.println("testInsert result--------------------------------------");
//        
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test delete.
     */
//    public void testDeleteSystemConfig() {
//        SystemConfigDao systemConfigDao = (SystemConfigDao) this.getApplicationContext().getBean("systemConfigDao");
//        SystemConfig systemConfig = new SystemConfig();
//        systemConfigDao.deleteSystemConfig("1");
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test update.
     */
    public void testUpdateSystemConfig() {
        SystemConfigDao systemConfigDao = (SystemConfigDao) this.getApplicationContext().getBean("systemConfigDao");
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setConfigName("yangzj");
        

        systemConfig.setSubSystem("5");
        systemConfig.setLastUpdate(new Date());
        systemConfigDao.updateSystemConfig(systemConfig);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
//    public void testFindSystemConfig() {
//        SystemConfigDao systemConfigDao = (SystemConfigDao) this.getApplicationContext().getBean("systemConfigDao");
//        SystemConfig systemConfig = systemConfigDao.findSystemConfig("yangzj");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("configName=" + systemConfig.getConfigName());
//        System.out.println("configValue=" + systemConfig.getConfigValue());
//        System.out.println("valueType=" + systemConfig.getValueType());
//        System.out.println("valueRange=" + systemConfig.getValueRange());
//        System.out.println("configDesc=" + systemConfig.getConfigDesc());
//        System.out.println("visibaleFlag=" + systemConfig.getVisibaleFlag());
//        System.out.println("configRegion=" + systemConfig.getConfigRegion());
//        System.out.println("subSystem=" + systemConfig.getSubSystem());
//        System.out.println("lastUpdate=" + systemConfig.getLastUpdate());
//    }

    /**
     * test list.
     */
//    public void testListSystemConfig() {
//        SystemConfigDao systemConfigDao = (SystemConfigDao) this.getApplicationContext().getBean("systemConfigDao");
//        SystemConfig systemConfig = new SystemConfig();
//        systemConfig.setConfigName("1");
//        systemConfig.setConfigValue("1");
//        systemConfig.setValueType(2);
//        systemConfig.setValueRange("1");
//        systemConfig.setConfigDesc("1");
//        systemConfig.setVisibaleFlag("1");
//        systemConfig.setConfigRegion("1");
//        systemConfig.setSubSystem("1");
//        systemConfig.setLastUpdate(null);
//
//        System.out.println("testList result--------------------------------------");
//        List list =  systemConfigDao.listSystemConfig(systemConfig);
//        for (int i = 0; i < list.size(); i++) {
//            SystemConfig tmpSystemConfig = (SystemConfig) list.get(i);
//            System.out.println(i + " configName=" + tmpSystemConfig.getConfigName());
//            System.out.println(i + " configValue=" + tmpSystemConfig.getConfigValue());
//            System.out.println(i + " valueType=" + tmpSystemConfig.getValueType());
//            System.out.println(i + " valueRange=" + tmpSystemConfig.getValueRange());
//            System.out.println(i + " configDesc=" + tmpSystemConfig.getConfigDesc());
//            System.out.println(i + " visibaleFlag=" + tmpSystemConfig.getVisibaleFlag());
//            System.out.println(i + " configRegion=" + tmpSystemConfig.getConfigRegion());
//            System.out.println(i + " subSystem=" + tmpSystemConfig.getSubSystem());
//            System.out.println(i + " lastUpdate=" + tmpSystemConfig.getLastUpdate());
//        }
//
//    }
    
   
}
