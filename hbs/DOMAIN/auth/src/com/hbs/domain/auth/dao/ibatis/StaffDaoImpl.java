package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.auth.dao.StaffDao;

/**
 * StaffDao接口实现类.
 * @author hbs
 *
 */
public class StaffDaoImpl extends SqlMapClientDaoSupport implements StaffDao
{
    /**
     * logger.
     */
    private static final Logger logger = Logger.getLogger(StaffDaoImpl.class);

    
    
    /**
     * insert.
     * @param staff staff
     * @return id
     * @throws DataAccessException DataAccessException
     */
    public Integer insertStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入insertStaff(Staff), 输入参数[" + staff + "]");
    	}
        
        
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Staff_insertStaff", staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开insertStaff(Staff), 返回[" + id + "]");
		}
    	return id;
    }

    /**
     * delete.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
    public void deleteStaff(String pk)throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入deleteStaff(String), 输入参数[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Staff_deleteStaff", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开deleteStaff(String)");
		}
    }
    
    /**
     * update.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
    public void updateStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("进入updateStaff(Staff), 输入参数[" + staff + "]");
		}
    	getSqlMapClientTemplate().update("Staff_updateStaff", staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("离开updateStaff(Staff)");
		}
    }
    
    /**
     * find.
     * @param id id
     * @return staff
     * @throws DataAccessException DataAccessException
     */
    public Staff findStaff(String pk) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入findStaff(Staff), 输入参数[" + pk + "]");
		}
        Staff staff = (Staff) getSqlMapClientTemplate().queryForObject("Staff_findStaff", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开findStaff(Staff), 返回[" + staff + "]");
		}
        return staff;
    }
    
    /**
     * list.
     * @param staff staff
     * @return staff list
     * @throws DataAccessException DataAccessException
     */
    @SuppressWarnings("unchecked")
	public List<Staff> listStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listStaff(Staff), 输入参数[" + staff + "]");
		}
        List<Staff> list = getSqlMapClientTemplate().queryForList("Staff_listStaff", staff);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listStaff(Staff), 返回[" + list + "]");
		}
        return list;
    }  
    
    /**
     * listCount.
     * @param staff staff
     * @return list count
     * @throws DataAccessException DataAccessException
     */
    public Integer listStaffCount(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listStaffCount(Staff), 输入参数[" + staff + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Staff_listStaffCount", staff);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listStaffCount(Staff), 返回[" + count + "]");
		}
        return count;
    }

	@SuppressWarnings("unchecked")
	public List<Staff> listStaffByRoleId(String roleId) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("进入listStaffByRoleId(String roleId), 输入参数[" + roleId + "]");
		}
        List<Staff> list = getSqlMapClientTemplate().queryForList("Staff_listStaffByRoleId", roleId);
		if (logger.isDebugEnabled()) {
        	logger.debug("离开listStaffByRoleId(String roleId), 返回[" + list + "]");
		}
        return list;
	}  
}
