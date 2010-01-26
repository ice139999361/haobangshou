package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.dao.baseinfo.ContactInfoDao;

/**
 * ContactInfoDao接口实现类.
 * @author hbs
 *
 */
public class VendorContactInfoDaoImpl extends SqlMapClientDaoSupport implements ContactInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(VendorContactInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param contactInfo contactInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertContactInfo(ContactInfo), 输入参数[" + contactInfo + "]");
    	}
    	getSqlMapClientTemplate().insert("Vendor_ContactInfo_insertContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertContactInfo(ContactInfo), 返回[]");
		}
    	
    }

    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteContactInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteContactInfo(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Vendor_ContactInfo_deleteContactInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteContactInfo(String)");
		}
    }
    
    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteContactInfo(ContactInfo contactInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteContactInfo(String), 输入参数[" + contactInfo + "]");
		}
        getSqlMapClientTemplate().update("Vendor_ContactInfo_deleteContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteContactInfo(String)");
		}
    }
    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateContactInfo(ContactInfo), 输入参数[" + contactInfo + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_ContactInfo_updateContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateContactInfo(ContactInfo)");
		}
    }
    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateContactInfoByState(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateContactInfo(ContactInfo), 输入参数[" + contactInfo + "]");
		}
    	getSqlMapClientTemplate().update("Vendor_ContactInfo_updateContactInfoByState", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateContactInfo(ContactInfo)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return contactInfo
     * @throws DataAccessException DataAccessException
     */
    public ContactInfo findContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findContactInfo(ContactInfo), 输入参数[" + contactInfo + "]");
		}
        ContactInfo cInfo = (ContactInfo) getSqlMapClientTemplate().queryForObject("Vendor_ContactInfo_findContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findContactInfo(ContactInfo), 返回[" + contactInfo + "]");
		}
        return cInfo;
    }
    
    /**
     * list.
     * @param contactInfo contactInfo
     * @return contactInfo list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<ContactInfo> listContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listContactInfo(ContactInfo), 输入参数[" + contactInfo + "]");
		}
        List<ContactInfo> list = getSqlMapClientTemplate().queryForList("Vendor_ContactInfo_listContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listContactInfo(ContactInfo), 返回[" + list + "]");
		}
        return list;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.ContactInfoDao#findContactInfoById(java.lang.String)
	 */
	public ContactInfo findContactInfoById(String id)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findContactInfo(ContactInfo), 输入参数[" + id + "]");
		}
        ContactInfo cInfo = (ContactInfo) getSqlMapClientTemplate().queryForObject("Vendor_ContactInfo_findContactInfoById", id);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findContactInfo(ContactInfo), 返回[" + id + "]");
		}
        return cInfo;
	}      
    
}
