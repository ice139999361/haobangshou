package com.hbs.domain.customer.order_lock.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.hbs.domain.customer.order_lock.dao.CustOrderLockDao;
import com.hbs.domain.customer.order_lock.pojo.CustOrderLockInfo;

public class CustOrderLockDaoImpl extends SqlMapClientDaoSupport implements CustOrderLockDao {

	private static final Logger logger = Logger.getLogger(CustOrderLockDaoImpl.class);
	
	public void deleteCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustOrderLockInfo(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().update("CustOrderLockInfo_deleteCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustOrderLockInfo(CustOrderLockInfo), ����");
		}
	}

	public CustOrderLockInfo findCustOrderLockInfoByBizKey(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����findCustOrderLockInfo(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
    	}

		CustOrderLockInfo ret = (CustOrderLockInfo)getSqlMapClientTemplate().queryForObject("CustOrderLockInfo_findCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪findCustOrderLockInfo(CustOrderLockInfo), ����[" + ret + "]");
		}
		return ret;
	}

	public void insertCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteCustOrderLockInfo(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().insert("CustOrderLockInfo_insertCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteCustOrderLockInfo(CustOrderLockInfo), ����");
		}
	}

	public List<CustOrderLockInfo> listCustOrderLockInfo(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderLockInfo(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
		}
        List<CustOrderLockInfo> list = getSqlMapClientTemplate().queryForList("CustOrderLockInfo_listCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderLockInfo(CustOrderLockInfo), ����[" + list + "]");
		}
        return list;
	}

	public Integer listCustOrderLockInfoCount(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listCustOrderLockInfoCount(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustOrderLockInfo_listCustOrderLockInfoCount", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listCustOrderLockInfoCount(CustOrderLockInfo), ����[" + count + "]");
		}
        return count;
	}

	public void updateCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateCustOrderLockInfo(CustOrderLockInfo), �������[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().update("CustOrderLockInfo_updateCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateCustOrderLockInfo(CustOrderLockInfo), ����");
		}
	}

}
