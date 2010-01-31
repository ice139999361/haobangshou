package com.hbs.domain.vendor.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;
import com.hbs.domain.vendor.order.dao.VendorOrderDetailDao;

/**
 * VendorOrderDetailDao�ӿ�ʵ����.
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
    		logger.debug("����insertVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
    	}
        
        
    	int seqid = (Integer)getSqlMapClientTemplate().insert("VendorOrderDetail_insertVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertVendorOrderDetail(VendorOrderDetail), ����");
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
    		logger.debug("����deleteVendorOrderDetail(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorOrderDetail_deleteVendorOrderDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteVendorOrderDetail(String)");
		}
    }
    
    /**
     * update.
     * @param vendorOrderDetail vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    public void updateVendorOrderDetailByState(VendorOrderDetail vendorOrderDetail) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailByState", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    
    public void updateVendorOrderDetailByActiveState(VendorOrderDetail vendorOrderDetail) throws DataAccessException{
    	if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailByActiveState", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorOrderDetail(VendorOrderDetail)");
		}
    }
    
	public void updateVendorOrderDetailAmount(VendorOrderDetail vendorOrderDetail) throws DataAccessException{
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("VendorOrderDetail_updateVendorOrderDetailAmount", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorOrderDetail(VendorOrderDetail)");
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
        	logger.debug("����findVendorOrderDetail(VendorOrderDetail), �������[" + pk + "]");
		}
        VendorOrderDetail vendorOrderDetail = (VendorOrderDetail) getSqlMapClientTemplate().queryForObject("VendorOrderDetail_findVendorOrderDetailById", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorOrderDetail(VendorOrderDetail), ����[" + vendorOrderDetail + "]");
		}
        return vendorOrderDetail;
    }
    
    public VendorOrderDetail findVendorOrderDetailByBizKey(VendorOrderDetail vDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorOrderDetail(VendorOrderDetail), �������[" + vDetail + "]");
		}
        VendorOrderDetail vendorOrderDetail = (VendorOrderDetail) getSqlMapClientTemplate().queryForObject("VendorOrderDetail_findVendorOrderDetailByBizKey", vDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorOrderDetail(VendorOrderDetail), ����[" + vendorOrderDetail + "]");
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
        	logger.debug("����listVendorOrderDetail(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
        List<VendorOrderDetail> list = getSqlMapClientTemplate().queryForList("VendorOrderDetail_listVendorOrderDetail", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorOrderDetail(VendorOrderDetail), ����[" + list + "]");
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
        	logger.debug("����listVendorOrderDetailCount(VendorOrderDetail), �������[" + vendorOrderDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorOrderDetail_listVendorOrderDetailCount", vendorOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorOrderDetailCount(VendorOrderDetail), ����[" + count + "]");
		}
        return count;
    }  
}
