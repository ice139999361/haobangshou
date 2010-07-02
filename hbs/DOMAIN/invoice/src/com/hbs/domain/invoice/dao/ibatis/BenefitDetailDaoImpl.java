package com.hbs.domain.invoice.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.invoice.pojo.BenefitDetail;
import com.hbs.domain.invoice.dao.BenefitDetailDao;

/**
 * BenefitDetailDao接口实现类.
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
    		logger.debug("进入insertBenefitDetail(BenefitDetail), 输入参数[" + benefitDetail + "]");
    	}
        
        // 得到sequence
    	//String id = getNextId();
        // todo: set id
        
    	getSqlMapClientTemplate().insert("BenefitDetail_insertBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertBenefitDetail(BenefitDetail), 返回[" + "]");
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
    		logger.debug("进入deleteBenefitDetail(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("BenefitDetail_deleteBenefitDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteBenefitDetail(String)");
		}
    }
    
    /**
     * update.
     * @param benefitDetail benefitDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateBenefitDetail(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
    	getSqlMapClientTemplate().update("BenefitDetail_updateBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateBenefitDetail(BenefitDetail)");
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
        	logger.debug("进入findBenefitDetail(BenefitDetail), 输入参数[" + pk + "]");
		}
        BenefitDetail benefitDetail = (BenefitDetail) getSqlMapClientTemplate().queryForObject("BenefitDetail_findBenefitDetail", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findBenefitDetail(BenefitDetail), 返回[" + benefitDetail + "]");
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
        	logger.debug("进入listBenefitDetail(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitDetail", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBenefitDetail(BenefitDetail), 返回[" + list + "]");
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
        	logger.debug("进入listBenefitDetailCount(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BenefitDetail_listBenefitDetailCount", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBenefitDetailCount(BenefitDetail), 返回[" + count + "]");
		}
        return count;
    }  
}
