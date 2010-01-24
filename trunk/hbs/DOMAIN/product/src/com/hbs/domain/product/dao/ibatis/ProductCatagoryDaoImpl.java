package com.hbs.domain.product.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.product.pojo.ProductCatagory;
import com.hbs.domain.product.dao.ProductCatagoryDao;

/**
 * ProductCatagoryDao接口实现类.
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
    		logger.debug("进入insertProductCatagory(ProductCatagory), 输入参数[" + productCatagory + "]");
    	}
        
       
        
    	getSqlMapClientTemplate().insert("ProductCatagory_insertProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertProductCatagory(ProductCatagory), 返回");
		}
    	
    }

    /**
     * delete.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    public void deleteProductCatagory(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteProductCatagory(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("ProductCatagory_deleteProductCatagory", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteProductCatagory(String)");
		}
    }
    
    /**
     * update.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    public void updateProductCatagory(ProductCatagory productCatagory) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateProductCatagory(ProductCatagory), 输入参数[" + productCatagory + "]");
		}
    	getSqlMapClientTemplate().update("ProductCatagory_updateProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateProductCatagory(ProductCatagory)");
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
        	logger.debug("进入findProductCatagory(ProductCatagory), 输入参数[" + pk + "]");
		}
        ProductCatagory productCatagory = (ProductCatagory) getSqlMapClientTemplate().queryForObject("ProductCatagory_findProductCatagory", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findProductCatagory(ProductCatagory), 返回[" + productCatagory + "]");
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
        	logger.debug("进入listProductCatagory(ProductCatagory), 输入参数[" + productCatagory + "]");
		}
        List<ProductCatagory> list = getSqlMapClientTemplate().queryForList("ProductCatagory_listProductCatagory", productCatagory);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listProductCatagory(ProductCatagory), 返回[" + list + "]");
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
        	logger.debug("进入listProductCatagoryCount(ProductCatagory), 输入参数[" + productCatagory + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ProductCatagory_listProductCatagoryCount", productCatagory);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listProductCatagoryCount(ProductCatagory), 返回[" + count + "]");
		}
        return count;
    }  
}
