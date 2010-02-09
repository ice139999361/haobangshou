package com.hbs.domain.warehouse.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.warehouse.dao.WarehouseSendInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;

/**
 * test WarehouseSendInfoDao.
 * @author hbs
 *
 */
public class WarehouseSendInfoHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WarehouseSendInfo.xml)
        return new String[] { "classpath:/applicationContext-test-warehouseSendInfo.xml", "classpath:/applicationContext-test-warehouseSendInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertWarehouseSendInfo() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao)this.getApplicationContext().getBean("warehouseSendInfoDao");
        WarehouseSendInfo warehouseSendInfo = new WarehouseSendInfo();
        warehouseSendInfo.setSendPoNo("1");
        warehouseSendInfo.setCustCode("1");
        warehouseSendInfo.setShortName("1");
        warehouseSendInfo.setReceiveName("1");
        warehouseSendInfo.setReceiveAddress("1");
        warehouseSendInfo.setReceiveZip("1");
        warehouseSendInfo.setConTel("1");
        warehouseSendInfo.setConFax("1");
        warehouseSendInfo.setCompanyBranch("1");
        warehouseSendInfo.setHouseType("1");
        warehouseSendInfo.setSettlement_type("1");
        warehouseSendInfo.setCreateDate(new Date());
        warehouseSendInfo.setOperId("1");
        warehouseSendInfo.setOperStaff("1");
        warehouseSendInfo.setSendDesc("1");
        warehouseSendInfo.setPeriod("1");
        warehouseSendInfo.setState("1");
        warehouseSendInfo.setActiveState("1");
        warehouseSendInfo.setFinanceState("1");
        warehouseSendInfo.setPoNoType("1");
//        String id = warehouseSendInfoDao.insertWarehouseSendInfo(warehouseSendInfo);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteWarehouseSendInfo() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao) this.getApplicationContext().getBean("warehouseSendInfoDao");
        WarehouseSendInfo warehouseSendInfo = new WarehouseSendInfo();
        warehouseSendInfoDao.deleteWarehouseSendInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateWarehouseSendInfo() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao) this.getApplicationContext().getBean("warehouseSendInfoDao");
        WarehouseSendInfo warehouseSendInfo = new WarehouseSendInfo();
        warehouseSendInfo.setSendPoNo("1");
        warehouseSendInfo.setCustCode("1");
        warehouseSendInfo.setShortName("1");
        warehouseSendInfo.setReceiveName("1");
        warehouseSendInfo.setReceiveAddress("1");
        warehouseSendInfo.setReceiveZip("1");
        warehouseSendInfo.setConTel("1");
        warehouseSendInfo.setConFax("1");
        warehouseSendInfo.setCompanyBranch("1");
        warehouseSendInfo.setHouseType("1");
        warehouseSendInfo.setSettlement_type("1");
        warehouseSendInfo.setCreateDate(new Date());
        warehouseSendInfo.setOperId("1");
        warehouseSendInfo.setOperStaff("1");
        warehouseSendInfo.setSendDesc("1");
        warehouseSendInfo.setPeriod("1");
        warehouseSendInfo.setState("1");
        warehouseSendInfo.setActiveState("1");
        warehouseSendInfo.setFinanceState("1");
        warehouseSendInfo.setPoNoType("1");
        warehouseSendInfoDao.updateWarehouseSendInfo(warehouseSendInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindWarehouseSendInfo() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao) this.getApplicationContext().getBean("warehouseSendInfoDao");
