package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.RoleResource;
import com.hbs.domain.auth.dao.RoleResourceDao;

/**
 * RoleResourceDao接口实现类.
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
    		logger.debug("进入insertRoleResource(RoleResource), 输入参数[" + roleResource + "]");
    	}
        
    	getSqlMapClientTemplate().insert("RoleResource_insertRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertRoleResource(RoleResource), 返回");
		}
    	
    }

    /**
     * delete.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    public void deleteRoleResource(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteRoleResource(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("RoleResource_deleteRoleResource", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteRoleResource(String)");
		}
    }
    
    /**
     * update.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
    public void updateRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateRoleResource(RoleResource), 输入参数[" + roleResource + "]");
		}
    	getSqlMapClientTemplate().update("RoleResource_updateRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateRoleResource(RoleResource)");
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
        	logger.debug("进入findRoleResource(RoleResource), 输入参数[" + pk + "]");
		}
        RoleResource roleResource = (RoleResource) getSqlMapClientTemplate().queryForObject("RoleResource_findRoleResource", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findRoleResource(RoleResource), 返回[" + roleResource + "]");
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
        	logger.debug("进入listRoleResource(RoleResource), 输入参数[" + roleResource + "]");
		}
        List<RoleResource> list = getSqlMapClientTemplate().queryForList("RoleResource_listRoleResource", roleResource);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listRoleResource(RoleResource), 返回[" + list + "]");
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
        	logger.debug("进入listRoleResourceCount(RoleResource), 输入参数[" + roleResource + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("RoleResource_listRoleResourceCount", roleResource);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listRoleResourceCount(RoleResource), 返回[" + count + "]");
		}
        return count;
    }  
}
