package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;


/**
 * PrePaidInfoDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface PrePaidInfoDao {
    /**
     * insert.
     * @param prePaidInfo prePaidInfo
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException ;

    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    void deletePrePaidInfoByID(String id) throws DataAccessException ;
    
    /**
     * delete.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    void deletePrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException ;
    
    /**
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    void updatePrePaidInfoByState(PrePaidInfo prePaidInfo) throws DataAccessException ;

    
    /**
     * update.
     * @param prePaidInfo prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    void updatePrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return prePaidInfo
     * @throws DataAccessException DataAccessException
     */
    PrePaidInfo findPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException ;
    
    /**
     * list.
     * @param prePaidInfo prePaidInfo
     * @return prePaidInfo list
     * @throws DataAccessException DataAccessException
     */
    List<PrePaidInfo> listPrePaidInfo(PrePaidInfo prePaidInfo) throws DataAccessException ;
    
    
}
