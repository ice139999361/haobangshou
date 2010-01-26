package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;


/**
 * ContactInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface ContactInfoDao {
    /**
     * insert.
     * @param contactInfo contactInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertContactInfo(ContactInfo contactInfo) throws DataAccessException ;

    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteContactInfoByID(String id) throws DataAccessException ;
    
    /**
     * delete.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    void deleteContactInfo(ContactInfo contactInfo) throws DataAccessException ;
    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    void updateContactInfo(ContactInfo contactInfo) throws DataAccessException ;

    
    /**
     * update.
     * @param contactInfo contactInfo
     * @throws DataAccessException DataAccessException
     */
    void updateContactInfoByState(ContactInfo contactInfo) throws DataAccessException ;
    /**
     * find.
     * @param id id
     * @return contactInfo
     * @throws DataAccessException DataAccessException
     */
    ContactInfo findContactInfo(ContactInfo contactInfo) throws DataAccessException ;
    ContactInfo findContactInfoById(String id) throws DataAccessException ;
    /**
     * list.
     * @param contactInfo contactInfo
     * @return contactInfo list
     * @throws DataAccessException DataAccessException
     */
    List<ContactInfo> listContactInfo(ContactInfo contactInfo) throws DataAccessException ;
    
   
}
