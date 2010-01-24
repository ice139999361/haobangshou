package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;


/**
 * AccountPreiodDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface AccountPreiodDao {
    /**
     * insert.
     * @param accountPreiod accountPreiod
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException ;

    /**
     * delete.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    void deleteAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException ;
    
    /**
     * 
     * @param pk
     * @throws DataAccessException
     */
    public void deleteAccountPreiodByID(String pk)throws DataAccessException;
    /**
     * update.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    void updateAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException ;
    
    /**
     * update by state
     * @param accountPreiod
     * @throws DataAccessException
     */
    void updateAccountPreiodByState(AccountPreiod accountPreiod)throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return accountPreiod
     * @throws DataAccessException DataAccessException
     */
    AccountPreiod findAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException ;
    
    /**
     * list.
     * @param accountPreiod accountPreiod
     * @return accountPreiod list
     * @throws DataAccessException DataAccessException
     */
    List<AccountPreiod> listAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException ;
    
   
}
