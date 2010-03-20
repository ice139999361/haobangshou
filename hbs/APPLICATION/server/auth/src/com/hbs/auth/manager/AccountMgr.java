package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.AccountDao;
import com.hbs.domain.auth.pojo.Account;

/**
 * 
 * @author tony.chen
 *
 */


public class AccountMgr {
	
	private static final String ACCOUNT_DAO_NAME = "accountDao";
	private static final Logger logger = Logger.getLogger(AccountMgr.class);
	
	public Integer insertAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertAccount(Account account): parameter=" + account.toString());
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		Integer ret = accountDao.insertAccount(account);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertAccount(Account account)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
	public void deleteAccount(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteAccount(String id): parameter=" + id);
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		accountDao.deleteAccount(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteAccount(String id)");
    	}
	}
    
    /**
     * update.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
	public void updateAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateAccount(Account account): parameter=" + account.toString());
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		accountDao.updateAccount(account);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateAccount(Account account)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return account
     * @throws DataAccessException DataAccessException
     */
	public Account findAccount(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findAccount(String id): parameter=" + id);
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		Account ret = accountDao.findAccount(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findAccount(String id)");
    	}
		return ret;
	}
    
    /**
     * find by staffId.
     * @param id id
     * @return account
     * @throws DataAccessException DataAccessException
     */
	public Account findAccountById(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findAccountById(String id): parameter=" + id);
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		Account ret = accountDao.findAccountById(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findAccountById(String id)");
    	}
		return ret;
	}

	/**
     * list.
     * @param account account
     * @return account list
     * @throws DataAccessException DataAccessException
     */
	public List<Account> listAccount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listAccount(Account account): parameter=" + account.toString());
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		List<Account> ret = accountDao.listAccount(account);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listAccount(Account account)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param account account
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listAccountCount(Account account) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listAccountCount(Account account): parameter=" + account.toString());
    	}
		AccountDao accountDao = (AccountDao)BeanLocator.getInstance().getBean(ACCOUNT_DAO_NAME);
		Integer ret = accountDao.listAccountCount(account);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listAccountCount(Account account)");
    	}
		return ret;
	}
}
