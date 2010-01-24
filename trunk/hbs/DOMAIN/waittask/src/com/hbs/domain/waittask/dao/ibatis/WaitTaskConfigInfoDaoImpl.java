package com.hbs.domain.waittask.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.waittask.pojo.WaitTaskConfigInfo;
import com.hbs.domain.waittask.dao.WaitTaskConfigInfoDao;

/**
 * WaitTaskConfigInfoDao�ӿ�ʵ����.
 * @author sims autoCoder1.0
 *
 */
public class WaitTaskConfigInfoDaoImpl extends SqlMapClientDaoSupport implements WaitTaskConfigInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(WaitTaskConfigInfoDaoImpl.class);

  
    /**
     * list.
     * @param waitTaskConfigInfo waitTaskConfigInfo
     * @return waitTaskConfigInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List listWaitTaskConfigInfo() throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listWaitTaskConfigInfo(WaitTaskConfigInfo)");
		}
        List list = getSqlMapClientTemplate().queryForList("WaitTaskConfigInfo_listWaitTaskConfigInfo", null);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listWaitTaskConfigInfo(WaitTaskConfigInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * find.
     * @param id id
     * @return waitTaskConfigInfo
     * @throws DataAccessException DataAccessException
     */
    public WaitTaskConfigInfo findWaitTaskConfigInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findWaitTaskConfigInfo(WaitTaskConfigInfo), �������[" + pk + "]");
		}
        WaitTaskConfigInfo waitTaskConfigInfo = (WaitTaskConfigInfo) getSqlMapClientTemplate().queryForObject("WaitTaskConfigInfo_findWaitTaskConfigInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findWaitTaskConfigInfo(WaitTaskConfigInfo), ����[" + waitTaskConfigInfo + "]");
		}
        return waitTaskConfigInfo;
    }
}
