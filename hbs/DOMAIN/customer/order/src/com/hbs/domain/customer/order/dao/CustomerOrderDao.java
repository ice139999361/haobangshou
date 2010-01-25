package com.hbs.domain.customer.order.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.customer.order.pojo.CustomerOrder;


/**
 * CustomerOrderDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface CustomerOrderDao {
    /**
     * insert.
     * @param customerOrder customerOrder
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertCustomerOrder(CustomerOrder customerOrder) throws DataAccessException ;

    /**
     * delete.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    void deleteCustomerOrder(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param customerOrder customerOrder
     * @throws DataAccessException DataAccessException
     */
    void updateCustomerOrder(CustomerOrder customerOrder) throws DataAccessException ;
    void updateCustomerOrderByActiveState(CustomerOrder customerOrder) throws DataAccessException ;
    void updateCustomerOrderByState(CustomerOrder customerOrder) throws DataAccessException ;
    /**
     * find.
     * @param id id
     * @return customerOrder
     * @throws DataAccessException DataAccessException
     */
    CustomerOrder findCustomerOrder(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param customerOrder customerOrder
     * @return customerOrder list
     * @throws DataAccessException DataAccessException
     */
    List<CustomerOrder> listCustomerOrder(CustomerOrder customerOrder) throws DataAccessException ;
    
    /**
     * listCount.
     * @param customerOrder customerOrder
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listCustomerOrderCount(CustomerOrder customerOrder) throws DataAccessException ;
}
