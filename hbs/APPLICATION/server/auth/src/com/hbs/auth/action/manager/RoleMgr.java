package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.RoleDao;
import com.hbs.domain.auth.pojo.Role;

/**
 * 
 * @author tony.chen
 *
 */


public class RoleMgr {
	
	private static final String ROLE_DAO_NAME = "roleDao";
	private static final Logger logger = Logger.getLogger(RoleMgr.class);
	
	public Integer insertRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertRole(Role role): parameter=" + role.toString());
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		Integer ret = roleDao.insertRole(role);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertRole(Role role)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
	public void deleteRole(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteRole(String id): parameter=" + id);
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		roleDao.deleteRole(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteRole(String id)");
    	}
	}
    
    /**
     * update.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
	public void updateRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateRole(Role role): parameter=" + role.toString());
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		roleDao.updateRole(role);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateRole(Role role)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return role
     * @throws DataAccessException DataAccessException
     */
	public Role findRole(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findRole(String id): parameter=" + id);
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		Role ret = roleDao.findRole(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findRole(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param role role
     * @return role list
     * @throws DataAccessException DataAccessException
     */
	public List<Role> listRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listRole(Role role): parameter=" + role.toString());
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		List<Role> ret = roleDao.listRole(role);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listRole(Role role)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param role role
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listRoleCount(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listRoleCount(Role role): parameter=" + role.toString());
    	}
		RoleDao roleDao = (RoleDao)BeanLocator.getInstance().getBean(ROLE_DAO_NAME);
		Integer ret = roleDao.listRoleCount(role);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listRoleCount(Role role)");
    	}
		return ret;
	}
}
