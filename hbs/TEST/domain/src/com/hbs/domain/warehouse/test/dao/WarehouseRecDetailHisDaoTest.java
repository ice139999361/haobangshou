package com.hbs.domain.warehouse.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.warehouse.dao.WarehouseRecDetailDao;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;

/**
 * test WarehouseRecDetailDao.
 * @author hbs
 *
 */
public class WarehouseRecDetailHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-WarehouseRecDetail.xml)
        return new String[] { "classpath:/applicationContext-test-warehouseRecDetail.xml", "classpath:/applicationContext-test-warehouseRecDetail.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertWarehouseRecDetail() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao)this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = new WarehouseRecDetail();
        warehouseRecDetail.setRecDetailSeqId(1);
        warehouseRecDetail.setRecPoNo("1");
        warehouseRecDetail.setVendorCode("1");
        warehouseRecDetail.setShortName("1");
        warehouseRecDetail.setPoNoDate(new Date());
        warehouseRecDetail.setApplyDate(new Date());
        warehouseRecDetail.setHouseType("1");
        warehouseRecDetail.setRltPoNo("1");
        warehouseRecDetail.setSettlementType("1");
        warehouseRecDetail.setPartNo("1");
      //  warehouseRecDetail.setVenPartNo("1");
        warehouseRecDetail.setPnDesc("1");
//        warehouseRecDetail.setPrice("1");
//        warehouseRecDetail.setIsTax("1");
//        warehouseRecDetail.setTaxRate("1");
//        warehouseRecDetail.setAmount(1);
//        warehouseRecDetail.setPeriod("1");
//        warehouseRecDetail.setFinanceState("1");
//        warehouseRecDetail.setPoNoType("1");
//        String id = warehouseRecDetailDao.insertWarehouseRecDetail(warehouseRecDetail);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
//        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteWarehouseRecDetail() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao) this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = new WarehouseRecDetail();
        warehouseRecDetailDao.deleteWarehouseRecDetail("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateWarehouseRecDetail() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao) this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = new WarehouseRecDetail();
        warehouseRecDetail.setRecDetailSeqId(2);
        warehouseRecDetail.setRecPoNo("1");
        warehouseRecDetail.setVendorCode("1");
        warehouseRecDetail.setShortName("1");
        warehouseRecDetail.setPoNoDate(new Date());
        warehouseRecDetail.setApplyDate(new Date());
        warehouseRecDetail.setHouseType("1");
        warehouseRecDetail.setRltPoNo("1");
        warehouseRecDetail.setSettlementType("1");
        warehouseRecDetail.setPartNo("1");
    //    warehouseRecDetail.setVenPartNo("1");
        warehouseRecDetail.setPnDesc("1");
//        warehouseRecDetail.setPrice("1");
//        warehouseRecDetail.setIsTax("1");
//        warehouseRecDetail.setTaxRate("1");
//        warehouseRecDetail.setAmount(2);
        warehouseRecDetail.setPeriod("1");
        warehouseRecDetail.setFinanceState("1");
        warehouseRecDetail.setPoNoType("1");
        warehouseRecDetailDao.updateWarehouseRecDetail(warehouseRecDetail);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindWarehouseRecDetail() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao) this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = warehouseRecDetailDao.findWarehouseRecDetail("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("recDetailSeqId=" + warehouseRecDetail.getRecDetailSeqId());
        System.out.println("recPoNo=" + warehouseRecDetail.getRecPoNo());
        System.out.println("vendorCode=" + warehouseRecDetail.getVendorCode());
        System.out.println("shortName=" + warehouseRecDetail.getShortName());
        System.out.println("poNoDate=" + warehouseRecDetail.getPoNoDate());
        System.out.println("applyDate=" + warehouseRecDetail.getApplyDate());
        System.out.println("houseType=" + warehouseRecDetail.getHouseType());
        System.out.println("rltPoNo=" + warehouseRecDetail.getRltPoNo());
        System.out.println("settlementType=" + warehouseRecDetail.getSettlementType());
        System.out.println("partNo=" + warehouseRecDetail.getPartNo());
    //    System.out.println("venPartNo=" + warehouseRecDetail.getVenPartNo());
        System.out.println("pnDesc=" + warehouseRecDetail.getPnDesc());
        System.out.println("price=" + warehouseRecDetail.getPrice());
        System.out.println("isTax=" + warehouseRecDetail.getIsTax());
        System.out.println("taxRate=" + warehouseRecDetail.getTaxRate());
        System.out.println("amount=" + warehouseRecDetail.getAmount());
        System.out.println("period=" + warehouseRecDetail.getPeriod());
        System.out.println("financeState=" + warehouseRecDetail.getFinanceState());
        System.out.println("poNoType=" + warehouseRecDetail.getPoNoType());
    }

    /**
     * test list.
     */
    public void testListWarehouseRecDetail() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao) this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = new WarehouseRecDetail();
        warehouseRecDetail.setRecDetailSeqId(2);
        warehouseRecDetail.setRecPoNo("1");
        warehouseRecDetail.setVendorCode("1");
        warehouseRecDetail.setShortName("1");
        warehouseRecDetail.setPoNoDate(null);
        warehouseRecDetail.setApplyDate(null);
        warehouseRecDetail.setHouseType("1");
        warehouseRecDetail.setRltPoNo("1");
        warehouseRecDetail.setSettlementType("1");
        warehouseRecDetail.setPartNo("1");
   //     warehouseRecDetail.setVenPartNo("1");
