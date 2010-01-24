package com.hbs.domain.customer.customerinfo.test.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.customer.customerinfo.dao.CustPartNoInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;

/**
 * test CustPartNoInfoDao.
 * @author hbs
 *
 */
public class CustPartNoInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-CustPartNoInfo.xml)
        return new String[] { "classpath:/applicationContext.xml", "classpath:/applicationContext.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertCustPartNoInfo() {
        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao)this.getApplicationContext().getBean("customerPartNoInfoDao");
        CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
        custPartNoInfo.setCustPartNo("1");
        custPartNoInfo.setCommCode("1");
        custPartNoInfo.setState("1");
        custPartNoInfo.setPnDesc("1");
        custPartNoInfo.setPrice(new BigDecimal(0.1));
        custPartNoInfo.setPriceTax(new BigDecimal(0.1));
        custPartNoInfo.setCreateDate(new Date());
        custPartNoInfo.setStaffId("1");
        custPartNoInfo.setStaffName("1");
        custPartNoInfo.setPartNo("1");
        custPartNoInfo.setCatCode("1");
        custPartNoInfo.setClsName("1");
        custPartNoInfo.setMinAmount(1);
        custPartNoInfo.setSampleCode("1");
        custPartNoInfo.setVendorCode("1");
        int id = custPartNoInfoDao.insertCustPartNoInfo(custPartNoInfo);
        System.out.println("testInsert result--------------------------------------");
        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
//    public void testDeleteCustPartNoInfo() {
//        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao) this.getApplicationContext().getBean("custPartNoInfoDao");
//        CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
//        //custPartNoInfoDao.deleteCustPartNoInfo("1");
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test update.
     */
//    public void testUpdateCustPartNoInfo() {
//        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao) this.getApplicationContext().getBean("custPartNoInfoDao");
//        CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
//        custPartNoInfo.setCustPartNo("1");
//        custPartNoInfo.setCommCode("1");
//        custPartNoInfo.setState("1");
//        custPartNoInfo.setPnDesc("1");
//        //custPartNoInfo.setPrice("1");
//        //custPartNoInfo.setPriceTax("1");
//        custPartNoInfo.setCreateDate(new Date());
//        custPartNoInfo.setStaffId("1");
//        custPartNoInfo.setStaffName("1");
//        custPartNoInfo.setPartNo("1");
//        custPartNoInfo.setCatCode("1");
//        custPartNoInfo.setClsName("1");
//        custPartNoInfo.setMinAmount(2);
//        custPartNoInfo.setSampleCode("1");
//        custPartNoInfo.setVendorCode("1");
//        custPartNoInfoDao.updateCustPartNoInfo(custPartNoInfo);
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test find.
     */
//    public void testFindCustPartNoInfo() {
//        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao) this.getApplicationContext().getBean("custPartNoInfoDao");
////        CustPartNoInfo custPartNoInfo = custPartNoInfoDao.findCustPartNoInfo("1");
////        
////        System.out.println("testFind result--------------------------------------");
////        System.out.println("custPartNo=" + custPartNoInfo.getCustPartNo());
////        System.out.println("commCode=" + custPartNoInfo.getCommCode());
////        System.out.println("state=" + custPartNoInfo.getState());
////        System.out.println("pnDesc=" + custPartNoInfo.getPnDesc());
////        System.out.println("price=" + custPartNoInfo.getPrice());
////        System.out.println("priceTax=" + custPartNoInfo.getPriceTax());
////        System.out.println("createDate=" + custPartNoInfo.getCreateDate());
////        System.out.println("staffId=" + custPartNoInfo.getStaffId());
////        System.out.println("staffName=" + custPartNoInfo.getStaffName());
////        System.out.println("partNo=" + custPartNoInfo.getPartNo());
////        System.out.println("catCode=" + custPartNoInfo.getCatCode());
////        System.out.println("clsName=" + custPartNoInfo.getClsName());
////        System.out.println("minAmount=" + custPartNoInfo.getMinAmount());
////        System.out.println("sampleCode=" + custPartNoInfo.getSampleCode());
////        System.out.println("vendorCode=" + custPartNoInfo.getVendorCode());
//    }

    /**
     * test list.
     */
