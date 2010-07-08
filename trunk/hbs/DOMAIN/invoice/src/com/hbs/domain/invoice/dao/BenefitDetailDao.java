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
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    List<BenefitDetail> listBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param benefitDetail benefitDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listBenefitDetailCount(BenefitDetail benefitDetail) throws DataAccessException ;
    
    /**
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    List<BenefitDetail> listBenefitTotal(BenefitDetail benefitDetail) throws DataAccessException ;
    
    /**
     * listCount.
     * @param benefitDetail benefitDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listBenefitTotalCount(BenefitDetail benefitDetail) throws DataAccessException ;
}
