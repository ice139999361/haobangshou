package com.hbs.domain.vendor.vendorinfo.pojo;

import java.math.BigDecimal;
import java.util.List;

import com.hbs.domain.common.pojo.base.BaseDomain;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;


/**
 * VendorInfo对象.
 * @author hbs
 *
 */
public class VendorInfo extends BaseDomain{
    
	
	/**
	 * 序列号
	 */
	private Integer baseSeqId;
	
    /**
     * 供应商编码，格式GV0001.
     */
    private String commCode;
    
    /**
     * 状态0----正式数据1---临时数据（没有提交审批）2---待审批数据3---审批不通过4---废弃数据.
     */
    private String state;
    
    /**
     * 客户简称.
     */
    private String shortName;
    
    /**
     * 英文简称.
     */
    private String enShortName;
    
    /**
     * 公司中文名称.
     */
    private String allName;
    
    /**
     * 公司英文名称.
     */
    private String enName;
    
    /**
     * 公司地址.
     */
    private String address;
    
    /**
     * 公司英文地址.
     */
    private String enAddress;
    
    /**
     * 性质：如台资，港资等.
     */
    private String commType;
    
    /**
     * 规模，1-500人，大企业等.
     */
    private String commScale;
    
    /**
     * 公司网址.
     */
    private String webSite;
    
    /**
     * 法人代表.
     */
    private String representative;
    
    /**
     * 纳税人识别号.
     */
    private String taxCode;
    
    /**
     * 对应的分公司或分支机构，从字典表选取或手工输入.
     */
    private String companyBranch;
    
    /**
     * 信用度，参见字典表定义.
     */
    private String creditRate;
    
    /**
     * 信用等级描述.
     */
    private String creditDesc;
    
    /**
     * 重要程度，参见字典表定义.
     */
    private String importantCode;
    
    /**
     * 重要程度描述.
     */
    private String importantDesc;
    
    /**
     * 结算类型，参见字典表定义.
     */
    private String settlementType;
    
    /**
     * 结算方式描述.
     */
    private String settlementDesc;
    
    /**
     * 客结算币种，参见字典表.
     */
    private String currency;
    
    /**
     * 结算币种描述.
     */
    private String currencyDesc;
    
    /**
     * 销售人员ID.
     */
    private String staffId;
    
    /**
     * 销售人员名字.
     */
    private String staffName;
    
    /**
     * 客户备注.
     */
    private String commDesc;
    
    /**
     * 对应供应商编码.
     */
    private String vendorCode;
    
    /**
     * 合同费，百分值.
     */
    private BigDecimal contactFee;
    
    /**
     * 发货单是否显示单价0---不显示1---显示.
     */
    private String isShowPrice;
    
    /**
     * 交易税率，百分值.
     */
    private BigDecimal taxRate;


    
    /**
     * 联系列表
     */
    private List<ContactInfo> listContactInfo;
    /**
     * 银行列表
     */
    private List<BankInfo> listBankInfo;
    /**
     * 账期信息
     */
    private AccountPreiod accountPreiod;
    /**
     * 预付费信息
     */
    private PrePaidInfo prePaidInfo;
    
    /**
	 * @return the baseSeqId
	 */
	public Integer getBaseSeqId() {
		return baseSeqId;
	}

	/**
	 * @param baseSeqId the baseSeqId to set
	 */
	public void setBaseSeqId(Integer baseSeqId) {
		this.baseSeqId = baseSeqId;
	}

	public String getCommCode() {
        return this.commCode;
    }	
  
    public void setCommCode(String commCode) {
        this.commCode = commCode;
    }
    
    public String getState() {
        return this.state;
    }	
  
    public void setState(String state) {
        this.state = state;
    }
    
    public String getShortName() {
        return this.shortName;
    }	
  
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public String getEnShortName() {
        return this.enShortName;
    }	
  
    public void setEnShortName(String enShortName) {
        this.enShortName = enShortName;
    }
    
    public String getAllName() {
        return this.allName;
    }	
  
    public void setAllName(String allName) {
        this.allName = allName;
    }
    
    public String getEnName() {
        return this.enName;
    }	
  
    public void setEnName(String enName) {
        this.enName = enName;
    }
    
    public String getAddress() {
        return this.address;
    }	
  
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEnAddress() {
        return this.enAddress;
    }	
  
