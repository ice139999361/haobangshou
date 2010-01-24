package com.hbs.domain.product.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.product.pojo.ProductClass;


/**
 * ProductClassDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface ProductClassDao {
    /**
     * insert.
     * @param productClass productClass
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertProductClass(ProductClass productClass) throws DataAccessException ;

    /**
     * delete.
     * @param productClass productClass
     * @throws DataAccessException DataAccessException
     */
    void deleteProductClass(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param productClass productClass
     * @throws DataAccessException DataAccessException
     */
    void updateProductClass(ProductClass productClass) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return productClass
     * @throws DataAccessException DataAccessException
     */
    ProductClass findProductClass(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param productClass productClass
     * @return productClass list
     * @throws DataAccessException DataAccessException
     */
    List<ProductClass> listProductClass(ProductClass productClass) throws DataAccessException ;
    
    /**
     * listCount.
     * @param productClass productClass
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listProductClassCount(ProductClass productClass) throws DataAccessException ;
}
