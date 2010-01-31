package com.hbs.domain.vendor.order.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.vendor.order.pojo.VendorOrder;


/**
 * VendorOrderDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface VendorOrderDao {
    /**
     * insert.
     * @param vendorOrder vendorOrder
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertVendorOrder(VendorOrder vendorOrder) throws DataAccessException ;

    /**
     * delete.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorOrder(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorOrder vendorOrder
     * @throws DataAccessException DataAccessException
     */
    void updateVendorOrder(VendorOrder vendorOrder) throws DataAccessException ;
    void updateVendorOrderByState(VendorOrder vendorOrder) throws DataAccessException ;
    void updateVendorOrderByActiveState(VendorOrder vendorOrder) throws DataAccessException ;
    
    /**
     * find.
     * @param id id
     * @return vendorOrder
     * @throws DataAccessException DataAccessException
     */
    VendorOrder findVendorOrder(VendorOrder vendorOrder) throws DataAccessException ;
    
    /**
     * list.
     * @param vendorOrder vendorOrder
     * @return vendorOrder list
     * @throws DataAccessException DataAccessException
     */
    List<VendorOrder> listVendorOrder(VendorOrder vendorOrder) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorOrder vendorOrder
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorOrderCount(VendorOrder vendorOrder) throws DataAccessException ;
}
