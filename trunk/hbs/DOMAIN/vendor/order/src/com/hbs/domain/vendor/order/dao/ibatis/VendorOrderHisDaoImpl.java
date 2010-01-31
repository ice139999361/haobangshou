package com.hbs.domain.vendor.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.dao.VendorOrderDao;

/**
 * VendorOrderDao接口实现类.
 * @author hbs
 *
 */
public class VendorOrderHisDaoImpl extends SqlMapClientDaoSupport implements VendorOrderDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorOrderDaoImpl.class);

    
    
    /**
     * insert.
     * @param vendorOrder vendorOrder
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertVendorOrder(VendorOrder vendorOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertVendorOrder(VendorOrder), 输入参数[" + vendorOrder + "]");
    	}
        
       
    	getSqlMapClientTemplate().insert("VendorOrder_insertVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertVendorOrder(VendorOrder), 返回");
		}
    	
    }

    /**
     * delete.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorOrder(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteVendorOrder(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorOrder_deleteVendorOrder", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteVendorOrder(String)");
		}
    }
    
    /**
     * update.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorOrder(VendorOrder vendorOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorOrder(VendorOrder), 输入参数[" + vendorOrder + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrder_updateVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorOrder(VendorOrder)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return vendorOrder
     * @throws DataAccessException DataAccessException
     */
    public VendorOrder findVendorOrder(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findVendorOrder(VendorOrder), 输入参数[" + pk + "]");
		}
        VendorOrder vendorOrder = (VendorOrder) getSqlMapClientTemplate().queryForObject("VendorOrder_findVendorOrder", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorOrder(VendorOrder), 返回[" + vendorOrder + "]");
		}
        return vendorOrder;
    }
    
    /**
     * list.
     * @param vendorOrder vendorOrder
     * @return vendorOrder list
     * @throws DataAccessException DataAccessException
     */
    public List listVendorOrder(VendorOrder vendorOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listVendorOrder(VendorOrder), 输入参数[" + vendorOrder + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("VendorOrder_listVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorOrder(VendorOrder), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param vendorOrder vendorOrder
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listVendorOrderCount(VendorOrder vendorOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listVendorOrderCount(VendorOrder), 输入参数[" + vendorOrder + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorOrder_listVendorOrderCount", vendorOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorOrderCount(VendorOrder), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.vendor.order.dao.VendorOrderDao#updateVendorOrderByState(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public void updateVendorOrderByState(VendorOrder vendorOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.vendor.order.dao.VendorOrderDao#updateVendorOrderByActiveState(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public void updateVendorOrderByActiveState(VendorOrder vendorOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.vendor.order.dao.VendorOrderDao#findVendorOrder(com.hbs.domain.vendor.order.pojo.VendorOrder)
	 */
	public VendorOrder findVendorOrder(VendorOrder vendorOrder)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}  
}
