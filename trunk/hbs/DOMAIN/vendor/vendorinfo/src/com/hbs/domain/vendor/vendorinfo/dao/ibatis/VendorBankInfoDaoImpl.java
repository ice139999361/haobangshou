package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.dao.baseinfo.BankInfoDao;

/**
 * BankInfoDao接口实现类.
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
    		logger.debug("进入insertBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
    	}
       
    	getSqlMapClientTemplate().insert("BankInfo_insertBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertBankInfo(BankInfo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteBankInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteBankInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("BankInfo_deleteBankInfo", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteBankInfo(String)");
		}
    }
    
    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
		}
    	getSqlMapClientTemplate().update("BankInfo_updateBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateBankInfo(BankInfo)");
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
        	logger.debug("进入findBankInfo(BankInfo), 输入参数[" + pk + "]");
		}
        BankInfo bankInfo = (BankInfo) getSqlMapClientTemplate().queryForObject("BankInfo_findBankInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findBankInfo(BankInfo), 返回[" + bankInfo + "]");
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
        	logger.debug("进入listBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("BankInfo_listBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBankInfo(BankInfo), 返回[" + list + "]");
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
        	logger.debug("进入listBankInfoCount(BankInfo), 输入参数[" + bankInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("BankInfo_listBankInfoCount", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBankInfoCount(BankInfo), 返回[" + count + "]");
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
