package com.hbs.domain.vendor.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.order.pojo.VendorOrder;
import com.hbs.domain.vendor.order.dao.VendorOrderDao;

/**
 * VendorOrderDao�ӿ�ʵ����.
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
    		logger.debug("����insertVendorOrder(VendorOrder), �������[" + vendorOrder + "]");
    	}
        
       
    	getSqlMapClientTemplate().insert("VendorOrder_insertVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertVendorOrder(VendorOrder), ����");
		}
    	
    }

    /**
     * delete.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorOrder(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteVendorOrder(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorOrder_deleteVendorOrder", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteVendorOrder(String)");
		}
    }
    
    /**
     * update.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorOrder(VendorOrder vendorOrder) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorOrder(VendorOrder), �������[" + vendorOrder + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrder_updateVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorOrder(VendorOrder)");
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
        	logger.debug("����findVendorOrder(VendorOrder), �������[" + pk + "]");
		}
        VendorOrder vendorOrder = (VendorOrder) getSqlMapClientTemplate().queryForObject("VendorOrder_findVendorOrder", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorOrder(VendorOrder), ����[" + vendorOrder + "]");
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
        	logger.debug("����listVendorOrder(VendorOrder), �������[" + vendorOrder + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("VendorOrder_listVendorOrder", vendorOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorOrder(VendorOrder), ����[" + list + "]");
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
        	logger.debug("����listVendorOrderCount(VendorOrder), �������[" + vendorOrder + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorOrder_listVendorOrderCount", vendorOrder);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorOrderCount(VendorOrder), ����[" + count + "]");
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
