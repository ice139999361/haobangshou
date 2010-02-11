package com.hbs.domain.warehouse.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.warehouse.pojo.WarehouseSendDetail;


/**
 * WarehouseSendDetailDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface WarehouseSendDetailDao {
    /**
     * insert.
     * @param warehouseSendDetail warehouseSendDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;

    /**
     * delete.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    void deleteWarehouseSendDetail(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param warehouseSendDetail warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    void updateWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    void updateWarehouseSendDetailByState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    void updateWarehouseSendDetailByActiveState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    void updateWarehouseSendDetailByFinanceState(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    void updateWarehouseSendDetailByFinancePeriod(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    /**
     * find.
     * @param id id
     * @return warehouseSendDetail
     * @throws DataAccessException DataAccessException
     */
    WarehouseSendDetail findWarehouseSendDetail(String id) throws DataAccessException ;
    WarehouseSendDetail findWarehouseSendDetailByBizKey(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    
    /**
     * list.
     * @param warehouseSendDetail warehouseSendDetail
     * @return warehouseSendDetail list
     * @throws DataAccessException DataAccessException
     */
    List<WarehouseSendDetail> listWarehouseSendDetail(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param warehouseSendDetail warehouseSendDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listWarehouseSendDetailCount(WarehouseSendDetail warehouseSendDetail) throws DataAccessException ;
}
