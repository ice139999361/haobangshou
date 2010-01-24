package com.hbs.domain.vendor.vendorinfo.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorPartNoInfo;


/**
 * VendorPartNoInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface VendorPartNoInfoDao {
    /**
     * insert.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;

    /**
     * delete.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorPartNoInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void updateVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    VendorPartNoInfo findVendorPartNoInfo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return vendorPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    List listVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorPartNoInfoCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
}
