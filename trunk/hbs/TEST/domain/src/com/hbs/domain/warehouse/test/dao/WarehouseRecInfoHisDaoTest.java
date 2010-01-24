package com.hbs.domain.warehouse.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.warehouse.dao.WarehouseRecInfoDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;

/**
 * test WarehouseRecInfoDao.
 * @author hbs
 *
 */
public class WarehouseRecInfoHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WarehouseRecInfo.xml)
        return new String[] { "classpath:/applicationContext-test-warehouseRecInfo.xml", "classpath:/applicationContext-test-warehouseRecInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertWarehouseRecInfo() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao)this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = new WarehouseRecInfo();
        warehouseRecInfo.setRecPoNo("1");
        warehouseRecInfo.setVendorCode("1");
        warehouseRecInfo.setShortName("1");
        warehouseRecInfo.setPoNoDate(new Date());
        warehouseRecInfo.setApplyDate(new Date());
        warehouseRecInfo.setHouseType("1");
        warehouseRecInfo.setOperId("1");
        warehouseRecInfo.setOperStaff("1");
        warehouseRecInfo.setOperTime("1");
        warehouseRecInfo.setReceiveDesc("1");
        warehouseRecInfo.setPeriod("1");
        warehouseRecInfo.setState(1);
        warehouseRecInfo.setActiveState(1);
        warehouseRecInfo.setFinanceState("1");
        warehouseRecInfo.setPoNoType("1");
//        String id = warehouseRecInfoDao.insertWarehouseRecInfo(warehouseRecInfo);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
//        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteWarehouseRecInfo() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao) this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = new WarehouseRecInfo();
        warehouseRecInfoDao.deleteWarehouseRecInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateWarehouseRecInfo() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao) this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = new WarehouseRecInfo();
        warehouseRecInfo.setRecPoNo("1");
        warehouseRecInfo.setVendorCode("1");
        warehouseRecInfo.setShortName("1");
        warehouseRecInfo.setPoNoDate(new Date());
        warehouseRecInfo.setApplyDate(new Date());
        warehouseRecInfo.setHouseType("1");
        warehouseRecInfo.setOperId("1");
        warehouseRecInfo.setOperStaff("1");
        warehouseRecInfo.setOperTime("1");
        warehouseRecInfo.setReceiveDesc("1");
        warehouseRecInfo.setPeriod("1");
        warehouseRecInfo.setState(2);
        warehouseRecInfo.setActiveState(2);
        warehouseRecInfo.setFinanceState("1");
        warehouseRecInfo.setPoNoType("1");
        warehouseRecInfoDao.updateWarehouseRecInfo(warehouseRecInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindWarehouseRecInfo() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao) this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = warehouseRecInfoDao.findWarehouseRecInfo("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("recPoNo=" + warehouseRecInfo.getRecPoNo());
        System.out.println("vendorCode=" + warehouseRecInfo.getVendorCode());
        System.out.println("shortName=" + warehouseRecInfo.getShortName());
        System.out.println("poNoDate=" + warehouseRecInfo.getPoNoDate());
        System.out.println("applyDate=" + warehouseRecInfo.getApplyDate());
        System.out.println("houseType=" + warehouseRecInfo.getHouseType());
        System.out.println("operId=" + warehouseRecInfo.getOperId());
        System.out.println("operStaff=" + warehouseRecInfo.getOperStaff());
        System.out.println("operTime=" + warehouseRecInfo.getOperTime());
        System.out.println("receiveDesc=" + warehouseRecInfo.getReceiveDesc());
        System.out.println("period=" + warehouseRecInfo.getPeriod());
        System.out.println("state=" + warehouseRecInfo.getState());
        System.out.println("activeState=" + warehouseRecInfo.getActiveState());
        System.out.println("financeState=" + warehouseRecInfo.getFinanceState());
        System.out.println("poNoType=" + warehouseRecInfo.getPoNoType());
    }

    /**
     * test list.
     */
    public void testListWarehouseRecInfo() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao) this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = new WarehouseRecInfo();
        warehouseRecInfo.setRecPoNo("1");
        warehouseRecInfo.setVendorCode("1");
        warehouseRecInfo.setShortName("1");
        warehouseRecInfo.setPoNoDate(null);
        warehouseRecInfo.setApplyDate(null);
        warehouseRecInfo.setHouseType("1");
        warehouseRecInfo.setOperId("1");
        warehouseRecInfo.setOperStaff("1");
        warehouseRecInfo.setOperTime("1");
        warehouseRecInfo.setReceiveDesc("1");
        warehouseRecInfo.setPeriod("1");
        warehouseRecInfo.setState(2);
        warehouseRecInfo.setActiveState(2);
        warehouseRecInfo.setFinanceState("1");
        warehouseRecInfo.setPoNoType("1");

        System.out.println("testList result--------------------------------------");
        List list =  warehouseRecInfoDao.listWarehouseRecInfo(warehouseRecInfo);
        for (int i = 0; i < list.size(); i++) {
            WarehouseRecInfo tmpWarehouseRecInfo = (WarehouseRecInfo) list.get(i);
            System.out.println(i + " recPoNo=" + tmpWarehouseRecInfo.getRecPoNo());
            System.out.println(i + " vendorCode=" + tmpWarehouseRecInfo.getVendorCode());
            System.out.println(i + " shortName=" + tmpWarehouseRecInfo.getShortName());
            System.out.println(i + " poNoDate=" + tmpWarehouseRecInfo.getPoNoDate());
            System.out.println(i + " applyDate=" + tmpWarehouseRecInfo.getApplyDate());
            System.out.println(i + " houseType=" + tmpWarehouseRecInfo.getHouseType());
            System.out.println(i + " operId=" + tmpWarehouseRecInfo.getOperId());
            System.out.println(i + " operStaff=" + tmpWarehouseRecInfo.getOperStaff());
            System.out.println(i + " operTime=" + tmpWarehouseRecInfo.getOperTime());
            System.out.println(i + " receiveDesc=" + tmpWarehouseRecInfo.getReceiveDesc());
            System.out.println(i + " period=" + tmpWarehouseRecInfo.getPeriod());
            System.out.println(i + " state=" + tmpWarehouseRecInfo.getState());
            System.out.println(i + " activeState=" + tmpWarehouseRecInfo.getActiveState());
            System.out.println(i + " financeState=" + tmpWarehouseRecInfo.getFinanceState());
            System.out.println(i + " poNoType=" + tmpWarehouseRecInfo.getPoNoType());
        }

    }
    
    /**
     * test list.
     */
    public void testListWarehouseRecInfoCount() {
        WarehouseRecInfoDao warehouseRecInfoDao = (WarehouseRecInfoDao) this.getApplicationContext().getBean("warehouseRecInfoDao");
        WarehouseRecInfo warehouseRecInfo = new WarehouseRecInfo();
        warehouseRecInfo.setRecPoNo("1");
        warehouseRecInfo.setVendorCode("1");
        warehouseRecInfo.setShortName("1");
        warehouseRecInfo.setPoNoDate(null);
        warehouseRecInfo.setApplyDate(null);
        warehouseRecInfo.setHouseType("1");
        warehouseRecInfo.setOperId("1");
        warehouseRecInfo.setOperStaff("1");
        warehouseRecInfo.setOperTime("1");
        warehouseRecInfo.setReceiveDesc("1");
        warehouseRecInfo.setPeriod("1");
        warehouseRecInfo.setState(2);
        warehouseRecInfo.setActiveState(2);
        warehouseRecInfo.setFinanceState("1");
        warehouseRecInfo.setPoNoType("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  warehouseRecInfoDao.listWarehouseRecInfoCount(warehouseRecInfo);
		System.out.println("count=" + count);
    }
}
