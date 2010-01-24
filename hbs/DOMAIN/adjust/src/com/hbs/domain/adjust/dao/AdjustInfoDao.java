package com.hbs.domain.adjust.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.adjust.pojo.AdjustInfo;


/**
 * AdjustInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface AdjustInfoDao {
    /**
     * insert.
     * @param adjustInfo adjustInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException ;

    /**
     * delete.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteAdjustInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param adjustInfo adjustInfo
     * @throws DataAccessException DataAccessException
     */
    void updateAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return adjustInfo
     * @throws DataAccessException DataAccessException
     */
    AdjustInfo findAdjustInfo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param adjustInfo adjustInfo
     * @return adjustInfo list
     * @throws DataAccessException DataAccessException
     */
    List listAdjustInfo(AdjustInfo adjustInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param adjustInfo adjustInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listAdjustInfoCount(AdjustInfo adjustInfo) throws DataAccessException ;
}
