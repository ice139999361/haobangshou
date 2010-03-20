package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Account;
import com.hbs.domain.auth.dao.AccountDao;

/**
 * AccountDao�ӿ�ʵ����.
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
    		logger.debug("����insertAccount(Account), �������[" + account + "]");
    	}
        
      
    	getSqlMapClientTemplate().insert("Account_insertAccount", account);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertAccount(Account), ����");
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
    		logger.debug("����deleteAccount(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Account_deleteAccount", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteAccount(String)");
		}
    }
    
    /**
     * update.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
    public void updateAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateAccount(Account), �������[" + account + "]");
		}
    	getSqlMapClientTemplate().update("Account_updateAccount", account);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateAccount(Account)");
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
        	logger.debug("����findAccount(Account), �������[" + pk + "]");
		}
        Account account = (Account) getSqlMapClientTemplate().queryForObject("Account_findAccount", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findAccount(Account), ����[" + account + "]");
		}
        return account;
    }
    
	public Account findAccountById(String id) {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findAccountById(id), �������[" + id + "]");
		}
        Account account = (Account) getSqlMapClientTemplate().queryForObject("Account_findAccountById", id);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findAccountById(id), ����[" + account + "]");
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
        	logger.debug("����listAccount(Account), �������[" + account + "]");
		}
        List<Account> list = getSqlMapClientTemplate().queryForList("Account_listAccount", account);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAccount(Account), ����[" + list + "]");
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
        	logger.debug("����listAccountCount(Account), �������[" + account + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Account_listAccountCount", account);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAccountCount(Account), ����[" + count + "]");
		}
        return count;
    }

}
