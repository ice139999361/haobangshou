package com.hbs.domain.common.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.common.pojo.SystemConfig;


/**
 * SystemConfigDao½Ó¿Ú.
 * @author yangzj
 *
 */
public interface SystemConfigDao {
    /**
     * insert.
     * @param systemConfig systemConfig
     * @return id
     * @throws DataAccessException DataAccessException
     * @deprecated
     */
    void insertSystemConfig(SystemConfig systemConfig) throws DataAccessException ;

    /**
     * delete.
     * @param systemConfig systemConfig
     * @throws DataAccessException DataAccessException
     * @deprecated
     */
    void deleteSystemConfig(String Id) throws DataAccessException ;
    
    /**
     * update.
     * @param systemConfig systemConfig
     * @throws DataAccessException DataAccessException
     * @deprecated
     */
    void updateSystemConfig(SystemConfig systemConfig) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return systemConfig
     * @throws DataAccessException DataAccessException
     */
    SystemConfig findSystemConfig(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param systemConfig systemConfig
     * @return systemConfig list
     * @throws DataAccessException DataAccessException
     * @deprecated
     */
    List<SystemConfig> listSystemConfig(SystemConfig systemConfig) throws DataAccessException ;
    
    
}
