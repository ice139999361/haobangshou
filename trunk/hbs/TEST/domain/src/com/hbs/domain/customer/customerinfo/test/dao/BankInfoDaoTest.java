package com.hbs.domain.customer.customerinfo.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.BankInfoDao;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;

/**
 * test BankInfoDao.
 * @author hbs
 *
 */
public class BankInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-BankInfo.xml)
        return new String[] { "classpath:/applicationContext-test-bankInfo.xml", "classpath:/applicationContext-test-bankInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertBankInfo() {
        BankInfoDao bankInfoDao = (BankInfoDao)this.getApplicationContext().getBean("bankInfoDao");
        BankInfo bankInfo = new BankInfo();
        bankInfo.setCommCode("1");
        bankInfo.setState("1");
        bankInfo.setAccountName("1");
        bankInfo.setAccountBank("1");
        bankInfo.setAccount("1");
   //     String id = bankInfoDao.insertBankInfo(bankInfo);
        System.out.println("testInsert result--------------------------------------");
   //     System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteBankInfo() {
        BankInfoDao bankInfoDao = (BankInfoDao) this.getApplicationContext().getBean("bankInfoDao");
        BankInfo bankInfo = new BankInfo();
       // bankInfoDao.deleteBankInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateBankInfo() {
        BankInfoDao bankInfoDao = (BankInfoDao) this.getApplicationContext().getBean("bankInfoDao");
        BankInfo bankInfo = new BankInfo();
        bankInfo.setCommCode("1");
        bankInfo.setState("1");
        bankInfo.setAccountName("1");
        bankInfo.setAccountBank("1");
        bankInfo.setAccount("1");
        bankInfoDao.updateBankInfo(bankInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindBankInfo() {
        BankInfoDao bankInfoDao = (BankInfoDao) this.getApplicationContext().getBean("bankInfoDao");
//        BankInfo bankInfo = bankInfoDao.findBankInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + bankInfo.getCommCode());
//        System.out.println("state=" + bankInfo.getState());
//        System.out.println("accountName=" + bankInfo.getAccountName());
//        System.out.println("accountBank=" + bankInfo.getAccountBank());
//        System.out.println("account=" + bankInfo.getAccount());
    }

    /**
     * test list.
     */
    public void testListBankInfo() {
        BankInfoDao bankInfoDao = (BankInfoDao) this.getApplicationContext().getBean("bankInfoDao");
        BankInfo bankInfo = new BankInfo();
        bankInfo.setCommCode("1");
        bankInfo.setState("1");
        bankInfo.setAccountName("1");
        bankInfo.setAccountBank("1");
        bankInfo.setAccount("1");

        System.out.println("testList result--------------------------------------");
        List list =  bankInfoDao.listBankInfo(bankInfo);
        for (int i = 0; i < list.size(); i++) {
            BankInfo tmpBankInfo = (BankInfo) list.get(i);
            System.out.println(i + " commCode=" + tmpBankInfo.getCommCode());
            System.out.println(i + " state=" + tmpBankInfo.getState());
            System.out.println(i + " accountName=" + tmpBankInfo.getAccountName());
            System.out.println(i + " accountBank=" + tmpBankInfo.getAccountBank());
            System.out.println(i + " account=" + tmpBankInfo.getAccount());
        }

    }
    
    /**
     * test list.
     */
    public void testListBankInfoCount() {
        BankInfoDao bankInfoDao = (BankInfoDao) this.getApplicationContext().getBean("bankInfoDao");
        BankInfo bankInfo = new BankInfo();
        bankInfo.setCommCode("1");
        bankInfo.setState("1");
        bankInfo.setAccountName("1");
        bankInfo.setAccountBank("1");
        bankInfo.setAccount("1");

        System.out.println("testListCount result--------------------------------------");
//        Integer count =  bankInfoDao.listBankInfoCount(bankInfo);
//		System.out.println("count=" + count);
    }
}
