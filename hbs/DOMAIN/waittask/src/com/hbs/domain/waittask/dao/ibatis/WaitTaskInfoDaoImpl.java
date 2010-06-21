package com.hbs.domain.waittask.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.waittask.pojo.WaitTaskInfo;
import com.hbs.domain.waittask.dao.WaitTaskInfoDao;

/**
 * WaitTaskInfoDao�ӿ�ʵ����.
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
    		logger.debug("����insertWaitTaskInfo(WaitTaskInfo), �������[" + waitTaskInfo + "]");
    	}
      
        
    	getSqlMapClientTemplate().insert("WaitTaskInfo_insertWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertWaitTaskInfo(WaitTaskInfo),");
		}
    	
    }

    /**
     * delete.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteWaitTaskInfo(String businessKey)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWaitTaskInfo(String), �������[" + businessKey + "]");
		}
        getSqlMapClientTemplate().update("WaitTaskInfo_deleteWaitTaskInfo", businessKey);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWaitTaskInfo(String)");
		}
    }
    public void deleteWaitTaskInfoByExpireTime(String expireTime)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteWaitTaskInfo(String), �������[" + expireTime + "]");
		}
        getSqlMapClientTemplate().update("WaitTaskInfo_deleteWaitTaskInfoByExpireTime", expireTime);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteWaitTaskInfo(String)");
		}
    }
    /**
     * update.
     * @param waitTaskInfo waitTaskInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateWaitTaskInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateWaitTaskInfo(WaitTaskInfo), �������[" + waitTaskInfo + "]");
		}
    	getSqlMapClientTemplate().update("WaitTaskInfo_updateWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateWaitTaskInfo(WaitTaskInfo)");
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
        	logger.debug("����findWaitTaskInfo(WaitTaskInfo), �������[" + businessKey + "]");
		}
        WaitTaskInfo waitTaskInfo = (WaitTaskInfo) getSqlMapClientTemplate().queryForObject("WaitTaskInfo_findWaitTaskInfo", businessKey);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWaitTaskInfo(WaitTaskInfo), ����[" + waitTaskInfo + "]");
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
        	logger.debug("����listWaitTaskInfo(WaitTaskInfo), �������[" + waitTaskInfo + "]");
		}
        List<WaitTaskInfo> list = getSqlMapClientTemplate().queryForList("WaitTaskInfo_listWaitTaskInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWaitTaskInfo(WaitTaskInfo), ����[" + list + "]");
		}
        return list;
    }  
    @SuppressWarnings("unchecked")
	public List<WaitTaskInfo> listWaitTaskMustInfo(WaitTaskInfo waitTaskInfo) throws DataAccessException {
    	if (logger.isDebugEnabled()) {
        	logger.debug("����listWaitTaskMustInfo(WaitTaskInfo), �������[" + waitTaskInfo + "]");
		}
        List<WaitTaskInfo> list = getSqlMapClientTemplate().queryForList("WaitTaskInfo_listWaitTaskMustInfo", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWaitTaskMustInfo(WaitTaskInfo), ����[" + list + "]");
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
        	logger.debug("����listWaitTaskInfoCount(WaitTaskInfo), �������[" + waitTaskInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("WaitTaskInfo_listWaitTaskInfoCount", waitTaskInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWaitTaskInfoCount(WaitTaskInfo), ����[" + count + "]");
		}
        return count;
    }  
}
