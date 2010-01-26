package com.hbs.domain.vendor.vendorinfo.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;

/**
 * test VendorInfoDao.
 * @author hbs
 *
 */
public class VendorInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-VendorInfo.xml)
        return new String[] { "classpath:/applicationContext-test-vendorInfo.xml", "classpath:/applicationContext-test-vendorInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertVendorInfo() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao)this.getApplicationContext().getBean("vendorInfoDao");
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfo.setCommCode("1");
        vendorInfo.setState("1");
        vendorInfo.setShortName("1");
        vendorInfo.setEnShortName("1");
        vendorInfo.setAllName("1");
        vendorInfo.setEnName("1");
        vendorInfo.setAddress("1");
        vendorInfo.setEnAddress("1");
        vendorInfo.setCommType("1");
        vendorInfo.setCommScale("1");
        vendorInfo.setWebSite("1");
        vendorInfo.setRepresentative("1");
        vendorInfo.setTaxCode("1");
        vendorInfo.setCompanyBranch("1");
        vendorInfo.setCreditRate("1");
        vendorInfo.setCreditDesc("1");
        vendorInfo.setImportantCode("1");
        vendorInfo.setImportantDesc("1");
        vendorInfo.setSettlementType("1");
        vendorInfo.setSettlementDesc("1");
        vendorInfo.setCurrency("1");
        vendorInfo.setCurrencyDesc("1");
        vendorInfo.setStaffId("1");
        vendorInfo.setStaffName("1");
        vendorInfo.setCommDesc("1");
        vendorInfo.setVendorCode("1");
        //vendorInfo.setContactFee("1");
        vendorInfo.setIsShowPrice("1");
        //vendorInfo.setTaxRate("1");
        //String id = vendorInfoDao.insertVendorInfo(vendorInfo);
        System.out.println("testInsert result--------------------------------------");
        //System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteVendorInfo() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao) this.getApplicationContext().getBean("vendorInfoDao");
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfoDao.deleteVendorInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateVendorInfo() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao) this.getApplicationContext().getBean("vendorInfoDao");
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfo.setCommCode("1");
        vendorInfo.setState("1");
        vendorInfo.setShortName("1");
        vendorInfo.setEnShortName("1");
        vendorInfo.setAllName("1");
        vendorInfo.setEnName("1");
        vendorInfo.setAddress("1");
        vendorInfo.setEnAddress("1");
        vendorInfo.setCommType("1");
        vendorInfo.setCommScale("1");
        vendorInfo.setWebSite("1");
        vendorInfo.setRepresentative("1");
        vendorInfo.setTaxCode("1");
        vendorInfo.setCompanyBranch("1");
        vendorInfo.setCreditRate("1");
        vendorInfo.setCreditDesc("1");
        vendorInfo.setImportantCode("1");
        vendorInfo.setImportantDesc("1");
        vendorInfo.setSettlementType("1");
        vendorInfo.setSettlementDesc("1");
        vendorInfo.setCurrency("1");
        vendorInfo.setCurrencyDesc("1");
        vendorInfo.setStaffId("1");
        vendorInfo.setStaffName("1");
        vendorInfo.setCommDesc("1");
        vendorInfo.setVendorCode("1");
        //vendorInfo.setContactFee("1");
        vendorInfo.setIsShowPrice("1");
        //vendorInfo.setTaxRate("1");
        vendorInfoDao.updateVendorInfo(vendorInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindVendorInfo() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao) this.getApplicationContext().getBean("vendorInfoDao");
