package com.hbs.domain.product.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.product.pojo.ProductClass;
import com.hbs.domain.product.dao.ProductClassDao;

/**
 * ProductClassDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class ProductClassDaoImpl extends SqlMapClientDaoSupport implements ProductClassDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ProductClassDaoImpl.class);

    
    
    /**
     * insert.
     * @param productClass productClass
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertProductClass(ProductClass productClass) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertProductClass(ProductClass), �������[" + productClass + "]");
    	}
        
       
    	Integer iRet =(Integer)getSqlMapClientTemplate().insert("ProductClass_insertProductClass", productClass);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertProductClass(ProductClass), ����");
		}
    	return iRet;
    }

    /**
     * delete.
     * @param productClass productClass
     * @throws DataAccessException DataAccessException
     */
    public void deleteProductClass(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteProductClass(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("ProductClass_deleteProductClass", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteProductClass(String)");
		}
    }
    
    /**
     * update.
     * @param productClass productClass
     * @throws DataAccessException DataAccessException
     */
    public void updateProductClass(ProductClass productClass) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateProductClass(ProductClass), �������[" + productClass + "]");
		}
    	getSqlMapClientTemplate().update("ProductClass_updateProductClass", productClass);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateProductClass(ProductClass)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return productClass
     * @throws DataAccessException DataAccessException
     */
    public ProductClass findProductClass(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findProductClass(ProductClass), �������[" + pk + "]");
		}
        ProductClass productClass = (ProductClass) getSqlMapClientTemplate().queryForObject("ProductClass_findProductClass", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findProductClass(ProductClass), ����[" + productClass + "]");
		}
        return productClass;
    }
    
    /**
     * list.
     * @param productClass productClass
     * @return productClass list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<ProductClass> listProductClass(ProductClass productClass) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listProductClass(ProductClass), �������[" + productClass + "]");
		}
        List<ProductClass> list = getSqlMapClientTemplate().queryForList("ProductClass_listProductClass", productClass);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listProductClass(ProductClass), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param productClass productClass
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listProductClassCount(ProductClass productClass) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listProductClassCount(ProductClass), �������[" + productClass + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ProductClass_listProductClassCount", productClass);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listProductClassCount(ProductClass), ����[" + count + "]");
		}
        return count;
    }  
}
