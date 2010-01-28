package com.hbs.domain.common.dao.ibatis;


import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.SysSequence;
import com.hbs.domain.common.dao.SysSequenceDao;

/**
 * SysSequenceDao接口实现类.
 * @author hbs
 *
 */
public class SysSequenceDaoImpl extends SqlMapClientDaoSupport implements SysSequenceDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(SysSequenceDaoImpl.class);

    
    
   
    
    /**
     * update.
     * @param sysSequence sysSequence
     * @throws DataAccessException DataAccessException
     */
    public void updateSysSequence(SysSequence sysSequence) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateSysSequence(SysSequence), 输入参数[" + sysSequence + "]");
		}
    	getSqlMapClientTemplate().update("SysSequence_updateSysSequence", sysSequence);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateSysSequence(SysSequence)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return sysSequence
     * @throws DataAccessException DataAccessException
     */
    public SysSequence findSysSequence(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findSysSequence(SysSequence), 输入参数[" + pk + "]");
		}
        SysSequence sysSequence = (SysSequence) getSqlMapClientTemplate().queryForObject("SysSequence_findSysSequence", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findSysSequence(SysSequence), 返回[" + sysSequence + "]");
		}
        return sysSequence;
    }
    
    
}
