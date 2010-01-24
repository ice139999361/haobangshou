package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.vendor.vendorinfo.dao.VendorPartNoInfoDao;

/**
 * VendorPartNoInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class VendorPartNoInfoDaoImpl extends SqlMapClientDaoSupport implements VendorPartNoInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPartNoInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
    	}
        
      
    	getSqlMapClientTemplate().insert("VendorPartNoInfo_insertVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertVendorPartNoInfo(VendorPartNoInfo), ����[]");
		}
    	
    }

    /**
     * delete.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorPartNoInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteVendorPartNoInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorPartNoInfo_deleteVendorPartNoInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteVendorPartNoInfo(String)");
		}
    }
    
    /**
     * update.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorPartNoInfo_updateVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorPartNoInfo(VendorPartNoInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public VendorPartNoInfo findVendorPartNoInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorPartNoInfo(VendorPartNoInfo), �������[" + pk + "]");
		}
        VendorPartNoInfo vendorPartNoInfo = (VendorPartNoInfo) getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_findVendorPartNoInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorPartNoInfo(VendorPartNoInfo), ����[" + vendorPartNoInfo + "]");
		}
        return vendorPartNoInfo;
    }
    
    /**
     * list.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return vendorPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("VendorPartNoInfo_listVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorPartNoInfo(VendorPartNoInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listVendorPartNoInfoCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorPartNoInfoCount(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_listVendorPartNoInfoCount", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorPartNoInfoCount(VendorPartNoInfo), ����[" + count + "]");
		}
        return count;
    }  
}
