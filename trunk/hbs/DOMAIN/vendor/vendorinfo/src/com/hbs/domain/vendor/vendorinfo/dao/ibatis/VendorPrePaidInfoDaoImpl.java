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
        
    	getSqlMapClientTemplate().insert("PrePaidInfo_insertPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertPrePaidInfo(PrePaidInfo), 返回[]");
		}
    	
    }

    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deletePrePaidInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("PrePaidInfo_deletePrePaidInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deletePrePaidInfo(String)");
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
    	getSqlMapClientTemplate().update("PrePaidInfo_updatePrePaidInfo", prePaidInfo);
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
    public PrePaidInfo findPrePaidInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findPrePaidInfo(PrePaidInfo), 输入参数[" + pk + "]");
		}
        PrePaidInfo prePaidInfo = (PrePaidInfo) getSqlMapClientTemplate().queryForObject("PrePaidInfo_findPrePaidInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findPrePaidInfo(PrePaidInfo), 返回[" + prePaidInfo + "]");
		}
        return prePaidInfo;
    }
    
    /**
     * list.
     * @param prePaidInfo prePaidInfo
     * @return prePaidInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listPrePaidInfo(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("PrePaidInfo_listPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listPrePaidInfo(PrePaidInfo), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param prePaidInfo prePaidInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listPrePaidInfoCount(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listPrePaidInfoCount(PrePaidInfo), 输入参数[" + prePaidInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("PrePaidInfo_listPrePaidInfoCount", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listPrePaidInfoCount(PrePaidInfo), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao#deletePrePaidInfo(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)
	 */
	public void deletePrePaidInfo(PrePaidInfo prePaidInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao#deletePrePaidInfoByID(java.lang.String)
	 */
	public void deletePrePaidInfoByID(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao#findPrePaidInfo(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)
	 */
	public PrePaidInfo findPrePaidInfo(PrePaidInfo prePaidInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao#updatePrePaidInfoByState(com.hbs.domain.common.pojo.baseinfo.PrePaidInfo)
	 */
	public void updatePrePaidInfoByState(PrePaidInfo prePaidInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
