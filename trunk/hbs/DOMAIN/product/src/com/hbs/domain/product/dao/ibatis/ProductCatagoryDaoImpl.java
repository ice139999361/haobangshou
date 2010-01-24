package com.hbs.domain.product.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.product.pojo.ProductCatagory;
import com.hbs.domain.product.dao.ProductCatagoryDao;

/**
 * ProductCatagoryDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class ProductCatagoryDaoImpl extends SqlMapClientDaoSupport implements ProductCatagoryDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ProductCatagoryDaoImpl.class);

    
    
    /**
     * insert.
     * @param productCatagory productCatagory
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertProductCatagory(ProductCatagory productCatagory) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertProductCatagory(ProductCatagory), �������[" + productCatagory + "]");
    	}
        
       
        
    	getSqlMapClientTemplate().insert("ProductCatagory_insertProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertProductCatagory(ProductCatagory), ����");
		}
    	
    }

    /**
     * delete.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    public void deleteProductCatagory(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteProductCatagory(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("ProductCatagory_deleteProductCatagory", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteProductCatagory(String)");
		}
    }
    
    /**
     * update.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    public void updateProductCatagory(ProductCatagory productCatagory) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateProductCatagory(ProductCatagory), �������[" + productCatagory + "]");
		}
    	getSqlMapClientTemplate().update("ProductCatagory_updateProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateProductCatagory(ProductCatagory)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return productCatagory
     * @throws DataAccessException DataAccessException
     */
    public ProductCatagory findProductCatagory(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findProductCatagory(ProductCatagory), �������[" + pk + "]");
		}
        ProductCatagory productCatagory = (ProductCatagory) getSqlMapClientTemplate().queryForObject("ProductCatagory_findProductCatagory", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findProductCatagory(ProductCatagory), ����[" + productCatagory + "]");
		}
        return productCatagory;
    }
    
    /**
     * list.
     * @param productCatagory productCatagory
     * @return productCatagory list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<ProductCatagory> listProductCatagory(ProductCatagory productCatagory) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listProductCatagory(ProductCatagory), �������[" + productCatagory + "]");
		}
        List<ProductCatagory> list = getSqlMapClientTemplate().queryForList("ProductCatagory_listProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listProductCatagory(ProductCatagory), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param productCatagory productCatagory
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listProductCatagoryCount(ProductCatagory productCatagory) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listProductCatagoryCount(ProductCatagory), �������[" + productCatagory + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ProductCatagory_listProductCatagoryCount", productCatagory);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listProductCatagoryCount(ProductCatagory), ����[" + count + "]");
		}
        return count;
    }  
}
