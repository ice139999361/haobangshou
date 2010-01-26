package com.hbs.domain.customer.customerinfo.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.dao.baseinfo.ContactInfoDao;

/**
 * ContactInfoDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class ContactInfoDaoImpl extends SqlMapClientDaoSupport implements ContactInfoDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(ContactInfoDaoImpl.class);

    
    
    /**
     * insert.
     * @param contactInfo contactInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertContactInfo(ContactInfo), �������[" + contactInfo + "]");
    	}
    	getSqlMapClientTemplate().insert("ContactInfo_insertContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertContactInfo(ContactInfo), ����");
		}
    	
    }

    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteContactInfoByID(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteContactInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("ContactInfo_deleteContactInfoByID", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteContactInfo(String)");
		}
    }
    
    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteContactInfo(ContactInfo contactInfo)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteContactInfo(String), �������[" + contactInfo + "]");
		}
        getSqlMapClientTemplate().update("ContactInfo_deleteContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteContactInfo(String)");
		}
    }
    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateContactInfo(ContactInfo), �������[" + contactInfo + "]");
		}
    	getSqlMapClientTemplate().update("ContactInfo_updateContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateContactInfo(ContactInfo)");
		}
    }
    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void updateContactInfoByState(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateContactInfo(ContactInfo), �������[" + contactInfo + "]");
		}
    	getSqlMapClientTemplate().update("ContactInfo_updateContactInfoByState", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateContactInfo(ContactInfo)");
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
        	logger.debug("����findContactInfo(ContactInfo), �������[" + contactInfo + "]");
		}
        ContactInfo cInfo = (ContactInfo) getSqlMapClientTemplate().queryForObject("ContactInfo_findContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findContactInfo(ContactInfo), ����[" + contactInfo + "]");
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
        	logger.debug("����listContactInfo(ContactInfo), �������[" + contactInfo + "]");
		}
        List<ContactInfo> list = getSqlMapClientTemplate().queryForList("ContactInfo_listContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listContactInfo(ContactInfo), ����[" + list + "]");
		}
        return list;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.ContactInfoDao#findContactInfoById(java.lang.String)
	 */
	public ContactInfo findContactInfoById(String id)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findContactInfo(ContactInfo), �������[" + id + "]");
		}
        ContactInfo cInfo = (ContactInfo) getSqlMapClientTemplate().queryForObject("ContactInfo_findContactInfoById", id);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findContactInfo(ContactInfo), ����[" + id + "]");
		}
        return cInfo;
	}  
    
  
}
