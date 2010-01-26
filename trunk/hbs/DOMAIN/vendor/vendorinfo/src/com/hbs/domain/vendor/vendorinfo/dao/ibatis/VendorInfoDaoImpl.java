package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;

/**
 * VendorInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class VendorInfoDaoImpl extends SqlMapClientDaoSupport implements VendorInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param vendorInfo vendorInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public int insertVendorInfo(VendorInfo vendorInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertVendorInfo(VendorInfo), �������[" + vendorInfo + "]");
    	}
		int id = (Integer)getSqlMapClientTemplate().insert("VendorInfo_insertVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertVendorInfo(VendorInfo), ����");
		}
    	return id;
    }

    /**
     * delete.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteVendorInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorInfo_deleteVendorInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteVendorInfo(String)");
		}
    }
    
    /**
     * update.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorInfo(VendorInfo vendorInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorInfo(VendorInfo), �������[" + vendorInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorInfo_updateVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateVendorInfo(VendorInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return vendorInfo
     * @throws DataAccessException DataAccessException
     */
    public VendorInfo findVendorInfoByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorInfo(VendorInfo), �������[" + pk + "]");
		}
        VendorInfo vendorInfo = (VendorInfo) getSqlMapClientTemplate().queryForObject("VendorInfo_findVendorInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorInfo(VendorInfo), ����[" + vendorInfo + "]");
		}
        return vendorInfo;
    }
    
    public VendorInfo findVendorInfoByBase(VendorInfo vInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorInfo(VendorInfo), �������[" + vInfo + "]");
		}
        VendorInfo vendorInfo = (VendorInfo) getSqlMapClientTemplate().queryForObject("VendorInfo_findVendorInfoByBase", vInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorInfo(VendorInfo), ����[" + vendorInfo + "]");
		}
        return vendorInfo;
    }
    
    /**
     * list.
     * @param vendorInfo vendorInfo
     * @return vendorInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<VendorInfo> listVendorInfo(VendorInfo vendorInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorInfo(VendorInfo), �������[" + vendorInfo + "]");
		}
        List<VendorInfo> list = getSqlMapClientTemplate().queryForList("VendorInfo_listVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorInfo(VendorInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param vendorInfo vendorInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listVendorInfoCount(VendorInfo vendorInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorInfoCount(VendorInfo), �������[" + vendorInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorInfo_listVendorInfoCount", vendorInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorInfoCount(VendorInfo), ����[" + count + "]");
		}
        return count;
    }  
}
