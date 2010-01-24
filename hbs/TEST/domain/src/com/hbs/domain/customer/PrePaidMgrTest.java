/**
 * system £ºhbs
 * desc:
 * version: 1.0
 * author : yangzj
 */
package com.hbs.domain.customer;

import static org.junit.Assert.*;

import org.junit.Test;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.customerinfo.manager.CustPrePaidMgr;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;

/**
 * @author Administrator
 *
 */
public class PrePaidMgrTest {

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.PrePaidMgr#insertAccountPreiod(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)}.
	 */
//	@Test
//	public void testInsertAccountPreiod() {
//		BeanLocator.setConfigurationFileName("applicationContext.xml");
//		try{
//			CustPrePaidMgr cMgr = new CustPrePaidMgr();
//		PrePaidInfo prePaidInfo = new PrePaidInfo();
//        prePaidInfo.setBaseSeqId("1");
//        prePaidInfo.setCommCode("GC0001");
//        prePaidInfo.setState("1");
//        prePaidInfo.setPrePaid("1.00");
//        prePaidInfo.setReminderDay("1");
//        int i = cMgr.insertPrePaidInfo(prePaidInfo);
//        System.out.println("result is ====" + i);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.PrePaidMgr#updateAccountPreiod(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo, java.lang.String, java.lang.String, java.lang.String)}.
	 */
//	@Test
//	public void testUpdateAccountPreiod() {
//		BeanLocator.setConfigurationFileName("applicationContext.xml");
//		try{
//			CustPrePaidMgr cMgr = new CustPrePaidMgr();
//		PrePaidInfo prePaidInfo = new PrePaidInfo();
//		prePaidInfo.setSeqId("2");
//        prePaidInfo.setBaseSeqId("1");
//        prePaidInfo.setCommCode("GC0001");
//        prePaidInfo.setState("3");
//        prePaidInfo.setPrePaid("1.00");
//        prePaidInfo.setReminderDay("10");
//        String otherInfo = "yangzj memo";
//        int i = cMgr.updatePrePaidInfo(prePaidInfo, "yangzj", "yangzj", otherInfo);
//        System.out.println("result is ====" + i);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.PrePaidMgr#getPrePaidInfo(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)}.
	 */
	@Test
	public void testGetPrePaidInfo() {
		BeanLocator.setConfigurationFileName("applicationContext.xml");
		try{
			CustPrePaidMgr cMgr = new CustPrePaidMgr();
		PrePaidInfo prePaidInfo = new PrePaidInfo();
		prePaidInfo.setSeqId("2");
        prePaidInfo.setBaseSeqId("1");
        prePaidInfo.setCommCode("GC0001");
        prePaidInfo.setState("3");
        prePaidInfo.setPrePaid("1.00");
        prePaidInfo.setReminderDay("10");
        String otherInfo = "yangzj memo";
        prePaidInfo = cMgr.getPrePaidInfo(prePaidInfo);
        System.out.println("result is ====" + prePaidInfo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.hbs.common.manager.baseinfo.PrePaidMgr#listPrePaidInfo(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)}.
	 */
//	@Test
//	public void testListPrePaidInfo() {
//		fail("Not yet implemented"); // TODO
//	}

}
