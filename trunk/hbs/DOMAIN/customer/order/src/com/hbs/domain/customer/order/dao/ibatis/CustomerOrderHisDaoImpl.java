package com.hbs.domain.customer.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.order.pojo.CustomerOrder;
import com.hbs.domain.customer.order.dao.CustomerOrderDao;

/**
 * CustomerOrderDao接口实现类.
 * @author hbs
 *
 */
public class CustomerOrderHisDaoImpl extends SqlMapClientDaoSupport implements CustomerOrderDao
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
    		logger.debug("进入insertCustomerOrder(CustomerOrder), 输入参数[" + customerOrder + "]");
    	}
        
        
    	getSqlMapClientTemplate().insert("CustomerOrderHis_insertCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertCustomerOrder(CustomerOrder), 返回");
		}
    	
    }

    /**
     * delete.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustomerOrder(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteCustomerOrder(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustomerOrderHis_deleteCustomerOrder", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCustomerOrder(String)");
		}
    }
    
    /**
     * update.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    public void updateCustomerOrder(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateCustomerOrder(CustomerOrder), 输入参数[" + customerOrder + "]");
		}
    	getSqlMapClientTemplate().update("CustomerOrderHis_updateCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateCustomerOrder(CustomerOrder)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return customerOrder
     * @throws DataAccessException DataAccessException
     */
    public CustomerOrder findCustomerOrder(CustomerOrder order) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findCustomerOrder(CustomerOrder), 输入参数[" + order + "]");
		}
        CustomerOrder customerOrder = (CustomerOrder) getSqlMapClientTemplate().queryForObject("CustomerOrderHis_findCustomerOrder", order);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findCustomerOrder(CustomerOrder), 返回[" + customerOrder + "]");
		}
        return customerOrder;
    }
    
    /**
     * list.
     * @param customerOrder customerOrder
     * @return customerOrder list
     * @throws DataAccessException DataAccessException
     */
    public List listCustomerOrder(CustomerOrder customerOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listCustomerOrder(CustomerOrder), 输入参数[" + customerOrder + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("CustomerOrderHis_listCustomerOrder", customerOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustomerOrder(CustomerOrder), 返回[" + list + "]");
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
        	logger.debug("进入listCustomerOrderCount(CustomerOrder), 输入参数[" + customerOrder + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustomerOrderHis_listCustomerOrderCount", customerOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustomerOrderCount(CustomerOrder), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustomerOrderDao#updateCustomerOrderByActiveState(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public void updateCustomerOrderByActiveState(CustomerOrder customerOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustomerOrderDao#updateCustomerOrderByState(com.hbs.domain.customer.order.pojo.CustomerOrder)
	 */
	public void updateCustomerOrderByState(CustomerOrder customerOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
