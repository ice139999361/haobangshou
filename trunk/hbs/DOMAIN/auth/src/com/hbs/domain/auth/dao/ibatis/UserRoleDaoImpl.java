package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.UserRole;
import com.hbs.domain.auth.dao.UserRoleDao;

/**
 * UserRoleDao�ӿ�ʵ����.
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
    		logger.debug("����insertUserRole(UserRole), �������[" + userRole + "]");
    	}
        
       
    	getSqlMapClientTemplate().insert("UserRole_insertUserRole", userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertUserRole(UserRole), ����");
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
    		logger.debug("����deleteUserRole(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("UserRole_deleteUserRole", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteUserRole(String)");
		}
    }
    
    /**
     * update.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
    public void updateUserRole(UserRole userRole) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateUserRole(UserRole), �������[" + userRole + "]");
		}
    	getSqlMapClientTemplate().update("UserRole_updateUserRole", userRole);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateUserRole(UserRole)");
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
        	logger.debug("����findUserRole(UserRole), �������[" + pk + "]");
		}
        UserRole userRole = (UserRole) getSqlMapClientTemplate().queryForObject("UserRole_findUserRole", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findUserRole(UserRole), ����[" + userRole + "]");
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
        	logger.debug("����listUserRole(UserRole), �������[" + userRole + "]");
		}
        List<UserRole> list = getSqlMapClientTemplate().queryForList("UserRole_listUserRole", userRole);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listUserRole(UserRole), ����[" + list + "]");
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
        	logger.debug("����listUserRoleCount(UserRole), �������[" + userRole + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("UserRole_listUserRoleCount", userRole);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listUserRoleCount(UserRole), ����[" + count + "]");
		}
        return count;
    }  
}
