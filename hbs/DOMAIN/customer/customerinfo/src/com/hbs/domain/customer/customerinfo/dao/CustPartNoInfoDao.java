package com.hbs.domain.customer.customerinfo.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.customer.customerinfo.pojo.CustPartNoInfo;


/**
 * CustPartNoInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface CustPartNoInfoDao {
    /**
     * insert.
     * @param custPartNoInfo custPartNoInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    int insertCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException ;

    /**
     * delete.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteCustPartNoInfoByID(String pk)throws DataAccessException;
    
    /**
     * delete.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo)throws DataAccessException ;
    
    /**
     * update.
     * @param custPartNoInfo custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void updateCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException ;
    void updateCustPartNoInfoByState(CustPartNoInfo custPartNoInfo) throws DataAccessException;
    /**
     * find.
     * @param id id
     * @return custPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    CustPartNoInfo findCustPartNoInfoByID(String pk) throws DataAccessException ;
    CustPartNoInfo findCustPartNoInfoByBizKey(CustPartNoInfo custPartNoInfo) throws DataAccessException ;
    /**
     * list.
     * @param custPartNoInfo custPartNoInfo
     * @return custPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    List<CustPartNoInfo> listCustPartNoInfo(CustPartNoInfo custPartNoInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param custPartNoInfo custPartNoInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listCustPartNoInfoCount(CustPartNoInfo custPartNoInfo) throws DataAccessException ;
}