//        VendorInfo vendorInfo = vendorInfoDao.findVendorInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + vendorInfo.getCommCode());
//        System.out.println("state=" + vendorInfo.getState());
//        System.out.println("shortName=" + vendorInfo.getShortName());
//        System.out.println("enShortName=" + vendorInfo.getEnShortName());
//        System.out.println("allName=" + vendorInfo.getAllName());
//        System.out.println("enName=" + vendorInfo.getEnName());
//        System.out.println("address=" + vendorInfo.getAddress());
//        System.out.println("enAddress=" + vendorInfo.getEnAddress());
//        System.out.println("commType=" + vendorInfo.getCommType());
//        System.out.println("commScale=" + vendorInfo.getCommScale());
//        System.out.println("webSite=" + vendorInfo.getWebSite());
//        System.out.println("representative=" + vendorInfo.getRepresentative());
//        System.out.println("taxCode=" + vendorInfo.getTaxCode());
//        System.out.println("companyBranch=" + vendorInfo.getCompanyBranch());
//        System.out.println("creditRate=" + vendorInfo.getCreditRate());
//        System.out.println("creditDesc=" + vendorInfo.getCreditDesc());
//        System.out.println("importantCode=" + vendorInfo.getImportantCode());
//        System.out.println("importantDesc=" + vendorInfo.getImportantDesc());
//        System.out.println("settlementType=" + vendorInfo.getSettlementType());
//        System.out.println("settlementDesc=" + vendorInfo.getSettlementDesc());
//        System.out.println("currency=" + vendorInfo.getCurrency());
//        System.out.println("currencyDesc=" + vendorInfo.getCurrencyDesc());
//        System.out.println("staffId=" + vendorInfo.getStaffId());
//        System.out.println("staffName=" + vendorInfo.getStaffName());
//        System.out.println("commDesc=" + vendorInfo.getCommDesc());
//        System.out.println("vendorCode=" + vendorInfo.getVendorCode());
//        System.out.println("contactFee=" + vendorInfo.getContactFee());
//        System.out.println("isShowPrice=" + vendorInfo.getIsShowPrice());
//        System.out.println("taxRate=" + vendorInfo.getTaxRate());
    }

    /**
     * test list.
     */
    public void testListVendorInfo() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao) this.getApplicationContext().getBean("vendorInfoDao");
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfo.setCommCode("1");
        vendorInfo.setState("1");
        vendorInfo.setShortName("1");
        vendorInfo.setEnShortName("1");
        vendorInfo.setAllName("1");
        vendorInfo.setEnName("1");
        vendorInfo.setAddress("1");
        vendorInfo.setEnAddress("1");
        vendorInfo.setCommType("1");
        vendorInfo.setCommScale("1");
        vendorInfo.setWebSite("1");
        vendorInfo.setRepresentative("1");
        vendorInfo.setTaxCode("1");
        vendorInfo.setCompanyBranch("1");
        vendorInfo.setCreditRate("1");
        vendorInfo.setCreditDesc("1");
        vendorInfo.setImportantCode("1");
        vendorInfo.setImportantDesc("1");
        vendorInfo.setSettlementType("1");
        vendorInfo.setSettlementDesc("1");
        vendorInfo.setCurrency("1");
        vendorInfo.setCurrencyDesc("1");
        vendorInfo.setStaffId("1");
        vendorInfo.setStaffName("1");
        vendorInfo.setCommDesc("1");
        vendorInfo.setVendorCode("1");
        //vendorInfo.setContactFee("1");
        vendorInfo.setIsShowPrice("1");
        //vendorInfo.setTaxRate("1");

        System.out.println("testList result--------------------------------------");
        List list =  vendorInfoDao.listVendorInfo(vendorInfo);
        for (int i = 0; i < list.size(); i++) {
            VendorInfo tmpVendorInfo = (VendorInfo) list.get(i);
            System.out.println(i + " commCode=" + tmpVendorInfo.getCommCode());
            System.out.println(i + " state=" + tmpVendorInfo.getState());
            System.out.println(i + " shortName=" + tmpVendorInfo.getShortName());
            System.out.println(i + " enShortName=" + tmpVendorInfo.getEnShortName());
            System.out.println(i + " allName=" + tmpVendorInfo.getAllName());
            System.out.println(i + " enName=" + tmpVendorInfo.getEnName());
            System.out.println(i + " address=" + tmpVendorInfo.getAddress());
            System.out.println(i + " enAddress=" + tmpVendorInfo.getEnAddress());
            System.out.println(i + " commType=" + tmpVendorInfo.getCommType());
            System.out.println(i + " commScale=" + tmpVendorInfo.getCommScale());
            System.out.println(i + " webSite=" + tmpVendorInfo.getWebSite());
            System.out.println(i + " representative=" + tmpVendorInfo.getRepresentative());
            System.out.println(i + " taxCode=" + tmpVendorInfo.getTaxCode());
            System.out.println(i + " companyBranch=" + tmpVendorInfo.getCompanyBranch());
            System.out.println(i + " creditRate=" + tmpVendorInfo.getCreditRate());
            System.out.println(i + " creditDesc=" + tmpVendorInfo.getCreditDesc());
            System.out.println(i + " importantCode=" + tmpVendorInfo.getImportantCode());
            System.out.println(i + " importantDesc=" + tmpVendorInfo.getImportantDesc());
            System.out.println(i + " settlementType=" + tmpVendorInfo.getSettlementType());
            System.out.println(i + " settlementDesc=" + tmpVendorInfo.getSettlementDesc());
            System.out.println(i + " currency=" + tmpVendorInfo.getCurrency());
            System.out.println(i + " currencyDesc=" + tmpVendorInfo.getCurrencyDesc());
            System.out.println(i + " staffId=" + tmpVendorInfo.getStaffId());
            System.out.println(i + " staffName=" + tmpVendorInfo.getStaffName());
            System.out.println(i + " commDesc=" + tmpVendorInfo.getCommDesc());
            System.out.println(i + " vendorCode=" + tmpVendorInfo.getVendorCode());
            System.out.println(i + " contactFee=" + tmpVendorInfo.getContactFee());
            System.out.println(i + " isShowPrice=" + tmpVendorInfo.getIsShowPrice());
            System.out.println(i + " taxRate=" + tmpVendorInfo.getTaxRate());
        }

    }
    
    /**
     * test list.
     */
    public void testListVendorInfoCount() {
        VendorInfoDao vendorInfoDao = (VendorInfoDao) this.getApplicationContext().getBean("vendorInfoDao");
        VendorInfo vendorInfo = new VendorInfo();
        vendorInfo.setCommCode("1");
        vendorInfo.setState("1");
        vendorInfo.setShortName("1");
        vendorInfo.setEnShortName("1");
        vendorInfo.setAllName("1");
        vendorInfo.setEnName("1");
        vendorInfo.setAddress("1");
        vendorInfo.setEnAddress("1");
        vendorInfo.setCommType("1");
        vendorInfo.setCommScale("1");
        vendorInfo.setWebSite("1");
        vendorInfo.setRepresentative("1");
        vendorInfo.setTaxCode("1");
        vendorInfo.setCompanyBranch("1");
        vendorInfo.setCreditRate("1");
        vendorInfo.setCreditDesc("1");
        vendorInfo.setImportantCode("1");
        vendorInfo.setImportantDesc("1");
        vendorInfo.setSettlementType("1");
        vendorInfo.setSettlementDesc("1");
        vendorInfo.setCurrency("1");
        vendorInfo.setCurrencyDesc("1");
        vendorInfo.setStaffId("1");
        vendorInfo.setStaffName("1");
        vendorInfo.setCommDesc("1");
        vendorInfo.setVendorCode("1");
       // vendorInfo.setContactFee("1");
        vendorInfo.setIsShowPrice("1");
       // vendorInfo.setTaxRate("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  vendorInfoDao.listVendorInfoCount(vendorInfo);
		System.out.println("count=" + count);
    }
}
