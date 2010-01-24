package com.hbs.domain.customer.order.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.customer.order.dao.CustomerOrderDao;
import com.hbs.domain.customer.order.pojo.CustomerOrder;

/**
 * test CustomerOrderDao.
 * @author hbs
 *
 */
public class CustomerOrderDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-CustomerOrder.xml)
        return new String[] { "classpath:/applicationContext-test-customerOrder.xml", "classpath:/applicationContext-test-customerOrder.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertCustomerOrder() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao)this.getApplicationContext().getBean("customerOrderDao");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCommCode("1");
        customerOrder.setPoNo("1");
        customerOrder.setPoNoType("1");
        customerOrder.setShortName("1");
        customerOrder.setOderTime(new Date());
        customerOrder.setConName("1");
        customerOrder.setConTel("1");
        customerOrder.setConFax("1");
        customerOrder.setCompanyBranch("1");
        customerOrder.setSettlementType("1");
        customerOrder.setReceiveName("1");
        customerOrder.setReceiveAddress("1");
        customerOrder.setReceiveZip("1");
        customerOrder.setStaffId("1");
        customerOrder.setStaffName("1");
        customerOrder.setSalesId("1");
        customerOrder.setSales("1");
        customerOrder.setIsShowPrice("1");
        customerOrder.setVendorCode("1");
       // customerOrder.setFristCreateTime("1");
       // customerOrder.setCreateTime("1");
        customerOrder.setPeriod("1");
        customerOrder.setState("1");
        customerOrder.setActiveState("1");
        //String id = customerOrderDao.insertCustomerOrder(customerOrder);
        System.out.println("testInsert result--------------------------------------");
        //System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteCustomerOrder() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getApplicationContext().getBean("customerOrderDao");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrderDao.deleteCustomerOrder("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateCustomerOrder() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getApplicationContext().getBean("customerOrderDao");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCommCode("1");
        customerOrder.setPoNo("1");
        customerOrder.setPoNoType("1");
        customerOrder.setShortName("1");
        customerOrder.setOderTime(new Date());
        customerOrder.setConName("1");
        customerOrder.setConTel("1");
        customerOrder.setConFax("1");
        customerOrder.setCompanyBranch("1");
        customerOrder.setSettlementType("1");
        customerOrder.setReceiveName("1");
        customerOrder.setReceiveAddress("1");
        customerOrder.setReceiveZip("1");
        customerOrder.setStaffId("1");
        customerOrder.setStaffName("1");
        customerOrder.setSalesId("1");
        customerOrder.setSales("1");
        customerOrder.setIsShowPrice("1");
        customerOrder.setVendorCode("1");
        //customerOrder.setFristCreateTime("1");
       // customerOrder.setCreateTime("1");
        customerOrder.setPeriod("1");
        customerOrder.setState("1");
        customerOrder.setActiveState("1");
        customerOrderDao.updateCustomerOrder(customerOrder);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindCustomerOrder() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getApplicationContext().getBean("customerOrderDao");
//        CustomerOrder customerOrder = customerOrderDao.findCustomerOrder("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("commCode=" + customerOrder.getCommCode());
//        System.out.println("poNo=" + customerOrder.getPoNo());
//        System.out.println("poNoType=" + customerOrder.getPoNoType());
//        System.out.println("shortName=" + customerOrder.getShortName());
//        System.out.println("oderTime=" + customerOrder.getOderTime());
//        System.out.println("conName=" + customerOrder.getConName());
//        System.out.println("conTel=" + customerOrder.getConTel());
//        System.out.println("conFax=" + customerOrder.getConFax());
//        System.out.println("companyBranch=" + customerOrder.getCompanyBranch());
//        System.out.println("SettlementType=" + customerOrder.getSettlementType());
//        System.out.println("ReceiveName=" + customerOrder.getReceiveName());
//        System.out.println("receiveAddress=" + customerOrder.getReceiveAddress());
//        System.out.println("receiveZip=" + customerOrder.getReceiveZip());
//        System.out.println("staffId=" + customerOrder.getStaffId());
//        System.out.println("staffName=" + customerOrder.getStaffName());
//        System.out.println("salesId=" + customerOrder.getSalesId());
//        System.out.println("sales=" + customerOrder.getSales());
//        System.out.println("isShowPrice=" + customerOrder.getIsShowPrice());
//        System.out.println("vendorCode=" + customerOrder.getVendorCode());
//        System.out.println("fristCreateTime=" + customerOrder.getFristCreateTime());
//        System.out.println("createTime=" + customerOrder.getCreateTime());
//        System.out.println("period=" + customerOrder.getPeriod());
//        System.out.println("state=" + customerOrder.getState());
//        System.out.println("activeState=" + customerOrder.getActiveState());
    }

    /**
     * test list.
     */
    public void testListCustomerOrder() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getApplicationContext().getBean("customerOrderDao");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCommCode("1");
        customerOrder.setPoNo("1");
        customerOrder.setPoNoType("1");
        customerOrder.setShortName("1");
        customerOrder.setOderTime(null);
        customerOrder.setConName("1");
        customerOrder.setConTel("1");
        customerOrder.setConFax("1");
        customerOrder.setCompanyBranch("1");
        customerOrder.setSettlementType("1");
        customerOrder.setReceiveName("1");
        customerOrder.setReceiveAddress("1");
        customerOrder.setReceiveZip("1");
        customerOrder.setStaffId("1");
        customerOrder.setStaffName("1");
        customerOrder.setSalesId("1");
        customerOrder.setSales("1");
        customerOrder.setIsShowPrice("1");
        customerOrder.setVendorCode("1");
       // customerOrder.setFristCreateTime("1");
       // customerOrder.setCreateTime("1");
        customerOrder.setPeriod("1");
        customerOrder.setState("1");
        customerOrder.setActiveState("1");

        System.out.println("testList result--------------------------------------");
        List list =  customerOrderDao.listCustomerOrder(customerOrder);
        for (int i = 0; i < list.size(); i++) {
            CustomerOrder tmpCustomerOrder = (CustomerOrder) list.get(i);
            System.out.println(i + " commCode=" + tmpCustomerOrder.getCommCode());
            System.out.println(i + " poNo=" + tmpCustomerOrder.getPoNo());
            System.out.println(i + " poNoType=" + tmpCustomerOrder.getPoNoType());
            System.out.println(i + " shortName=" + tmpCustomerOrder.getShortName());
            System.out.println(i + " oderTime=" + tmpCustomerOrder.getOderTime());
            System.out.println(i + " conName=" + tmpCustomerOrder.getConName());
            System.out.println(i + " conTel=" + tmpCustomerOrder.getConTel());
            System.out.println(i + " conFax=" + tmpCustomerOrder.getConFax());
            System.out.println(i + " companyBranch=" + tmpCustomerOrder.getCompanyBranch());
            System.out.println(i + " SettlementType=" + tmpCustomerOrder.getSettlementType());
            System.out.println(i + " ReceiveName=" + tmpCustomerOrder.getReceiveName());
            System.out.println(i + " receiveAddress=" + tmpCustomerOrder.getReceiveAddress());
            System.out.println(i + " receiveZip=" + tmpCustomerOrder.getReceiveZip());
            System.out.println(i + " staffId=" + tmpCustomerOrder.getStaffId());
            System.out.println(i + " staffName=" + tmpCustomerOrder.getStaffName());
            System.out.println(i + " salesId=" + tmpCustomerOrder.getSalesId());
            System.out.println(i + " sales=" + tmpCustomerOrder.getSales());
            System.out.println(i + " isShowPrice=" + tmpCustomerOrder.getIsShowPrice());
            System.out.println(i + " vendorCode=" + tmpCustomerOrder.getVendorCode());
            System.out.println(i + " fristCreateTime=" + tmpCustomerOrder.getFristCreateTime());
            System.out.println(i + " createTime=" + tmpCustomerOrder.getCreateTime());
            System.out.println(i + " period=" + tmpCustomerOrder.getPeriod());
            System.out.println(i + " state=" + tmpCustomerOrder.getState());
            System.out.println(i + " activeState=" + tmpCustomerOrder.getActiveState());
        }

    }
    
    /**
     * test list.
     */
    public void testListCustomerOrderCount() {
        CustomerOrderDao customerOrderDao = (CustomerOrderDao) this.getApplicationContext().getBean("customerOrderDao");
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCommCode("1");
        customerOrder.setPoNo("1");
        customerOrder.setPoNoType("1");
        customerOrder.setShortName("1");
        customerOrder.setOderTime(null);
        customerOrder.setConName("1");
        customerOrder.setConTel("1");
        customerOrder.setConFax("1");
        customerOrder.setCompanyBranch("1");
        customerOrder.setSettlementType("1");
        customerOrder.setReceiveName("1");
        customerOrder.setReceiveAddress("1");
        customerOrder.setReceiveZip("1");
        customerOrder.setStaffId("1");
        customerOrder.setStaffName("1");
        customerOrder.setSalesId("1");
        customerOrder.setSales("1");
        customerOrder.setIsShowPrice("1");
        customerOrder.setVendorCode("1");
       // customerOrder.setFristCreateTime("1");
       // customerOrder.setCreateTime("1");
        customerOrder.setPeriod("1");
        customerOrder.setState("1");
        customerOrder.setActiveState("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  customerOrderDao.listCustomerOrderCount(customerOrder);
		System.out.println("count=" + count);
    }
}
