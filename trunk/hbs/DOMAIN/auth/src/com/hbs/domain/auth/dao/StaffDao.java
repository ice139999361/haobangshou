package com.hbs.domain.auth.dao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.hbs.domain.auth.pojo.Staff;


/**
 * StaffDao½Ó¿Ú.
 * @author hbs
 *
 */
public interface StaffDao {
    /**
     * insert.
     * @param staff staff
     * @return id
     * @throws DataAccessException DataAccessException
     */
    Integer insertStaff(Staff staff) throws DataAccessException ;

    /**
     * delete.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
    void deleteStaff(String id) throws DataAccessException ;
    
    /**
     * update.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
    void updateStaff(Staff staff) throws DataAccessException ;

    /**
     * find.
     * @param id id
     * @return staff
     * @throws DataAccessException DataAccessException
     */
    Staff findStaff(String id) throws DataAccessException ;
    
    /**
     * list.
     * @param staff staff
     * @return staff list
     * @throws DataAccessException DataAccessException
     */
    List<Staff> listStaff(Staff staff) throws DataAccessException ;
    
    /**
     * listCount.
     * @param staff staff
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    Integer listStaffCount(Staff staff) throws DataAccessException ;
}
