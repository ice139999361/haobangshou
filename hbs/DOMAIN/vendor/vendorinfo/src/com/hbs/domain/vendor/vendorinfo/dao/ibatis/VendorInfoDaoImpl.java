package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;
import com.hbs.domain.vendor.vendorinfo.dao.VendorInfoDao;

/**
 * VendorInfoDao接口实现类.
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
    		logger.debug("进入insertVendorInfo(VendorInfo), 输入参数[" + vendorInfo + "]");
    	}
		int id = (Integer)getSqlMapClientTemplate().insert("VendorInfo_insertVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertVendorInfo(VendorInfo), 返回");
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
    		logger.debug("进入deleteVendorInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("VendorInfo_deleteVendorInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteVendorInfo(String)");
		}
    }
    
    /**
     * update.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateVendorInfo(VendorInfo vendorInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateVendorInfo(VendorInfo), 输入参数[" + vendorInfo + "]");
		}
    	getSqlMapClientTemplate().update("VendorInfo_updateVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateVendorInfo(VendorInfo)");
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
        	logger.debug("进入findVendorInfo(VendorInfo), 输入参数[" + pk + "]");
		}
        VendorInfo vendorInfo = (VendorInfo) getSqlMapClientTemplate().queryForObject("VendorInfo_findVendorInfoByID", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorInfo(VendorInfo), 返回[" + vendorInfo + "]");
		}
        return vendorInfo;
    }
    
    public VendorInfo findVendorInfoByBase(VendorInfo vInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findVendorInfo(VendorInfo), 输入参数[" + vInfo + "]");
		}
        VendorInfo vendorInfo = (VendorInfo) getSqlMapClientTemplate().queryForObject("VendorInfo_findVendorInfoByBase", vInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findVendorInfo(VendorInfo), 返回[" + vendorInfo + "]");
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
        	logger.debug("进入listVendorInfo(VendorInfo), 输入参数[" + vendorInfo + "]");
		}
        List<VendorInfo> list = getSqlMapClientTemplate().queryForList("VendorInfo_listVendorInfo", vendorInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorInfo(VendorInfo), 返回[" + list + "]");
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
        	logger.debug("进入listVendorInfoCount(VendorInfo), 输入参数[" + vendorInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("VendorInfo_listVendorInfoCount", vendorInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listVendorInfoCount(VendorInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
