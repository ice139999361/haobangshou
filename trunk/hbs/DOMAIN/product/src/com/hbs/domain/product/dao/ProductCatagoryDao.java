package com.hbs.domain.product.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.product.pojo.ProductCatagory;


/**
 * ProductCatagoryDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface ProductCatagoryDao {
    /**
     * insert.
     * @param productCatagory productCatagory
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertProductCatagory(ProductCatagory productCatagory) throws DataAccessException ;

    /**
     * delete.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    void deleteProductCatagory(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param productCatagory productCatagory
     * @throws DataAccessException DataAccessException
     */
    void updateProductCatagory(ProductCatagory productCatagory) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return productCatagory
     * @throws DataAccessException DataAccessException
     */
    ProductCatagory findProductCatagory(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param productCatagory productCatagory
     * @return productCatagory list
     * @throws DataAccessException DataAccessException
     */
    List<ProductCatagory> listProductCatagory(ProductCatagory productCatagory) throws DataAccessException ;
    
    /**
     * listCount.
     * @param productCatagory productCatagory
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listProductCatagoryCount(ProductCatagory productCatagory) throws DataAccessException ;
}
