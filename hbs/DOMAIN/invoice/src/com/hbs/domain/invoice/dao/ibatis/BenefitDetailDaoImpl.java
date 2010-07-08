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
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<BenefitDetail> listBenefitDetail(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBenefitDetail(BenefitDetail), �������[" + benefitDetail + "]");
		}
        List<BenefitDetail> list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitDetail", benefitDetail);
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
    
    
    
    /**
     * list.
     * @param benefitDetail benefitDetail
     * @return benefitDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<BenefitDetail> listBenefitTotal(BenefitDetail benefitDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBenefitTotal(BenefitDetail), �������[" + benefitDetail + "]");
		}
        List<BenefitDetail> list = getSqlMapClientTemplate().queryForList("BenefitDetail_listBenefitTotal", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBenefitTotal(BenefitDetail), ����[" + list + "]");
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
        	logger.debug("����listBenefitTotalCount(BenefitDetail), �������[" + benefitDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BenefitDetail_listBenefitTotalCount", benefitDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBenefitTotalCount(BenefitDetail), ����[" + count + "]");
		}
        return count;
    } 
}
