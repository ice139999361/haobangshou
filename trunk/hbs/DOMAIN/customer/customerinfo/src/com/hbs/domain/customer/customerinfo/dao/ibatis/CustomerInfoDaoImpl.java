package com.hbs.domain.customer.customerinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;
import com.hbs.domain.customer.customerinfo.dao.CustomerInfoDao;

/**
 * CustomerInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class CustomerInfoDaoImpl extends SqlMapClientDaoSupport implements CustomerInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param customerInfo customerInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public int insertCustomerInfo(CustomerInfo customerInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertCustomerInfo(CustomerInfo), �������[" + customerInfo + "]");
    	}
        
       
        
    	int id = (Integer)getSqlMapClientTemplate().insert("CustomerInfo_insertCustomerInfo", customerInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertCustomerInfo(CustomerInfo), ����");
		}
    	return id;
    }

    /**
     * delete.
     * @param customerInfo customerInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustomerInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustomerInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustomerInfo_deleteCustomerInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustomerInfo(String)");
		}
    }
    
    public void deleteCustomerInfoByBase(CustomerInfo customerInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustomerInfo(String), �������[" + customerInfo + "]");
		}
        getSqlMapClientTemplate().update("CustomerInfo_deleteCustomerInfoByBase", customerInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustomerInfo(String)");
		}
    }
    
    /**
     * update.
     * @param customerInfo customerInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateCustomerInfo(CustomerInfo customerInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustomerInfo(CustomerInfo), �������[" + customerInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustomerInfo_updateCustomerInfo", customerInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustomerInfo(CustomerInfo)");
		}
    }
    
    public void updateCustomerInfoByState(CustomerInfo customerInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustomerInfo(CustomerInfo), �������[" + customerInfo + "]");
		}
    	getSqlMapClientTemplate().update("CustomerInfo_updateCustomerInfoByState", customerInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustomerInfo(CustomerInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return customerInfo
     * @throws DataAccessException DataAccessException
     */
    public CustomerInfo findCustomerInfoByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustomerInfo(CustomerInfo), �������[" + pk + "]");
		}
        CustomerInfo customerInfo = (CustomerInfo) getSqlMapClientTemplate().queryForObject("CustomerInfo_findCustomerInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustomerInfo(CustomerInfo), ����[" + customerInfo + "]");
		}
        return customerInfo;
    }
    
    /**
     * find.
     * @param id id
     * @return customerInfo
     * @throws DataAccessException DataAccessException
     */
    public CustomerInfo findCustomerInfoByBase(CustomerInfo cInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustomerInfo(CustomerInfo), �������[" + cInfo + "]");
		}
        CustomerInfo customerInfo = (CustomerInfo) getSqlMapClientTemplate().queryForObject("CustomerInfo_findCustomerInfoByBase", cInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustomerInfo(CustomerInfo), ����[" + customerInfo + "]");
		}
        return customerInfo;
    }
    
    /**
     * list.
     * @param customerInfo customerInfo
     * @return customerInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<CustomerInfo> listCustomerInfo(CustomerInfo customerInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustomerInfo(CustomerInfo), �������[" + customerInfo + "]");
		}
        List<CustomerInfo> list = getSqlMapClientTemplate().queryForList("CustomerInfo_listCustomerInfo", customerInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustomerInfo(CustomerInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param customerInfo customerInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listCustomerInfoCount(CustomerInfo customerInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustomerInfoCount(CustomerInfo), �������[" + customerInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustomerInfo_listCustomerInfoCount", customerInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustomerInfoCount(CustomerInfo), ����[" + count + "]");
		}
        return count;
    }  
}
