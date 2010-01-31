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
public class CustOrderDetailHisDaoImpl extends SqlMapClientDaoSupport implements CustOrderDetailDao
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
        
        
    	getSqlMapClientTemplate().insert("CustOrderDetailHis_insertCustOrderDetail", custOrderDetail);
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
        getSqlMapClientTemplate().update("CustOrderDetailHis_deleteCustOrderDetail", pk);
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
    	getSqlMapClientTemplate().update("CustOrderDetailHis_updateCustOrderDetail", custOrderDetail);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderDetail(CustOrderDetail)");
		}
    }
    
    
    
    /**
     * list.
     * @param custOrderDetail custOrderDetail
     * @return custOrderDetail list
     * @throws DataAccessException DataAccessException
     */
    public List listCustOrderDetail(CustOrderDetail custOrderDetail) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderDetail(CustOrderDetail), �������[" + custOrderDetail + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("CustOrderDetailHis_listCustOrderDetail", custOrderDetail);
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
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustOrderDetailHis_listCustOrderDetailCount", custOrderDetail);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderDetailCount(CustOrderDetail), ����[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#updateCustOrderDetailByActiveState(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public void updateCustOrderDetailByActiveState(
			CustOrderDetail custOrderDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#listtotalMoneyByPeriod(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public List<CustOrderDetail> listtotalMoneyByPeriod(
			CustOrderDetail custOrderDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#updateCustOrderDetailByState(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public void updateCustOrderDetailByState(CustOrderDetail custOrderDetail)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#listCustOrderDetailState(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public List<CustOrderDetail> listCustOrderDetailState(
			CustOrderDetail custOrderDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#updateCustOrderDetailAmount(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public void updateCustOrderDetailAmount(CustOrderDetail custOrderDetail)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#findCustOrderDetailByBizKey(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public CustOrderDetail findCustOrderDetailByBizKey(
			CustOrderDetail orderDetail) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#findCustOrderDetailById(java.lang.String)
	 */
	public CustOrderDetail findCustOrderDetailById(String id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.customer.order.dao.CustOrderDetailDao#updateCustOrderDetailByRltPoNo(com.hbs.domain.customer.order.pojo.CustOrderDetail)
	 */
	public void updateCustOrderDetailByRltPoNo(CustOrderDetail custOrderDetail)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
