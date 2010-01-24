package com.hbs.domain.product.test.dao;

import java.util.List;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.hbs.domain.product.dao.ProductCatagoryDao;
import com.hbs.domain.product.pojo.ProductCatagory;

/**
 * test ProductCatagoryDao.
 * @author hbs
 *
 */
public class ProductCatagoryDaoTest extends AbstractTransactionalDataSourceSpringContextTests {

    @Override 
    public String[] getConfigLocations() { 
        // spring+junit�Զ����ز��������ļ�(applicationContext-test-ProductCatagory.xml)
        return new String[] { "classpath:/applicationContext-test-productCatagory.xml", "classpath:/applicationContext-test-productCatagory.xml" }; 
    } 
    
    /**
     * test insert.
     */
    public void testInsertProductCatagory() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao)this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = new ProductCatagory();
        productCatagory.setCatCode("1");
        productCatagory.setCatName("1");
        productCatagory.setCatDesc("1");
         productCatagoryDao.insertProductCatagory(productCatagory);
        System.out.println("testInsert result--------------------------------------");
        
        
        // ���ִ��setComplete()������ɾ���ĵȲ������ύ������ǿ�ƻع�
        setComplete(); 
    }

    /**
     * test delete.
     */
    public void testDeleteProductCatagory() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao) this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = new ProductCatagory();
        productCatagoryDao.deleteProductCatagory("1");
        
        // ���ִ��setComplete()������ɾ���ĵȲ������ύ������ǿ�ƻع�
        setComplete(); 
    }

    /**
     * test update.
     */
    public void testUpdateProductCatagory() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao) this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = new ProductCatagory();
        productCatagory.setCatCode("1");
        productCatagory.setCatName("1");
        productCatagory.setCatDesc("1");
        productCatagoryDao.updateProductCatagory(productCatagory);
        
        // ���ִ��setComplete()������ɾ���ĵȲ������ύ������ǿ�ƻع�
        setComplete(); 
    }

    /**
     * test find.
     */
    public void testFindProductCatagory() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao) this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = productCatagoryDao.findProductCatagory("1");
        
        System.out.println("testFind result--------------------------------------");
        System.out.println("catCode=" + productCatagory.getCatCode());
        System.out.println("catName=" + productCatagory.getCatName());
        System.out.println("catDesc=" + productCatagory.getCatDesc());
    }

    /**
     * test list.
     */
    public void testListProductCatagory() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao) this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = new ProductCatagory();
        productCatagory.setCatCode("1");
        productCatagory.setCatName("1");
        productCatagory.setCatDesc("1");

        System.out.println("testList result--------------------------------------");
        List list =  productCatagoryDao.listProductCatagory(productCatagory);
        for (int i = 0; i < list.size(); i++) {
            ProductCatagory tmpProductCatagory = (ProductCatagory) list.get(i);
            System.out.println(i + " catCode=" + tmpProductCatagory.getCatCode());
            System.out.println(i + " catName=" + tmpProductCatagory.getCatName());
            System.out.println(i + " catDesc=" + tmpProductCatagory.getCatDesc());
        }

    }
    
    /**
     * test list.
     */
    public void testListProductCatagoryCount() {
        ProductCatagoryDao productCatagoryDao = (ProductCatagoryDao) this.getApplicationContext().getBean("productCatagoryDao");
        ProductCatagory productCatagory = new ProductCatagory();
        productCatagory.setCatCode("1");
        productCatagory.setCatName("1");
        productCatagory.setCatDesc("1");

        System.out.println("testListCount result--------------------------------------");
        Integer count =  productCatagoryDao.listProductCatagoryCount(productCatagory);
		System.out.println("count=" + count);
    }
}
