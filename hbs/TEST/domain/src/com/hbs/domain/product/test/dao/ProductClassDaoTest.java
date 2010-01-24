package com.hbs.domain.product.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.product.dao.ProductClassDao;
import com.hbs.domain.product.pojo.ProductClass;

/**
 * test ProductClassDao.
 * @author hbs
 *
 */
public class ProductClassDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit自动加载测试配置文件(applicationContext-test-ProductClass.xml)
        return new String[] { "classpath:/applicationContext-test-productClass.xml", "classpath:/applicationContext-test-productClass.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertProductClass() {
        ProductClassDao productClassDao = (ProductClassDao)this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = new ProductClass();
        productClass.setClsCode("1");
        productClass.setCatCode("1");
        productClass.setClsName("1");
        productClass.setClsDesc("1");
        productClassDao.insertProductClass(productClass);
        System.out.println("testInsert result--------------------------------------");
       
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteProductClass() {
        ProductClassDao productClassDao = (ProductClassDao) this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = new ProductClass();
        productClassDao.deleteProductClass("1");
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateProductClass() {
        ProductClassDao productClassDao = (ProductClassDao) this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = new ProductClass();
        productClass.setClsCode("1");
        productClass.setCatCode("1");
        productClass.setClsName("1");
        productClass.setClsDesc("1");
        productClassDao.updateProductClass(productClass);
        
        // 如果执行setComplete()，增、删、改等操作被提交，否则将强制回滚
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindProductClass() {
        ProductClassDao productClassDao = (ProductClassDao) this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = productClassDao.findProductClass("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("clsCode=" + productClass.getClsCode());
        System.out.println("catCode=" + productClass.getCatCode());
        System.out.println("clsName=" + productClass.getClsName());
        System.out.println("clsDesc=" + productClass.getClsDesc());
    }

    /**
     * test list.
     */
    public void testListProductClass() {
        ProductClassDao productClassDao = (ProductClassDao) this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = new ProductClass();
        productClass.setClsCode("1");
        productClass.setCatCode("1");
        productClass.setClsName("1");
        productClass.setClsDesc("1");

        System.out.println("testList result--------------------------------------");
        List list =  productClassDao.listProductClass(productClass);
        for (int i = 0; i < list.size(); i++) {
            ProductClass tmpProductClass = (ProductClass) list.get(i);
            System.out.println(i + " clsCode=" + tmpProductClass.getClsCode());
            System.out.println(i + " catCode=" + tmpProductClass.getCatCode());
            System.out.println(i + " clsName=" + tmpProductClass.getClsName());
            System.out.println(i + " clsDesc=" + tmpProductClass.getClsDesc());
        }

    }
    
    /**
     * test list.
     */
    public void testListProductClassCount() {
        ProductClassDao productClassDao = (ProductClassDao) this.getApplicationContext().getBean("productClassDao");
        ProductClass productClass = new ProductClass();
        productClass.setClsCode("1");
        productClass.setCatCode("1");
        productClass.setClsName("1");
        productClass.setClsDesc("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  productClassDao.listProductClassCount(productClass);
		System.out.println("count=" + count);
    }
}
