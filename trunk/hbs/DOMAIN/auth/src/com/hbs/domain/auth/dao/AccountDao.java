package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.Account;


/**
 * AccountDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface AccountDao {
    /**
     * insert.
     * @param account account
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertAccount(Account account) throws DataAccessException ;

    /**
     * delete.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
    void deleteAccount(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param account account
     * @throws DataAccessException DataAccessException
     */
    void updateAccount(Account account) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return account
     * @throws DataAccessException DataAccessException
     */
    Account findAccount(String id) throws DataAccessException ;
    
    /**
     * find by staffId.
     * @param id id
     * @return account
     * @throws DataAccessException DataAccessException
     */
	Account findAccountById(String id);

	/**
     * list.
     * @param account account
     * @return account list
     * @throws DataAccessException DataAccessException
     */
    List<Account> listAccount(Account account) throws DataAccessException ;
    
    /**
     * listCount.
     * @param account account
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listAccountCount(Account account) throws DataAccessException ;

}
