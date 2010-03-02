package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.Role;


/**
 * RoleDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface RoleDao {
    /**
     * insert.
     * @param role role
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertRole(Role role) throws DataAccessException ;

    /**
     * delete.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
    void deleteRole(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param role role
     * @throws DataAccessException DataAccessException
     */
    void updateRole(Role role) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return role
     * @throws DataAccessException DataAccessException
     */
    Role findRole(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param role role
     * @return role list
     * @throws DataAccessException DataAccessException
     */
    List<Role> listRole(Role role) throws DataAccessException ;
    
    /**
     * listCount.
     * @param role role
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listRoleCount(Role role) throws DataAccessException ;
}
