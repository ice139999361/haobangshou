package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.RoleResource;
import com.hbs.domain.auth.dao.RoleResourceDao;

/**
 * RoleResourceDao�ӿ�ʵ����.
 * @author hbs
 *
 */
public class RoleResourceDaoImpl extends SqlMapClientDaoSupport implements RoleResourceDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(RoleResourceDaoImpl.class);

    
    
    /**
     * insert.
     * @param roleResource roleResource
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public void insertRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����insertRoleResource(RoleResource), �������[" + roleResource + "]");
    	}
        
    	getSqlMapClientTemplate().insert("RoleResource_insertRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertRoleResource(RoleResource), ����");
		}
    	
    }

    /**
     * delete.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    public void deleteRoleResource(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����deleteRoleResource(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("RoleResource_deleteRoleResource", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteRoleResource(String)");
		}
    }
    
    /**
     * update.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    public void updateRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateRoleResource(RoleResource), �������[" + roleResource + "]");
		}
    	getSqlMapClientTemplate().update("RoleResource_updateRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateRoleResource(RoleResource)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return roleResource
     * @throws DataAccessException DataAccessException
     */
    public RoleResource findRoleResource(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����findRoleResource(RoleResource), �������[" + pk + "]");
		}
        RoleResource roleResource = (RoleResource) getSqlMapClientTemplate().queryForObject("RoleResource_findRoleResource", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findRoleResource(RoleResource), ����[" + roleResource + "]");
		}
        return roleResource;
    }
    
    /**
     * list.
     * @param roleResource roleResource
     * @return roleResource list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<RoleResource> listRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listRoleResource(RoleResource), �������[" + roleResource + "]");
		}
        List<RoleResource> list = getSqlMapClientTemplate().queryForList("RoleResource_listRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listRoleResource(RoleResource), ����[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param roleResource roleResource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listRoleResourceCount(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listRoleResourceCount(RoleResource), �������[" + roleResource + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("RoleResource_listRoleResourceCount", roleResource);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listRoleResourceCount(RoleResource), ����[" + count + "]");
		}
        return count;
    }  
}
