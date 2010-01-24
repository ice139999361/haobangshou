package com.hbs.domain.waittask.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.waittask.dao.WaitTaskConfigInfoDao;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;

/**
 * test WaitTaskConfigInfoDao.
 * @author sims autoCoder1.0
 *
 */
public class WaitTaskConfigInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WaitTaskConfigInfo.xml)
        return new String[] { "classpath:/applicationContext-test-waitTaskConfigInfo.xml", "classpath:/applicationContext-test-waitTaskConfigInfo.xml" }; 
    } 
    
 
    /**
     * test list.
     */
//    public void testListWaitTaskConfigInfo() {
//        WaitTaskConfigInfoDao waitTaskConfigInfoDao = (WaitTaskConfigInfoDao) this.getApplicationContext().getBean("waitTaskConfigInfoDao");
//        
//
//        System.out.println("testList result--------------------------------------");
//        List list =  waitTaskConfigInfoDao.listWaitTaskConfigInfo();
//        for (int i = 0; i < list.size(); i++) {
//            WaitTaskConfigInfo tmpWaitTaskConfigInfo = (WaitTaskConfigInfo) list.get(i);
//            System.out.println(i + " configId=" + tmpWaitTaskConfigInfo.getConfigId());
//            System.out.println(i + " taskType=" + tmpWaitTaskConfigInfo.getTaskType());
//            System.out.println(i + " businessType=" + tmpWaitTaskConfigInfo.getBusinessType());
//            System.out.println(i + " url=" + tmpWaitTaskConfigInfo.getUrl());
//            System.out.println(i + " comments=" + tmpWaitTaskConfigInfo.getComments());
//            System.out.println(i + " systemName=" + tmpWaitTaskConfigInfo.getSystemName());
//        }
//
//    }
    
    /**
     * test find.
     */
    public void testFindWaitTaskConfigInfo() {
        WaitTaskConfigInfoDao waitTaskConfigInfoDao = (WaitTaskConfigInfoDao) this.getApplicationContext().getBean("waitTaskConfigInfoDao");
        WaitTaskConfigInfo waitTaskConfigInfo = waitTaskConfigInfoDao.findWaitTaskConfigInfo("C001");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("configId=" + waitTaskConfigInfo.getConfigId());
        System.out.println("taskType=" + waitTaskConfigInfo.getTaskType());
        System.out.println("businessType=" + waitTaskConfigInfo.getBusinessType());
        System.out.println("url=" + waitTaskConfigInfo.getUrl());
        System.out.println("comments=" + waitTaskConfigInfo.getComments());
        System.out.println("systemName=" + waitTaskConfigInfo.getSystemName());
    }
}
