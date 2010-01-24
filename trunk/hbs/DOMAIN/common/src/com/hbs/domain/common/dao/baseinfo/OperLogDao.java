package com.hbs.domain.common.dao.baseinfo;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.baseinfo.OperLog;


/**
 * OperLogDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface OperLogDao {
    /**
     * insert.
     * @param operLog operLog
     * @return id
     * @throws DataAccessException DataAccessException
     */
    void insertOperLog(OperLog operLog) throws DataAccessException ;

   
    /**
     * find.
     * @param id id
     * @return operLog
     * @throws DataAccessException DataAccessException
     */
    OperLog findOperLog(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param String operLey
     * @return operLog list
     * @throws DataAccessException DataAccessException
     */
    List<OperLog> listOperLog(String operLey) throws DataAccessException ;
}
