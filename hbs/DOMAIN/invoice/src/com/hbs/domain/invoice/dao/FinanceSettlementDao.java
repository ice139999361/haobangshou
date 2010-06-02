package com.hbs.domain.invoice.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.invoice.pojo.FinanceSettlement;


/**
 * FinanceSettlementDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface FinanceSettlementDao {
    /**
     * insert.
     * @param financeSettlement financeSettlement
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException ;

   
    
    /**
     * update.
     * @param financeSettlement financeSettlement
     * @throws DataAccessException DataAccessException
     */
    void updateFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return financeSettlement
     * @throws DataAccessException DataAccessException
     */
    FinanceSettlement findFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException ;
    
    /**
     * list.
     * @param financeSettlement financeSettlement
     * @return financeSettlement list
     * @throws DataAccessException DataAccessException
     */
    List<FinanceSettlement> listFinanceSettlement(FinanceSettlement financeSettlement) throws DataAccessException ;
    
    /**
     * listCount.
     * @param financeSettlement financeSettlement
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listFinanceSettlementCount(FinanceSettlement financeSettlement) throws DataAccessException ;
}
