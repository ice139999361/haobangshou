package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;
import com.hbs.domain.invoice.dao.PeriodSpecMemoDao;

/**
 * PeriodSpecMemoDao接口实现类.
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
    		logger.debug("进入insertPeriodSpecMemo(PeriodSpecMemo), 输入参数[" + periodSpecMemo + "]");
    	}
       
    	getSqlMapClientTemplate().insert("PeriodSpecMemo_insertPeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertPeriodSpecMemo(PeriodSpecMemo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    public void deletePeriodSpecMemo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deletePeriodSpecMemo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("PeriodSpecMemo_deletePeriodSpecMemo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deletePeriodSpecMemo(String)");
		}
    }
    
    /**
     * update.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    public void updatePeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updatePeriodSpecMemo(PeriodSpecMemo), 输入参数[" + periodSpecMemo + "]");
		}
    	getSqlMapClientTemplate().update("PeriodSpecMemo_updatePeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updatePeriodSpecMemo(PeriodSpecMemo)");
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
        	logger.debug("进入findPeriodSpecMemo(PeriodSpecMemo), 输入参数[" + pk + "]");
		}
        PeriodSpecMemo periodSpecMemo = (PeriodSpecMemo) getSqlMapClientTemplate().queryForObject("PeriodSpecMemo_findPeriodSpecMemo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findPeriodSpecMemo(PeriodSpecMemo), 返回[" + periodSpecMemo + "]");
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
        	logger.debug("进入listPeriodSpecMemo(PeriodSpecMemo), 输入参数[" + periodSpecMemo + "]");
		}
        List<PeriodSpecMemo> list = getSqlMapClientTemplate().queryForList("PeriodSpecMemo_listPeriodSpecMemo", periodSpecMemo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listPeriodSpecMemo(PeriodSpecMemo), 返回[" + list + "]");
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
        	logger.debug("进入listPeriodSpecMemoCount(PeriodSpecMemo), 输入参数[" + periodSpecMemo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("PeriodSpecMemo_listPeriodSpecMemoCount", periodSpecMemo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listPeriodSpecMemoCount(PeriodSpecMemo), 返回[" + count + "]");
		}
        return count;
    }  
}
