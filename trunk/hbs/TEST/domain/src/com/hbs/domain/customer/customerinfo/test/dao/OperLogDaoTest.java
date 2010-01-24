package com.hbs.domain.customer.customerinfo.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.OperLogDao;
import com.hbs.domain.common.pojo.baseinfo.OperLog;

/**
 * test OperLogDao.
 * @author hbs
 *
 */
public class OperLogDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-OperLog.xml)
        return new String[] { "classpath:/applicationContext-test-operLog.xml", "classpath:/applicationContext-test-operLog.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertOperLog() {
        OperLogDao operLogDao = (OperLogDao)this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = new OperLog();
        operLog.setSeqId(1);
        operLog.setOperTime(new Date());
        operLog.setStaffId("1");
        operLog.setStaffName("1");
        operLog.setOperType("1");
        operLog.setOperObject("1");
        operLog.setOperKey("1");
        operLog.setOperContent("1");
        operLog.setMemo("1");
        //String id = operLogDao.insertOperLog(operLog);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteOperLog() {
        OperLogDao operLogDao = (OperLogDao) this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = new OperLog();
       // operLogDao.deleteOperLog("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateOperLog() {
        OperLogDao operLogDao = (OperLogDao) this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = new OperLog();
        operLog.setSeqId(2);
        operLog.setOperTime(new Date());
        operLog.setStaffId("1");
        operLog.setStaffName("1");
        operLog.setOperType("1");
        operLog.setOperObject("1");
        operLog.setOperKey("1");
        operLog.setOperContent("1");
        operLog.setMemo("1");
       // operLogDao.updateOperLog(operLog);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindOperLog() {
        OperLogDao operLogDao = (OperLogDao) this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = operLogDao.findOperLog("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("seqId=" + operLog.getSeqId());
        System.out.println("operTime=" + operLog.getOperTime());
        System.out.println("staffId=" + operLog.getStaffId());
        System.out.println("staffName=" + operLog.getStaffName());
        System.out.println("operType=" + operLog.getOperType());
        System.out.println("operObject=" + operLog.getOperObject());
        System.out.println("operKey=" + operLog.getOperKey());
        System.out.println("operContent=" + operLog.getOperContent());
        System.out.println("memo=" + operLog.getMemo());
    }

    /**
     * test list.
     */
    public void testListOperLog() {
        OperLogDao operLogDao = (OperLogDao) this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = new OperLog();
        operLog.setSeqId(2);
        operLog.setOperTime(null);
        operLog.setStaffId("1");
        operLog.setStaffName("1");
        operLog.setOperType("1");
        operLog.setOperObject("1");
        operLog.setOperKey("1");
        operLog.setOperContent("1");
        operLog.setMemo("1");

        System.out.println("testList result--------------------------------------");
//        List list =  operLogDao.listOperLog(operLog);
//        for (int i = 0; i < list.size(); i++) {
//            OperLog tmpOperLog = (OperLog) list.get(i);
//            System.out.println(i + " seqId=" + tmpOperLog.getSeqId());
//            System.out.println(i + " operTime=" + tmpOperLog.getOperTime());
//            System.out.println(i + " staffId=" + tmpOperLog.getStaffId());
//            System.out.println(i + " staffName=" + tmpOperLog.getStaffName());
//            System.out.println(i + " operType=" + tmpOperLog.getOperType());
//            System.out.println(i + " operObject=" + tmpOperLog.getOperObject());
//            System.out.println(i + " operKey=" + tmpOperLog.getOperKey());
//            System.out.println(i + " operContent=" + tmpOperLog.getOperContent());
//            System.out.println(i + " memo=" + tmpOperLog.getMemo());
//        }

    }
    
    /**
     * test list.
     */
    public void testListOperLogCount() {
        OperLogDao operLogDao = (OperLogDao) this.getApplicationContext().getBean("operLogDao");
        OperLog operLog = new OperLog();
        operLog.setSeqId(2);
        operLog.setOperTime(null);
        operLog.setStaffId("1");
        operLog.setStaffName("1");
        operLog.setOperType("1");
        operLog.setOperObject("1");
        operLog.setOperKey("1");
        operLog.setOperContent("1");
        operLog.setMemo("1");

        System.out.println("testListCount result--------------------------------------");
      //  Integer count =  operLogDao.listOperLogCount(operLog);
	//	System.out.println("count=" + count);
    }
}
