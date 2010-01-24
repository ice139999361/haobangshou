package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;
import com.hbs.domain.common.dao.baseinfo.PrePaidInfoDao;

/**
 * PrePaidInfoDao�ӿ�ʵ����.
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
    		logger.debug("����insertPrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
    	}
        
    	getSqlMapClientTemplate().insert("PrePaidInfo_insertPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertPrePaidInfo(PrePaidInfo), ����[]");
		}
    	
    }

    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deletePrePaidInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("PrePaidInfo_deletePrePaidInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deletePrePaidInfo(String)");
		}
    }
    
    /**
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void updatePrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updatePrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
    	getSqlMapClientTemplate().update("PrePaidInfo_updatePrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updatePrePaidInfo(PrePaidInfo)");
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
        	logger.debug("����findPrePaidInfo(PrePaidInfo), �������[" + pk + "]");
		}
        PrePaidInfo prePaidInfo = (PrePaidInfo) getSqlMapClientTemplate().queryForObject("PrePaidInfo_findPrePaidInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findPrePaidInfo(PrePaidInfo), ����[" + prePaidInfo + "]");
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
        	logger.debug("����listPrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("PrePaidInfo_listPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listPrePaidInfo(PrePaidInfo), ����[" + list + "]");
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
        	logger.debug("����listPrePaidInfoCount(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("PrePaidInfo_listPrePaidInfoCount", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listPrePaidInfoCount(PrePaidInfo), ����[" + count + "]");
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
