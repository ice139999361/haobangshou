package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Role;
import com.hbs.domain.auth.dao.RoleDao;

/**
 * RoleDao�ӿ�ʵ����.
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
    		logger.debug("����insertRole(Role), �������[" + role + "]");
    	}
        
        
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Role_insertRole", role);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertRole(Role), ����[" + id + "]");
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
    		logger.debug("����deleteRole(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Role_deleteRole", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteRole(String)");
		}
    }
    
    /**
     * update.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
    public void updateRole(Role role) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateRole(Role), �������[" + role + "]");
		}
    	getSqlMapClientTemplate().update("Role_updateRole", role);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateRole(Role)");
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
        	logger.debug("����findRole(Role), �������[" + pk + "]");
		}
        Role role = (Role) getSqlMapClientTemplate().queryForObject("Role_findRole", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findRole(Role), ����[" + role + "]");
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
        	logger.debug("����listRole(Role), �������[" + role + "]");
		}
        List<Role> list = getSqlMapClientTemplate().queryForList("Role_listRole", role);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listRole(Role), ����[" + list + "]");
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
        	logger.debug("����listRoleCount(Role), �������[" + role + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Role_listRoleCount", role);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listRoleCount(Role), ����[" + count + "]");
		}
        return count;
    }  
}
