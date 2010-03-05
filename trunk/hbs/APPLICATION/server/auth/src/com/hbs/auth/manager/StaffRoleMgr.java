package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.UserRoleDao;
import com.hbs.domain.auth.pojo.UserRole;

/**
 * 
 * @author tony.chen
 *
 */


public class StaffRoleMgr {
	
	private static final String USER_ROLE_DAO_NAME = "userRoleDao";
	private static final Logger logger = Logger.getLogger(StaffRoleMgr.class);
	
	public Integer insertUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertUserRole(UserRole userRole): parameter=" + userRole.toString());
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		Integer ret = userRoleDao.insertUserRole(userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertUserRole(UserRole userRole)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
	public void deleteUserRole(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteUserRole(String id): parameter=" + id);
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		userRoleDao.deleteUserRole(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteUserRole(String id)");
    	}
	}
    
    /**
     * update.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
	public void updateUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateUserRole(UserRole userRole): parameter=" + userRole.toString());
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		userRoleDao.updateUserRole(userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateUserRole(UserRole userRole)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return userRole
     * @throws DataAccessException DataAccessException
     */
	public UserRole findUserRole(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findUserRole(String id): parameter=" + id);
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		UserRole ret = userRoleDao.findUserRole(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findUserRole(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param userRole userRole
     * @return userRole list
     * @throws DataAccessException DataAccessException
     */
	public List<UserRole> listUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listUserRole(UserRole userRole): parameter=" + userRole.toString());
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		List<UserRole> ret = userRoleDao.listUserRole(userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listUserRole(UserRole userRole)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param userRole userRole
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listUserRoleCount(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listUserRoleCount(UserRole userRole): parameter=" + userRole.toString());
    	}
		UserRoleDao userRoleDao = (UserRoleDao)BeanLocator.getInstance().getBean(USER_ROLE_DAO_NAME);
		Integer ret = userRoleDao.listUserRoleCount(userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listUserRoleCount(UserRole userRole)");
    	}
		return ret;
	}
}
