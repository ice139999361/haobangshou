package com.hbs.domain.vendor.vendorinfo.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.vendor.vendorinfo.dao.VendorPartNoInfoDao;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;

/**
 * test VendorPartNoInfoDao.
 * @author hbs
 *
 */
public class VendorPartNoInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-VendorPartNoInfo.xml)
        return new String[] { "classpath:/applicationContext-test-vendorPartNoInfo.xml", "classpath:/applicationContext-test-vendorPartNoInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertVendorPartNoInfo() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao)this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
        vendorPartNoInfo.setCustPartNo("1");
        vendorPartNoInfo.setCommCode("1");
        vendorPartNoInfo.setState("1");
        vendorPartNoInfo.setPnDesc("1");
        //vendorPartNoInfo.setPrice("1");
        //vendorPartNoInfo.setPriceTax("1");
        vendorPartNoInfo.setCreateDate(new Date());
        vendorPartNoInfo.setStaffId("1");
        vendorPartNoInfo.setStaffName("1");
        vendorPartNoInfo.setPartNo("1");
        vendorPartNoInfo.setCatCode("1");
        vendorPartNoInfo.setClsName("1");
        vendorPartNoInfo.setMinAmount(1);
        vendorPartNoInfo.setMinPackage(1);
        vendorPartNoInfo.setSampleCode("1");
        //String id = vendorPartNoInfoDao.insertVendorPartNoInfo(vendorPartNoInfo);
        System.out.println("testInsert result--------------------------------------");
        //System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteVendorPartNoInfo() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao) this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
        vendorPartNoInfoDao.deleteVendorPartNoInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateVendorPartNoInfo() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao) this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
        vendorPartNoInfo.setCustPartNo("1");
        vendorPartNoInfo.setCommCode("1");
        vendorPartNoInfo.setState("1");
        vendorPartNoInfo.setPnDesc("1");
        //vendorPartNoInfo.setPrice("1");
        //vendorPartNoInfo.setPriceTax("1");
        vendorPartNoInfo.setCreateDate(new Date());
        vendorPartNoInfo.setStaffId("1");
        vendorPartNoInfo.setStaffName("1");
        vendorPartNoInfo.setPartNo("1");
        vendorPartNoInfo.setCatCode("1");
        vendorPartNoInfo.setClsName("1");
        vendorPartNoInfo.setMinAmount(2);
        vendorPartNoInfo.setMinPackage(2);
        vendorPartNoInfo.setSampleCode("1");
        vendorPartNoInfoDao.updateVendorPartNoInfo(vendorPartNoInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindVendorPartNoInfo() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao) this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = vendorPartNoInfoDao.findVendorPartNoInfo("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("custPartNo=" + vendorPartNoInfo.getCustPartNo());
        System.out.println("commCode=" + vendorPartNoInfo.getCommCode());
        System.out.println("state=" + vendorPartNoInfo.getState());
        System.out.println("pnDesc=" + vendorPartNoInfo.getPnDesc());
        System.out.println("price=" + vendorPartNoInfo.getPrice());
        System.out.println("priceTax=" + vendorPartNoInfo.getPriceTax());
        System.out.println("createDate=" + vendorPartNoInfo.getCreateDate());
        System.out.println("staffId=" + vendorPartNoInfo.getStaffId());
        System.out.println("staffName=" + vendorPartNoInfo.getStaffName());
        System.out.println("partNo=" + vendorPartNoInfo.getPartNo());
        System.out.println("catCode=" + vendorPartNoInfo.getCatCode());
        System.out.println("clsName=" + vendorPartNoInfo.getClsName());
        System.out.println("minAmount=" + vendorPartNoInfo.getMinAmount());
        System.out.println("minPackage=" + vendorPartNoInfo.getMinPackage());
        System.out.println("sampleCode=" + vendorPartNoInfo.getSampleCode());
    }

    /**
     * test list.
     */
    public void testListVendorPartNoInfo() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao) this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
        vendorPartNoInfo.setCustPartNo("1");
        vendorPartNoInfo.setCommCode("1");
        vendorPartNoInfo.setState("1");
        vendorPartNoInfo.setPnDesc("1");
        //vendorPartNoInfo.setPrice("1");
        //vendorPartNoInfo.setPriceTax("1");
        vendorPartNoInfo.setCreateDate(null);
        vendorPartNoInfo.setStaffId("1");
        vendorPartNoInfo.setStaffName("1");
        vendorPartNoInfo.setPartNo("1");
        vendorPartNoInfo.setCatCode("1");
        vendorPartNoInfo.setClsName("1");
        vendorPartNoInfo.setMinAmount(2);
        vendorPartNoInfo.setMinPackage(2);
        vendorPartNoInfo.setSampleCode("1");

        System.out.println("testList result--------------------------------------");
        List list =  vendorPartNoInfoDao.listVendorPartNoInfo(vendorPartNoInfo);
        for (int i = 0; i < list.size(); i++) {
            VendorPartNoInfo tmpVendorPartNoInfo = (VendorPartNoInfo) list.get(i);
            System.out.println(i + " custPartNo=" + tmpVendorPartNoInfo.getCustPartNo());
            System.out.println(i + " commCode=" + tmpVendorPartNoInfo.getCommCode());
            System.out.println(i + " state=" + tmpVendorPartNoInfo.getState());
            System.out.println(i + " pnDesc=" + tmpVendorPartNoInfo.getPnDesc());
            System.out.println(i + " price=" + tmpVendorPartNoInfo.getPrice());
            System.out.println(i + " priceTax=" + tmpVendorPartNoInfo.getPriceTax());
            System.out.println(i + " createDate=" + tmpVendorPartNoInfo.getCreateDate());
            System.out.println(i + " staffId=" + tmpVendorPartNoInfo.getStaffId());
            System.out.println(i + " staffName=" + tmpVendorPartNoInfo.getStaffName());
            System.out.println(i + " partNo=" + tmpVendorPartNoInfo.getPartNo());
            System.out.println(i + " catCode=" + tmpVendorPartNoInfo.getCatCode());
            System.out.println(i + " clsName=" + tmpVendorPartNoInfo.getClsName());
            System.out.println(i + " minAmount=" + tmpVendorPartNoInfo.getMinAmount());
            System.out.println(i + " minPackage=" + tmpVendorPartNoInfo.getMinPackage());
            System.out.println(i + " sampleCode=" + tmpVendorPartNoInfo.getSampleCode());
        }

    }
    
    /**
     * test list.
     */
    public void testListVendorPartNoInfoCount() {
        VendorPartNoInfoDao vendorPartNoInfoDao = (VendorPartNoInfoDao) this.getApplicationContext().getBean("vendorPartNoInfoDao");
        VendorPartNoInfo vendorPartNoInfo = new VendorPartNoInfo();
        vendorPartNoInfo.setCustPartNo("1");
        vendorPartNoInfo.setCommCode("1");
        vendorPartNoInfo.setState("1");
        vendorPartNoInfo.setPnDesc("1");
        //vendorPartNoInfo.setPrice("1");
        //vendorPartNoInfo.setPriceTax("1");
        vendorPartNoInfo.setCreateDate(null);
        vendorPartNoInfo.setStaffId("1");
        vendorPartNoInfo.setStaffName("1");
        vendorPartNoInfo.setPartNo("1");
        vendorPartNoInfo.setCatCode("1");
        vendorPartNoInfo.setClsName("1");
        vendorPartNoInfo.setMinAmount(2);
        vendorPartNoInfo.setMinPackage(2);
        vendorPartNoInfo.setSampleCode("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  vendorPartNoInfoDao.listVendorPartNoInfoCount(vendorPartNoInfo);
		System.out.println("count=" + count);
    }
}
