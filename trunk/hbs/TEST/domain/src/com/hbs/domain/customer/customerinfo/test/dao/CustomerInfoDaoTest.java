package com.hbs.domain.customer.customerinfo.test.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.customer.customerinfo.dao.CustomerInfoDao;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;

/**
 * test CustomerInfoDao.
 * @author hbs
 *
 */
public class CustomerInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-CustomerInfo.xml)
        return new String[] { "classpath:/applicationContext.xml", "classpath:/applicationContext.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertCustomerInfo() {
        CustomerInfoDao customerInfoDao = (CustomerInfoDao)this.getApplicationContext().getBean("customerInfoDao");
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCommCode("1");
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
        customerInfoDao.insertCustomerInfo(customerInfo);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
//    public void testDeleteCustomerInfo() {
//        CustomerInfoDao customerInfoDao = (CustomerInfoDao) this.getApplicationContext().getBean("customerInfoDao");
//        CustomerInfo customerInfo = new CustomerInfo();
//        customerInfoDao.deleteCustomerInfo("1");
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test update.
     */
//    public void testUpdateCustomerInfo() {
//        CustomerInfoDao customerInfoDao = (CustomerInfoDao) this.getApplicationContext().getBean("customerInfoDao");
//        CustomerInfo customerInfo = new CustomerInfo();
//        customerInfo.setCommCode("1");
//        customerInfo.setState("1");
//        customerInfo.setShortName("1");
//        customerInfo.setEnShortName("1");
//        customerInfo.setAllName("1");
//        customerInfo.setEnName("1");
//        customerInfo.setAddress("1");
//        customerInfo.setEnAddress("1");
//        customerInfo.setCommType("1");
//        customerInfo.setCommScale("1");
//        customerInfo.setWebSite("1");
//        customerInfo.setRepresentative("1");
//        customerInfo.setTaxCode("1");
//        customerInfo.setCompanyBranch("1");
//        customerInfo.setCreditRate("1");
//        customerInfo.setCreditDesc("1");
//        customerInfo.setImportantCode("1");
//        customerInfo.setImportantDesc("1");
//        customerInfo.setSettlementType("1");
//        customerInfo.setSettlementDesc("1");
//        customerInfo.setCurrency("1");
//        customerInfo.setCurrencyDesc("1");
//        customerInfo.setStaffId("1");
//        customerInfo.setStaffName("1");
//        customerInfo.setCommDesc("1");
//        customerInfo.setVendorCode("1");
//       // customerInfo.setContactFee("1");
//        customerInfo.setIsShowPrice("1");
//        //customerInfo.setTaxRate("1");
//        customerInfo.setAssStaffId("1");
//        customerInfo.setAssStaffName("1");
//        customerInfoDao.updateCustomerInfo(customerInfo);
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test find.
     */
//    public void testFindCustomerInfo() {
//        CustomerInfoDao customerInfoDao = (CustomerInfoDao) this.getApplicationContext().getBean("customerInfoDao");
//        CustomerInfo customerInfo = customerInfoDao.findCustomerInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + customerInfo.getCommCode());
//        System.out.println("state=" + customerInfo.getState());
//        System.out.println("shortName=" + customerInfo.getShortName());
//        System.out.println("enShortName=" + customerInfo.getEnShortName());
//        System.out.println("allName=" + customerInfo.getAllName());
//        System.out.println("enName=" + customerInfo.getEnName());
//        System.out.println("address=" + customerInfo.getAddress());
//        System.out.println("enAddress=" + customerInfo.getEnAddress());
//        System.out.println("commType=" + customerInfo.getCommType());
//        System.out.println("commScale=" + customerInfo.getCommScale());
//        System.out.println("webSite=" + customerInfo.getWebSite());
//        System.out.println("representative=" + customerInfo.getRepresentative());
//        System.out.println("taxCode=" + customerInfo.getTaxCode());
//        System.out.println("companyBranch=" + customerInfo.getCompanyBranch());
//        System.out.println("creditRate=" + customerInfo.getCreditRate());
//        System.out.println("creditDesc=" + customerInfo.getCreditDesc());
//        System.out.println("importantCode=" + customerInfo.getImportantCode());
//        System.out.println("importantDesc=" + customerInfo.getImportantDesc());
//        System.out.println("settlementType=" + customerInfo.getSettlementType());
//        System.out.println("settlementDesc=" + customerInfo.getSettlementDesc());
//        System.out.println("currency=" + customerInfo.getCurrency());
//        System.out.println("currencyDesc=" + customerInfo.getCurrencyDesc());
//        System.out.println("staffId=" + customerInfo.getStaffId());
//        System.out.println("staffName=" + customerInfo.getStaffName());
//        System.out.println("commDesc=" + customerInfo.getCommDesc());
//        System.out.println("vendorCode=" + customerInfo.getVendorCode());
//        System.out.println("contactFee=" + customerInfo.getContactFee());
//        System.out.println("isShowPrice=" + customerInfo.getIsShowPrice());
//        System.out.println("taxRate=" + customerInfo.getTaxRate());
//        System.out.println("assStaffId=" + customerInfo.getAssStaffId());
//        System.out.println("assStaffName=" + customerInfo.getAssStaffName());
//    }

    /**
     * test list.
     */
//    public void testListCustomerInfo() {
//        CustomerInfoDao customerInfoDao = (CustomerInfoDao) this.getApplicationContext().getBean("customerInfoDao");
//        CustomerInfo customerInfo = new CustomerInfo();
//        customerInfo.setCommCode("1");
//        customerInfo.setState("1");
//        customerInfo.setShortName("1");
//        customerInfo.setEnShortName("1");
//        customerInfo.setAllName("1");
//        customerInfo.setEnName("1");
//        customerInfo.setAddress("1");
//        customerInfo.setEnAddress("1");
//        customerInfo.setCommType("1");
//        customerInfo.setCommScale("1");
//        customerInfo.setWebSite("1");
//        customerInfo.setRepresentative("1");
//        customerInfo.setTaxCode("1");
//        customerInfo.setCompanyBranch("1");
//        customerInfo.setCreditRate("1");
//        customerInfo.setCreditDesc("1");
//        customerInfo.setImportantCode("1");
//        customerInfo.setImportantDesc("1");
//        customerInfo.setSettlementType("1");
//        customerInfo.setSettlementDesc("1");
//        customerInfo.setCurrency("1");
//        customerInfo.setCurrencyDesc("1");
//        customerInfo.setStaffId("1");
//        customerInfo.setStaffName("1");
//        customerInfo.setCommDesc("1");
//        customerInfo.setVendorCode("1");
//        //customerInfo.setContactFee("1");
//        customerInfo.setIsShowPrice("1");
//       // customerInfo.setTaxRate("1");
//        customerInfo.setAssStaffId("1");
//        customerInfo.setAssStaffName("1");
//
//        System.out.println("testList result--------------------------------------");
//        List list =  customerInfoDao.listCustomerInfo(customerInfo);
//        for (int i = 0; i < list.size(); i++) {
//            CustomerInfo tmpCustomerInfo = (CustomerInfo) list.get(i);
//            System.out.println(i + " commCode=" + tmpCustomerInfo.getCommCode());
//            System.out.println(i + " state=" + tmpCustomerInfo.getState());
//            System.out.println(i + " shortName=" + tmpCustomerInfo.getShortName());
//            System.out.println(i + " enShortName=" + tmpCustomerInfo.getEnShortName());
//            System.out.println(i + " allName=" + tmpCustomerInfo.getAllName());
//            System.out.println(i + " enName=" + tmpCustomerInfo.getEnName());
//            System.out.println(i + " address=" + tmpCustomerInfo.getAddress());
//            System.out.println(i + " enAddress=" + tmpCustomerInfo.getEnAddress());
//            System.out.println(i + " commType=" + tmpCustomerInfo.getCommType());
//            System.out.println(i + " commScale=" + tmpCustomerInfo.getCommScale());
//            System.out.println(i + " webSite=" + tmpCustomerInfo.getWebSite());
//            System.out.println(i + " representative=" + tmpCustomerInfo.getRepresentative());
//            System.out.println(i + " taxCode=" + tmpCustomerInfo.getTaxCode());
//            System.out.println(i + " companyBranch=" + tmpCustomerInfo.getCompanyBranch());
//            System.out.println(i + " creditRate=" + tmpCustomerInfo.getCreditRate());
//            System.out.println(i + " creditDesc=" + tmpCustomerInfo.getCreditDesc());
//            System.out.println(i + " importantCode=" + tmpCustomerInfo.getImportantCode());
//            System.out.println(i + " importantDesc=" + tmpCustomerInfo.getImportantDesc());
//            System.out.println(i + " settlementType=" + tmpCustomerInfo.getSettlementType());
//            System.out.println(i + " settlementDesc=" + tmpCustomerInfo.getSettlementDesc());
//            System.out.println(i + " currency=" + tmpCustomerInfo.getCurrency());
//            System.out.println(i + " currencyDesc=" + tmpCustomerInfo.getCurrencyDesc());
//            System.out.println(i + " staffId=" + tmpCustomerInfo.getStaffId());
//            System.out.println(i + " staffName=" + tmpCustomerInfo.getStaffName());
//            System.out.println(i + " commDesc=" + tmpCustomerInfo.getCommDesc());
//            System.out.println(i + " vendorCode=" + tmpCustomerInfo.getVendorCode());
//            System.out.println(i + " contactFee=" + tmpCustomerInfo.getContactFee());
//            System.out.println(i + " isShowPrice=" + tmpCustomerInfo.getIsShowPrice());
//            System.out.println(i + " taxRate=" + tmpCustomerInfo.getTaxRate());
//            System.out.println(i + " assStaffId=" + tmpCustomerInfo.getAssStaffId());
//            System.out.println(i + " assStaffName=" + tmpCustomerInfo.getAssStaffName());
//        }
//
//    }
//    
    /**
     * test list.
     */
//    public void testListCustomerInfoCount() {
//        CustomerInfoDao customerInfoDao = (CustomerInfoDao) this.getApplicationContext().getBean("customerInfoDao");
//        CustomerInfo customerInfo = new CustomerInfo();
//        customerInfo.setCommCode("1");
//        customerInfo.setState("1");
//        customerInfo.setShortName("1");
//        customerInfo.setEnShortName("1");
//        customerInfo.setAllName("1");
//        customerInfo.setEnName("1");
//        customerInfo.setAddress("1");
//        customerInfo.setEnAddress("1");
//        customerInfo.setCommType("1");
//        customerInfo.setCommScale("1");
//        customerInfo.setWebSite("1");
//        customerInfo.setRepresentative("1");
//        customerInfo.setTaxCode("1");
//        customerInfo.setCompanyBranch("1");
//        customerInfo.setCreditRate("1");
//        customerInfo.setCreditDesc("1");
//        customerInfo.setImportantCode("1");
//        customerInfo.setImportantDesc("1");
//        customerInfo.setSettlementType("1");
//        customerInfo.setSettlementDesc("1");
//        customerInfo.setCurrency("1");
//        customerInfo.setCurrencyDesc("1");
//        customerInfo.setStaffId("1");
//        customerInfo.setStaffName("1");
//        customerInfo.setCommDesc("1");
//        customerInfo.setVendorCode("1");
//        //customerInfo.setContactFee("1");
//        customerInfo.setIsShowPrice("1");
//        //customerInfo.setTaxRate("1");
//        customerInfo.setAssStaffId("1");
//        customerInfo.setAssStaffName("1");
//
//        System.out.println("testListCount result--------------------------------------");
//        Integer count =  customerInfoDao.listCustomerInfoCount(customerInfo);
//		System.out.println("count=" + count);
//    }
}
