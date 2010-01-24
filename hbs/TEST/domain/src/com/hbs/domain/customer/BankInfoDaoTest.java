/**
 * system ：hbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.domain.customer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustBankInfoMgr;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;

/**
 * @author Administrator
 *
 */
public class BankInfoDaoTest {

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#insertBankInfo(com.hbs.domain.common.pojo.baseinfo.BankInfo, java.lang.String, java.lang.String)}.
	 */
//	@Test
//	public void testInsertBankInfo() {
//		BeanLocator.setConfigurationFileName("applicationContext.xml");
//		try{
//			CustBankInfoMgr cMgr = new CustBankInfoMgr();
//		BankInfo bankInfo = new BankInfo();
//		bankInfo.setBaseSeqId("1");
//        bankInfo.setCommCode("GC001");
//        bankInfo.setState("1");
//        bankInfo.setAccountName("中国银行");
//        bankInfo.setAccountBank("中国银行");
//        bankInfo.setAccount("123456789");
//        int id = cMgr.insertBankInfo(bankInfo, null, null);
//        System.out.println("testInsert result--------------------------------------" + id);
//        
//		}catch(Exception e){
//        	e.printStackTrace();
//        }
//   //     System.out.println("return id=" + id);
//	}

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#insertBankInfoList(java.util.List, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testInsertBankInfoList() {
		BeanLocator.setConfigurationFileName("applicationContext.xml");
		try{
			CustBankInfoMgr cMgr = new CustBankInfoMgr();
			List<BankInfo> list = new ArrayList<BankInfo>();
		BankInfo bankInfo = new BankInfo();
		bankInfo.setBaseSeqId("1");
        bankInfo.setCommCode("GC002");
        bankInfo.setState("1");
        bankInfo.setAccountName("中国银行");
        bankInfo.setAccountBank("中国银行");
        bankInfo.setAccount("123456789");
        list.add(bankInfo);
        bankInfo = new BankInfo();
        bankInfo.setBaseSeqId("1");
        bankInfo.setCommCode("GC002");
        bankInfo.setState("1");
        bankInfo.setAccountName("中国人们");
        bankInfo.setAccountBank("中国人们");
        bankInfo.setAccount("000000000");
        list.add(bankInfo);
        
        int id = cMgr.insertBankInfoList(list, null, null);
        System.out.println("testInsert result--------------------------------------" + id);
        
		}catch(Exception e){
        	e.printStackTrace();
        }
	}
//
//	/**
//	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#updateBankInfo(com.hbs.domain.common.pojo.baseinfo.BankInfo, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testUpdateBankInfo() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#updateBankInfoList(java.util.List, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testUpdateBankInfoList() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#getBankInfo(java.lang.String)}.
//	 */
//	@Test
//	public void testGetBankInfo() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	/**
//	 * Test method for {@link com.hbs.common.manager.baseinfo.BankInfoMgr#listBankInfo(com.hbs.domain.common.pojo.baseinfo.BankInfo)}.
//	 */
//	@Test
//	public void testListBankInfo() {
//		fail("Not yet implemented"); // TODO
//	}

}
