package com.hbs.domain.warehouse.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.warehouse.pojo.WarehouseSendInfo;


/**
 * WarehouseSendInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WarehouseSendInfoDao {
    /**
     * insert.
     * @param warehouseSendInfo warehouseSendInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException ;

    /**
     * delete.
     * @param warehouseSendInfo warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteWarehouseSendInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param warehouseSendInfo warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    void updateWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return warehouseSendInfo
     * @throws DataAccessException DataAccessException
     */
    WarehouseSendInfo findWarehouseSendInfo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param warehouseSendInfo warehouseSendInfo
     * @return warehouseSendInfo list
     * @throws DataAccessException DataAccessException
     */
    List listWarehouseSendInfo(WarehouseSendInfo warehouseSendInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param warehouseSendInfo warehouseSendInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWarehouseSendInfoCount(WarehouseSendInfo warehouseSendInfo) throws DataAccessException ;
}
