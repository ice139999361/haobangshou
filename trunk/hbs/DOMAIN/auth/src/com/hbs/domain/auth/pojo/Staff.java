package com.hbs.domain.auth.pojo;

import com.hbs.domain.common.pojo.base.BaseDomain;


/**
 * Staff对象.
 * @author hbs
 *
 */
public class Staff extends BaseDomain{
    
    /**
     * 用户ID(PK.
     */
    private Integer staffId;
    
    /**
     * 用户名(UNIQUE).
     */
    private String staffName;
    
    /**
     * 身份证号码.
     */
    private String identityNumber;
    
    /**
     * 性别.
     */
    private String gender;
    
    /**
     * 出生日期.
     */
    private String birthDate;
    
    /**
     * 电话.
     */
    private String phone;
    
    /**
     * 手机.
     */
    private String mobile;
    
    /**
     * 邮箱.
     */
    private String email;
    
    /**
     * 职务.
     */
    private String duty;
    
    /**
     * 注释.
     */
    private String memo;


    
    public Integer getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    public String getStaffName() {
        return this.staffName;
    }	
  
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getIdentityNumber() {
        return this.identityNumber;
    }	
  
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
    
    public String getGender() {
        return this.gender;
    }	
  
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getBirthDate() {
        return this.birthDate;
    }	
  
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getPhone() {
        return this.phone;
    }	
  
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getMobile() {
        return this.mobile;
    }	
  
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getEmail() {
        return this.email;
    }	
  
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDuty() {
        return this.duty;
    }	
  
    public void setDuty(String duty) {
        this.duty = duty;
    }
    
    public String getMemo() {
        return this.memo;
    }	
  
    public void setMemo(String memo) {
        this.memo = memo;
    }

	public Staff(Integer id, String name)
	{
		staffId = id;
		staffName = name;
	}
	
	public Staff() {}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("staffId=").append(staffId).append(";");
		sb.append("staffName=").append(staffName).append(";");
		sb.append("identityNumber=").append(identityNumber).append(";");
		sb.append("gender=").append(gender).append(";");
		sb.append("birthDate=").append(birthDate).append(";");
		sb.append("phone=").append(phone).append(";");		
		sb.append("mobile=").append(mobile).append(";");
		sb.append("email=").append(email).append(";");
		sb.append("duty=").append(duty).append(";");
		sb.append("memo=").append(memo);
		return sb.toString();
	}
}
