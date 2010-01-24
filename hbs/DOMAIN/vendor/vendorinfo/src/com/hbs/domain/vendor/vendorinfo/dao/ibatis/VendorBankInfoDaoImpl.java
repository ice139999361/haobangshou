package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

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
public class VendorBankInfoDaoImpl extends SqlMapClientDaoSupport implements BankInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorBankInfoDaoImpl.class);

    
    
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
    public void deleteBankInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteBankInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("BankInfo_deleteBankInfo", pk);
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
     * find.
     * @param id id
     * @return bankInfo
     * @throws DataAccessException DataAccessException
     */
    public BankInfo findBankInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findBankInfo(BankInfo), �������[" + pk + "]");
		}
        BankInfo bankInfo = (BankInfo) getSqlMapClientTemplate().queryForObject("BankInfo_findBankInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findBankInfo(BankInfo), ����[" + bankInfo + "]");
		}
        return bankInfo;
    }
    
    /**
     * list.
     * @param bankInfo bankInfo
     * @return bankInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBankInfo(BankInfo), �������[" + bankInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("BankInfo_listBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBankInfo(BankInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param bankInfo bankInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listBankInfoCount(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listBankInfoCount(BankInfo), �������[" + bankInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BankInfo_listBankInfoCount", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listBankInfoCount(BankInfo), ����[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.BankInfoDao#deleteBankInfo(com.hbs.domain.common.pojo.baseinfo.BankInfo)
	 */
	public void deleteBankInfo(BankInfo bankInfo) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.BankInfoDao#deleteBankInfoByID(java.lang.String)
	 */
	public void deleteBankInfoByID(String pk) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.BankInfoDao#updateBankInfoByState(com.hbs.domain.common.pojo.baseinfo.BankInfo)
	 */
	public void updateBankInfoByState(BankInfo bankInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
