package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;


/**
 * BankInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface BankInfoDao {
    /**
     * insert.
     * @param bankInfo bankInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertBankInfo(BankInfo bankInfo) throws DataAccessException ;

    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteBankInfo(BankInfo bankInfo) throws DataAccessException ;
    
    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteBankInfoByID(String pk) throws DataAccessException ;
    
    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    void updateBankInfo(BankInfo bankInfo) throws DataAccessException ;

    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    void updateBankInfoByState(BankInfo bankInfo) throws DataAccessException ;

    /**
     * find.
     * @param bankInfo
     * @return bankInfo
     * @throws DataAccessException DataAccessException
     */
    BankInfo findBankInfo(BankInfo bankInfo) throws DataAccessException ;
    
    BankInfo findBankInfoById(String id) throws DataAccessException;
    
    /**
     * list.
     * @param bankInfo bankInfo
     * @return bankInfo list
     * @throws DataAccessException DataAccessException
     */
    List<BankInfo> listBankInfo(BankInfo bankInfo) throws DataAccessException ;
    
    
}
