package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Role;
import com.hbs.domain.auth.dao.RoleDao;

/**
 * RoleDao接口实现类.
 * @author hbs
 *
 */
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    
    
    /**
     * insert.
     * @param role role
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertRole(Role), 输入参数[" + role + "]");
    	}
        
        
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Role_insertRole", role);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertRole(Role), 返回[" + id + "]");
		}
    	return id;
    }

    /**
     * delete.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
    public void deleteRole(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteRole(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Role_deleteRole", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteRole(String)");
		}
    }
    
    /**
     * update.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
    public void updateRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateRole(Role), 输入参数[" + role + "]");
		}
    	getSqlMapClientTemplate().update("Role_updateRole", role);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateRole(Role)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return role
     * @throws DataAccessException DataAccessException
     */
    public Role findRole(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findRole(Role), 输入参数[" + pk + "]");
		}
        Role role = (Role) getSqlMapClientTemplate().queryForObject("Role_findRole", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findRole(Role), 返回[" + role + "]");
		}
        return role;
    }
    
    /**
     * list.
     * @param role role
     * @return role list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<Role> listRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listRole(Role), 输入参数[" + role + "]");
		}
        List<Role> list = getSqlMapClientTemplate().queryForList("Role_listRole", role);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listRole(Role), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param role role
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listRoleCount(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listRoleCount(Role), 输入参数[" + role + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Role_listRoleCount", role);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listRoleCount(Role), 返回[" + count + "]");
		}
        return count;
    }  
}
