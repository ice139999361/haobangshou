package com.hbs.domain.invoice.test.dao;

import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.common.dao.baseinfo.InvoiceInfoDao;
import com.hbs.domain.common.pojo.baseinfo.InvoiceInfo;

/**
 * test InvoiceInfoDao.
 * @author hbs
 *
 */
public class VendorRecInvoiceHisDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-InvoiceInfo.xml)
        return new String[] { "classpath:/applicationContext-test-invoiceInfo.xml", "classpath:/applicationContext-test-invoiceInfo.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertInvoiceInfo() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao)this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceSeqId(1);
        invoiceInfo.setStaffId("1");
        invoiceInfo.setStaffName("1");
        invoiceInfo.setCreateTime(new Date());
        invoiceInfo.setPoNo("1");
       // invoiceInfo.setPO_NO_DATE("1");
        //invoiceInfo.setCcode(new Date());
        //invoiceInfo.setShortName(new Date());
        invoiceInfo.setPartNo("1");
        invoiceInfo.setCpartNo("1");
        invoiceInfo.setPnDesc("1");
        invoiceInfo.setAmount(1);
        //invoiceInfo.setAllMoney("1");
        //invoiceInfo.setCurMoney("1");
        //invoiceInfo.setLeftMoney("1");
        invoiceInfo.setInvoiceDesc("1");
        //String id = invoiceInfoDao.insertInvoiceInfo(invoiceInfo);
        System.out.println("testInsert result--------------------------------------");
       // System.out.println("return id=" + id);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteInvoiceInfo() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao) this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfoDao.deleteInvoiceInfo("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateInvoiceInfo() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao) this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceSeqId(2);
        invoiceInfo.setStaffId("1");
        invoiceInfo.setStaffName("1");
        invoiceInfo.setCreateTime(new Date());
        invoiceInfo.setPoNo("1");
       // invoiceInfo.setPO_NO_DATE("1");
        //invoiceInfo.setCcode(new Date());
        //invoiceInfo.setShortName(new Date());
        invoiceInfo.setPartNo("1");
        invoiceInfo.setCpartNo("1");
        invoiceInfo.setPnDesc("1");
        invoiceInfo.setAmount(2);
        //invoiceInfo.setAllMoney("1");
        //invoiceInfo.setCurMoney("1");
       // invoiceInfo.setLeftMoney("1");
        invoiceInfo.setInvoiceDesc("1");
        invoiceInfoDao.updateInvoiceInfo(invoiceInfo);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindInvoiceInfo() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao) this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = invoiceInfoDao.findInvoiceInfo("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("invoiceSeqId=" + invoiceInfo.getInvoiceSeqId());
        System.out.println("staffId=" + invoiceInfo.getStaffId());
        System.out.println("staffName=" + invoiceInfo.getStaffName());
        System.out.println("createTime=" + invoiceInfo.getCreateTime());
        System.out.println("poNo=" + invoiceInfo.getPoNo());
       // System.out.println("PO_NO_DATE=" + invoiceInfo.getPO_NO_DATE());
        System.out.println("ccode=" + invoiceInfo.getCcode());
        System.out.println("shortName=" + invoiceInfo.getShortName());
        System.out.println("partNo=" + invoiceInfo.getPartNo());
        System.out.println("cpartNo=" + invoiceInfo.getCpartNo());
        System.out.println("pnDesc=" + invoiceInfo.getPnDesc());
        System.out.println("amount=" + invoiceInfo.getAmount());
        System.out.println("allMoney=" + invoiceInfo.getAllMoney());
        System.out.println("curMoney=" + invoiceInfo.getCurMoney());
        System.out.println("leftMoney=" + invoiceInfo.getLeftMoney());
        System.out.println("invoiceDesc=" + invoiceInfo.getInvoiceDesc());
    }

    /**
     * test list.
     */
    public void testListInvoiceInfo() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao) this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceSeqId(2);
        invoiceInfo.setStaffId("1");
        invoiceInfo.setStaffName("1");
        invoiceInfo.setCreateTime(null);
        invoiceInfo.setPoNo("1");
       // invoiceInfo.setPO_NO_DATE("1");
        invoiceInfo.setCcode(null);
        invoiceInfo.setShortName(null);
        invoiceInfo.setPartNo("1");
        invoiceInfo.setCpartNo("1");
        invoiceInfo.setPnDesc("1");
        invoiceInfo.setAmount(2);
        //invoiceInfo.setAllMoney("1");
        //invoiceInfo.setCurMoney("1");
        //invoiceInfo.setLeftMoney("1");
        invoiceInfo.setInvoiceDesc("1");

        System.out.println("testList result--------------------------------------");
        List list =  invoiceInfoDao.listInvoiceInfo(invoiceInfo);
        for (int i = 0; i < list.size(); i++) {
            InvoiceInfo tmpInvoiceInfo = (InvoiceInfo) list.get(i);
            System.out.println(i + " invoiceSeqId=" + tmpInvoiceInfo.getInvoiceSeqId());
            System.out.println(i + " staffId=" + tmpInvoiceInfo.getStaffId());
            System.out.println(i + " staffName=" + tmpInvoiceInfo.getStaffName());
            System.out.println(i + " createTime=" + tmpInvoiceInfo.getCreateTime());
            System.out.println(i + " poNo=" + tmpInvoiceInfo.getPoNo());
          //  System.out.println(i + " PO_NO_DATE=" + tmpInvoiceInfo.getPO_NO_DATE());
            System.out.println(i + " ccode=" + tmpInvoiceInfo.getCcode());
            System.out.println(i + " shortName=" + tmpInvoiceInfo.getShortName());
            System.out.println(i + " partNo=" + tmpInvoiceInfo.getPartNo());
            System.out.println(i + " cpartNo=" + tmpInvoiceInfo.getCpartNo());
            System.out.println(i + " pnDesc=" + tmpInvoiceInfo.getPnDesc());
            System.out.println(i + " amount=" + tmpInvoiceInfo.getAmount());
            System.out.println(i + " allMoney=" + tmpInvoiceInfo.getAllMoney());
            System.out.println(i + " curMoney=" + tmpInvoiceInfo.getCurMoney());
            System.out.println(i + " leftMoney=" + tmpInvoiceInfo.getLeftMoney());
            System.out.println(i + " invoiceDesc=" + tmpInvoiceInfo.getInvoiceDesc());
        }

    }
    
    /**
     * test list.
     */
    public void testListInvoiceInfoCount() {
        InvoiceInfoDao invoiceInfoDao = (InvoiceInfoDao) this.getApplicationContext().getBean("invoiceInfoDao");
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setInvoiceSeqId(2);
        invoiceInfo.setStaffId("1");
        invoiceInfo.setStaffName("1");
        invoiceInfo.setCreateTime(null);
        invoiceInfo.setPoNo("1");
      //  invoiceInfo.setPO_NO_DATE("1");
        invoiceInfo.setCcode(null);
        invoiceInfo.setShortName(null);
        invoiceInfo.setPartNo("1");
        invoiceInfo.setCpartNo("1");
        invoiceInfo.setPnDesc("1");
        invoiceInfo.setAmount(2);
        //invoiceInfo.setAllMoney("1");
        //invoiceInfo.setCurMoney("1");
        //invoiceInfo.setLeftMoney("1");
        invoiceInfo.setInvoiceDesc("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  invoiceInfoDao.listInvoiceInfoCount(invoiceInfo);
		System.out.println("count=" + count);
    }
}
