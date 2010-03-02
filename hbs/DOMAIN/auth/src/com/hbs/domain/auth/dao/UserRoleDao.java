package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.UserRole;


/**
 * UserRoleDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface UserRoleDao {
    /**
     * insert.
     * @param userRole userRole
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertUserRole(UserRole userRole) throws DataAccessException ;

    /**
     * delete.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
    void deleteUserRole(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param userRole userRole
     * @throws DataAccessException DataAccessException
     */
    void updateUserRole(UserRole userRole) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return userRole
     * @throws DataAccessException DataAccessException
     */
    UserRole findUserRole(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param userRole userRole
     * @return userRole list
     * @throws DataAccessException DataAccessException
     */
    List<UserRole> listUserRole(UserRole userRole) throws DataAccessException ;
    
    /**
     * listCount.
     * @param userRole userRole
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listUserRoleCount(UserRole userRole) throws DataAccessException ;
}
