package com.hbs.domain.customer.order.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;


/**
 * CustOrderDetailDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface CustOrderDetailDao {
    /**
     * insert.
     * @param custOrderDetail custOrderDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException ;

    /**
     * delete.
     * @param custOrderDetail custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    void deleteCustOrderDetail(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param custOrderDetail custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    void updateCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException ;
    void updateCustOrderDetailByActiveState(CustOrderDetail custOrderDetail) throws DataAccessException ;
    void updateCustOrderDetailByState(CustOrderDetail custOrderDetail) throws DataAccessException;
    void updateCustOrderDetailAmount(CustOrderDetail custOrderDetail) throws DataAccessException;
    /**
     * find.
     * @param id id
     * @return custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    CustOrderDetail findCustOrderDetailById(String id) throws DataAccessException ;
    CustOrderDetail findCustOrderDetailByBizKey(CustOrderDetail orderDetail) throws DataAccessException ;
    
    /**
     * list.
     * @param custOrderDetail custOrderDetail
     * @return custOrderDetail list
     * @throws DataAccessException DataAccessException
     */
    List<CustOrderDetail> listCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException ;
    List<CustOrderDetail> listtotalMoneyByPeriod(CustOrderDetail custOrderDetail) throws DataAccessException; 
    List<CustOrderDetail> listCustOrderDetailState(CustOrderDetail custOrderDetail) throws DataAccessException;
    /**
     * listCount.
     * @param custOrderDetail custOrderDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listCustOrderDetailCount(CustOrderDetail custOrderDetail) throws DataAccessException ;
}
