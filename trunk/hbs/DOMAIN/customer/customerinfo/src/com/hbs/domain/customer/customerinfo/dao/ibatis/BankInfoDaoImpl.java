package com.hbs.domain.customer.customerinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.dao.baseinfo.BankInfoDao;

/**
 * BankInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class BankInfoDaoImpl extends SqlMapClientDaoSupport implements BankInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(BankInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param bankInfo bankInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertBankInfo(BankInfo), �������[" + bankInfo + "]");
    	}
        
       
        
    	getSqlMapClientTemplate().insert("BankInfo_insertBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertBankInfo(BankInfo), ����");
		}
    	
    }

    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteBankInfo(BankInfo bankInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteBankInfo(String), �������[" + bankInfo + "]");
		}
        getSqlMapClientTemplate().update("BankInfo_deleteBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteBankInfo(String)");
		}
    }
    
    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteBankInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteBankInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("BankInfo_deleteBankInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteBankInfo(String)");
		}
    }
    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateBankInfo(BankInfo), �������[" + bankInfo + "]");
		}
    	getSqlMapClientTemplate().update("BankInfo_updateBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateBankInfo(BankInfo)");
		}
    }
    
    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateBankInfoByState(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateBankInfo(BankInfo), �������[" + bankInfo + "]");
		}
    	getSqlMapClientTemplate().update("BankInfo_updateBankInfoByState", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateBankInfo(BankInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return bankInfo
     * @throws DataAccessException DataAccessException
     */
    public BankInfo findBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findBankInfo(BankInfo), �������[" + bankInfo + "]");
		}
        BankInfo tempBankInfo = (BankInfo) getSqlMapClientTemplate().queryForObject("BankInfo_findBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findBankInfo(BankInfo), ����[" + tempBankInfo + "]");
		}
        return tempBankInfo;
    }
    
    /**
     * list.
     * @param bankInfo bankInfo
     * @return bankInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<BankInfo> listBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBankInfo(BankInfo), �������[" + bankInfo + "]");
		}
        List<BankInfo> list = getSqlMapClientTemplate().queryForList("BankInfo_listBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBankInfo(BankInfo), ����[" + list + "]");
		}
        return list;
    }  
    
   
}
