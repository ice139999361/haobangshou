package com.hbs.domain.customer.customerinfo.dao.ibatis;

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
public class PrePaidInfoDaoImpl extends SqlMapClientDaoSupport implements PrePaidInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(PrePaidInfoDaoImpl.class);

    
    
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
    		logger.debug("�뿪insertPrePaidInfo(PrePaidInfo), ����");
		}
    	
    }

    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deletePrePaidInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("PrePaidInfo_deletePrePaidInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deletePrePaidInfo");
		}
    }
    
    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void deletePrePaidInfo(PrePaidInfo prePaidInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deletePrePaidInfo(String), �������[" + prePaidInfo + "]");
		}
        getSqlMapClientTemplate().update("PrePaidInfo_deletePrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deletePrePaidInfo");
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
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    public void updatePrePaidInfoByState(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updatePrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
    	getSqlMapClientTemplate().update("PrePaidInfo_updatePrePaidInfoByState", prePaidInfo);
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
    public PrePaidInfo findPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findPrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
        PrePaidInfo tempPrePaidInfo = (PrePaidInfo) getSqlMapClientTemplate().queryForObject("PrePaidInfo_findPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findPrePaidInfo(PrePaidInfo), ����[" + prePaidInfo + "]");
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
        	logger.debug("����listPrePaidInfo(PrePaidInfo), �������[" + prePaidInfo + "]");
		}
        List<PrePaidInfo> list = getSqlMapClientTemplate().queryForList("PrePaidInfo_listPrePaidInfo", prePaidInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listPrePaidInfo(PrePaidInfo), ����[" + list + "]");
		}
        return list;
    }  
    
   
}
