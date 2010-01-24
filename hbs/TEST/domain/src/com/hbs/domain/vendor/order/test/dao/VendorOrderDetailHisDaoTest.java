package com.hbs.domain.vendor.order.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;

/**
 * test VendorOrderDetailDao.
 * @author hbs
 *
 */
public class VendorOrderDetailHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-VendorOrderDetail.xml)
        return new String[] { "classpath:/applicationContext-test-vendorOrderDetail.xml", "classpath:/applicationContext-test-vendorOrderDetail.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertVendorOrderDetail() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao)this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
        vendorOrderDetail.setOperSeqId("1");
        vendorOrderDetail.setCommCode("1");
        vendorOrderDetail.setShortName("1");
        vendorOrderDetail.setPoNoType("1");
        vendorOrderDetail.setPoNo("1");
        vendorOrderDetail.setCpartNo(new Date());
        vendorOrderDetail.setPartNo("1");
        vendorOrderDetail.setPnDesc("1");
//        vendorOrderDetail.setCprice("1");
//        vendorOrderDetail.setIsTax("1");
//        vendorOrderDetail.setTaxRate("1");
//        vendorOrderDetail.setSpecDesc("1");
//        vendorOrderDetail.setCommDesc("1");
//        vendorOrderDetail.setAmount(1);
//        vendorOrderDetail.setMoney("1");
//        vendorOrderDetail.setDeliveryAmount(1);
//        vendorOrderDetail.setOrgDeliveryDate(new Date());
//        vendorOrderDetail.setVerDeliveryDate(new Date());
//        vendorOrderDetail.setPeriod("1");
//        vendorOrderDetail.setRltOrderPoNo("1");
//        vendorOrderDetail.setState("1");
//        vendorOrderDetail.setActiveState("1");
//        vendorOrderDetail.setRltRecPoNo("1");
//        String id = vendorOrderDetailDao.insertVendorOrderDetail(vendorOrderDetail);
//        System.out.println("testInsert result--------------------------------------");
//        System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteVendorOrderDetail() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao) this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
        vendorOrderDetailDao.deleteVendorOrderDetail("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateVendorOrderDetail() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao) this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
        vendorOrderDetail.setOperSeqId("1");
        vendorOrderDetail.setCommCode("1");
        vendorOrderDetail.setShortName("1");
        vendorOrderDetail.setPoNoType("1");
        vendorOrderDetail.setPoNo("1");
        vendorOrderDetail.setCpartNo(new Date());
        vendorOrderDetail.setPartNo("1");
        vendorOrderDetail.setPnDesc("1");
//        vendorOrderDetail.setCprice("1");
//        vendorOrderDetail.setIsTax("1");
//        vendorOrderDetail.setTaxRate("1");
//        vendorOrderDetail.setSpecDesc("1");
//        vendorOrderDetail.setCommDesc("1");
//        vendorOrderDetail.setAmount(2);
//        vendorOrderDetail.setMoney("1");
//        vendorOrderDetail.setDeliveryAmount(2);
        vendorOrderDetail.setOrgDeliveryDate(new Date());
        vendorOrderDetail.setVerDeliveryDate(new Date());
        vendorOrderDetail.setPeriod("1");
        vendorOrderDetail.setRltOrderPoNo("1");
        vendorOrderDetail.setState("1");
        vendorOrderDetail.setActiveState("1");
        vendorOrderDetail.setRltRecPoNo("1");
        vendorOrderDetailDao.updateVendorOrderDetail(vendorOrderDetail);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindVendorOrderDetail() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao) this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = vendorOrderDetailDao.findVendorOrderDetail("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("operSeqId=" + vendorOrderDetail.getOperSeqId());
        System.out.println("commCode=" + vendorOrderDetail.getCommCode());
        System.out.println("shortName=" + vendorOrderDetail.getShortName());
        System.out.println("poNoType=" + vendorOrderDetail.getPoNoType());
        System.out.println("poNo=" + vendorOrderDetail.getPoNo());
        System.out.println("cpartNo=" + vendorOrderDetail.getCpartNo());
        System.out.println("partNo=" + vendorOrderDetail.getPartNo());
        System.out.println("pnDesc=" + vendorOrderDetail.getPnDesc());
        System.out.println("cprice=" + vendorOrderDetail.getCprice());
        System.out.println("isTax=" + vendorOrderDetail.getIsTax());
        System.out.println("taxRate=" + vendorOrderDetail.getTaxRate());
        System.out.println("specDesc=" + vendorOrderDetail.getSpecDesc());
        System.out.println("commDesc=" + vendorOrderDetail.getCommDesc());
        System.out.println("amount=" + vendorOrderDetail.getAmount());
        System.out.println("money=" + vendorOrderDetail.getMoney());
        System.out.println("deliveryAmount=" + vendorOrderDetail.getDeliveryAmount());
        System.out.println("orgDeliveryDate=" + vendorOrderDetail.getOrgDeliveryDate());
        System.out.println("verDeliveryDate=" + vendorOrderDetail.getVerDeliveryDate());
        System.out.println("period=" + vendorOrderDetail.getPeriod());
        System.out.println("rltOrderPoNo=" + vendorOrderDetail.getRltOrderPoNo());
        System.out.println("state=" + vendorOrderDetail.getState());
        System.out.println("activeState=" + vendorOrderDetail.getActiveState());
        System.out.println("rltRecPoNo=" + vendorOrderDetail.getRltRecPoNo());
    }

    /**
     * test list.
     */
    public void testListVendorOrderDetail() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao) this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
        vendorOrderDetail.setOperSeqId("1");
        vendorOrderDetail.setCommCode("1");
        vendorOrderDetail.setShortName("1");
        vendorOrderDetail.setPoNoType("1");
        vendorOrderDetail.setPoNo("1");
        vendorOrderDetail.setCpartNo(null);
        vendorOrderDetail.setPartNo("1");
        vendorOrderDetail.setPnDesc("1");
