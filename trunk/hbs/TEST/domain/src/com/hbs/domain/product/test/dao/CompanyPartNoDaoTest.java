package com.hbs.domain.product.test.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.product.dao.CompanyPartNoDao;
import com.hbs.domain.product.pojo.CompanyPartNo;

/**
 * test CompanyPartNoDao.
 * @author hbs
 *
 */
public class CompanyPartNoDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-CompanyPartNo.xml)
        return new String[] { "classpath:/applicationContext-test-companyPartNo.xml", "classpath:/applicationContext-test-companyPartNo.xml" }; 
    } 
    
    /**
     * test insert.
     */
//    public void testInsertCompanyPartNo() {
//        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao)this.getApplicationContext().getBean("companyPartNoDao");
//        CompanyPartNo companyPartNo = new CompanyPartNo();
//        companyPartNo.setPartNo("YANG004");
//        companyPartNo.setPnDesc("不好啊");
//        companyPartNo.setPrice(new BigDecimal(3.111));
//        companyPartNo.setTaxPrice(new BigDecimal(3.2));
//        companyPartNo.setCreateTime(new Date());
//        companyPartNo.setClsCode("1");
//        companyPartNo.setCatCode("1");
//        companyPartNoDao.insertCompanyPartNo(companyPartNo);
//        System.out.println("testInsert result--------------------------------------");
//       
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test delete.
     */
//    public void testDeleteCompanyPartNo() {
//        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao) this.getApplicationContext().getBean("companyPartNoDao");
//        CompanyPartNo companyPartNo = new CompanyPartNo();
//        companyPartNoDao.deleteCompanyPartNo("1");
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test update.
     */
//    public void testUpdateCompanyPartNo() {
//        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao) this.getApplicationContext().getBean("companyPartNoDao");
//        CompanyPartNo companyPartNo = new CompanyPartNo();
//        companyPartNo.setPartNo("1");
//        companyPartNo.setPnDesc("1");
////        companyPartNo.setPrice("1");
////        companyPartNo.setTaxPrice("1");
//        companyPartNo.setCreateTime(new Date());
//        companyPartNo.setClsCode("1");
//        companyPartNo.setCatCode("1");
//        companyPartNoDao.updateCompanyPartNo(companyPartNo);
//        
//        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
//        setComplete(); 
//    }

    /**
     * test find.
     */
//    public void testFindCompanyPartNo() {
//        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao) this.getApplicationContext().getBean("companyPartNoDao");
//        CompanyPartNo companyPartNo = companyPartNoDao.findCompanyPartNo("1");
//        
//        System.out.println("testFind result--------------------------------------");
//        System.out.println("partNo=" + companyPartNo.getPartNo());
//        System.out.println("pnDesc=" + companyPartNo.getPnDesc());
//        System.out.println("price=" + companyPartNo.getPrice());
//        System.out.println("taxPrice=" + companyPartNo.getTaxPrice());
//        System.out.println("createTime=" + companyPartNo.getCreateTime());
//        System.out.println("clsCode=" + companyPartNo.getClsCode());
//        System.out.println("catCode=" + companyPartNo.getCatCode());
//    }

    /**
     * test list.
     */
    public void testListCompanyPartNo() {
        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao) this.getApplicationContext().getBean("companyPartNoDao");
        CompanyPartNo companyPartNo = new CompanyPartNo();
//        companyPartNo.setPartNo("1");
        companyPartNo.setPnDesc("不");
////        companyPartNo.setPrice("1");
////        companyPartNo.setTaxPrice("1");
//        companyPartNo.setCreateTime(null);
//        companyPartNo.setClsCode("1");
//        companyPartNo.setCatCode("1");
        companyPartNo.setEnd(5);
        companyPartNo.setSort("part_No desc , pn_desc desc");
        companyPartNo.setOrder("desc");
        System.out.println("testList result--------------------------------------");
        List list =  companyPartNoDao.listCompanyPartNo(companyPartNo);
        for (int i = 0; i < list.size(); i++) {
            CompanyPartNo tmpCompanyPartNo = (CompanyPartNo) list.get(i);
            System.out.println(i + " partNo=" + tmpCompanyPartNo.getPartNo());
            System.out.println(i + " pnDesc=" + tmpCompanyPartNo.getPnDesc());
            System.out.println(i + " price=" + tmpCompanyPartNo.getPrice());
            System.out.println(i + " taxPrice=" + tmpCompanyPartNo.getTaxPrice());
            System.out.println(i + " createTime=" + tmpCompanyPartNo.getCreateTime());
            System.out.println(i + " clsCode=" + tmpCompanyPartNo.getClsCode());
            System.out.println(i + " catCode=" + tmpCompanyPartNo.getCatCode());
        }

    }
    
    /**
     * test list.
     */
    public void testListCompanyPartNoCount() {
        CompanyPartNoDao companyPartNoDao = (CompanyPartNoDao) this.getApplicationContext().getBean("companyPartNoDao");
        CompanyPartNo companyPartNo = new CompanyPartNo();
//      companyPartNo.setPartNo("1");
      companyPartNo.setPnDesc("不");
////      companyPartNo.setPrice("1");
////      companyPartNo.setTaxPrice("1");
//      companyPartNo.setCreateTime(null);
//      companyPartNo.setClsCode("1");
//      companyPartNo.setCatCode("1");
      companyPartNo.setEnd(5);
      companyPartNo.setSort("part_No desc , pn_desc desc");
      companyPartNo.setOrder("desc");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  companyPartNoDao.listCompanyPartNoCount(companyPartNo);
		System.out.println("count=" + count);
    }
}
