package com.hbs.domain.common.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.common.pojo.SystemConfig;
import com.hbs.domain.common.dao.SystemConfigDao;

/**
 * SystemConfigDao�ӿ�ʵ����.
 * @author yangzj
 *
 */
public class SystemConfigDaoImpl extends SqlMapClientDaoSupport implements SystemConfigDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(SystemConfigDaoImpl.class);

   
    /**
     * insert.
     * @param systemConfig systemConfig
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertSystemConfig(SystemConfig systemConfig) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertSystemConfig(SystemConfig), �������[" + systemConfig + "]");
    	}
    	getSqlMapClientTemplate().insert("SystemConfig_insertSystemConfig", systemConfig);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertSystemConfig(SystemConfig), ����");
		}
    	
    }

    /**
     * delete.
     * @param systemConfig systemConfig
     * @throws DataAccessException DataAccessException
     */
    public void deleteSystemConfig(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteSystemConfig(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("SystemConfig_deleteSystemConfig", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteSystemConfig(String)");
		}
    }
    
    /**
     * update.
     * @param systemConfig systemConfig
     * @throws DataAccessException DataAccessException
     */
    public void updateSystemConfig(SystemConfig systemConfig) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateSystemConfig(SystemConfig), �������[" + systemConfig + "]");
		}
    	getSqlMapClientTemplate().update("SystemConfig_updateSystemConfig", systemConfig);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateSystemConfig(SystemConfig)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return systemConfig
     * @throws DataAccessException DataAccessException
     */
    public SystemConfig findSystemConfig(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findSystemConfig(SystemConfig), �������[" + pk + "]");
		}
        SystemConfig systemConfig = (SystemConfig) getSqlMapClientTemplate().queryForObject("SystemConfig_findSystemConfig", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findSystemConfig(SystemConfig), ����[" + systemConfig + "]");
		}
        return systemConfig;
    }
    
    /**
     * list.
     * @param systemConfig systemConfig
     * @return systemConfig list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<SystemConfig> listSystemConfig(SystemConfig systemConfig) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listSystemConfig(SystemConfig), �������[" + systemConfig + "]");
		}
        List<SystemConfig> list = getSqlMapClientTemplate().queryForList("SystemConfig_listSystemConfig", systemConfig);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listSystemConfig(SystemConfig), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param systemConfig systemConfig
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listSystemConfigCount(SystemConfig systemConfig) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listSystemConfigCount(SystemConfig), �������[" + systemConfig + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("SystemConfig_listSystemConfigCount", systemConfig);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listSystemConfigCount(SystemConfig), ����[" + count + "]");
		}
        return count;
    }  
}
