package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.dao.baseinfo.AccountPreiodDao;

/**
 * AccountPreiodDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class VendorAccountPreiodDaoImpl extends SqlMapClientDaoSupport implements AccountPreiodDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorAccountPreiodDaoImpl.class);

    
    
    /**
     * insert.
     * @param accountPreiod accountPreiod
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertAccountPreiod(AccountPreiod), �������[" + accountPreiod + "]");
    	}
        
    	getSqlMapClientTemplate().insert("Vendor_AccountPreiod_insertAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertAccountPreiod(AccountPreiod), ����");
		}
    
    }

    /**
     * delete.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public void deleteAccountPreiod(AccountPreiod accountPreiod)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteAccountPreiod(String), �������[" + accountPreiod + "]");
		}
        getSqlMapClientTemplate().update("Vendor_AccountPreiod_deleteAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteAccountPreiod(String)");
		}
    }
    
    /**
     * update.
     * @param accountPreiod accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public void updateAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateAccountPreiod(AccountPreiod), �������[" + accountPreiod + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_AccountPreiod_updateAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateAccountPreiod(AccountPreiod)");
		}
    }
    
    
    /* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.AccountPreiodDao#updateAccountPreiodByState(com.hbs.domain.common.pojo.baseinfo.AccountPreiod)
	 */
	public void updateAccountPreiodByState(AccountPreiod accountPreiod)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateAccountPreiod(AccountPreiod), �������[" + accountPreiod + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_AccountPreiod_updateAccountPreiodByState", accountPreiod);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateAccountPreiod(AccountPreiod)");
		}
		
	}
    /**
     * find.
     * @param id id
     * @return accountPreiod
     * @throws DataAccessException DataAccessException
     */
    public AccountPreiod findAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findAccountPreiod(AccountPreiod), �������[" + accountPreiod + "]");
		}
        AccountPreiod aPreiod = (AccountPreiod) getSqlMapClientTemplate().queryForObject("Vendor_AccountPreiod_findAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findAccountPreiod(AccountPreiod), ����[" + accountPreiod + "]");
		}
        return aPreiod;
    }
    
    /**
     * list.
     * @param accountPreiod accountPreiod
     * @return accountPreiod list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<AccountPreiod> listAccountPreiod(AccountPreiod accountPreiod) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listAccountPreiod(AccountPreiod), �������[" + accountPreiod + "]");
		}
        List<AccountPreiod> list = getSqlMapClientTemplate().queryForList("Vendor_AccountPreiod_listAccountPreiod", accountPreiod);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listAccountPreiod(AccountPreiod), ����[" + list + "]");
		}
        return list;
    }  
    
   

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.AccountPreiodDao#deleteAccountPreiodByID(java.lang.String)
	 */
	public void deleteAccountPreiodByID(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteAccountPreiod(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Vendor_AccountPreiod_deleteAccountPreiodByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteAccountPreiod(String)");
		}
		
	}  
}