//        vendorOrderDetail.setCprice("1");
//        vendorOrderDetail.setIsTax("1");
//        vendorOrderDetail.setTaxRate("1");
//        vendorOrderDetail.setSpecDesc("1");
//        vendorOrderDetail.setCommDesc("1");
//        vendorOrderDetail.setAmount(2);
//        vendorOrderDetail.setMoney("1");
//        vendorOrderDetail.setDeliveryAmount(2);
        vendorOrderDetail.setOrgDeliveryDate(null);
        vendorOrderDetail.setVerDeliveryDate(null);
        vendorOrderDetail.setPeriod("1");
        vendorOrderDetail.setRltOrderPoNo("1");
        vendorOrderDetail.setState("1");
        vendorOrderDetail.setActiveState("1");
        vendorOrderDetail.setRltRecPoNo("1");

        System.out.println("testList result--------------------------------------");
        List list =  vendorOrderDetailDao.listVendorOrderDetail(vendorOrderDetail);
        for (int i = 0; i < list.size(); i++) {
            VendorOrderDetail tmpVendorOrderDetail = (VendorOrderDetail) list.get(i);
            System.out.println(i + " operSeqId=" + tmpVendorOrderDetail.getOperSeqId());
            System.out.println(i + " commCode=" + tmpVendorOrderDetail.getCommCode());
            System.out.println(i + " shortName=" + tmpVendorOrderDetail.getShortName());
            System.out.println(i + " poNoType=" + tmpVendorOrderDetail.getPoNoType());
            System.out.println(i + " poNo=" + tmpVendorOrderDetail.getPoNo());
            System.out.println(i + " cpartNo=" + tmpVendorOrderDetail.getCpartNo());
            System.out.println(i + " partNo=" + tmpVendorOrderDetail.getPartNo());
            System.out.println(i + " pnDesc=" + tmpVendorOrderDetail.getPnDesc());
            System.out.println(i + " cprice=" + tmpVendorOrderDetail.getCprice());
            System.out.println(i + " isTax=" + tmpVendorOrderDetail.getIsTax());
            System.out.println(i + " taxRate=" + tmpVendorOrderDetail.getTaxRate());
            System.out.println(i + " specDesc=" + tmpVendorOrderDetail.getSpecDesc());
            System.out.println(i + " commDesc=" + tmpVendorOrderDetail.getCommDesc());
            System.out.println(i + " amount=" + tmpVendorOrderDetail.getAmount());
            System.out.println(i + " money=" + tmpVendorOrderDetail.getMoney());
            System.out.println(i + " deliveryAmount=" + tmpVendorOrderDetail.getDeliveryAmount());
            System.out.println(i + " orgDeliveryDate=" + tmpVendorOrderDetail.getOrgDeliveryDate());
            System.out.println(i + " verDeliveryDate=" + tmpVendorOrderDetail.getVerDeliveryDate());
            System.out.println(i + " period=" + tmpVendorOrderDetail.getPeriod());
            System.out.println(i + " rltOrderPoNo=" + tmpVendorOrderDetail.getRltOrderPoNo());
            System.out.println(i + " state=" + tmpVendorOrderDetail.getState());
            System.out.println(i + " activeState=" + tmpVendorOrderDetail.getActiveState());
            System.out.println(i + " rltRecPoNo=" + tmpVendorOrderDetail.getRltRecPoNo());
        }

    }
    
    /**
     * test list.
     */
    public void testListVendorOrderDetailCount() {
        VendorOrderDetailDao vendorOrderDetailDao = (VendorOrderDetailDao) this.getApplicationContext().getBean("vendorOrderDetailDao");
        VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
        vendorOrderDetail.setOperSeqId("1");
        vendorOrderDetail.setCommCode("1");
        vendorOrderDetail.setShortName("1");
        vendorOrderDetail.setPoNoType("1");
        vendorOrderDetail.setPoNo("1");
        vendorOrderDetail.setCpartNo(null);
        vendorOrderDetail.setPartNo("1");
        vendorOrderDetail.setPnDesc("1");
//        vendorOrderDetail.setCprice("1");
//        vendorOrderDetail.setIsTax("1");
//        vendorOrderDetail.setTaxRate("1");
//        vendorOrderDetail.setSpecDesc("1");
//        vendorOrderDetail.setCommDesc("1");
//        vendorOrderDetail.setAmount(2);
//        vendorOrderDetail.setMoney("1");
        vendorOrderDetail.setDeliveryAmount(2);
        vendorOrderDetail.setOrgDeliveryDate(null);
        vendorOrderDetail.setVerDeliveryDate(null);
        vendorOrderDetail.setPeriod("1");
        vendorOrderDetail.setRltOrderPoNo("1");
        vendorOrderDetail.setState("1");
        vendorOrderDetail.setActiveState("1");
        vendorOrderDetail.setRltRecPoNo("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  vendorOrderDetailDao.listVendorOrderDetailCount(vendorOrderDetail);
		System.out.println("count=" + count);
    }
}
