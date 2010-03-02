package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.UserRole;
import com.hbs.domain.auth.dao.UserRoleDao;

/**
 * UserRoleDao接口实现类.
 * @author hbs
 *
 */
public class UserRoleDaoImpl extends SqlMapClientDaoSupport implements UserRoleDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(UserRoleDaoImpl.class);

    
    
    /**
     * insert.
     * @param userRole userRole
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertUserRole(UserRole), 输入参数[" + userRole + "]");
    	}
        
       
    	getSqlMapClientTemplate().insert("UserRole_insertUserRole", userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertUserRole(UserRole), 返回");
		}
    	return userRole.getStaffId();
    }

    /**
     * delete.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
    public void deleteUserRole(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteUserRole(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("UserRole_deleteUserRole", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteUserRole(String)");
		}
    }
    
    /**
     * update.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
    public void updateUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateUserRole(UserRole), 输入参数[" + userRole + "]");
		}
    	getSqlMapClientTemplate().update("UserRole_updateUserRole", userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateUserRole(UserRole)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return userRole
     * @throws DataAccessException DataAccessException
     */
    public UserRole findUserRole(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findUserRole(UserRole), 输入参数[" + pk + "]");
		}
        UserRole userRole = (UserRole) getSqlMapClientTemplate().queryForObject("UserRole_findUserRole", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findUserRole(UserRole), 返回[" + userRole + "]");
		}
        return userRole;
    }
    
    /**
     * list.
     * @param userRole userRole
     * @return userRole list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<UserRole> listUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listUserRole(UserRole), 输入参数[" + userRole + "]");
		}
        List<UserRole> list = getSqlMapClientTemplate().queryForList("UserRole_listUserRole", userRole);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listUserRole(UserRole), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param userRole userRole
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listUserRoleCount(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listUserRoleCount(UserRole), 输入参数[" + userRole + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("UserRole_listUserRoleCount", userRole);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listUserRoleCount(UserRole), 返回[" + count + "]");
		}
        return count;
    }  
}
