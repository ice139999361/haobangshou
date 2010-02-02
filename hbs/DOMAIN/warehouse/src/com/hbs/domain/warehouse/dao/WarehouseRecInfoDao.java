package com.hbs.domain.warehouse.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.warehouse.pojo.WarehouseRecInfo;


/**
 * WarehouseRecInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WarehouseRecInfoDao {
    /**
     * insert.
     * @param warehouseRecInfo warehouseRecInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException ;

    /**
     * delete.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteWarehouseRecInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param warehouseRecInfo warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    void updateWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return warehouseRecInfo
     * @throws DataAccessException DataAccessException
     */
    WarehouseRecInfo findWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException ;
    
    /**
     * list.
     * @param warehouseRecInfo warehouseRecInfo
     * @return warehouseRecInfo list
     * @throws DataAccessException DataAccessException
     */
    List listWarehouseRecInfo(WarehouseRecInfo warehouseRecInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param warehouseRecInfo warehouseRecInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWarehouseRecInfoCount(WarehouseRecInfo warehouseRecInfo) throws DataAccessException ;
}
