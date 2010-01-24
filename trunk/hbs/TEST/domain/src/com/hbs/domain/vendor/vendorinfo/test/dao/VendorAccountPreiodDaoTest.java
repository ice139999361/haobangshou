package com.hbs.domain.vendor.vendorinfo.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.AccountPreiodDao;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;

/**
 * test AccountPreiodDao.
 * @author hbs
 *
 */
public class VendorAccountPreiodDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-AccountPreiod.xml)
        return new String[] { "classpath:/applicationContext-test-accountPreiod.xml", "classpath:/applicationContext-test-accountPreiod.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertAccountPreiod() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao)this.getApplicationContext().getBean("accountPreiodDao");
        AccountPreiod accountPreiod = new AccountPreiod();
        accountPreiod.setCommCode("1");
        accountPreiod.setState("1");
        accountPreiod.setAccountType("1");
        accountPreiod.setAccountPeriod("1");
        accountPreiod.setPeriodStart("1");
        accountPreiod.setAccounDay("1");
        accountPreiod.setSettlementDay("1");
       // accountPreiod.setMaxMoney("1");
        accountPreiod.setReminderDay("1");
        //String id = accountPreiodDao.insertAccountPreiod(accountPreiod);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteAccountPreiod() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao) this.getApplicationContext().getBean("accountPreiodDao");
        AccountPreiod accountPreiod = new AccountPreiod();
      //  accountPreiodDao.deleteAccountPreiod("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateAccountPreiod() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao) this.getApplicationContext().getBean("accountPreiodDao");
        AccountPreiod accountPreiod = new AccountPreiod();
        accountPreiod.setCommCode("1");
        accountPreiod.setState("1");
        accountPreiod.setAccountType("1");
        accountPreiod.setAccountPeriod("1");
        accountPreiod.setPeriodStart("1");
        accountPreiod.setAccounDay("1");
        accountPreiod.setSettlementDay("1");
       // accountPreiod.setMaxMoney("1");
        accountPreiod.setReminderDay("1");
        accountPreiodDao.updateAccountPreiod(accountPreiod);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindAccountPreiod() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao) this.getApplicationContext().getBean("accountPreiodDao");
     //   AccountPreiod accountPreiod = accountPreiodDao.findAccountPreiod("1");
        
        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + accountPreiod.getCommCode());
//        System.out.println("state=" + accountPreiod.getState());
//        System.out.println("accountType=" + accountPreiod.getAccountType());
//        System.out.println("accountPeriod=" + accountPreiod.getAccountPeriod());
//        System.out.println("periodStart=" + accountPreiod.getPeriodStart());
//        System.out.println("accounDay=" + accountPreiod.getAccounDay());
//        System.out.println("settlementDay=" + accountPreiod.getSettlementDay());
//        System.out.println("maxMoney=" + accountPreiod.getMaxMoney());
//        System.out.println("reminderDay=" + accountPreiod.getReminderDay());
    }

    /**
     * test list.
     */
    public void testListAccountPreiod() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao) this.getApplicationContext().getBean("accountPreiodDao");
        AccountPreiod accountPreiod = new AccountPreiod();
        accountPreiod.setCommCode("1");
        accountPreiod.setState("1");
        accountPreiod.setAccountType("1");
        accountPreiod.setAccountPeriod("1");
        accountPreiod.setPeriodStart("1");
        accountPreiod.setAccounDay("1");
        accountPreiod.setSettlementDay("1");
       // accountPreiod.setMaxMoney("1");
        accountPreiod.setReminderDay("1");

        System.out.println("testList result--------------------------------------");
        List list =  accountPreiodDao.listAccountPreiod(accountPreiod);
        for (int i = 0; i < list.size(); i++) {
            AccountPreiod tmpAccountPreiod = (AccountPreiod) list.get(i);
            System.out.println(i + " commCode=" + tmpAccountPreiod.getCommCode());
            System.out.println(i + " state=" + tmpAccountPreiod.getState());
            System.out.println(i + " accountType=" + tmpAccountPreiod.getAccountType());
            System.out.println(i + " accountPeriod=" + tmpAccountPreiod.getAccountPeriod());
            System.out.println(i + " periodStart=" + tmpAccountPreiod.getPeriodStart());
            System.out.println(i + " accounDay=" + tmpAccountPreiod.getAccounDay());
            System.out.println(i + " settlementDay=" + tmpAccountPreiod.getSettlementDay());
            System.out.println(i + " maxMoney=" + tmpAccountPreiod.getMaxMoney());
            System.out.println(i + " reminderDay=" + tmpAccountPreiod.getReminderDay());
        }

    }
    
    /**
     * test list.
     */
    public void testListAccountPreiodCount() {
        AccountPreiodDao accountPreiodDao = (AccountPreiodDao) this.getApplicationContext().getBean("accountPreiodDao");
        AccountPreiod accountPreiod = new AccountPreiod();
        accountPreiod.setCommCode("1");
        accountPreiod.setState("1");
        accountPreiod.setAccountType("1");
        accountPreiod.setAccountPeriod("1");
        accountPreiod.setPeriodStart("1");
        accountPreiod.setAccounDay("1");
        accountPreiod.setSettlementDay("1");
       // accountPreiod.setMaxMoney("1");
        accountPreiod.setReminderDay("1");

        System.out.println("testListCount result--------------------------------------");
      //  Integer count =  accountPreiodDao.listAccountPreiodCount(accountPreiod);
		//System.out.println("count=" + count);
    }
}
