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
    public int insertVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
    	}
        
      
    	int ret =(Integer)getSqlMapClientTemplate().insert("VendorPartNoInfo_insertVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertVendorPartNoInfo(VendorPartNoInfo), ����[]");
		}
    	return ret;
    }

    /**
     * delete.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteVendorPartNoInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteVendorPartNoInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorPartNoInfo_deleteVendorPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteVendorPartNoInfo(String)");
		}
    }
    
    public void deleteVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteVendorPartNoInfo(String), �������[" + vendorPartNoInfo + "]");
		}
        getSqlMapClientTemplate().update("VendorPartNoInfo_deleteVendorPartNoInfoByBizKey", vendorPartNoInfo);
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
    
    public void updateVendorPartNoInfoByState(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorPartNoInfo_updateVendorPartNoInfoByState", vendorPartNoInfo);
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
    public VendorPartNoInfo findVendorPartNoInfoByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorPartNoInfo(VendorPartNoInfo), �������[" + pk + "]");
		}
        VendorPartNoInfo vendorPartNoInfo = (VendorPartNoInfo) getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_findVendorPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorPartNoInfo(VendorPartNoInfo), ����[" + vendorPartNoInfo + "]");
		}
        return vendorPartNoInfo;
    }
    
    public VendorPartNoInfo findVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
        VendorPartNoInfo vPartNoInfo = (VendorPartNoInfo) getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_findVendorPartNoInfoByBizKey", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findVendorPartNoInfo(VendorPartNoInfo), ����[" + vPartNoInfo + "]");
		}
        return vPartNoInfo;
    }
    
    /**
     * list.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return vendorPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<VendorPartNoInfo> listVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorPartNoInfo(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
        List<VendorPartNoInfo> list = getSqlMapClientTemplate().queryForList("VendorPartNoInfo_listVendorPartNoInfo", vendorPartNoInfo);
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
    public Integer listVendorPartNoInfoCheckCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listVendorPartNoInfoCheckCount(VendorPartNoInfo), �������[" + vendorPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_listVendorPartNoInfoCheckCount", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listVendorPartNoInfoCheckCount(VendorPartNoInfo), ����[" + count + "]");
		}
        return count;
    } 
}
