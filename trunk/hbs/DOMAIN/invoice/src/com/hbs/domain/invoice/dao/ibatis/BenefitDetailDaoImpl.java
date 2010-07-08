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
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<BenefitDetail> listBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listBenefitDetail(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
        List<BenefitDetail> list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitDetail", benefitDetail);
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
    
    
    
    /**
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<BenefitDetail> listBenefitTotal(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listBenefitTotal(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
        List<BenefitDetail> list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitTotal", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBenefitTotal(BenefitDetail), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param benefitDetail benefitDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listBenefitTotalCount(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listBenefitTotalCount(BenefitDetail), 输入参数[" + benefitDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BenefitDetail_listBenefitTotalCount", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBenefitTotalCount(BenefitDetail), 返回[" + count + "]");
		}
        return count;
    } 
}
