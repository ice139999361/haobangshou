package com.hbs.domain.customer.customerinfo.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;


/**
 * CustomerInfo对象.
 * @author hbs
 *
 */
public class CustomerInfo extends BaseDomain{
    
	/**
	 * 序列号
	 */
	private Integer baseSeqId;
    /**
     * 客户编码.
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
     * 客户公司地址.
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
     * 客户公司网址.
     */
    private String webSite;
    
    /**
     * 客户法人代表.
     */
    private String representative;
    
    /**
     * 客户纳税人识别号.
     */
    private String taxCode;
    
    /**
     * 对应的分公司或分支机构，从字典表选取或手工输入.
     */
    private String companyBranch;
    
    /**
     * 客户信用度，参见字典表定义.
     */
    private String creditRate;
    
    /**
     * 信用等级描述.
     */
    private String creditDesc;
    
    /**
     * 客户的重要程度，参见字典表定义.
     */
    private String importantCode;
    
    /**
     * 客户重要程度描述.
     */
    private String importantDesc;
    
    /**
     * 客户结算类型，参见字典表定义.
     */
    private String settlementType;
    
    /**
     * 结算方式描述.
     */
    private String settlementDesc;
    
    /**
     * 客户结算币种，参见字典表.
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
     * 对应的业务部助理ID.
     */
    private String assStaffId;
    
    /**
     * 对应的业务部助理姓名.
     */
    private String assStaffName;
    
    /**
     * 对账信息备注
     */
    
    private String specMemo;
    /**
     * 企业类型
     */
    private String saleType;
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
     * 物料对照关系信息
     */
    private List<CustPartNoInfo> partNoInfoList;
    
    /**
     * 录入时间
     */
    private Date createTime = new Date();
    
    
    public List<CustPartNoInfo> getPartNoInfoList() {
		return partNoInfoList;
	}

	public void setPartNoInfoList(List<CustPartNoInfo> partNoInfoList) {
		this.partNoInfoList = partNoInfoList;
	}

	public List<ContactInfo> getListContactInfo() {
		return listContactInfo;
	}

	public void setListContactInfo(List<ContactInfo> listContactInfo) {
		this.listContactInfo = listContactInfo;
	}

	public List<BankInfo> getListBankInfo() {
		return listBankInfo;
	}

	public void setListBankInfo(List<BankInfo> listBankInfo) {
		this.listBankInfo = listBankInfo;
	}

	public AccountPreiod getAccountPreiod() {
		return accountPreiod;
	}

	public void setAccountPreiod(AccountPreiod accountPreiod) {
		this.accountPreiod = accountPreiod;
	}

	public PrePaidInfo getPrePaidInfo() {
		return prePaidInfo;
	}

	public void setPrePaidInfo(PrePaidInfo prePaidInfo) {
		this.prePaidInfo = prePaidInfo;
	}

	public Integer getBaseSeqId() {
		return baseSeqId;
	}

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
    /**
     * 获取公司或分支机构描述
     * @return
     */
    public String getCompanyBranchDesc(){
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getCompanyBranch());
    	ceParam.setEncodeType("COMPANY_BRANCH");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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
    
    /**
     * 获取是否显示单价描述
     * @return
     */
    public String getIsShowPriceDesc(){
    	String retStr ="未定义";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getIsShowPrice());
    	ceParam.setEncodeType("IS_SHOW_PRICE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeValue();
    	}
    	return retStr;
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
    
    public String getAssStaffId() {
        return this.assStaffId;
    }	
  
    public void setAssStaffId(String assStaffId) {
        this.assStaffId = assStaffId;
    }
    
    public String getAssStaffName() {
        return this.assStaffName;
    }	
  
    public void setAssStaffName(String assStaffName) {
        this.assStaffName = assStaffName;
    }
    
    

	public String getSpecMemo() {
		return specMemo;
	}

	public void setSpecMemo(String specMemo) {
		this.specMemo = specMemo;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	/**
	 * 根据state返回其中文说明
	 * @return
	 */
	public String getStateDesc() {
		try {
			
			ConfigEncode ce = new ConfigEncode();
			ce.setEncodeType("CUSTOMER_INFO_STATE");
			ce.setEncodeKey(getState());
			ce = ConfigEncodeMgr.getConfigEncode(ce);
			if(ce == null)
				return null;
			else
				return ce.getEncodeValue();
		} catch (Exception e) {
			Logger logger = Logger.getLogger(CustomerInfo.class);
			logger.error("catch Exception in getStateDesc state=" + getState(), e);
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("baseSeqId=").append(baseSeqId).append(";");
		sb.append("commCode=").append(commCode).append(";");
		sb.append("state=").append(state).append(";");
		sb.append("shortName=").append(shortName).append(";");
		sb.append("enShortName=").append(enShortName).append(";");
		sb.append("allName=").append(allName).append(";");
		sb.append("enName=").append(enName).append(";");
		sb.append("address=").append(address).append(";");
		sb.append("enAddress=").append(enAddress).append(";");
		sb.append("commType=").append(commType).append(";");
		sb.append("commScale=").append(commScale).append(";");
		sb.append("webSite=").append(webSite).append(";");
		sb.append("representative=").append(representative).append(";");
		sb.append("taxCode=").append(taxCode).append(";");
		sb.append("companyBranch=").append(companyBranch).append(";");
		sb.append("creditRate=").append(creditRate).append(";");
		sb.append("creditDesc=").append(creditDesc).append(";");
		sb.append("importantCode=").append(importantCode).append(";");
		sb.append("importantDesc=").append(importantDesc).append(";");
		sb.append("settlementType=").append(settlementType).append(";");
		sb.append("settlementDesc=").append(settlementDesc).append(";");
		sb.append("currency=").append(currency).append(";");
		sb.append("currencyDesc=").append(currencyDesc).append(";");
		sb.append("staffId=").append(staffId).append(";");
		sb.append("staffName=").append(staffName).append(";");
		sb.append("commDesc=").append(commDesc).append(";");
		sb.append("vendorCode=").append(vendorCode).append(";");
		sb.append("contactFee=").append(contactFee).append(";");
		sb.append("isShowPrice=").append(isShowPrice).append(";");
		sb.append("taxRate=").append(taxRate).append(";");
		sb.append("assStaffId=").append(assStaffId).append(";");
		sb.append("assStaffName=").append(assStaffName).append(";");
		sb.append("createTime=").append(createTime);
		
		return sb.toString();
	}

}
