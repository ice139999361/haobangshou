package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.invoice.pojo.BenefitDetail;
import com.hbs.domain.invoice.dao.BenefitDetailDao;

/**
 * BenefitDetailDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class BenefitDetailDaoImpl extends SqlMapClientDaoSupport implements BenefitDetailDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(BenefitDetailDaoImpl.class);

    
    
    /**
     * insert.
     * @param benefitDetail benefitDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public String insertBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertBenefitDetail(BenefitDetail), �������[" + benefitDetail + "]");
    	}
        
        // �õ�sequence
    	//String id = getNextId();
        // todo: set id
        
    	getSqlMapClientTemplate().insert("BenefitDetail_insertBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertBenefitDetail(BenefitDetail), ����[" + "]");
		}
    	return null;
    }

    /**
     * delete.
     * @param benefitDetail benefitDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteBenefitDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteBenefitDetail(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("BenefitDetail_deleteBenefitDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteBenefitDetail(String)");
		}
    }
    
    /**
     * update.
     * @param benefitDetail benefitDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateBenefitDetail(BenefitDetail), �������[" + benefitDetail + "]");
		}
    	getSqlMapClientTemplate().update("BenefitDetail_updateBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateBenefitDetail(BenefitDetail)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return benefitDetail
     * @throws DataAccessException DataAccessException
     */
    public BenefitDetail findBenefitDetail(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findBenefitDetail(BenefitDetail), �������[" + pk + "]");
		}
        BenefitDetail benefitDetail = (BenefitDetail) getSqlMapClientTemplate().queryForObject("BenefitDetail_findBenefitDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findBenefitDetail(BenefitDetail), ����[" + benefitDetail + "]");
		}
        return benefitDetail;
    }
    
    /**
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    public List listBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBenefitDetail(BenefitDetail), �������[" + benefitDetail + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBenefitDetail(BenefitDetail), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param benefitDetail benefitDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listBenefitDetailCount(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBenefitDetailCount(BenefitDetail), �������[" + benefitDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BenefitDetail_listBenefitDetailCount", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBenefitDetailCount(BenefitDetail), ����[" + count + "]");
		}
        return count;
    }  
}
