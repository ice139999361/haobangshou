package com.hbs.domain.common.dao;


import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.SysSequence;


/**
 * SysSequenceDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface SysSequenceDao {
    
    
    /**
     * update.
     * @param sysSequence sysSequence
     * @throws DataAccessException DataAccessException
     */
    void updateSysSequence(SysSequence sysSequence) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return sysSequence
     * @throws DataAccessException DataAccessException
     */
    SysSequence findSysSequence(String id) throws DataAccessException ;
    
    
}
