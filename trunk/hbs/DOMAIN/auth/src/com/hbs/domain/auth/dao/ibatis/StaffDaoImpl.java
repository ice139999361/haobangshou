package com.hbs.domain.auth.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.hbs.domain.auth.pojo.Staff;
import com.hbs.domain.auth.dao.StaffDao;

/**
 * StaffDao�ӿ�ʵ����.
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
    		logger.debug("����insertStaff(Staff), �������[" + staff + "]");
    	}
        
        
        
    	Integer id =(Integer)getSqlMapClientTemplate().insert("Staff_insertStaff", staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪insertStaff(Staff), ����[" + id + "]");
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
    		logger.debug("����deleteStaff(String), �������[" + pk + "]");
		}
        getSqlMapClientTemplate().update("Staff_deleteStaff", pk);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪deleteStaff(String)");
		}
    }
    
    /**
     * update.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
    public void updateStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("����updateStaff(Staff), �������[" + staff + "]");
		}
    	getSqlMapClientTemplate().update("Staff_updateStaff", staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("�뿪updateStaff(Staff)");
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
        	logger.debug("����findStaff(Staff), �������[" + pk + "]");
		}
        Staff staff = (Staff) getSqlMapClientTemplate().queryForObject("Staff_findStaff", pk);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪findStaff(Staff), ����[" + staff + "]");
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
        	logger.debug("����listStaff(Staff), �������[" + staff + "]");
		}
        List<Staff> list = getSqlMapClientTemplate().queryForList("Staff_listStaff", staff);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listStaff(Staff), ����[" + list + "]");
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
        	logger.debug("����listStaffCount(Staff), �������[" + staff + "]");
		}
        Integer count = (Integer)getSqlMapClientTemplate().queryForObject("Staff_listStaffCount", staff);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listStaffCount(Staff), ����[" + count + "]");
		}
        return count;
    }

	@SuppressWarnings("unchecked")
	public List<Staff> listStaffByRoleId(String roleId) throws DataAccessException {
		if (logger.isDebugEnabled()) {
        	logger.debug("����listStaffByRoleId(String roleId), �������[" + roleId + "]");
		}
        List<Staff> list = getSqlMapClientTemplate().queryForList("Staff_listStaffByRoleId", roleId);
		if (logger.isDebugEnabled()) {
        	logger.debug("�뿪listStaffByRoleId(String roleId), ����[" + list + "]");
		}
        return list;
	}  
}
