package com.hbs.domain.warehouse.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.warehouse.dao.WarehouseSendDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;

/**
 * test WarehouseSendDetailDao.
 * @author hbs
 *
 */
public class WarehouseSendDetailHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WarehouseSendDetail.xml)
        return new String[] { "classpath:/applicationContext-test-warehouseSendDetail.xml", "classpath:/applicationContext-test-warehouseSendDetail.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertWarehouseSendDetail() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao)this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = new WarehouseSendDetail();
        warehouseSendDetail.setSendSeqId(1);
        warehouseSendDetail.setSendPoNo("1");
        warehouseSendDetail.setPartNo("1");
        warehouseSendDetail.setCustPartNo("1");
        warehouseSendDetail.setPnDesc("1");
        warehouseSendDetail.setSpecDesc("1");
        warehouseSendDetail.setIsTax("1");
//        warehouseSendDetail.setPrice("1");
//        warehouseSendDetail.setTaxRate("1");
//        warehouseSendDetail.setIsShowPrice("1");
//        warehouseSendDetail.setRltPoNo("1");
//        warehouseSendDetail.setSettlementType("1");
//        warehouseSendDetail.setSpecDesc("1");
//        warehouseSendDetail.setAmount(1);
//        warehouseSendDetail.setCurMoney("1");
//        warehouseSendDetail.setVendorCode("1");
//        warehouseSendDetail.setVendorPoNo("1");
//        warehouseSendDetail.setPeriod("1");
//        warehouseSendDetail.setCREATE_TIME(new Date());
//        warehouseSendDetail.setState("1");
//        warehouseSendDetail.setActiveState("1");
//        warehouseSendDetail.setFinanceState("1");
//        warehouseSendDetail.setPoNoType("1");
//        String id = warehouseSendDetailDao.insertWarehouseSendDetail(warehouseSendDetail);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteWarehouseSendDetail() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao) this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = new WarehouseSendDetail();
        warehouseSendDetailDao.deleteWarehouseSendDetail("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateWarehouseSendDetail() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao) this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = new WarehouseSendDetail();
        warehouseSendDetail.setSendSeqId(2);
        warehouseSendDetail.setSendPoNo("1");
        warehouseSendDetail.setPartNo("1");
        warehouseSendDetail.setCustPartNo("1");
        warehouseSendDetail.setPnDesc("1");
        warehouseSendDetail.setSpecDesc("1");
        warehouseSendDetail.setIsTax("1");
//        warehouseSendDetail.setPrice("1");
//        warehouseSendDetail.setTaxRate("1");
//        warehouseSendDetail.setIsShowPrice("1");
//        warehouseSendDetail.setRltPoNo("1");
//        warehouseSendDetail.setSettlementType("1");
//        warehouseSendDetail.setSpecDesc("1");
//        warehouseSendDetail.setAmount(2);
//        warehouseSendDetail.setCurMoney("1");
//        warehouseSendDetail.setVendorCode("1");
        warehouseSendDetail.setVendorPoNo("1");
        warehouseSendDetail.setPeriod("1");
       // warehouseSendDetail.setCREATE_TIME(new Date());
        warehouseSendDetail.setState("1");
        warehouseSendDetail.setActiveState("1");
        warehouseSendDetail.setFinanceState("1");
        warehouseSendDetail.setPoNoType("1");
        warehouseSendDetailDao.updateWarehouseSendDetail(warehouseSendDetail);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindWarehouseSendDetail() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao) this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = warehouseSendDetailDao.findWarehouseSendDetail("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("sendSeqId=" + warehouseSendDetail.getSendSeqId());
        System.out.println("sendPoNo=" + warehouseSendDetail.getSendPoNo());
        System.out.println("partNo=" + warehouseSendDetail.getPartNo());
        System.out.println("custPartNo=" + warehouseSendDetail.getCustPartNo());
        System.out.println("pnDesc=" + warehouseSendDetail.getPnDesc());
        System.out.println("specDesc=" + warehouseSendDetail.getSpecDesc());
        System.out.println("isTax=" + warehouseSendDetail.getIsTax());
        System.out.println("price=" + warehouseSendDetail.getPrice());
        System.out.println("taxRate=" + warehouseSendDetail.getTaxRate());
        System.out.println("isShowPrice=" + warehouseSendDetail.getIsShowPrice());
        System.out.println("rltPoNo=" + warehouseSendDetail.getRltPoNo());
        System.out.println("settlementType=" + warehouseSendDetail.getSettlementType());
        System.out.println("specDesc=" + warehouseSendDetail.getSpecDesc());
        System.out.println("amount=" + warehouseSendDetail.getAmount());
        System.out.println("curMoney=" + warehouseSendDetail.getCurMoney());
        System.out.println("vendorCode=" + warehouseSendDetail.getVendorCode());
        System.out.println("vendorPoNo=" + warehouseSendDetail.getVendorPoNo());
        System.out.println("period=" + warehouseSendDetail.getPeriod());
      //  System.out.println("CREATE_TIME=" + warehouseSendDetail.getCREATE_TIME());
        System.out.println("state=" + warehouseSendDetail.getState());
        System.out.println("activeState=" + warehouseSendDetail.getActiveState());
        System.out.println("financeState=" + warehouseSendDetail.getFinanceState());
        System.out.println("poNoType=" + warehouseSendDetail.getPoNoType());
    }

    /**
     * test list.
     */
    public void testListWarehouseSendDetail() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao) this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = new WarehouseSendDetail();
        warehouseSendDetail.setSendSeqId(2);
        warehouseSendDetail.setSendPoNo("1");
        warehouseSendDetail.setPartNo("1");
        warehouseSendDetail.setCustPartNo("1");
        warehouseSendDetail.setPnDesc("1");
        warehouseSendDetail.setSpecDesc("1");
        warehouseSendDetail.setIsTax("1");