//    public void testListCustPartNoInfo() {
//        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao) this.getApplicationContext().getBean("custPartNoInfoDao");
//        CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
//        custPartNoInfo.setCustPartNo("1");
//        custPartNoInfo.setCommCode("1");
//        custPartNoInfo.setState("1");
//        custPartNoInfo.setPnDesc("1");
//        //custPartNoInfo.setPrice("1");
//        //custPartNoInfo.setPriceTax("1");
//        custPartNoInfo.setCreateDate(null);
//        custPartNoInfo.setStaffId("1");
//        custPartNoInfo.setStaffName("1");
//        custPartNoInfo.setPartNo("1");
//        custPartNoInfo.setCatCode("1");
//        custPartNoInfo.setClsName("1");
//        custPartNoInfo.setMinAmount(2);
//        custPartNoInfo.setSampleCode("1");
//        custPartNoInfo.setVendorCode("1");
//
//        System.out.println("testList result--------------------------------------");
//        List list =  custPartNoInfoDao.listCustPartNoInfo(custPartNoInfo);
//        for (int i = 0; i < list.size(); i++) {
//            CustPartNoInfo tmpCustPartNoInfo = (CustPartNoInfo) list.get(i);
//            System.out.println(i + " custPartNo=" + tmpCustPartNoInfo.getCustPartNo());
//            System.out.println(i + " commCode=" + tmpCustPartNoInfo.getCommCode());
//            System.out.println(i + " state=" + tmpCustPartNoInfo.getState());
//            System.out.println(i + " pnDesc=" + tmpCustPartNoInfo.getPnDesc());
//            System.out.println(i + " price=" + tmpCustPartNoInfo.getPrice());
//            System.out.println(i + " priceTax=" + tmpCustPartNoInfo.getPriceTax());
//            System.out.println(i + " createDate=" + tmpCustPartNoInfo.getCreateDate());
//            System.out.println(i + " staffId=" + tmpCustPartNoInfo.getStaffId());
//            System.out.println(i + " staffName=" + tmpCustPartNoInfo.getStaffName());
//            System.out.println(i + " partNo=" + tmpCustPartNoInfo.getPartNo());
//            System.out.println(i + " catCode=" + tmpCustPartNoInfo.getCatCode());
//            System.out.println(i + " clsName=" + tmpCustPartNoInfo.getClsName());
//            System.out.println(i + " minAmount=" + tmpCustPartNoInfo.getMinAmount());
//            System.out.println(i + " sampleCode=" + tmpCustPartNoInfo.getSampleCode());
//            System.out.println(i + " vendorCode=" + tmpCustPartNoInfo.getVendorCode());
//        }
//
//    }
    
    /**
     * test list.
     */
//    public void testListCustPartNoInfoCount() {
//        CustPartNoInfoDao custPartNoInfoDao = (CustPartNoInfoDao) this.getApplicationContext().getBean("custPartNoInfoDao");
//        CustPartNoInfo custPartNoInfo = new CustPartNoInfo();
//        custPartNoInfo.setCustPartNo("1");
//        custPartNoInfo.setCommCode("1");
//        custPartNoInfo.setState("1");
//        custPartNoInfo.setPnDesc("1");
//        //custPartNoInfo.setPrice("1");
//        //custPartNoInfo.setPriceTax("1");
//        custPartNoInfo.setCreateDate(null);
//        custPartNoInfo.setStaffId("1");
//        custPartNoInfo.setStaffName("1");
//        custPartNoInfo.setPartNo("1");
//        custPartNoInfo.setCatCode("1");
//        custPartNoInfo.setClsName("1");
//        custPartNoInfo.setMinAmount(2);
//        custPartNoInfo.setSampleCode("1");
//        custPartNoInfo.setVendorCode("1");
//
//        System.out.println("testListCount result--------------------------------------");
//        Integer count =  custPartNoInfoDao.listCustPartNoInfoCount(custPartNoInfo);
//		System.out.println("count=" + count);
//    }
}
