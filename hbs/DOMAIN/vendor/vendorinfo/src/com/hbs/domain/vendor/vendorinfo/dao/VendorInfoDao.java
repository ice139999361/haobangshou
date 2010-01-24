package com.hbs.domain.vendor.vendorinfo.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.vendor.vendorinfo.pojo.VendorInfo;


/**
 * VendorInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface VendorInfoDao {
    /**
     * insert.
     * @param vendorInfo vendorInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;

    /**
     * delete.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorInfo(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    void updateVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return vendorInfo
     * @throws DataAccessException DataAccessException
     */
    VendorInfo findVendorInfo(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param vendorInfo vendorInfo
     * @return vendorInfo list
     * @throws DataAccessException DataAccessException
     */
    List listVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorInfo vendorInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorInfoCount(VendorInfo vendorInfo) throws DataAccessException ;
}
