package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.domain.auth.dao.AccountDao;

/**
 * AccountDao接口实现类.
 * @author hbs
 *
 */
public class AccountDaoImpl extends SqlMapClientDaoSupport implements AccountDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(AccountDaoImpl.class);

    
    
    /**
     * insert.
     * @param account account
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertAccount(Account), 输入参数[" + account + "]");
    	}
        
      
    	getSqlMapClientTemplate().insert("Account_insertAccount", account);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertAccount(Account), 返回");
		}
    	return account.getStaffId();
    }

    /**
     * delete.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
    public void deleteAccount(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteAccount(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Account_deleteAccount", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteAccount(String)");
		}
    }
    
    /**
     * update.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
    public void updateAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateAccount(Account), 输入参数[" + account + "]");
		}
    	getSqlMapClientTemplate().update("Account_updateAccount", account);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateAccount(Account)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return account
     * @throws DataAccessException DataAccessException
     */
    public Account findAccount(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findAccount(Account), 输入参数[" + pk + "]");
		}
        Account account = (Account) getSqlMapClientTemplate().queryForObject("Account_findAccount", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findAccount(Account), 返回[" + account + "]");
		}
        return account;
    }
    
	public Account findAccountById(String id) {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findAccountById(id), 输入参数[" + id + "]");
		}
        Account account = (Account) getSqlMapClientTemplate().queryForObject("Account_findAccountById", id);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findAccountById(id), 返回[" + account + "]");
		}
        return account;
	}  

	/**
     * list.
     * @param account account
     * @return account list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<Account> listAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listAccount(Account), 输入参数[" + account + "]");
		}
        List<Account> list = getSqlMapClientTemplate().queryForList("Account_listAccount", account);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAccount(Account), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param account account
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listAccountCount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listAccountCount(Account), 输入参数[" + account + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Account_listAccountCount", account);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listAccountCount(Account), 返回[" + count + "]");
		}
        return count;
    }

}
