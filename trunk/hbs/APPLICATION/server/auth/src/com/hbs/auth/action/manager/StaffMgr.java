package com.hbs.auth.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.hbs.common.springhelper.BeanLocator;
import com.hbs.domain.auth.dao.StaffDao;
import com.hbs.domain.auth.pojo.Staff;

/**
 * 
 * @author tony.chen
 *
 */


public class StaffMgr {
	
	private static final String STAFF_DAO_NAME = "staffDao";
	private static final Logger logger = Logger.getLogger(StaffMgr.class);
	
	public Integer insertStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking insertStaff(Staff staff): parameter=" + staff.toString());
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		Integer ret = staffDao.insertStaff(staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking insertStaff(Staff staff)");
    	}
		return ret;
	}

    /**
     * delete.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
	public void deleteStaff(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking deleteStaff(String id): parameter=" + id);
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		staffDao.deleteStaff(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking deleteStaff(String id)");
    	}
	}
    
    /**
     * update.
     * @param staff staff
     * @throws DataAccessException DataAccessException
     */
	public void updateStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking updateStaff(Staff staff): parameter=" + staff.toString());
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		staffDao.updateStaff(staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking updateStaff(Staff staff)");
    	}
	}

    /**
     * find.
     * @param id id
     * @return staff
     * @throws DataAccessException DataAccessException
     */
	public Staff findStaff(String id) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking findStaff(String id): parameter=" + id);
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		Staff ret = staffDao.findStaff(id);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking findStaff(String id)");
    	}
		return ret;
	}
    
    /**
     * list.
     * @param staff staff
     * @return staff list
     * @throws DataAccessException DataAccessException
     */
	public List<Staff> listStaff(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listStaff(Staff staff): parameter=" + staff.toString());
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		List<Staff> ret = staffDao.listStaff(staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listStaff(Staff staff)");
    	}
		return ret;
	}
    
    /**
     * listCount.
     * @param staff staff
     * @return list count
     * @throws DataAccessException DataAccessException
     */
	public Integer listStaffCount(Staff staff) throws DataAccessException {
		if (logger.isDebugEnabled()) {
    		logger.debug("Invoking listStaffCount(Staff staff): parameter=" + staff.toString());
    	}
		StaffDao staffDao = (StaffDao)BeanLocator.getInstance().getBean(STAFF_DAO_NAME);
		Integer ret = staffDao.listStaffCount(staff);
		if (logger.isDebugEnabled()) {
    		logger.debug("Finished invoking listStaffCount(Staff staff)");
    	}
		return ret;
	}
}
