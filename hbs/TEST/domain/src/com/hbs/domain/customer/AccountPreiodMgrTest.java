/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.domain.customer;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.common.manager.baseinfo.AccountPreiodMgr;
import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustAccountPreiodMgr;
import com.hbs.customerinfo.manager.CustomerInfoMgr;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * @author Administrator
 *
 */
public class AccountPreiodMgrTest{

	
	
	/**
	 * Test method for {@link com.hbs.customerinfo.manager.CustAccountPreiodMgr#insertAccountPreiod(com.hbs.domain.common.pojo.baseinfo.AccountPreiod)}.
	 */
//	@Test
//	public void testInsertAccountPreiod() {
//		BeanLocator.setConfigurationFileName("applicationContext.xml");
//		try{
//		AccountPreiodMgr mgr = new CustAccountPreiodMgr();
//		AccountPreiod accountPreiod = new AccountPreiod();
//		accountPreiod.setBaseSeqId("11");
//        accountPreiod.setCommCode("3");
//        accountPreiod.setState("2");
//        accountPreiod.setAccountType("1");
//        accountPreiod.setAccountPeriod("1");
//        accountPreiod.setPeriodStart("15");
//        accountPreiod.setAccounDay("5");
//        accountPreiod.setSettlementDay("2");
//        accountPreiod.setMaxMoney(new BigDecimal(500));
//        accountPreiod.setReminderDay("1");  
//        
//        int i = mgr.insertAccountPreiod(accountPreiod);
//       
//        System.out.println("testInsert result--------------------------------------" + i);
//		}catch(Exception e){
//        	e.printStackTrace();
//        }
////        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
       
//	}

	/**
	 * Test method for {@link com.hbs.customerinfo.manager.CustAccountPreiodMgr#updateAccountPreiod(com.hbs.domain.common.pojo.baseinfo.AccountPreiod, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdateAccountPreiod() {
		BeanLocator.setConfigurationFileName("applicationContext.xml");
		try{
		//CustomerInfoMgr mgr = new CustomerInfoMgr();
			CustomerInfoMgr mgr =(CustomerInfoMgr)	BeanLocator.getInstance().getBean("customerInfoMgr");
		CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCommCode("9");
        customerInfo.setState("1");
        customerInfo.setShortName("1");
        customerInfo.setEnShortName("1");
        customerInfo.setAllName("1");
        customerInfo.setEnName("1");
        customerInfo.setAddress("1");
        customerInfo.setEnAddress("1");
        customerInfo.setCommType("1");
        customerInfo.setCommScale("1");
        customerInfo.setWebSite("1");
        customerInfo.setRepresentative("1");
        customerInfo.setTaxCode("1");
        customerInfo.setCompanyBranch("1");
        customerInfo.setCreditRate("1");
        customerInfo.setCreditDesc("1");
        customerInfo.setImportantCode("1");
        customerInfo.setImportantDesc("1");
        customerInfo.setSettlementType("1");
        customerInfo.setSettlementDesc("1");
        customerInfo.setCurrency("1");
        customerInfo.setCurrencyDesc("1");
        customerInfo.setStaffId("1");
        customerInfo.setStaffName("1");
        customerInfo.setCommDesc("1");
        customerInfo.setVendorCode("1");
        customerInfo.setContactFee(new BigDecimal(0.04));
        customerInfo.setIsShowPrice("1");
        customerInfo.setTaxRate(new BigDecimal(0.17));
        customerInfo.setAssStaffId("1");
        customerInfo.setAssStaffName("1");
		//AccountPreiodMgr mgr = new CustAccountPreiodMgr();
		AccountPreiod accountPreiod = new AccountPreiod();
		accountPreiod.setSeqId("3");
		accountPreiod.setBaseSeqId("11");
        accountPreiod.setCommCode("9");
        accountPreiod.setState("0");
        accountPreiod.setAccountType("1");
        accountPreiod.setAccountPeriod("1");
        accountPreiod.setPeriodStart("15");
        accountPreiod.setAccounDay("5");
        accountPreiod.setSettlementDay("2");
        accountPreiod.setMaxMoney(new BigDecimal(500));
        accountPreiod.setReminderDay("123");  
        customerInfo.setAccountPreiod(accountPreiod);
        int i = mgr.saveTempCustomerInfo(customerInfo);
//        int i = mgr.updateAccountPreiod(accountPreiod, "1", "yangzj","123456");
//       
        System.out.println("testInsert result--------------------------------------" + i);
		}catch(Exception e){
        	e.printStackTrace();
        }

       
	}

	/**
	 * Test method for {@link com.hbs.customerinfo.manager.CustAccountPreiodMgr#updateAccountPreiodByState(com.hbs.domain.common.pojo.baseinfo.AccountPreiod, java.lang.String, java.lang.String)}.
	 */
//	@Test
//	public void testUpdateAccountPreiodByState() {
//		fail("Not yet implemented");
//	}

}