//        WarehouseSendInfo warehouseSendInfo = warehouseSendInfoDao.findWarehouseSendInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("sendPoNo=" + warehouseSendInfo.getSendPoNo());
//        System.out.println("custCode=" + warehouseSendInfo.getCustCode());
//        System.out.println("shortName=" + warehouseSendInfo.getShortName());
//        System.out.println("receiveName=" + warehouseSendInfo.getReceiveName());
//        System.out.println("receiveAddress=" + warehouseSendInfo.getReceiveAddress());
//        System.out.println("receiveZip=" + warehouseSendInfo.getReceiveZip());
//        System.out.println("conTel=" + warehouseSendInfo.getConTel());
//        System.out.println("conFax=" + warehouseSendInfo.getConFax());
//        System.out.println("companyBranch=" + warehouseSendInfo.getCompanyBranch());
//        System.out.println("houseType=" + warehouseSendInfo.getHouseType());
//        System.out.println("settlement_type=" + warehouseSendInfo.getSettlement_type());
//        System.out.println("createDate=" + warehouseSendInfo.getCreateDate());
//        System.out.println("operId=" + warehouseSendInfo.getOperId());
//        System.out.println("operStaff=" + warehouseSendInfo.getOperStaff());
//        System.out.println("sendDesc=" + warehouseSendInfo.getSendDesc());
//        System.out.println("period=" + warehouseSendInfo.getPeriod());
//        System.out.println("state=" + warehouseSendInfo.getState());
//        System.out.println("activeState=" + warehouseSendInfo.getActiveState());
//        System.out.println("financeState=" + warehouseSendInfo.getFinanceState());
//        System.out.println("poNoType=" + warehouseSendInfo.getPoNoType());
    }

    /**
     * test list.
     */
    public void testListWarehouseSendInfo() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao) this.getApplicationContext().getBean("warehouseSendInfoDao");
        WarehouseSendInfo warehouseSendInfo = new WarehouseSendInfo();
        warehouseSendInfo.setSendPoNo("1");
        warehouseSendInfo.setCustCode("1");
        warehouseSendInfo.setShortName("1");
        warehouseSendInfo.setReceiveName("1");
        warehouseSendInfo.setReceiveAddress("1");
        warehouseSendInfo.setReceiveZip("1");
        warehouseSendInfo.setConTel("1");
        warehouseSendInfo.setConFax("1");
        warehouseSendInfo.setCompanyBranch("1");
        warehouseSendInfo.setHouseType("1");
        warehouseSendInfo.setSettlement_type("1");
        warehouseSendInfo.setCreateDate(null);
        warehouseSendInfo.setOperId("1");
        warehouseSendInfo.setOperStaff("1");
        warehouseSendInfo.setSendDesc("1");
        warehouseSendInfo.setPeriod("1");
        warehouseSendInfo.setState("1");
        warehouseSendInfo.setActiveState("1");
        warehouseSendInfo.setFinanceState("1");
        warehouseSendInfo.setPoNoType("1");

        System.out.println("testList result--------------------------------------");
        List list =  warehouseSendInfoDao.listWarehouseSendInfo(warehouseSendInfo);
        for (int i = 0; i < list.size(); i++) {
            WarehouseSendInfo tmpWarehouseSendInfo = (WarehouseSendInfo) list.get(i);
            System.out.println(i + " sendPoNo=" + tmpWarehouseSendInfo.getSendPoNo());
            System.out.println(i + " custCode=" + tmpWarehouseSendInfo.getCustCode());
            System.out.println(i + " shortName=" + tmpWarehouseSendInfo.getShortName());
            System.out.println(i + " receiveName=" + tmpWarehouseSendInfo.getReceiveName());
            System.out.println(i + " receiveAddress=" + tmpWarehouseSendInfo.getReceiveAddress());
            System.out.println(i + " receiveZip=" + tmpWarehouseSendInfo.getReceiveZip());
            System.out.println(i + " conTel=" + tmpWarehouseSendInfo.getConTel());
            System.out.println(i + " conFax=" + tmpWarehouseSendInfo.getConFax());
            System.out.println(i + " companyBranch=" + tmpWarehouseSendInfo.getCompanyBranch());
            System.out.println(i + " houseType=" + tmpWarehouseSendInfo.getHouseType());
            System.out.println(i + " settlement_type=" + tmpWarehouseSendInfo.getSettlement_type());
            System.out.println(i + " createDate=" + tmpWarehouseSendInfo.getCreateDate());
            System.out.println(i + " operId=" + tmpWarehouseSendInfo.getOperId());
            System.out.println(i + " operStaff=" + tmpWarehouseSendInfo.getOperStaff());
            System.out.println(i + " sendDesc=" + tmpWarehouseSendInfo.getSendDesc());
            System.out.println(i + " period=" + tmpWarehouseSendInfo.getPeriod());
            System.out.println(i + " state=" + tmpWarehouseSendInfo.getState());
            System.out.println(i + " activeState=" + tmpWarehouseSendInfo.getActiveState());
            System.out.println(i + " financeState=" + tmpWarehouseSendInfo.getFinanceState());
            System.out.println(i + " poNoType=" + tmpWarehouseSendInfo.getPoNoType());
        }

    }
    
    /**
     * test list.
     */
    public void testListWarehouseSendInfoCount() {
        WarehouseSendInfoDao warehouseSendInfoDao = (WarehouseSendInfoDao) this.getApplicationContext().getBean("warehouseSendInfoDao");
        WarehouseSendInfo warehouseSendInfo = new WarehouseSendInfo();
        warehouseSendInfo.setSendPoNo("1");
        warehouseSendInfo.setCustCode("1");
        warehouseSendInfo.setShortName("1");
        warehouseSendInfo.setReceiveName("1");
        warehouseSendInfo.setReceiveAddress("1");
        warehouseSendInfo.setReceiveZip("1");
        warehouseSendInfo.setConTel("1");
        warehouseSendInfo.setConFax("1");
        warehouseSendInfo.setCompanyBranch("1");
        warehouseSendInfo.setHouseType("1");
        warehouseSendInfo.setSettlement_type("1");
        warehouseSendInfo.setCreateDate(null);
        warehouseSendInfo.setOperId("1");
        warehouseSendInfo.setOperStaff("1");
        warehouseSendInfo.setSendDesc("1");
        warehouseSendInfo.setPeriod("1");
        warehouseSendInfo.setState("1");
        warehouseSendInfo.setActiveState("1");
        warehouseSendInfo.setFinanceState("1");
        warehouseSendInfo.setPoNoType("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  warehouseSendInfoDao.listWarehouseSendInfoCount(warehouseSendInfo);
		System.out.println("count=" + count);
    }
}
