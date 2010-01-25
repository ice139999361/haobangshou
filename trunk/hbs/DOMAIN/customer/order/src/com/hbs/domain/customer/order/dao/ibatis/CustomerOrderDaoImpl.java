package com.hbs.domain.customer.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.customer.order.dao.CustomerOrderDao;

/**
 * CustomerOrderDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class CustomerOrderDaoImpl extends SqlMapClientDaoSupport implements CustomerOrderDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustomerOrderDaoImpl.class);

    
    
    /**
     * insert.
     * @param customerOrder customerOrder
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertCustomerOrder(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertCustomerOrder(CustomerOrder), �������[" + customerOrder + "]");
    	}
        
        
    	getSqlMapClientTemplate().insert("CustomerOrder_insertCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertCustomerOrder(CustomerOrder), ����");
		}
    	
    }

    /**
     * delete.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustomerOrder(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustomerOrder(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustomerOrder_deleteCustomerOrder", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustomerOrder(String)");
		}
    }
    
    /**
     * update.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    public void updateCustomerOrder(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustomerOrder(CustomerOrder), �������[" + customerOrder + "]");
		}
    	getSqlMapClientTemplate().update("CustomerOrder_updateCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustomerOrder(CustomerOrder)");
		}
    }
    
    public void updateCustomerOrderByActiveState(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustomerOrder(CustomerOrder), �������[" + customerOrder + "]");
		}
    	getSqlMapClientTemplate().update("CustomerOrder_updateCustomerOrderByActiveState", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustomerOrder(CustomerOrder)");
		}
    }
    
    public void updateCustomerOrderByState(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustomerOrder(CustomerOrder), �������[" + customerOrder + "]");
		}
    	getSqlMapClientTemplate().update("CustomerOrder_updateCustomerOrderByState", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustomerOrder(CustomerOrder)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return customerOrder
     * @throws DataAccessException DataAccessException
     */
    public CustomerOrder findCustomerOrder(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustomerOrder(CustomerOrder), �������[" + pk + "]");
		}
        CustomerOrder customerOrder = (CustomerOrder) getSqlMapClientTemplate().queryForObject("CustomerOrder_findCustomerOrder", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustomerOrder(CustomerOrder), ����[" + customerOrder + "]");
		}
        return customerOrder;
    }
    
    /**
     * list.
     * @param customerOrder customerOrder
     * @return customerOrder list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<CustomerOrder> listCustomerOrder(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustomerOrder(CustomerOrder), �������[" + customerOrder + "]");
		}
        List<CustomerOrder> list = getSqlMapClientTemplate().queryForList("CustomerOrder_listCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustomerOrder(CustomerOrder), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param customerOrder customerOrder
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listCustomerOrderCount(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustomerOrderCount(CustomerOrder), �������[" + customerOrder + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustomerOrder_listCustomerOrderCount", customerOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustomerOrderCount(CustomerOrder), ����[" + count + "]");
		}
        return count;
    }  
}
