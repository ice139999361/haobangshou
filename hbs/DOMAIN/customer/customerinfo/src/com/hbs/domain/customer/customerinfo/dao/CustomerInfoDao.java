package com.hbs.domain.customer.customerinfo.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.customer.customerinfo.pojo.CustomerInfo;


/**
 * CustomerInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface CustomerInfoDao {
    /**
     * insert.
     * @param customerInfo customerInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    int insertCustomerInfo(CustomerInfo customerInfo) throws DataAccessException ;

    /**
     * delete.
     * @param customerInfo customerInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteCustomerInfoByID(String id) throws DataAccessException ;
    void deleteCustomerInfoByBase(CustomerInfo customerInfo) throws DataAccessException ;
    
    /**
     * update.
     * @param customerInfo customerInfo
     * @throws DataAccessException DataAccessException
     */
    void updateCustomerInfo(CustomerInfo customerInfo) throws DataAccessException ;
    void updateCustomerInfoByState(CustomerInfo customerInfo) throws DataAccessException;

    /**
     * find.
     * @param id id
     * @return customerInfo
     * @throws DataAccessException DataAccessException
     */
    CustomerInfo findCustomerInfoByID(String id) throws DataAccessException ;
    CustomerInfo findCustomerInfoByBase(CustomerInfo customerInfo) throws DataAccessException ;
    /**
     * list.
     * @param customerInfo customerInfo
     * @return customerInfo list
     * @throws DataAccessException DataAccessException
     */
    List<CustomerInfo> listCustomerInfo(CustomerInfo customerInfo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param customerInfo customerInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listCustomerInfoCount(CustomerInfo customerInfo) throws DataAccessException ;
    Integer listCustomerInfoCheckCount(CustomerInfo customerInfo) throws DataAccessException ;
}
