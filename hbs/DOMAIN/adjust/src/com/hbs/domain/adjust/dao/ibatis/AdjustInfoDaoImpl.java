package com.hbs.domain.adjust.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.adjust.pojo.AdjustInfo;
import com.hbs.domain.adjust.dao.AdjustInfoDao;

/**
 * AdjustInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class AdjustInfoDaoImpl extends SqlMapClientDaoSupport implements AdjustInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(AdjustInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param adjustInfo adjustInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertAdjustInfo(AdjustInfo), �������[" + adjustInfo + "]");
    	}
        
        
    	getSqlMapClientTemplate().insert("AdjustInfo_insertAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertAdjustInfo(AdjustInfo), ����");
		}
    	
    }

    /**
     * delete.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteAdjustInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteAdjustInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("AdjustInfo_deleteAdjustInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteAdjustInfo(String)");
		}
    }
    
    /**
     * update.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateAdjustInfo(AdjustInfo), �������[" + adjustInfo + "]");
		}
    	getSqlMapClientTemplate().update("AdjustInfo_updateAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateAdjustInfo(AdjustInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return adjustInfo
     * @throws DataAccessException DataAccessException
     */
    public AdjustInfo findAdjustInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findAdjustInfo(AdjustInfo), �������[" + pk + "]");
		}
        AdjustInfo adjustInfo = (AdjustInfo) getSqlMapClientTemplate().queryForObject("AdjustInfo_findAdjustInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findAdjustInfo(AdjustInfo), ����[" + adjustInfo + "]");
		}
        return adjustInfo;
    }
    
    /**
     * list.
     * @param adjustInfo adjustInfo
     * @return adjustInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listAdjustInfo(AdjustInfo), �������[" + adjustInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("AdjustInfo_listAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAdjustInfo(AdjustInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param adjustInfo adjustInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listAdjustInfoCount(AdjustInfo adjustInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listAdjustInfoCount(AdjustInfo), �������[" + adjustInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("AdjustInfo_listAdjustInfoCount", adjustInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAdjustInfoCount(AdjustInfo), ����[" + count + "]");
		}
        return count;
    }  
}
