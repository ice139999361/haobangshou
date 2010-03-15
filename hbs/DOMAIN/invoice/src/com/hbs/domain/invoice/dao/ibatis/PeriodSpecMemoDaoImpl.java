package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.domain.invoice.dao.PeriodSpecMemoDao;

/**
 * PeriodSpecMemoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class PeriodSpecMemoDaoImpl extends SqlMapClientDaoSupport implements PeriodSpecMemoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(PeriodSpecMemoDaoImpl.class);

    
    
    /**
     * insert.
     * @param periodSpecMemo periodSpecMemo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertPeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertPeriodSpecMemo(PeriodSpecMemo), �������[" + periodSpecMemo + "]");
    	}
       
    	getSqlMapClientTemplate().insert("PeriodSpecMemo_insertPeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertPeriodSpecMemo(PeriodSpecMemo), ����");
		}
    	
    }

    /**
     * delete.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    public void deletePeriodSpecMemo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deletePeriodSpecMemo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("PeriodSpecMemo_deletePeriodSpecMemo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deletePeriodSpecMemo(String)");
		}
    }
    
    /**
     * update.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    public void updatePeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updatePeriodSpecMemo(PeriodSpecMemo), �������[" + periodSpecMemo + "]");
		}
    	getSqlMapClientTemplate().update("PeriodSpecMemo_updatePeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updatePeriodSpecMemo(PeriodSpecMemo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    public PeriodSpecMemo findPeriodSpecMemo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findPeriodSpecMemo(PeriodSpecMemo), �������[" + pk + "]");
		}
        PeriodSpecMemo periodSpecMemo = (PeriodSpecMemo) getSqlMapClientTemplate().queryForObject("PeriodSpecMemo_findPeriodSpecMemo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findPeriodSpecMemo(PeriodSpecMemo), ����[" + periodSpecMemo + "]");
		}
        return periodSpecMemo;
    }
    
    /**
     * list.
     * @param periodSpecMemo periodSpecMemo
     * @return periodSpecMemo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<PeriodSpecMemo> listPeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listPeriodSpecMemo(PeriodSpecMemo), �������[" + periodSpecMemo + "]");
		}
        List<PeriodSpecMemo> list = getSqlMapClientTemplate().queryForList("PeriodSpecMemo_listPeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listPeriodSpecMemo(PeriodSpecMemo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param periodSpecMemo periodSpecMemo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listPeriodSpecMemoCount(PeriodSpecMemo periodSpecMemo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listPeriodSpecMemoCount(PeriodSpecMemo), �������[" + periodSpecMemo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("PeriodSpecMemo_listPeriodSpecMemoCount", periodSpecMemo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listPeriodSpecMemoCount(PeriodSpecMemo), ����[" + count + "]");
		}
        return count;
    }  
}
