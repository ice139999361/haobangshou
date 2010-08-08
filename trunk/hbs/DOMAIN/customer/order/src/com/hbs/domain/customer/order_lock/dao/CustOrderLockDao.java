package com.hbs.domain.customer.order_lock.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.hbs.domain.customer.order_lock.pojo.CustOrderLockInfo;

/**
 * @author xyf
 *
 */
public interface CustOrderLockDao {
	void insertCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;
	void deleteCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;
	void updateCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;
	CustOrderLockInfo findCustOrderLockInfoByBizKey(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;
	List<CustOrderLockInfo> listCustOrderLockInfo(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;
	Integer listCustOrderLockInfoCount(CustOrderLockInfo custOrderLockInfo) throws DataAccessException ;	
}
