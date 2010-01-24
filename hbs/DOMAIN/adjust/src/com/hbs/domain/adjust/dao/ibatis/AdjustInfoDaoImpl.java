package com.hbs.domain.adjust.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.adjust.pojo.AdjustInfo;
import com.hbs.domain.adjust.dao.AdjustInfoDao;

/**
 * AdjustInfoDao接口实现类.
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
    		logger.debug("进入insertAdjustInfo(AdjustInfo), 输入参数[" + adjustInfo + "]");
    	}
        
        
    	getSqlMapClientTemplate().insert("AdjustInfo_insertAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertAdjustInfo(AdjustInfo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteAdjustInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteAdjustInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("AdjustInfo_deleteAdjustInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteAdjustInfo(String)");
		}
    }
    
    /**
     * update.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateAdjustInfo(AdjustInfo), 输入参数[" + adjustInfo + "]");
		}
    	getSqlMapClientTemplate().update("AdjustInfo_updateAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateAdjustInfo(AdjustInfo)");
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
        	logger.debug("进入findAdjustInfo(AdjustInfo), 输入参数[" + pk + "]");
		}
        AdjustInfo adjustInfo = (AdjustInfo) getSqlMapClientTemplate().queryForObject("AdjustInfo_findAdjustInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findAdjustInfo(AdjustInfo), 返回[" + adjustInfo + "]");
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
        	logger.debug("进入listAdjustInfo(AdjustInfo), 输入参数[" + adjustInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("AdjustInfo_listAdjustInfo", adjustInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAdjustInfo(AdjustInfo), 返回[" + list + "]");
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
        	logger.debug("进入listAdjustInfoCount(AdjustInfo), 输入参数[" + adjustInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("AdjustInfo_listAdjustInfoCount", adjustInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAdjustInfoCount(AdjustInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
