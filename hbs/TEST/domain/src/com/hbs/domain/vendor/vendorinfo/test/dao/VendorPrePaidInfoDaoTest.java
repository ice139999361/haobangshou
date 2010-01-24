package com.hbs.domain.vendor.vendorinfo.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;

/**
 * test PrePaidInfoDao.
 * @author hbs
 *
 */
public class VendorPrePaidInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-PrePaidInfo.xml)
        return new String[] { "classpath:/applicationContext-test-prePaidInfo.xml", "classpath:/applicationContext-test-prePaidInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertPrePaidInfo() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao)this.getApplicationContext().getBean("prePaidInfoDao");
        PrePaidInfo prePaidInfo = new PrePaidInfo();
        prePaidInfo.setCommCode("1");
        prePaidInfo.setState("1");
        prePaidInfo.setPrePaid("1");
        prePaidInfo.setReminderDay("1");
        //String id = prePaidInfoDao.insertPrePaidInfo(prePaidInfo);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeletePrePaidInfo() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao) this.getApplicationContext().getBean("prePaidInfoDao");
        PrePaidInfo prePaidInfo = new PrePaidInfo();
       // prePaidInfoDao.deletePrePaidInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdatePrePaidInfo() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao) this.getApplicationContext().getBean("prePaidInfoDao");
        PrePaidInfo prePaidInfo = new PrePaidInfo();
        prePaidInfo.setCommCode("1");
        prePaidInfo.setState("1");
        prePaidInfo.setPrePaid("1");
        prePaidInfo.setReminderDay("1");
        prePaidInfoDao.updatePrePaidInfo(prePaidInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindPrePaidInfo() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao) this.getApplicationContext().getBean("prePaidInfoDao");
//        PrePaidInfo prePaidInfo = prePaidInfoDao.findPrePaidInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + prePaidInfo.getCommCode());
//        System.out.println("state=" + prePaidInfo.getState());
//        System.out.println("prePaid=" + prePaidInfo.getPrePaid());
//        System.out.println("reminderDay=" + prePaidInfo.getReminderDay());
    }

    /**
     * test list.
     */
    public void testListPrePaidInfo() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao) this.getApplicationContext().getBean("prePaidInfoDao");
        PrePaidInfo prePaidInfo = new PrePaidInfo();
        prePaidInfo.setCommCode("1");
        prePaidInfo.setState("1");
        prePaidInfo.setPrePaid("1");
        prePaidInfo.setReminderDay("1");

        System.out.println("testList result--------------------------------------");
        List list =  prePaidInfoDao.listPrePaidInfo(prePaidInfo);
        for (int i = 0; i < list.size(); i++) {
            PrePaidInfo tmpPrePaidInfo = (PrePaidInfo) list.get(i);
            System.out.println(i + " commCode=" + tmpPrePaidInfo.getCommCode());
            System.out.println(i + " state=" + tmpPrePaidInfo.getState());
            System.out.println(i + " prePaid=" + tmpPrePaidInfo.getPrePaid());
            System.out.println(i + " reminderDay=" + tmpPrePaidInfo.getReminderDay());
        }

    }
    
    /**
     * test list.
     */
    public void testListPrePaidInfoCount() {
        PrePaidInfoDao prePaidInfoDao = (PrePaidInfoDao) this.getApplicationContext().getBean("prePaidInfoDao");
        PrePaidInfo prePaidInfo = new PrePaidInfo();
        prePaidInfo.setCommCode("1");
        prePaidInfo.setState("1");
        prePaidInfo.setPrePaid("1");
        prePaidInfo.setReminderDay("1");

        System.out.println("testListCount result--------------------------------------");
//        Integer count =  prePaidInfoDao.listPrePaidInfoCount(prePaidInfo);
//		System.out.println("count=" + count);
    }
}
