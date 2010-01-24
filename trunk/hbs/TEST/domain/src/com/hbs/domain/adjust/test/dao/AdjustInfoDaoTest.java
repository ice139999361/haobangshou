package com.hbs.domain.adjust.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.adjust.dao.AdjustInfoDao;
import com.hbs.domain.adjust.pojo.AdjustInfo;

/**
 * test AdjustInfoDao.
 * @author hbs
 *
 */
public class AdjustInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-AdjustInfo.xml)
        return new String[] { "classpath:/applicationContext-test-adjustInfo.xml", "classpath:/applicationContext-test-adjustInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertAdjustInfo() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao)this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = new AdjustInfo();
        adjustInfo.setApplySeqId(1);
        adjustInfo.setStaffId("1");
        adjustInfo.setStaffName("1");
        adjustInfo.setApplyDate(new Date());
        adjustInfo.setPoNo("1");
        adjustInfo.setPartNo("1");
        adjustInfo.setPnDesc("1");
        adjustInfo.setHouseType("1");
        adjustInfo.setApplyAmount(1);
        adjustInfo.setVendorCode("1");
        adjustInfo.setShortName("1");
        adjustInfo.setApplyContent("1");
        adjustInfo.setFromCustCode("1");
        adjustInfo.setFromCustName("1");
        adjustInfo.setToCustCode("1");
        adjustInfo.setToCustName("1");
        adjustInfo.setAuditStaffId("1");
        adjustInfo.setAuditStaffName("1");
        adjustInfo.setAuditDate(new Date());
        adjustInfo.setAuditAgree("1");
        adjustInfo.setAuditContent("1");
        //String id = adjustInfoDao.insertAdjustInfo(adjustInfo);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteAdjustInfo() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao) this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = new AdjustInfo();
        adjustInfoDao.deleteAdjustInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateAdjustInfo() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao) this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = new AdjustInfo();
        adjustInfo.setApplySeqId(2);
        adjustInfo.setStaffId("1");
        adjustInfo.setStaffName("1");
        adjustInfo.setApplyDate(new Date());
        adjustInfo.setPoNo("1");
        adjustInfo.setPartNo("1");
        adjustInfo.setPnDesc("1");
        adjustInfo.setHouseType("1");
        adjustInfo.setApplyAmount(2);
        adjustInfo.setVendorCode("1");
        adjustInfo.setShortName("1");
        adjustInfo.setApplyContent("1");
        adjustInfo.setFromCustCode("1");
        adjustInfo.setFromCustName("1");
        adjustInfo.setToCustCode("1");
        adjustInfo.setToCustName("1");
        adjustInfo.setAuditStaffId("1");
        adjustInfo.setAuditStaffName("1");
        adjustInfo.setAuditDate(new Date());
        adjustInfo.setAuditAgree("1");
        adjustInfo.setAuditContent("1");
        adjustInfoDao.updateAdjustInfo(adjustInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindAdjustInfo() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao) this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = adjustInfoDao.findAdjustInfo("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("applySeqId=" + adjustInfo.getApplySeqId());
        System.out.println("staffId=" + adjustInfo.getStaffId());
        System.out.println("staffName=" + adjustInfo.getStaffName());
        System.out.println("applyDate=" + adjustInfo.getApplyDate());
        System.out.println("poNo=" + adjustInfo.getPoNo());
        System.out.println("partNo=" + adjustInfo.getPartNo());
        System.out.println("pnDesc=" + adjustInfo.getPnDesc());
        System.out.println("houseType=" + adjustInfo.getHouseType());
        System.out.println("applyAmount=" + adjustInfo.getApplyAmount());
        System.out.println("vendorCode=" + adjustInfo.getVendorCode());
        System.out.println("shortName=" + adjustInfo.getShortName());
        System.out.println("applyContent=" + adjustInfo.getApplyContent());
        System.out.println("fromCustCode=" + adjustInfo.getFromCustCode());
        System.out.println("fromCustName=" + adjustInfo.getFromCustName());
        System.out.println("toCustCode=" + adjustInfo.getToCustCode());
        System.out.println("toCustName=" + adjustInfo.getToCustName());
        System.out.println("auditStaffId=" + adjustInfo.getAuditStaffId());
        System.out.println("auditStaffName=" + adjustInfo.getAuditStaffName());
        System.out.println("auditDate=" + adjustInfo.getAuditDate());
        System.out.println("auditAgree=" + adjustInfo.getAuditAgree());
        System.out.println("auditContent=" + adjustInfo.getAuditContent());
    }

    /**
     * test list.
     */
    public void testListAdjustInfo() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao) this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = new AdjustInfo();
        adjustInfo.setApplySeqId(2);
        adjustInfo.setStaffId("1");
        adjustInfo.setStaffName("1");
        adjustInfo.setApplyDate(null);
        adjustInfo.setPoNo("1");
        adjustInfo.setPartNo("1");
        adjustInfo.setPnDesc("1");
        adjustInfo.setHouseType("1");
        adjustInfo.setApplyAmount(2);
        adjustInfo.setVendorCode("1");
        adjustInfo.setShortName("1");
        adjustInfo.setApplyContent("1");
        adjustInfo.setFromCustCode("1");
        adjustInfo.setFromCustName("1");
        adjustInfo.setToCustCode("1");
        adjustInfo.setToCustName("1");
        adjustInfo.setAuditStaffId("1");
        adjustInfo.setAuditStaffName("1");
        adjustInfo.setAuditDate(null);
        adjustInfo.setAuditAgree("1");
        adjustInfo.setAuditContent("1");

        System.out.println("testList result--------------------------------------");
        List list =  adjustInfoDao.listAdjustInfo(adjustInfo);
        for (int i = 0; i < list.size(); i++) {
            AdjustInfo tmpAdjustInfo = (AdjustInfo) list.get(i);
            System.out.println(i + " applySeqId=" + tmpAdjustInfo.getApplySeqId());
            System.out.println(i + " staffId=" + tmpAdjustInfo.getStaffId());
            System.out.println(i + " staffName=" + tmpAdjustInfo.getStaffName());
            System.out.println(i + " applyDate=" + tmpAdjustInfo.getApplyDate());
            System.out.println(i + " poNo=" + tmpAdjustInfo.getPoNo());
            System.out.println(i + " partNo=" + tmpAdjustInfo.getPartNo());
            System.out.println(i + " pnDesc=" + tmpAdjustInfo.getPnDesc());
            System.out.println(i + " houseType=" + tmpAdjustInfo.getHouseType());
            System.out.println(i + " applyAmount=" + tmpAdjustInfo.getApplyAmount());
            System.out.println(i + " vendorCode=" + tmpAdjustInfo.getVendorCode());
            System.out.println(i + " shortName=" + tmpAdjustInfo.getShortName());
            System.out.println(i + " applyContent=" + tmpAdjustInfo.getApplyContent());
            System.out.println(i + " fromCustCode=" + tmpAdjustInfo.getFromCustCode());
            System.out.println(i + " fromCustName=" + tmpAdjustInfo.getFromCustName());
            System.out.println(i + " toCustCode=" + tmpAdjustInfo.getToCustCode());
            System.out.println(i + " toCustName=" + tmpAdjustInfo.getToCustName());
            System.out.println(i + " auditStaffId=" + tmpAdjustInfo.getAuditStaffId());
            System.out.println(i + " auditStaffName=" + tmpAdjustInfo.getAuditStaffName());
            System.out.println(i + " auditDate=" + tmpAdjustInfo.getAuditDate());
            System.out.println(i + " auditAgree=" + tmpAdjustInfo.getAuditAgree());
            System.out.println(i + " auditContent=" + tmpAdjustInfo.getAuditContent());
        }

    }
    
    /**
     * test list.
     */
    public void testListAdjustInfoCount() {
        AdjustInfoDao adjustInfoDao = (AdjustInfoDao) this.getApplicationContext().getBean("adjustInfoDao");
        AdjustInfo adjustInfo = new AdjustInfo();
        adjustInfo.setApplySeqId(2);
        adjustInfo.setStaffId("1");
        adjustInfo.setStaffName("1");
        adjustInfo.setApplyDate(null);
        adjustInfo.setPoNo("1");
        adjustInfo.setPartNo("1");
        adjustInfo.setPnDesc("1");
        adjustInfo.setHouseType("1");
        adjustInfo.setApplyAmount(2);
        adjustInfo.setVendorCode("1");
        adjustInfo.setShortName("1");
        adjustInfo.setApplyContent("1");
        adjustInfo.setFromCustCode("1");
        adjustInfo.setFromCustName("1");
        adjustInfo.setToCustCode("1");
        adjustInfo.setToCustName("1");
        adjustInfo.setAuditStaffId("1");
        adjustInfo.setAuditStaffName("1");
        adjustInfo.setAuditDate(null);
        adjustInfo.setAuditAgree("1");
        adjustInfo.setAuditContent("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  adjustInfoDao.listAdjustInfoCount(adjustInfo);
		System.out.println("count=" + count);
    }
}
