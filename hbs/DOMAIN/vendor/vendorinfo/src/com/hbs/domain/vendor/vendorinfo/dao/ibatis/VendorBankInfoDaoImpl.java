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
       
    	getSqlMapClientTemplate().insert("Vendor_BankInfo_insertBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertBankInfo(BankInfo), 返回");
		}
    	
    }

    /**
     * delete.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteBankInfo(BankInfo bankInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteBankInfo(String), 输入参数[" + bankInfo + "]");
		}
        getSqlMapClientTemplate().update("Vendor_BankInfo_deleteBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteBankInfo(String)");
		}
    }
    
    
    /* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.BankInfoDao#deleteBankInfoByID(java.lang.String)
	 */
	public void deleteBankInfoByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteBankInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Vendor_BankInfo_deleteBankInfoByID", pk);
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
    	getSqlMapClientTemplate().update("Vendor_BankInfo_updateBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateBankInfo(BankInfo)");
		}
    }
    
    /**
     * update.
     * @param bankInfo bankInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateBankInfoByState(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_BankInfo_updateBankInfoByState", bankInfo);
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
    public BankInfo findBankInfo(BankInfo bankInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
		}
        BankInfo bInfo = (BankInfo) getSqlMapClientTemplate().queryForObject("Vendor_BankInfo_findBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findBankInfo(BankInfo), 返回[" + bankInfo + "]");
		}
        return bInfo;
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
        	logger.debug("进入listBankInfo(BankInfo), 输入参数[" + bankInfo + "]");
		}
        List<BankInfo> list = getSqlMapClientTemplate().queryForList("Vendor_BankInfo_listBankInfo", bankInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listBankInfo(BankInfo), 返回[" + list + "]");
		}
        return list;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.BankInfoDao#findBankInfoById(java.lang.String)
	 */
	public BankInfo findBankInfoById(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findBankInfo(BankInfo), 输入参数[" + id + "]");
		}
        BankInfo bInfo = (BankInfo) getSqlMapClientTemplate().queryForObject("Vendor_BankInfo_findBankInfoById", id);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findBankInfo(BankInfo), 返回[" + id + "]");
		}
        return bInfo;
	}  
    
    
}
