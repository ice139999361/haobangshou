package com.hbs.domain.invoice.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.invoice.pojo.PeriodSpecMemo;


/**
 * PeriodSpecMemoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface PeriodSpecMemoDao {
    /**
     * insert.
     * @param periodSpecMemo periodSpecMemo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertPeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException ;

    /**
     * delete.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    void deletePeriodSpecMemo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param periodSpecMemo periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    void updatePeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return periodSpecMemo
     * @throws DataAccessException DataAccessException
     */
    PeriodSpecMemo findPeriodSpecMemo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param periodSpecMemo periodSpecMemo
     * @return periodSpecMemo list
     * @throws DataAccessException DataAccessException
     */
    List<PeriodSpecMemo> listPeriodSpecMemo(PeriodSpecMemo periodSpecMemo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param periodSpecMemo periodSpecMemo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listPeriodSpecMemoCount(PeriodSpecMemo periodSpecMemo) throws DataAccessException ;
}
