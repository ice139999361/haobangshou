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
    int insertVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;

    /**
     * delete.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorInfoByID(String id) throws DataAccessException ;
    void deleteVendorInfoByBase(VendorInfo vendorInfo) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorInfo vendorInfo
     * @throws DataAccessException DataAccessException
     */
    void updateVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;
    void updateVendorInfoByState(VendorInfo vendorInfo) throws DataAccessException ;
    /**
     * find.
     * @param id id
     * @return vendorInfo
     * @throws DataAccessException DataAccessException
     */
    VendorInfo findVendorInfoByID(String pk) throws DataAccessException ;
    VendorInfo findVendorInfoByBase(VendorInfo vInfo) throws DataAccessException;
    
    /**
     * list.
     * @param vendorInfo vendorInfo
     * @return vendorInfo list
     * @throws DataAccessException DataAccessException
     */
    List<VendorInfo> listVendorInfo(VendorInfo vendorInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorInfo vendorInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorInfoCount(VendorInfo vendorInfo) throws DataAccessException ;
    Integer listVendorInfoCheckCount(VendorInfo vendorInfo) throws DataAccessException ;
}