    public void setEnAddress(String enAddress) {
        this.enAddress = enAddress;
    }
    
    public String getCommType() {
        return this.commType;
    }	
  
    public void setCommType(String commType) {
        this.commType = commType;
    }
    
    public String getCommScale() {
        return this.commScale;
    }	
  
    public void setCommScale(String commScale) {
        this.commScale = commScale;
    }
    
    public String getWebSite() {
        return this.webSite;
    }	
  
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
    
    public String getRepresentative() {
        return this.representative;
    }	
  
    public void setRepresentative(String representative) {
        this.representative = representative;
    }
    
    public String getTaxCode() {
        return this.taxCode;
    }	
  
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
    public String getCompanyBranch() {
        return this.companyBranch;
    }	
  
    public void setCompanyBranch(String companyBranch) {
        this.companyBranch = companyBranch;
    }
    
    public String getCreditRate() {
        return this.creditRate;
    }	
  
    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }
    
    public String getCreditDesc() {
        return this.creditDesc;
    }	
  
    public void setCreditDesc(String creditDesc) {
        this.creditDesc = creditDesc;
    }
    
    public String getImportantCode() {
        return this.importantCode;
    }	
  
    public void setImportantCode(String importantCode) {
        this.importantCode = importantCode;
    }
    
    public String getImportantDesc() {
        return this.importantDesc;
    }	
  
    public void setImportantDesc(String importantDesc) {
        this.importantDesc = importantDesc;
    }
    
    public String getSettlementType() {
        return this.settlementType;
    }	
  
    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }
    
    public String getSettlementDesc() {
        return this.settlementDesc;
    }	
  
    public void setSettlementDesc(String settlementDesc) {
        this.settlementDesc = settlementDesc;
    }
    
    public String getCurrency() {
        return this.currency;
    }	
  
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public String getCurrencyDesc() {
        return this.currencyDesc;
    }	
  
    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }
    
    public String getStaffId() {
        return this.staffId;
    }	
  
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    public String getStaffName() {
        return this.staffName;
    }	
  
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getCommDesc() {
        return this.commDesc;
    }	
  
    public void setCommDesc(String commDesc) {
        this.commDesc = commDesc;
    }
    
    public String getVendorCode() {
        return this.vendorCode;
    }	
  
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    public BigDecimal getContactFee() {
        return this.contactFee;
    }	
  
    public void setContactFee(BigDecimal contactFee) {
        this.contactFee = contactFee;
    }
    
    public String getIsShowPrice() {
        return this.isShowPrice;
    }	
  
    public void setIsShowPrice(String isShowPrice) {
        this.isShowPrice = isShowPrice;
    }
    
    public BigDecimal getTaxRate() {
        return this.taxRate;
    }	
  
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

	/**
	 * @return the listContactInfo
	 */
	public List<ContactInfo> getListContactInfo() {
		return listContactInfo;
	}

	/**
	 * @param listContactInfo the listContactInfo to set
	 */
	public void setListContactInfo(List<ContactInfo> listContactInfo) {
		this.listContactInfo = listContactInfo;
	}

	/**
	 * @return the listBankInfo
	 */
	public List<BankInfo> getListBankInfo() {
		return listBankInfo;
	}

	/**
	 * @param listBankInfo the listBankInfo to set
	 */
	public void setListBankInfo(List<BankInfo> listBankInfo) {
		this.listBankInfo = listBankInfo;
	}

	/**
	 * @return the accountPreiod
	 */
	public AccountPreiod getAccountPreiod() {
		return accountPreiod;
	}

	/**
	 * @param accountPreiod the accountPreiod to set
	 */
	public void setAccountPreiod(AccountPreiod accountPreiod) {
		this.accountPreiod = accountPreiod;
	}

	/**
	 * @return the prePaidInfo
	 */
	public PrePaidInfo getPrePaidInfo() {
		return prePaidInfo;
	}

	/**
	 * @param prePaidInfo the prePaidInfo to set
	 */
	public void setPrePaidInfo(PrePaidInfo prePaidInfo) {
		this.prePaidInfo = prePaidInfo;
	}

	
	public String getLogKey(){
		return this.commCode;
	}
	
	public String getWaitTaskKey(){
		return this.commCode;
	}
}
