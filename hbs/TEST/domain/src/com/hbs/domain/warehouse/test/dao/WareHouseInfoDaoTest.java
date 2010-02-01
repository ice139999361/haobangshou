package com.hbs.domain.warehouse.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.warehouse.dao.WareHouseInfoDao;
import com.hbs.domain.warehouse.pojo.WareHouseInfo;

/**
 * test WareHouseInfoDao.
 * @author hbs
 *
 */
public class WareHouseInfoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WareHouseInfo.xml)
        return new String[] { "classpath:/applicationContext-test-wareHouseInfo.xml", "classpath:/applicationContext-test-wareHouseInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertWareHouseInfo() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao)this.getApplicationContext().getBean("wareHouseInfoDao");
        WareHouseInfo wareHouseInfo = new WareHouseInfo();
        wareHouseInfo.setHouseSeqId(1);
        wareHouseInfo.setHouseType("1");
        wareHouseInfo.setHouseUse("1");
        wareHouseInfo.setVendorCode("1");
        wareHouseInfo.setPoNo("1");
        wareHouseInfo.setPartNo("1");
        wareHouseInfo.setPnDesc("1");
        wareHouseInfo.setCpartNo("1");
        wareHouseInfo.setCustCode("1");
        wareHouseInfo.setTotalAmount(1);
        wareHouseInfo.setLockAmount(1);
        wareHouseInfo.setUseAmount(1);
        wareHouseInfo.setMaxAmount(1);
        wareHouseInfo.setMinAmount(1);
        wareHouseInfo.setState("1");
//        String id = wareHouseInfoDao.insertWareHouseInfo(wareHouseInfo);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteWareHouseInfo() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao) this.getApplicationContext().getBean("wareHouseInfoDao");
        WareHouseInfo wareHouseInfo = new WareHouseInfo();
        wareHouseInfoDao.deleteWareHouseInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateWareHouseInfo() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao) this.getApplicationContext().getBean("wareHouseInfoDao");
        WareHouseInfo wareHouseInfo = new WareHouseInfo();
        wareHouseInfo.setHouseSeqId(2);
        wareHouseInfo.setHouseType("1");
        wareHouseInfo.setHouseUse("1");
        wareHouseInfo.setVendorCode("1");
        wareHouseInfo.setPoNo("1");
        wareHouseInfo.setPartNo("1");
        wareHouseInfo.setPnDesc("1");
        wareHouseInfo.setCpartNo("1");
        wareHouseInfo.setCustCode("1");
        wareHouseInfo.setTotalAmount(2);
        wareHouseInfo.setLockAmount(2);
        wareHouseInfo.setUseAmount(2);
        wareHouseInfo.setMaxAmount(2);
        wareHouseInfo.setMinAmount(2);
        wareHouseInfo.setState("1");
        wareHouseInfoDao.updateWareHouseInfo(wareHouseInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindWareHouseInfo() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao) this.getApplicationContext().getBean("wareHouseInfoDao");
