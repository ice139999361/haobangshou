package com.hbs.domain.customer.order.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.customer.order.dao.CustOrderDetailDao;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;

/**
 * test CustOrderDetailDao.
 * @author hbs
 *
 */
public class CustOrderDetailDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-CustOrderDetail.xml)
        return new String[] { "classpath:/applicationContext-test-custOrderDetail.xml", "classpath:/applicationContext-test-custOrderDetail.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertCustOrderDetail() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao)this.getApplicationContext().getBean("custOrderDetailDao");
        CustOrderDetail custOrderDetail = new CustOrderDetail();
        custOrderDetail.setOperSeqId("1");
        custOrderDetail.setCommCode("1");
        custOrderDetail.setShortName("1");
        custOrderDetail.setPoNoType("1");
        custOrderDetail.setPoNo("1");
       // custOrderDetail.setCpartNo(new Date());
        custOrderDetail.setPartNo("1");
        custOrderDetail.setPnDesc("1");
        //custOrderDetail.setCprice("1");
        custOrderDetail.setIsTax("1");
        //custOrderDetail.setTaxRate("1");
        custOrderDetail.setSpecDesc("1");
        custOrderDetail.setCommDesc("1");
        custOrderDetail.setAmount(1);
        //custOrderDetail.setMoney("1");
        custOrderDetail.setDeliveryAmount(1);
        custOrderDetail.setLockAmount(1);
        custOrderDetail.setSelfLockAmount(1);
        custOrderDetail.setCommLockAmount(1);
        custOrderDetail.setOrgDeliveryDate(new Date());
        custOrderDetail.setPreDeliveryDate(new Date());
        custOrderDetail.setVerDeliveryDate(new Date());
        custOrderDetail.setPeriod("1");
        custOrderDetail.setRltOrderPoNo("1");
        custOrderDetail.setVendorCode("1");
        custOrderDetail.setState("1");
        custOrderDetail.setActiveState("1");
        custOrderDetail.setRltSendPoNo("1");
        //String id = custOrderDetailDao.insertCustOrderDetail(custOrderDetail);
        System.out.println("testInsert result--------------------------------------");
        //System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteCustOrderDetail() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao) this.getApplicationContext().getBean("custOrderDetailDao");
        CustOrderDetail custOrderDetail = new CustOrderDetail();
        custOrderDetailDao.deleteCustOrderDetail("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateCustOrderDetail() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao) this.getApplicationContext().getBean("custOrderDetailDao");
        CustOrderDetail custOrderDetail = new CustOrderDetail();
        custOrderDetail.setOperSeqId("1");
        custOrderDetail.setCommCode("1");
        custOrderDetail.setShortName("1");
        custOrderDetail.setPoNoType("1");
        custOrderDetail.setPoNo("1");
       // custOrderDetail.setCpartNo(new Date());
        custOrderDetail.setPartNo("1");
        custOrderDetail.setPnDesc("1");
        //custOrderDetail.setCprice("1");
        custOrderDetail.setIsTax("1");
        //custOrderDetail.setTaxRate("1");
        custOrderDetail.setSpecDesc("1");
        custOrderDetail.setCommDesc("1");
        custOrderDetail.setAmount(2);
        //custOrderDetail.setMoney("1");
        custOrderDetail.setDeliveryAmount(2);
        custOrderDetail.setLockAmount(2);
        custOrderDetail.setSelfLockAmount(2);
        custOrderDetail.setCommLockAmount(2);
        custOrderDetail.setOrgDeliveryDate(new Date());
        custOrderDetail.setPreDeliveryDate(new Date());
        custOrderDetail.setVerDeliveryDate(new Date());
        custOrderDetail.setPeriod("1");
        custOrderDetail.setRltOrderPoNo("1");
        custOrderDetail.setVendorCode("1");
        custOrderDetail.setState("1");
        custOrderDetail.setActiveState("1");
        custOrderDetail.setRltSendPoNo("1");
        custOrderDetailDao.updateCustOrderDetail(custOrderDetail);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindCustOrderDetail() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao) this.getApplicationContext().getBean("custOrderDetailDao");
       // CustOrderDetail custOrderDetail = custOrderDetailDao.findCustOrderDetail("1");
        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("operSeqId=" + custOrderDetail.getOperSeqId());
//        System.out.println("commCode=" + custOrderDetail.getCommCode());
//        System.out.println("shortName=" + custOrderDetail.getShortName());
//        System.out.println("poNoType=" + custOrderDetail.getPoNoType());
//        System.out.println("poNo=" + custOrderDetail.getPoNo());
//        System.out.println("cpartNo=" + custOrderDetail.getCpartNo());
//        System.out.println("partNo=" + custOrderDetail.getPartNo());
//        System.out.println("pnDesc=" + custOrderDetail.getPnDesc());
//        System.out.println("cprice=" + custOrderDetail.getCprice());
//        System.out.println("isTax=" + custOrderDetail.getIsTax());
//        System.out.println("taxRate=" + custOrderDetail.getTaxRate());
//        System.out.println("specDesc=" + custOrderDetail.getSpecDesc());
//        System.out.println("commDesc=" + custOrderDetail.getCommDesc());
//        System.out.println("amount=" + custOrderDetail.getAmount());
//        System.out.println("money=" + custOrderDetail.getMoney());
//        System.out.println("deliveryAmount=" + custOrderDetail.getDeliveryAmount());
//        System.out.println("lockAmount=" + custOrderDetail.getLockAmount());
//        System.out.println("selfLockAmount=" + custOrderDetail.getSelfLockAmount());
//        System.out.println("commLockAmount=" + custOrderDetail.getCommLockAmount());
//        System.out.println("orgDeliveryDate=" + custOrderDetail.getOrgDeliveryDate());
//        System.out.println("preDeliveryDate=" + custOrderDetail.getPreDeliveryDate());
//        System.out.println("verDeliveryDate=" + custOrderDetail.getVerDeliveryDate());
//        System.out.println("period=" + custOrderDetail.getPeriod());
//        System.out.println("rltOrderPoNo=" + custOrderDetail.getRltOrderPoNo());
//        System.out.println("vendorCode=" + custOrderDetail.getVendorCode());
//        System.out.println("state=" + custOrderDetail.getState());
//        System.out.println("activeState=" + custOrderDetail.getActiveState());
//        System.out.println("rltSendPoNo=" + custOrderDetail.getRltSendPoNo());
    }

    /**
     * test list.
     */
    public void testListCustOrderDetail() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao) this.getApplicationContext().getBean("custOrderDetailDao");
        CustOrderDetail custOrderDetail = new CustOrderDetail();
        custOrderDetail.setOperSeqId("1");
        custOrderDetail.setCommCode("1");
        custOrderDetail.setShortName("1");
        custOrderDetail.setPoNoType("1");
        custOrderDetail.setPoNo("1");
        custOrderDetail.setCpartNo(null);
        custOrderDetail.setPartNo("1");
        custOrderDetail.setPnDesc("1");
        //custOrderDetail.setCprice("1");
        custOrderDetail.setIsTax("1");
        //custOrderDetail.setTaxRate("1");
        custOrderDetail.setSpecDesc("1");
        custOrderDetail.setCommDesc("1");
        custOrderDetail.setAmount(2);
        //custOrderDetail.setMoney("1");
        custOrderDetail.setDeliveryAmount(2);
        custOrderDetail.setLockAmount(2);
        custOrderDetail.setSelfLockAmount(2);
        custOrderDetail.setCommLockAmount(2);
        custOrderDetail.setOrgDeliveryDate(null);
        custOrderDetail.setPreDeliveryDate(null);
        custOrderDetail.setVerDeliveryDate(null);
        custOrderDetail.setPeriod("1");
        custOrderDetail.setRltOrderPoNo("1");
        custOrderDetail.setVendorCode("1");
        custOrderDetail.setState("1");
        custOrderDetail.setActiveState("1");
        custOrderDetail.setRltSendPoNo("1");

        System.out.println("testList result--------------------------------------");
        List list =  custOrderDetailDao.listCustOrderDetail(custOrderDetail);
        for (int i = 0; i < list.size(); i++) {
            CustOrderDetail tmpCustOrderDetail = (CustOrderDetail) list.get(i);
            System.out.println(i + " operSeqId=" + tmpCustOrderDetail.getOperSeqId());
            System.out.println(i + " commCode=" + tmpCustOrderDetail.getCommCode());
            System.out.println(i + " shortName=" + tmpCustOrderDetail.getShortName());
            System.out.println(i + " poNoType=" + tmpCustOrderDetail.getPoNoType());
            System.out.println(i + " poNo=" + tmpCustOrderDetail.getPoNo());
            System.out.println(i + " cpartNo=" + tmpCustOrderDetail.getCpartNo());
            System.out.println(i + " partNo=" + tmpCustOrderDetail.getPartNo());
            System.out.println(i + " pnDesc=" + tmpCustOrderDetail.getPnDesc());
            System.out.println(i + " cprice=" + tmpCustOrderDetail.getCprice());
            System.out.println(i + " isTax=" + tmpCustOrderDetail.getIsTax());
            System.out.println(i + " taxRate=" + tmpCustOrderDetail.getTaxRate());
            System.out.println(i + " specDesc=" + tmpCustOrderDetail.getSpecDesc());
            System.out.println(i + " commDesc=" + tmpCustOrderDetail.getCommDesc());
            System.out.println(i + " amount=" + tmpCustOrderDetail.getAmount());
            System.out.println(i + " money=" + tmpCustOrderDetail.getMoney());
            System.out.println(i + " deliveryAmount=" + tmpCustOrderDetail.getDeliveryAmount());
            System.out.println(i + " lockAmount=" + tmpCustOrderDetail.getLockAmount());
            System.out.println(i + " selfLockAmount=" + tmpCustOrderDetail.getSelfLockAmount());
            System.out.println(i + " commLockAmount=" + tmpCustOrderDetail.getCommLockAmount());
            System.out.println(i + " orgDeliveryDate=" + tmpCustOrderDetail.getOrgDeliveryDate());
            System.out.println(i + " preDeliveryDate=" + tmpCustOrderDetail.getPreDeliveryDate());
            System.out.println(i + " verDeliveryDate=" + tmpCustOrderDetail.getVerDeliveryDate());
            System.out.println(i + " period=" + tmpCustOrderDetail.getPeriod());
            System.out.println(i + " rltOrderPoNo=" + tmpCustOrderDetail.getRltOrderPoNo());
            System.out.println(i + " vendorCode=" + tmpCustOrderDetail.getVendorCode());
            System.out.println(i + " state=" + tmpCustOrderDetail.getState());
            System.out.println(i + " activeState=" + tmpCustOrderDetail.getActiveState());
            System.out.println(i + " rltSendPoNo=" + tmpCustOrderDetail.getRltSendPoNo());
        }

    }
    
    /**
     * test list.
     */
    public void testListCustOrderDetailCount() {
        CustOrderDetailDao custOrderDetailDao = (CustOrderDetailDao) this.getApplicationContext().getBean("custOrderDetailDao");
        CustOrderDetail custOrderDetail = new CustOrderDetail();
        custOrderDetail.setOperSeqId("1");
        custOrderDetail.setCommCode("1");
        custOrderDetail.setShortName("1");
        custOrderDetail.setPoNoType("1");
        custOrderDetail.setPoNo("1");
        custOrderDetail.setCpartNo(null);
        custOrderDetail.setPartNo("1");
        custOrderDetail.setPnDesc("1");
        //custOrderDetail.setCprice("1");
        custOrderDetail.setIsTax("1");
        //custOrderDetail.setTaxRate("1");
        custOrderDetail.setSpecDesc("1");
        custOrderDetail.setCommDesc("1");
        custOrderDetail.setAmount(2);
        //custOrderDetail.setMoney("1");
        custOrderDetail.setDeliveryAmount(2);
        custOrderDetail.setLockAmount(2);
        custOrderDetail.setSelfLockAmount(2);
        custOrderDetail.setCommLockAmount(2);
        custOrderDetail.setOrgDeliveryDate(null);
        custOrderDetail.setPreDeliveryDate(null);
        custOrderDetail.setVerDeliveryDate(null);
        custOrderDetail.setPeriod("1");
        custOrderDetail.setRltOrderPoNo("1");
        custOrderDetail.setVendorCode("1");
        custOrderDetail.setState("1");
        custOrderDetail.setActiveState("1");
        custOrderDetail.setRltSendPoNo("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  custOrderDetailDao.listCustOrderDetailCount(custOrderDetail);
		System.out.println("count=" + count);
    }
}
