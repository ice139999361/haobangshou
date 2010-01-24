package com.hbs.domain.vendor.order.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.vendor.order.pojo.VendorOrderDetail;


/**
 * VendorOrderDetailDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface VendorOrderDetailDao {
    /**
     * insert.
     * @param vendorOrderDetail vendorOrderDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException ;

    /**
     * delete.
     * @param vendorOrderDetail vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorOrderDetail(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorOrderDetail vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    void updateVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return vendorOrderDetail
     * @throws DataAccessException DataAccessException
     */
    VendorOrderDetail findVendorOrderDetail(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param vendorOrderDetail vendorOrderDetail
     * @return vendorOrderDetail list
     * @throws DataAccessException DataAccessException
     */
    List listVendorOrderDetail(VendorOrderDetail vendorOrderDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorOrderDetail vendorOrderDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorOrderDetailCount(VendorOrderDetail vendorOrderDetail) throws DataAccessException ;
}
