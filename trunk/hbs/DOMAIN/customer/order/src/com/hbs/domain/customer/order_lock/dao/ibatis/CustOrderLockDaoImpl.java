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
    		logger.debug("进入deleteCustOrderLockInfo(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().update("CustOrderLockInfo_deleteCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCustOrderLockInfo(CustOrderLockInfo), 返回");
		}
	}

	public CustOrderLockInfo findCustOrderLockInfoByBizKey(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入findCustOrderLockInfo(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
    	}

		CustOrderLockInfo ret = (CustOrderLockInfo)getSqlMapClientTemplate().queryForObject("CustOrderLockInfo_findCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开findCustOrderLockInfo(CustOrderLockInfo), 返回[" + ret + "]");
		}
		return ret;
	}

	public void insertCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteCustOrderLockInfo(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().insert("CustOrderLockInfo_insertCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteCustOrderLockInfo(CustOrderLockInfo), 返回");
		}
	}

	public List<CustOrderLockInfo> listCustOrderLockInfo(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listCustOrderLockInfo(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
		}
        List<CustOrderLockInfo> list = getSqlMapClientTemplate().queryForList("CustOrderLockInfo_listCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustOrderLockInfo(CustOrderLockInfo), 返回[" + list + "]");
		}
        return list;
	}

	public Integer listCustOrderLockInfoCount(
			CustOrderLockInfo custOrderLockInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listCustOrderLockInfoCount(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("CustOrderLockInfo_listCustOrderLockInfoCount", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listCustOrderLockInfoCount(CustOrderLockInfo), 返回[" + count + "]");
		}
        return count;
	}

	public void updateCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateCustOrderLockInfo(CustOrderLockInfo), 输入参数[" + custOrderLockInfo + "]");
    	}

    	getSqlMapClientTemplate().update("CustOrderLockInfo_updateCustOrderLockInfo", custOrderLockInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateCustOrderLockInfo(CustOrderLockInfo), 返回");
		}
	}

}
