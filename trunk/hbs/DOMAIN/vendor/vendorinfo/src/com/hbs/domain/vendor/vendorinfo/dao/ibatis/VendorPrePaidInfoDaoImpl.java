package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao;

/**
 * PrePaidInfoDao接口实现类.
 * @author hbs
 *
 */
public class VendorPrePaidInfoDaoImpl extends SqlMapClientDaoSupport implements PrePaidInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorPrePaidInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param prePaidInfo prePaidInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertPrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
    	}
        
    	getSqlMapClientTemplate().insert("Vendor_PrePaidInfo_insertPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertPrePaidInfo(PrePaidInfo), 返回[]");
		}
    	
    }

    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deletePrePaidInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Vendor_PrePaidInfo_deletePrePaidInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deletePrePaidInfo");
		}
    }
    
    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfo(PrePaidInfo prePaidInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deletePrePaidInfo(String), 输入参数[" + prePaidInfo + "]");
		}
        getSqlMapClientTemplate().update("Vendor_PrePaidInfo_deletePrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deletePrePaidInfo");
		}
    }
    
    /**
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void updatePrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updatePrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_PrePaidInfo_updatePrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updatePrePaidInfo(PrePaidInfo)");
		}
    }
    
    /**
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void updatePrePaidInfoByState(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updatePrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_PrePaidInfo_updatePrePaidInfoByState", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updatePrePaidInfo(PrePaidInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public PrePaidInfo findPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findPrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
        PrePaidInfo tempPrePaidInfo = (PrePaidInfo) getSqlMapClientTemplate().queryForObject("Vendor_PrePaidInfo_findPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findPrePaidInfo(PrePaidInfo), 返回[" + prePaidInfo + "]");
		}
        return tempPrePaidInfo;
    }
    
    /**
     * list.
     * @param prePaidInfo prePaidInfo
     * @return prePaidInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<PrePaidInfo> listPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listPrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
        List<PrePaidInfo> list = getSqlMapClientTemplate().queryForList("Vendor_PrePaidInfo_listPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listPrePaidInfo(PrePaidInfo), 返回[" + list + "]");
		}
        return list;
    }  
    
   
}
