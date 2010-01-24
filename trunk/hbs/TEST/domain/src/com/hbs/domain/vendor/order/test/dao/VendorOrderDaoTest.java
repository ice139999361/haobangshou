package com.hbs.domain.vendor.order.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.vendor.order.dao.VendorOrderDao;
import com.hbs.domain.vendor.order.pojo.VendorOrder;

/**
 * test VendorOrderDao.
 * @author hbs
 *
 */
public class VendorOrderDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-VendorOrder.xml)
        return new String[] { "classpath:/applicationContext-test-vendorOrder.xml", "classpath:/applicationContext-test-vendorOrder.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertVendorOrder() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao)this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = new VendorOrder();
        vendorOrder.setCommCode("1");
        vendorOrder.setPoNo("1");
        vendorOrder.setPoNoType("1");
        vendorOrder.setShortName("1");
        vendorOrder.setCreateTime(new Date());
        vendorOrder.setConName("1");
        vendorOrder.setConTel("1");
        vendorOrder.setConFax("1");
        vendorOrder.setCompanyBranch("1");
        vendorOrder.setSettlementType("1");
        vendorOrder.setReceiveName("1");
        vendorOrder.setReceiveAddress("1");
        vendorOrder.setReceiveZip("1");
        vendorOrder.setStaffId("1");
        vendorOrder.setStaffName("1");
        vendorOrder.setSales("1");
        vendorOrder.setIsShowPrice("1");
        vendorOrder.setOrderTime(new Date());
        vendorOrder.setPeriod("1");
        vendorOrder.setState("1");
        vendorOrder.setActiveState("1");
        //String id = vendorOrderDao.insertVendorOrder(vendorOrder);
        System.out.println("testInsert result--------------------------------------");
        //System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteVendorOrder() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao) this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = new VendorOrder();
        vendorOrderDao.deleteVendorOrder("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateVendorOrder() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao) this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = new VendorOrder();
        vendorOrder.setCommCode("1");
        vendorOrder.setPoNo("1");
        vendorOrder.setPoNoType("1");
        vendorOrder.setShortName("1");
        vendorOrder.setCreateTime(new Date());
        vendorOrder.setConName("1");
        vendorOrder.setConTel("1");
        vendorOrder.setConFax("1");
        vendorOrder.setCompanyBranch("1");
        vendorOrder.setSettlementType("1");
        vendorOrder.setReceiveName("1");
        vendorOrder.setReceiveAddress("1");
        vendorOrder.setReceiveZip("1");
        vendorOrder.setStaffId("1");
        vendorOrder.setStaffName("1");
        vendorOrder.setSales("1");
        vendorOrder.setIsShowPrice("1");
        vendorOrder.setOrderTime(new Date());
        vendorOrder.setPeriod("1");
        vendorOrder.setState("1");
        vendorOrder.setActiveState("1");
        vendorOrderDao.updateVendorOrder(vendorOrder);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindVendorOrder() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao) this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = vendorOrderDao.findVendorOrder("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("commCode=" + vendorOrder.getCommCode());
        System.out.println("poNo=" + vendorOrder.getPoNo());
        System.out.println("poNoType=" + vendorOrder.getPoNoType());
        System.out.println("shortName=" + vendorOrder.getShortName());
        System.out.println("createTime=" + vendorOrder.getCreateTime());
        System.out.println("conName=" + vendorOrder.getConName());
        System.out.println("conTel=" + vendorOrder.getConTel());
        System.out.println("conFax=" + vendorOrder.getConFax());
        System.out.println("companyBranch=" + vendorOrder.getCompanyBranch());
        System.out.println("SettlementType=" + vendorOrder.getSettlementType());
        System.out.println("ReceiveName=" + vendorOrder.getReceiveName());
        System.out.println("receiveAddress=" + vendorOrder.getReceiveAddress());
        System.out.println("receiveZip=" + vendorOrder.getReceiveZip());
        System.out.println("staffId=" + vendorOrder.getStaffId());
        System.out.println("staffName=" + vendorOrder.getStaffName());
        System.out.println("sales=" + vendorOrder.getSales());
        System.out.println("isShowPrice=" + vendorOrder.getIsShowPrice());
        System.out.println("orderTime=" + vendorOrder.getOrderTime());
        System.out.println("period=" + vendorOrder.getPeriod());
        System.out.println("state=" + vendorOrder.getState());
        System.out.println("activeState=" + vendorOrder.getActiveState());
    }

    /**
     * test list.
     */
    public void testListVendorOrder() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao) this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = new VendorOrder();
        vendorOrder.setCommCode("1");
        vendorOrder.setPoNo("1");
        vendorOrder.setPoNoType("1");
        vendorOrder.setShortName("1");
        vendorOrder.setCreateTime(null);
        vendorOrder.setConName("1");
        vendorOrder.setConTel("1");
        vendorOrder.setConFax("1");
        vendorOrder.setCompanyBranch("1");
        vendorOrder.setSettlementType("1");
        vendorOrder.setReceiveName("1");
        vendorOrder.setReceiveAddress("1");
        vendorOrder.setReceiveZip("1");
        vendorOrder.setStaffId("1");
        vendorOrder.setStaffName("1");
        vendorOrder.setSales("1");
        vendorOrder.setIsShowPrice("1");
        vendorOrder.setOrderTime(null);
        vendorOrder.setPeriod("1");
        vendorOrder.setState("1");
        vendorOrder.setActiveState("1");

        System.out.println("testList result--------------------------------------");
        List list =  vendorOrderDao.listVendorOrder(vendorOrder);
        for (int i = 0; i < list.size(); i++) {
            VendorOrder tmpVendorOrder = (VendorOrder) list.get(i);
            System.out.println(i + " commCode=" + tmpVendorOrder.getCommCode());
            System.out.println(i + " poNo=" + tmpVendorOrder.getPoNo());
            System.out.println(i + " poNoType=" + tmpVendorOrder.getPoNoType());
            System.out.println(i + " shortName=" + tmpVendorOrder.getShortName());
            System.out.println(i + " createTime=" + tmpVendorOrder.getCreateTime());
            System.out.println(i + " conName=" + tmpVendorOrder.getConName());
            System.out.println(i + " conTel=" + tmpVendorOrder.getConTel());
            System.out.println(i + " conFax=" + tmpVendorOrder.getConFax());
            System.out.println(i + " companyBranch=" + tmpVendorOrder.getCompanyBranch());
            System.out.println(i + " SettlementType=" + tmpVendorOrder.getSettlementType());
            System.out.println(i + " ReceiveName=" + tmpVendorOrder.getReceiveName());
            System.out.println(i + " receiveAddress=" + tmpVendorOrder.getReceiveAddress());
            System.out.println(i + " receiveZip=" + tmpVendorOrder.getReceiveZip());
            System.out.println(i + " staffId=" + tmpVendorOrder.getStaffId());
            System.out.println(i + " staffName=" + tmpVendorOrder.getStaffName());
            System.out.println(i + " sales=" + tmpVendorOrder.getSales());
            System.out.println(i + " isShowPrice=" + tmpVendorOrder.getIsShowPrice());
            System.out.println(i + " orderTime=" + tmpVendorOrder.getOrderTime());
            System.out.println(i + " period=" + tmpVendorOrder.getPeriod());
            System.out.println(i + " state=" + tmpVendorOrder.getState());
            System.out.println(i + " activeState=" + tmpVendorOrder.getActiveState());
        }

    }
    
    /**
     * test list.
     */
    public void testListVendorOrderCount() {
        VendorOrderDao vendorOrderDao = (VendorOrderDao) this.getApplicationContext().getBean("vendorOrderDao");
        VendorOrder vendorOrder = new VendorOrder();
        vendorOrder.setCommCode("1");
        vendorOrder.setPoNo("1");
        vendorOrder.setPoNoType("1");
        vendorOrder.setShortName("1");
        vendorOrder.setCreateTime(null);
        vendorOrder.setConName("1");
        vendorOrder.setConTel("1");
        vendorOrder.setConFax("1");
        vendorOrder.setCompanyBranch("1");
        vendorOrder.setSettlementType("1");
        vendorOrder.setReceiveName("1");
        vendorOrder.setReceiveAddress("1");
        vendorOrder.setReceiveZip("1");
        vendorOrder.setStaffId("1");
        vendorOrder.setStaffName("1");
        vendorOrder.setSales("1");
        vendorOrder.setIsShowPrice("1");
        vendorOrder.setOrderTime(null);
        vendorOrder.setPeriod("1");
        vendorOrder.setState("1");
        vendorOrder.setActiveState("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  vendorOrderDao.listVendorOrderCount(vendorOrder);
		System.out.println("count=" + count);
    }
}
