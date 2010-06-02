package com.hbs.domain.warehouse.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.warehouse.pojo.WarehouseRecDetail;


/**
 * WarehouseRecDetailDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WarehouseRecDetailDao {
    /**
     * insert.
     * @param warehouseRecDetail warehouseRecDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;

    /**
     * delete.
     * @param warehouseRecDetail warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    void deleteWarehouseRecDetail(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param warehouseRecDetail warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    void updateWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    void updateWarehouseRecDetailByState(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    void updateWarehouseRecDetailByActiveState(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    void updateWarehouseRecDetailByFinanceState(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    void updateWarehouseRecDetailByFinancePeriod(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    void updateWarehouseRecDetailByFinanceStateSettlement(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return warehouseRecDetail
     * @throws DataAccessException DataAccessException
     */
    WarehouseRecDetail findWarehouseRecDetail(String id) throws DataAccessException ;
    WarehouseRecDetail findWarehouseRecDetailByBizKey(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    
    /**
     * list.
     * @param warehouseRecDetail warehouseRecDetail
     * @return warehouseRecDetail list
     * @throws DataAccessException DataAccessException
     */
    List<WarehouseRecDetail> listWarehouseRecDetail(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param warehouseRecDetail warehouseRecDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWarehouseRecDetailCount(WarehouseRecDetail warehouseRecDetail) throws DataAccessException ;
}
