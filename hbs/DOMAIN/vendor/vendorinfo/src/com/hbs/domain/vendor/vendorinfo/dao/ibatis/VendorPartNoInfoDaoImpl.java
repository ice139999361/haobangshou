package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;
import com.hbs.domain.vendor.vendorinfo.dao.VendorPartNoInfoDao;

/**
 * VendorPartNoInfoDao接口实现类.
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
    		logger.debug("进入insertVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
    	}
        
      
    	int ret =(Integer)getSqlMapClientTemplate().insert("VendorPartNoInfo_insertVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertVendorPartNoInfo(VendorPartNoInfo), 返回[]");
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
    		logger.debug("进入deleteVendorPartNoInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorPartNoInfo_deleteVendorPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteVendorPartNoInfo(String)");
		}
    }
    
    public void deleteVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteVendorPartNoInfo(String), 输入参数[" + vendorPartNoInfo + "]");
		}
        getSqlMapClientTemplate().update("VendorPartNoInfo_deleteVendorPartNoInfoByBizKey", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteVendorPartNoInfo(String)");
		}
    }
    
    /**
     * update.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorPartNoInfo_updateVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorPartNoInfo(VendorPartNoInfo)");
		}
    }
    
    public void updateVendorPartNoInfoByState(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorPartNoInfo_updateVendorPartNoInfoByState", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorPartNoInfo(VendorPartNoInfo)");
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
        	logger.debug("进入findVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + pk + "]");
		}
        VendorPartNoInfo vendorPartNoInfo = (VendorPartNoInfo) getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_findVendorPartNoInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorPartNoInfo(VendorPartNoInfo), 返回[" + vendorPartNoInfo + "]");
		}
        return vendorPartNoInfo;
    }
    
    public VendorPartNoInfo findVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
        VendorPartNoInfo vPartNoInfo = (VendorPartNoInfo) getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_findVendorPartNoInfoByBizKey", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorPartNoInfo(VendorPartNoInfo), 返回[" + vPartNoInfo + "]");
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
        	logger.debug("进入listVendorPartNoInfo(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
        List<VendorPartNoInfo> list = getSqlMapClientTemplate().queryForList("VendorPartNoInfo_listVendorPartNoInfo", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorPartNoInfo(VendorPartNoInfo), 返回[" + list + "]");
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
        	logger.debug("进入listVendorPartNoInfoCount(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_listVendorPartNoInfoCount", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorPartNoInfoCount(VendorPartNoInfo), 返回[" + count + "]");
		}
        return count;
    } 
    public Integer listVendorPartNoInfoCheckCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listVendorPartNoInfoCheckCount(VendorPartNoInfo), 输入参数[" + vendorPartNoInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorPartNoInfo_listVendorPartNoInfoCheckCount", vendorPartNoInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorPartNoInfoCheckCount(VendorPartNoInfo), 返回[" + count + "]");
		}
        return count;
    } 
}
