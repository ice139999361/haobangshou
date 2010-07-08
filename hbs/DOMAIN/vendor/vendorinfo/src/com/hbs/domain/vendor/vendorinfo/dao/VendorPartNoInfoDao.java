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
    int insertVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;

    /**
     * delete.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteVendorPartNoInfoByID(String id) throws DataAccessException ;
    void deleteVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    
    /**
     * update.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    void updateVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    void updateVendorPartNoInfoByState(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return vendorPartNoInfo
     * @throws DataAccessException DataAccessException
     */
    VendorPartNoInfo findVendorPartNoInfoByID(String id) throws DataAccessException ;
    VendorPartNoInfo findVendorPartNoInfoByBizKey(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    
    /**
     * list.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return vendorPartNoInfo list
     * @throws DataAccessException DataAccessException
     */
    List<VendorPartNoInfo> listVendorPartNoInfo(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param vendorPartNoInfo vendorPartNoInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listVendorPartNoInfoCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
    Integer listVendorPartNoInfoCheckCount(VendorPartNoInfo vendorPartNoInfo) throws DataAccessException ;
}