//        warehouseRecDetail.setPnDesc("1");
//        warehouseRecDetail.setPrice("1");
//        warehouseRecDetail.setIsTax("1");
//        warehouseRecDetail.setTaxRate("1");
//        warehouseRecDetail.setAmount(2);
        warehouseRecDetail.setPeriod("1");
        warehouseRecDetail.setFinanceState("1");
        warehouseRecDetail.setPoNoType("1");

        System.out.println("testList result--------------------------------------");
        List list =  warehouseRecDetailDao.listWarehouseRecDetail(warehouseRecDetail);
        for (int i = 0; i < list.size(); i++) {
            WarehouseRecDetail tmpWarehouseRecDetail = (WarehouseRecDetail) list.get(i);
            System.out.println(i + " recDetailSeqId=" + tmpWarehouseRecDetail.getRecDetailSeqId());
            System.out.println(i + " recPoNo=" + tmpWarehouseRecDetail.getRecPoNo());
            System.out.println(i + " vendorCode=" + tmpWarehouseRecDetail.getVendorCode());
            System.out.println(i + " shortName=" + tmpWarehouseRecDetail.getShortName());
            System.out.println(i + " poNoDate=" + tmpWarehouseRecDetail.getPoNoDate());
            System.out.println(i + " applyDate=" + tmpWarehouseRecDetail.getApplyDate());
            System.out.println(i + " houseType=" + tmpWarehouseRecDetail.getHouseType());
            System.out.println(i + " rltPoNo=" + tmpWarehouseRecDetail.getRltPoNo());
            System.out.println(i + " settlementType=" + tmpWarehouseRecDetail.getSettlementType());
            System.out.println(i + " partNo=" + tmpWarehouseRecDetail.getPartNo());
    //        System.out.println(i + " venPartNo=" + tmpWarehouseRecDetail.getVenPartNo());
            System.out.println(i + " pnDesc=" + tmpWarehouseRecDetail.getPnDesc());
            System.out.println(i + " price=" + tmpWarehouseRecDetail.getPrice());
            System.out.println(i + " isTax=" + tmpWarehouseRecDetail.getIsTax());
            System.out.println(i + " taxRate=" + tmpWarehouseRecDetail.getTaxRate());
            System.out.println(i + " amount=" + tmpWarehouseRecDetail.getAmount());
            System.out.println(i + " period=" + tmpWarehouseRecDetail.getPeriod());
            System.out.println(i + " financeState=" + tmpWarehouseRecDetail.getFinanceState());
            System.out.println(i + " poNoType=" + tmpWarehouseRecDetail.getPoNoType());
        }

    }
    
    /**
     * test list.
     */
    public void testListWarehouseRecDetailCount() {
        WarehouseRecDetailDao warehouseRecDetailDao = (WarehouseRecDetailDao) this.getApplicationContext().getBean("warehouseRecDetailDao");
        WarehouseRecDetail warehouseRecDetail = new WarehouseRecDetail();
        warehouseRecDetail.setRecDetailSeqId(2);
        warehouseRecDetail.setRecPoNo("1");
        warehouseRecDetail.setVendorCode("1");
        warehouseRecDetail.setShortName("1");
        warehouseRecDetail.setPoNoDate(null);
        warehouseRecDetail.setApplyDate(null);
        warehouseRecDetail.setHouseType("1");
        warehouseRecDetail.setRltPoNo("1");
        warehouseRecDetail.setSettlementType("1");
        warehouseRecDetail.setPartNo("1");
   //     warehouseRecDetail.setVenPartNo("1");
        warehouseRecDetail.setPnDesc("1");
//        warehouseRecDetail.setPrice("1");
//        warehouseRecDetail.setIsTax("1");
//        warehouseRecDetail.setTaxRate("1");
//        warehouseRecDetail.setAmount(2);
//        warehouseRecDetail.setPeriod("1");
        warehouseRecDetail.setFinanceState("1");
        warehouseRecDetail.setPoNoType("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  warehouseRecDetailDao.listWarehouseRecDetailCount(warehouseRecDetail);
		System.out.println("count=" + count);
    }
}
