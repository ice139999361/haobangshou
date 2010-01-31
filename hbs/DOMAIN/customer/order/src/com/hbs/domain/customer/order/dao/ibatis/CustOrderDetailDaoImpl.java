package com.hbs.domain.customer.order.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.customer.order.pojo.CustOrderDetail;
import com.hbs.domain.customer.order.dao.CustOrderDetailDao;

/**
 * CustOrderDetailDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class CustOrderDetailDaoImpl extends SqlMapClientDaoSupport implements CustOrderDetailDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(CustOrderDetailDaoImpl.class);

    
    
    /**
     * insert.
     * @param custOrderDetail custOrderDetail
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
    	}
        
    	getSqlMapClientTemplate().insert("CustOrderDetail_insertCustOrderDetail", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertCustOrderDetail(CustOrderDetail), ����");
		}
    	
    }

    /**
     * delete.
     * @param custOrderDetail custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public void deleteCustOrderDetail(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustOrderDetail(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("CustOrderDetail_deleteCustOrderDetail", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustOrderDetail(String)");
		}
    }
    
    /**
     * update.
     * @param custOrderDetail custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public void updateCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("CustOrderDetail_updateCustOrderDetail", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    }
    public void updateCustOrderDetailByActiveState(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("CustOrderDetail_updateCustOrderDetailByActiveState", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    }
    
    public void updateCustOrderDetailByState(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("CustOrderDetail_updateCustOrderDetailByState", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    }
    
    public void updateCustOrderDetailAmount(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("CustOrderDetail_updateCustOrderDetailAmount", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    } 
    
    public void updateCustOrderDetailByRltPoNo(CustOrderDetail custOrderDetail) throws DataAccessException{
    	if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
    	getSqlMapClientTemplate().update("CustOrderDetail_updateCustOrderDetailByRltPoNo", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return custOrderDetail
     * @throws DataAccessException DataAccessException
     */
    public CustOrderDetail findCustOrderDetailById(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustOrderDetail(CustOrderDetail), �������[" + pk + "]");
		}
        CustOrderDetail custOrderDetail = (CustOrderDetail) getSqlMapClientTemplate().queryForObject("CustOrderDetail_findCustOrderDetailById", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustOrderDetail(CustOrderDetail), ����[" + custOrderDetail + "]");
		}
        return custOrderDetail;
    }
    
    public CustOrderDetail findCustOrderDetailByBizKey(CustOrderDetail orderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findCustOrderDetail(CustOrderDetail), �������[" + orderDetail + "]");
		}
        CustOrderDetail custOrderDetail = (CustOrderDetail) getSqlMapClientTemplate().queryForObject("CustOrderDetail_findCustOrderDetailByBizKey", orderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findCustOrderDetail(CustOrderDetail), ����[" + custOrderDetail + "]");
		}
        return custOrderDetail;
    }
    
    /**
     * list.
     * @param custOrderDetail custOrderDetail
     * @return custOrderDetail list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<CustOrderDetail> listCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
        List<CustOrderDetail> list = getSqlMapClientTemplate().queryForList("CustOrderDetail_listCustOrderDetail", custOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderDetail(CustOrderDetail), ����[" + list + "]");
		}
        return list;
    } 
    
    
    @SuppressWarnings("unchecked")
	public List<CustOrderDetail> listtotalMoneyByPeriod(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
        List<CustOrderDetail> list = getSqlMapClientTemplate().queryForList("CustOrderDetail_listtotalMoneyByPeriod", custOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderDetail(CustOrderDetail), ����[" + list + "]");
		}
        return list;
    } 
    
    @SuppressWarnings("unchecked")
	public List<CustOrderDetail> listCustOrderDetailState(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
        List<CustOrderDetail> list = getSqlMapClientTemplate().queryForList("CustOrderDetail_listCustOrderDetailState", custOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderDetail(CustOrderDetail), ����[" + list + "]");
		}
        return list;
    }
    
    
    /**
     * listCount.
     * @param custOrderDetail custOrderDetail
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listCustOrderDetailCount(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderDetailCount(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustOrderDetail_listCustOrderDetailCount", custOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderDetailCount(CustOrderDetail), ����[" + count + "]");
		}
        return count;
    }  
}
