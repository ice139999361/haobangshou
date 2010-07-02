package com.hbs.domain.invoice.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.invoice.pojo.BenefitDetail;


/**
 * BenefitDetailDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface BenefitDetailDao {
    /**
     * insert.
     * @param benefitDetail benefitDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    String insertBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException ;

    /**
     * delete.
     * @param benefitDetail benefitDetail
     * @throws DataAccessException DataAccessException
     */
    void deleteBenefitDetail(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param benefitDetail benefitDetail
     * @throws DataAccessException DataAccessException
     */
    void updateBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return benefitDetail
     * @throws DataAccessException DataAccessException
     */
    BenefitDetail findBenefitDetail(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    List listBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param benefitDetail benefitDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listBenefitDetailCount(BenefitDetail benefitDetail) throws DataAccessException ;
}
