package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.dao.baseinfo.AccountPreiodDao;

/**
 * AccountPreiodDao接口实现类.
 * @author hbs
 *
 */
public class VendorAccountPreiodDaoImpl extends SqlMapClientDaoSupport implements AccountPreiodDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorAccountPreiodDaoImpl.class);

    
    
    /**
     * insert.
     * @param accountPreiod accountPreiod
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertAccountPreiod(AccountPreiod), 输入参数[" + accountPreiod + "]");
    	}
        
    	getSqlMapClientTemplate().insert("AccountPreiod_insertAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertAccountPreiod(AccountPreiod), 返回");
		}
    
    }

    /**
     * delete.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public void deleteAccountPreiod(AccountPreiod accountPreiod)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteAccountPreiod(String), 输入参数[" + accountPreiod + "]");
		}
        getSqlMapClientTemplate().update("AccountPreiod_deleteAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteAccountPreiod(String)");
		}
    }
    
    /**
     * update.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public void updateAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateAccountPreiod(AccountPreiod), 输入参数[" + accountPreiod + "]");
		}
    	getSqlMapClientTemplate().update("AccountPreiod_updateAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateAccountPreiod(AccountPreiod)");
		}
    }
    
    
    /* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.AccountPreiodDao#updateAccountPreiodByState(com.hbs.domain.common.pojo.baseinfo.AccountPreiod)
	 */
	public void updateAccountPreiodByState(AccountPreiod accountPreiod)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
    /**
     * find.
     * @param id id
     * @return accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public AccountPreiod findAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findAccountPreiod(AccountPreiod), 输入参数[" + accountPreiod + "]");
		}
        AccountPreiod aPreiod = (AccountPreiod) getSqlMapClientTemplate().queryForObject("AccountPreiod_findAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findAccountPreiod(AccountPreiod), 返回[" + accountPreiod + "]");
		}
        return aPreiod;
    }
    
    /**
     * list.
     * @param accountPreiod accountPreiod
     * @return accountPreiod list
     * @throws DataAccessException DataAccessException
     */
    public List listAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listAccountPreiod(AccountPreiod), 输入参数[" + accountPreiod + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("AccountPreiod_listAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAccountPreiod(AccountPreiod), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param accountPreiod accountPreiod
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listAccountPreiodCount(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listAccountPreiodCount(AccountPreiod), 输入参数[" + accountPreiod + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("AccountPreiod_listAccountPreiodCount", accountPreiod);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAccountPreiodCount(AccountPreiod), 返回[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.AccountPreiodDao#deleteAccountPreiodByID(java.lang.String)
	 */
	public void deleteAccountPreiodByID(String pk) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