//        WareHouseInfo wareHouseInfo = wareHouseInfoDao.findWareHouseInfo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("houseSeqId=" + wareHouseInfo.getHouseSeqId());
//        System.out.println("houseType=" + wareHouseInfo.getHouseType());
//        System.out.println("houseUse=" + wareHouseInfo.getHouseUse());
//        System.out.println("vendorCode=" + wareHouseInfo.getVendorCode());
//        System.out.println("poNo=" + wareHouseInfo.getPoNo());
//        System.out.println("partNo=" + wareHouseInfo.getPartNo());
//        System.out.println("pnDesc=" + wareHouseInfo.getPnDesc());
//        System.out.println("cpartNo=" + wareHouseInfo.getCpartNo());
//        System.out.println("custCode=" + wareHouseInfo.getCustCode());
//        System.out.println("totalAmount=" + wareHouseInfo.getTotalAmount());
//        System.out.println("lockAmount=" + wareHouseInfo.getLockAmount());
//        System.out.println("useAmount=" + wareHouseInfo.getUseAmount());
//        System.out.println("maxAmount=" + wareHouseInfo.getMaxAmount());
//        System.out.println("minAmount=" + wareHouseInfo.getMinAmount());
//        System.out.println("state=" + wareHouseInfo.getState());
    }

    /**
     * test list.
     */
    public void testListWareHouseInfo() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao) this.getApplicationContext().getBean("wareHouseInfoDao");
        WareHouseInfo wareHouseInfo = new WareHouseInfo();
        wareHouseInfo.setHouseSeqId(2);
        wareHouseInfo.setHouseType("1");
        wareHouseInfo.setHouseUse("1");
        wareHouseInfo.setVendorCode("1");
        wareHouseInfo.setPoNo("1");
        wareHouseInfo.setPartNo("1");
        wareHouseInfo.setPnDesc("1");
        wareHouseInfo.setCpartNo("1");
        wareHouseInfo.setCustCode("1");
        wareHouseInfo.setTotalAmount(2);
        wareHouseInfo.setLockAmount(2);
        wareHouseInfo.setUseAmount(2);
        wareHouseInfo.setMaxAmount(2);
        wareHouseInfo.setMinAmount(2);
        wareHouseInfo.setState("1");

        System.out.println("testList result--------------------------------------");
        List list =  wareHouseInfoDao.listWareHouseInfo(wareHouseInfo);
        for (int i = 0; i < list.size(); i++) {
            WareHouseInfo tmpWareHouseInfo = (WareHouseInfo) list.get(i);
            System.out.println(i + " houseSeqId=" + tmpWareHouseInfo.getHouseSeqId());
            System.out.println(i + " houseType=" + tmpWareHouseInfo.getHouseType());
            System.out.println(i + " houseUse=" + tmpWareHouseInfo.getHouseUse());
            System.out.println(i + " vendorCode=" + tmpWareHouseInfo.getVendorCode());
            System.out.println(i + " poNo=" + tmpWareHouseInfo.getPoNo());
            System.out.println(i + " partNo=" + tmpWareHouseInfo.getPartNo());
            System.out.println(i + " pnDesc=" + tmpWareHouseInfo.getPnDesc());
            System.out.println(i + " cpartNo=" + tmpWareHouseInfo.getCpartNo());
            System.out.println(i + " custCode=" + tmpWareHouseInfo.getCustCode());
            System.out.println(i + " totalAmount=" + tmpWareHouseInfo.getTotalAmount());
            System.out.println(i + " lockAmount=" + tmpWareHouseInfo.getLockAmount());
            System.out.println(i + " useAmount=" + tmpWareHouseInfo.getUseAmount());
            System.out.println(i + " maxAmount=" + tmpWareHouseInfo.getMaxAmount());
            System.out.println(i + " minAmount=" + tmpWareHouseInfo.getMinAmount());
            System.out.println(i + " state=" + tmpWareHouseInfo.getState());
        }

    }
    
    /**
     * test list.
     */
    public void testListWareHouseInfoCount() {
        WareHouseInfoDao wareHouseInfoDao = (WareHouseInfoDao) this.getApplicationContext().getBean("wareHouseInfoDao");
        WareHouseInfo wareHouseInfo = new WareHouseInfo();
        wareHouseInfo.setHouseSeqId(2);
        wareHouseInfo.setHouseType("1");
        wareHouseInfo.setHouseUse("1");
        wareHouseInfo.setVendorCode("1");
        wareHouseInfo.setPoNo("1");
        wareHouseInfo.setPartNo("1");
        wareHouseInfo.setPnDesc("1");
        wareHouseInfo.setCpartNo("1");
        wareHouseInfo.setCustCode("1");
        wareHouseInfo.setTotalAmount(2);
        wareHouseInfo.setLockAmount(2);
        wareHouseInfo.setUseAmount(2);
        wareHouseInfo.setMaxAmount(2);
        wareHouseInfo.setMinAmount(2);
        wareHouseInfo.setState("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  wareHouseInfoDao.listWareHouseInfoCount(wareHouseInfo);
		System.out.println("count=" + count);
    }
}
