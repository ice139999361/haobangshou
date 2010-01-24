package com.hbs.domain.product.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.product.pojo.CompanyPartNo;


/**
 * CompanyPartNoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface CompanyPartNoDao {
    /**
     * insert.
     * @param companyPartNo companyPartNo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException ;

    /**
     * delete.
     * @param companyPartNo companyPartNo
     * @throws DataAccessException DataAccessException
     */
    void deleteCompanyPartNo(String partNo) throws DataAccessException ;
    
    /**
     * update.
     * @param companyPartNo companyPartNo
     * @throws DataAccessException DataAccessException
     */
    void updateCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return companyPartNo
     * @throws DataAccessException DataAccessException
     */
    CompanyPartNo findCompanyPartNo(String partNo) throws DataAccessException ;
    
    /**
     * list.
     * @param companyPartNo companyPartNo
     * @return companyPartNo list
     * @throws DataAccessException DataAccessException
     */
    List<CompanyPartNo> listCompanyPartNo(CompanyPartNo companyPartNo) throws DataAccessException ;
    
    /**
     * listCount.
     * @param companyPartNo companyPartNo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listCompanyPartNoCount(CompanyPartNo companyPartNo) throws DataAccessException ;
}
