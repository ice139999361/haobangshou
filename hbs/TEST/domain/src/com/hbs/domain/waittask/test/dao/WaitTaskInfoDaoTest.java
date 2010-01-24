package com.hbs.domain.waittask.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.waittask.dao.WaitTaskInfoDao;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;

/**
 * test WaitTaskInfoDao.
 * @author hbs
 *
 */
public class WaitTaskInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WaitTaskInfo.xml)
        return new String[] { "classpath:/applicationContext.xml", "classpath:/applicationContext.xml" }; 
    } 
    
    /**
     * test insert.
     */
//    public void testInsertWaitTaskInfo() {
//        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao)this.getApplicationContext().getBean("waitTaskInfoDao");
//        WaitTaskInfo waitTaskInfo = new WaitTaskInfo();        
//        waitTaskInfo.setTaskType("1");
//        waitTaskInfo.setBusinessKey("bbbbb");
//        waitTaskInfo.setBusinessType("1");
//        //waitTaskInfo.setRoleId("1");
//        waitTaskInfo.setStaffId("1");
//        waitTaskInfo.setUrl("1");
//        waitTaskInfo.setComments("1");
//        waitTaskInfo.setSystemName("1");
//        waitTaskInfo.setParam("1");
//        waitTaskInfo.setCreateTime(new Date());
//        waitTaskInfo.setExpireTime(new Date());
//        waitTaskInfoDao.insertWaitTaskInfo(waitTaskInfo);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" );
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test delete.
     */
//    public void testDeleteWaitTaskInfo() {
//        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) this.getApplicationContext().getBean("waitTaskInfoDao");
//        WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
//        waitTaskInfoDao.deleteWaitTaskInfo("aaaa");
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test update.
     */
//    public void testUpdateWaitTaskInfo() {
//        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) this.getApplicationContext().getBean("waitTaskInfoDao");
//        WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
//        waitTaskInfo.setTaskId("1");
//        waitTaskInfo.setTaskType("1");
//        waitTaskInfo.setBusinessKey("aaaa");
//        waitTaskInfo.setBusinessType("1");
//        
//        waitTaskInfo.setUrl("abcd");
//        waitTaskInfo.setComments("123456");
//        waitTaskInfo.setSystemName("1");
//        waitTaskInfo.setParam("1");
//        
//        
//        waitTaskInfoDao.updateWaitTaskInfo(waitTaskInfo);
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test find.
     */
//    public void testFindWaitTaskInfo() {
//        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) this.getApplicationContext().getBean("waitTaskInfoDao");
//        WaitTaskInfo waitTaskInfo = waitTaskInfoDao.findWaitTaskInfo("aaaa");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("taskId=" + waitTaskInfo.getTaskId());
//        System.out.println("taskType=" + waitTaskInfo.getTaskType());
//        System.out.println("businessKey=" + waitTaskInfo.getBusinessKey());
//        System.out.println("businessType=" + waitTaskInfo.getBusinessType());
//        System.out.println("roleId=" + waitTaskInfo.getRoleId());
//        System.out.println("staffId=" + waitTaskInfo.getStaffId());
//        System.out.println("url=" + waitTaskInfo.getUrl());
//        System.out.println("comments=" + waitTaskInfo.getComments());
//        System.out.println("systemName=" + waitTaskInfo.getSystemName());
//        System.out.println("param=" + waitTaskInfo.getParam());
//        System.out.println("createTime=" + waitTaskInfo.getCreateTime());
//        System.out.println("expireTime=" + waitTaskInfo.getExpireTime());
//    }

    /**
     * test list.
     */
    public void testListWaitTaskInfo() {
        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) this.getApplicationContext().getBean("waitTaskInfoDao");
        WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
//        waitTaskInfo.setTaskId("1");
//        waitTaskInfo.setTaskType("1");
//        waitTaskInfo.setBusinessKey("1");
//        waitTaskInfo.setBusinessType("1");
//       waitTaskInfo.setRoleId("1");
        waitTaskInfo.setStaffId("1");
//        waitTaskInfo.setUrl("1");
//        waitTaskInfo.setComments("1");
//        waitTaskInfo.setSystemName("1");
//        waitTaskInfo.setParam("1");
//        waitTaskInfo.setCreateTime(null);
//        waitTaskInfo.setExpireTime(null);

        System.out.println("testList result--------------------------------------");
        List list =  waitTaskInfoDao.listWaitTaskInfo(waitTaskInfo);
        for (int i = 0; i < list.size(); i++) {
            WaitTaskInfo tmpWaitTaskInfo = (WaitTaskInfo) list.get(i);
            System.out.println(i + " taskId=" + tmpWaitTaskInfo.getTaskId());
            System.out.println(i + " taskType=" + tmpWaitTaskInfo.getTaskType());
            System.out.println(i + " businessKey=" + tmpWaitTaskInfo.getBusinessKey());
            System.out.println(i + " businessType=" + tmpWaitTaskInfo.getBusinessType());
            System.out.println(i + " roleId=" + tmpWaitTaskInfo.getRoleId());
            System.out.println(i + " staffId=" + tmpWaitTaskInfo.getStaffId());
            System.out.println(i + " url=" + tmpWaitTaskInfo.getUrl());
            System.out.println(i + " comments=" + tmpWaitTaskInfo.getComments());
            System.out.println(i + " systemName=" + tmpWaitTaskInfo.getSystemName());
            System.out.println(i + " param=" + tmpWaitTaskInfo.getParam());
            System.out.println(i + " createTime=" + tmpWaitTaskInfo.getCreateTime());
            System.out.println(i + " expireTime=" + tmpWaitTaskInfo.getExpireTime());
        }

    }
    
    /**
     * test list.
     */
//    public void testListWaitTaskInfoCount() {
//        WaitTaskInfoDao waitTaskInfoDao = (WaitTaskInfoDao) this.getApplicationContext().getBean("waitTaskInfoDao");
//        WaitTaskInfo waitTaskInfo = new WaitTaskInfo();
//        waitTaskInfo.setTaskId("1");
//        waitTaskInfo.setTaskType("1");
//        waitTaskInfo.setBusinessKey("1");
//        waitTaskInfo.setBusinessType("1");
//        waitTaskInfo.setRoleId("1");
//        waitTaskInfo.setStaffId("1");
//        waitTaskInfo.setUrl("1");
//        waitTaskInfo.setComments("1");
//        waitTaskInfo.setSystemName("1");
//        waitTaskInfo.setParam("1");
//        waitTaskInfo.setCreateTime(null);
//        waitTaskInfo.setExpireTime(null);
//
//        System.out.println("testListCount result--------------------------------------");
//        Integer count =  waitTaskInfoDao.listWaitTaskInfoCount(waitTaskInfo);
//		System.out.println("count=" + count);
//    }
}
