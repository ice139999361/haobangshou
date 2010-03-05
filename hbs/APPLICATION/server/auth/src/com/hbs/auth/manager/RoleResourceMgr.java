package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.RoleResourceDao;
import com.hbs.domain.auth.pojo.RoleResource;

/**
 * 
 * @author tony.chen
 *
 */


public class RoleResourceMgr {
	
	private static final String ROLE_RESOURCE_DAO_NAME = "roleResourceDao";
	private static final Logger logger = Logger.getLogger(RoleResourceMgr.class);
	
	public void insertRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertRoleResource(RoleResource roleResource): parameter=" + roleResource.toString());
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		roleResourceDao.insertRoleResource(roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertRoleResource(RoleResource roleResource)");
    	}
	}

    /**
     * delete.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
	public void deleteRoleResource(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteRoleResource(String id): parameter=" + id);
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		roleResourceDao.deleteRoleResource(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteRoleResource(String id)");
    	}
	}
    
    /**
     * update.
     * @param roleResource roleResource
     * @throws DataAccessException DataAccessException
     */
	public void updateRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateRoleResource(RoleResource roleResource): parameter=" + roleResource.toString());
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		roleResourceDao.updateRoleResource(roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateRoleResource(RoleResource roleResource)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return roleResource
     * @throws DataAccessException DataAccessException
     */
	public RoleResource findRoleResource(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findRoleResource(String id): parameter=" + id);
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		RoleResource ret = roleResourceDao.findRoleResource(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findRoleResource(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param roleResource roleResource
     * @return roleResource list
     * @throws DataAccessException DataAccessException
     */
	public List<RoleResource> listRoleResource(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listRoleResource(RoleResource roleResource): parameter=" + roleResource.toString());
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		List<RoleResource> ret = roleResourceDao.listRoleResource(roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listRoleResource(RoleResource roleResource)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param roleResource roleResource
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listRoleResourceCount(RoleResource roleResource) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listRoleResourceCount(RoleResource roleResource): parameter=" + roleResource.toString());
    	}
		RoleResourceDao roleResourceDao = (RoleResourceDao)BeanLocator.getInstance().getBean(ROLE_RESOURCE_DAO_NAME);
		Integer ret = roleResourceDao.listRoleResourceCount(roleResource);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listRoleResourceCount(RoleResource roleResource)");
    	}
		return ret;
	}
}