//        warehouseSendDetail.setPrice("1");
//        warehouseSendDetail.setTaxRate("1");
//        warehouseSendDetail.setIsShowPrice("1");
//        warehouseSendDetail.setRltPoNo("1");
//        warehouseSendDetail.setSettlementType("1");
//        warehouseSendDetail.setSpecDesc("1");
//        warehouseSendDetail.setAmount(2);
//        warehouseSendDetail.setCurMoney("1");
        warehouseSendDetail.setVendorCode("1");
        warehouseSendDetail.setVendorPoNo("1");
        warehouseSendDetail.setPeriod("1");
      //  warehouseSendDetail.setCREATE_TIME(null);
        warehouseSendDetail.setState("1");
        warehouseSendDetail.setActiveState("1");
        warehouseSendDetail.setFinanceState("1");
        warehouseSendDetail.setPoNoType("1");

        System.out.println("testList result--------------------------------------");
        List list =  warehouseSendDetailDao.listWarehouseSendDetail(warehouseSendDetail);
        for (int i = 0; i < list.size(); i++) {
            WarehouseSendDetail tmpWarehouseSendDetail = (WarehouseSendDetail) list.get(i);
            System.out.println(i + " sendSeqId=" + tmpWarehouseSendDetail.getSendSeqId());
            System.out.println(i + " sendPoNo=" + tmpWarehouseSendDetail.getSendPoNo());
            System.out.println(i + " partNo=" + tmpWarehouseSendDetail.getPartNo());
            System.out.println(i + " custPartNo=" + tmpWarehouseSendDetail.getCustPartNo());
            System.out.println(i + " pnDesc=" + tmpWarehouseSendDetail.getPnDesc());
            System.out.println(i + " specDesc=" + tmpWarehouseSendDetail.getSpecDesc());
            System.out.println(i + " isTax=" + tmpWarehouseSendDetail.getIsTax());
            System.out.println(i + " price=" + tmpWarehouseSendDetail.getPrice());
            System.out.println(i + " taxRate=" + tmpWarehouseSendDetail.getTaxRate());
            System.out.println(i + " isShowPrice=" + tmpWarehouseSendDetail.getIsShowPrice());
            System.out.println(i + " rltPoNo=" + tmpWarehouseSendDetail.getRltPoNo());
            System.out.println(i + " settlementType=" + tmpWarehouseSendDetail.getSettlementType());
            System.out.println(i + " specDesc=" + tmpWarehouseSendDetail.getSpecDesc());
            System.out.println(i + " amount=" + tmpWarehouseSendDetail.getAmount());
            System.out.println(i + " curMoney=" + tmpWarehouseSendDetail.getCurMoney());
            System.out.println(i + " vendorCode=" + tmpWarehouseSendDetail.getVendorCode());
            System.out.println(i + " vendorPoNo=" + tmpWarehouseSendDetail.getVendorPoNo());
            System.out.println(i + " period=" + tmpWarehouseSendDetail.getPeriod());
        //    System.out.println(i + " CREATE_TIME=" + tmpWarehouseSendDetail.getCREATE_TIME());
            System.out.println(i + " state=" + tmpWarehouseSendDetail.getState());
            System.out.println(i + " activeState=" + tmpWarehouseSendDetail.getActiveState());
            System.out.println(i + " financeState=" + tmpWarehouseSendDetail.getFinanceState());
            System.out.println(i + " poNoType=" + tmpWarehouseSendDetail.getPoNoType());
        }

    }
    
    /**
     * test list.
     */
    public void testListWarehouseSendDetailCount() {
        WarehouseSendDetailDao warehouseSendDetailDao = (WarehouseSendDetailDao) this.getApplicationContext().getBean("warehouseSendDetailDao");
        WarehouseSendDetail warehouseSendDetail = new WarehouseSendDetail();
        warehouseSendDetail.setSendSeqId(2);
        warehouseSendDetail.setSendPoNo("1");
        warehouseSendDetail.setPartNo("1");
        warehouseSendDetail.setCustPartNo("1");
        warehouseSendDetail.setPnDesc("1");
        warehouseSendDetail.setSpecDesc("1");
        warehouseSendDetail.setIsTax("1");
//        warehouseSendDetail.setPrice("1");
//        warehouseSendDetail.setTaxRate("1");
//        warehouseSendDetail.setIsShowPrice("1");
//        warehouseSendDetail.setRltPoNo("1");
//        warehouseSendDetail.setSettlementType("1");
//        warehouseSendDetail.setSpecDesc("1");
//        warehouseSendDetail.setAmount(2);
//        warehouseSendDetail.setCurMoney("1");
        warehouseSendDetail.setVendorCode("1");
        warehouseSendDetail.setVendorPoNo("1");
        warehouseSendDetail.setPeriod("1");
      //  warehouseSendDetail.setCREATE_TIME(null);
        warehouseSendDetail.setState("1");
        warehouseSendDetail.setActiveState("1");
        warehouseSendDetail.setFinanceState("1");
        warehouseSendDetail.setPoNoType("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  warehouseSendDetailDao.listWarehouseSendDetailCount(warehouseSendDetail);
		System.out.println("count=" + count);
    }
}
