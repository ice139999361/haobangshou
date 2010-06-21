package com.hbs.domain.waittask.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.waittask.dao.WaitTaskInfoDao;

/**
 * WaitTaskInfoDao接口实现类.
 * @author hbs
 *
 */
public class WaitTaskInfoDaoImpl extends SqlMapClientDaoSupport implements WaitTaskInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WaitTaskInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param waitTaskInfo waitTaskInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertWaitTaskInfo(WaitTaskInfo), 输入参数[" + waitTaskInfo + "]");
    	}
      
        
    	getSqlMapClientTemplate().insert("WaitTaskInfo_insertWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertWaitTaskInfo(WaitTaskInfo),");
		}
    	
    }

    /**
     * delete.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWaitTaskInfo(String businessKey)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWaitTaskInfo(String), 输入参数[" + businessKey + "]");
		}
        getSqlMapClientTemplate().update("WaitTaskInfo_deleteWaitTaskInfo", businessKey);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWaitTaskInfo(String)");
		}
    }
    public void deleteWaitTaskInfoByExpireTime(String expireTime)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteWaitTaskInfo(String), 输入参数[" + expireTime + "]");
		}
        getSqlMapClientTemplate().update("WaitTaskInfo_deleteWaitTaskInfoByExpireTime", expireTime);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteWaitTaskInfo(String)");
		}
    }
    /**
     * update.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateWaitTaskInfo(WaitTaskInfo), 输入参数[" + waitTaskInfo + "]");
		}
    	getSqlMapClientTemplate().update("WaitTaskInfo_updateWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateWaitTaskInfo(WaitTaskInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    public WaitTaskInfo findWaitTaskInfo(String businessKey) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findWaitTaskInfo(WaitTaskInfo), 输入参数[" + businessKey + "]");
		}
        WaitTaskInfo waitTaskInfo = (WaitTaskInfo) getSqlMapClientTemplate().queryForObject("WaitTaskInfo_findWaitTaskInfo", businessKey);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findWaitTaskInfo(WaitTaskInfo), 返回[" + waitTaskInfo + "]");
		}
        return waitTaskInfo;
    }
    
    /**
     * list.
     * @param waitTaskInfo waitTaskInfo
     * @return waitTaskInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<WaitTaskInfo> listWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWaitTaskInfo(WaitTaskInfo), 输入参数[" + waitTaskInfo + "]");
		}
        List<WaitTaskInfo> list = getSqlMapClientTemplate().queryForList("WaitTaskInfo_listWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWaitTaskInfo(WaitTaskInfo), 返回[" + list + "]");
		}
        return list;
    }  
    @SuppressWarnings("unchecked")
	public List<WaitTaskInfo> listWaitTaskMustInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
        	logger.debug("进入listWaitTaskMustInfo(WaitTaskInfo), 输入参数[" + waitTaskInfo + "]");
		}
        List<WaitTaskInfo> list = getSqlMapClientTemplate().queryForList("WaitTaskInfo_listWaitTaskMustInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWaitTaskMustInfo(WaitTaskInfo), 返回[" + list + "]");
		}
        return list;
    }
    /**
     * listCount.
     * @param waitTaskInfo waitTaskInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listWaitTaskInfoCount(WaitTaskInfo waitTaskInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listWaitTaskInfoCount(WaitTaskInfo), 输入参数[" + waitTaskInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WaitTaskInfo_listWaitTaskInfoCount", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listWaitTaskInfoCount(WaitTaskInfo), 返回[" + count + "]");
		}
        return count;
    }  
}
