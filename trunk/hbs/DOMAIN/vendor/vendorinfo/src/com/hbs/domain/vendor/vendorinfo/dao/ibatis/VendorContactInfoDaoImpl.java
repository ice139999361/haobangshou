package com.hbs.domain.vendor.vendorinfo.dao.ibatis;

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
    		logger.debug("����insertContactInfo(ContactInfo), �������[" + contactInfo + "]");
    	}
        
        
    	getSqlMapClientTemplate().insert("ContactInfo_insertContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertContactInfo(ContactInfo), ����[]");
		}
    	
    }

    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    public void deleteContactInfo(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteContactInfo(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("ContactInfo_deleteContactInfo", pk);
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
     * find.
     * @param id id
     * @return contactInfo
     * @throws DataAccessException DataAccessException
     */
    public ContactInfo findContactInfo(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findContactInfo(ContactInfo), �������[" + pk + "]");
		}
        ContactInfo contactInfo = (ContactInfo) getSqlMapClientTemplate().queryForObject("ContactInfo_findContactInfo", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findContactInfo(ContactInfo), ����[" + contactInfo + "]");
		}
        return contactInfo;
    }
    
    /**
     * list.
     * @param contactInfo contactInfo
     * @return contactInfo list
     * @throws DataAccessException DataAccessException
     */
    public List listContactInfo(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listContactInfo(ContactInfo), �������[" + contactInfo + "]");
		}
        List list = getSqlMapClientTemplate().queryForList("ContactInfo_listContactInfo", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listContactInfo(ContactInfo), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param contactInfo contactInfo
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listContactInfoCount(ContactInfo contactInfo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listContactInfoCount(ContactInfo), �������[" + contactInfo + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("ContactInfo_listContactInfoCount", contactInfo);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listContactInfoCount(ContactInfo), ����[" + count + "]");
		}
        return count;
    }

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.ContactInfoDao#deleteContactInfo(com.hbs.domain.common.pojo.baseinfo.ContactInfo)
	 */
	public void deleteContactInfo(ContactInfo contactInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.ContactInfoDao#deleteContactInfoByID(java.lang.String)
	 */
	public void deleteContactInfoByID(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hbs.domain.common.dao.baseinfo.ContactInfoDao#updateContactInfoByState(com.hbs.domain.common.pojo.baseinfo.ContactInfo)
	 */
	public void updateContactInfoByState(ContactInfo contactInfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}  
}
