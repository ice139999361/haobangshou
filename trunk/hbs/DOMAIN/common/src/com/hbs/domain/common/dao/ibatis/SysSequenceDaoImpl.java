package com.hbs.domain.common.dao.ibatis;


import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.SysSequence;
import com.hbs.domain.common.dao.SysSequenceDao;

/**
 * SysSequenceDao�ӿ�ʵ����.
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
    		logger.debug("����updateSysSequence(SysSequence), �������[" + sysSequence + "]");
		}
    	getSqlMapClientTemplate().update("SysSequence_updateSysSequence", sysSequence);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateSysSequence(SysSequence)");
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
        	logger.debug("����findSysSequence(SysSequence), �������[" + pk + "]");
		}
        SysSequence sysSequence = (SysSequence) getSqlMapClientTemplate().queryForObject("SysSequence_findSysSequence", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findSysSequence(SysSequence), ����[" + sysSequence + "]");
		}
        return sysSequence;
    }
    
    
}
