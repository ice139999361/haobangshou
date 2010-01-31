package com.hbs.domain.vendor.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;

/**
 * VendorOrderDetailDao接口实现类.
 * @author hbs
 *
 */
public class VendorOrderDetailDaoImpl extends SqlMapClientDaoSupport implements VendorOrderDetailDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorOrderDetailDaoImpl.class);

    
    
    /**
     * insert.
     * @param vendorOrderDetail vendorOrderDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public int  insertVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
    	}
        
        
    	int seqid = (Integer)getSqlMapClientTemplate().insert("VendorOrderDetail_insertVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertVendorOrderDetail(VendorOrderDetail), 返回");
		}
    	return seqid;
    }

    /**
     * delete.
     * @param vendorOrderDetail vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorOrderDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteVendorOrderDetail(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorOrderDetail_deleteVendorOrderDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteVendorOrderDetail(String)");
		}
    }
    
    /**
     * update.
     * @param vendorOrderDetail vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    public void updateVendorOrderDetailByState(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailByState", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    
    public void updateVendorOrderDetailByActiveState(VendorOrderDetail vendorOrderDetail) throws DataAccessException{
    	if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailByActiveState", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    
	public void updateVendorOrderDetailAmount(VendorOrderDetail vendorOrderDetail) throws DataAccessException{
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailAmount", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorOrderDetail(VendorOrderDetail)");
		}	
	}
    /**
     * find.
     * @param id id
     * @return vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public VendorOrderDetail findVendorOrderDetailById(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findVendorOrderDetail(VendorOrderDetail), 输入参数[" + pk + "]");
		}
        VendorOrderDetail vendorOrderDetail = (VendorOrderDetail) getSqlMapClientTemplate().queryForObject("VendorOrderDetail_findVendorOrderDetailById", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorOrderDetail(VendorOrderDetail), 返回[" + vendorOrderDetail + "]");
		}
        return vendorOrderDetail;
    }
    
    public VendorOrderDetail findVendorOrderDetailByBizKey(VendorOrderDetail vDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findVendorOrderDetail(VendorOrderDetail), 输入参数[" + vDetail + "]");
		}
        VendorOrderDetail vendorOrderDetail = (VendorOrderDetail) getSqlMapClientTemplate().queryForObject("VendorOrderDetail_findVendorOrderDetailByBizKey", vDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorOrderDetail(VendorOrderDetail), 返回[" + vendorOrderDetail + "]");
		}
        return vendorOrderDetail;
    }
    
    /**
     * list.
     * @param vendorOrderDetail vendorOrderDetail
     * @return vendorOrderDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<VendorOrderDetail> listVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listVendorOrderDetail(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
        List<VendorOrderDetail> list = getSqlMapClientTemplate().queryForList("VendorOrderDetail_listVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorOrderDetail(VendorOrderDetail), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param vendorOrderDetail vendorOrderDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listVendorOrderDetailCount(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listVendorOrderDetailCount(VendorOrderDetail), 输入参数[" + vendorOrderDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorOrderDetail_listVendorOrderDetailCount", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorOrderDetailCount(VendorOrderDetail), 返回[" + count + "]");
		}
        return count;
    }  
}
