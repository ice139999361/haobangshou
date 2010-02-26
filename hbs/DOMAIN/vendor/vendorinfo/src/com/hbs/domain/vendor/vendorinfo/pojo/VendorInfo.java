package com.hbs.domain.vendor.vendorinfo.pojo;

import java.math.BigDecimal;
import java.util.List;

import com.hbs.common.manager.configencode.ConfigEncodeMgr;
import com.hbs.domain.common.pojo.ConfigEncode;
import com.hbs.domain.common.pojo.base.BaseDomain;
import com.hbs.domain.common.pojo.baseinfo.AccountPreiod;
import com.hbs.domain.common.pojo.baseinfo.BankInfo;
import com.hbs.domain.common.pojo.baseinfo.ContactInfo;
import com.hbs.domain.common.pojo.baseinfo.PrePaidInfo;


/**
 * VendorInfo����.
 * @author hbs
 *
 */
public class VendorInfo extends BaseDomain{
    
	
	/**
	 * ���к�
	 */
	private Integer baseSeqId;
	
    /**
     * ��Ӧ�̱��룬��ʽGV0001.
     */
    private String commCode;
    
    /**
     * ״̬0----��ʽ����1---��ʱ���ݣ�û���ύ������2---����������3---������ͨ��4---��������.
     */
    private String state;
    
    /**
     * �ͻ����.
     */
    private String shortName;
    
    /**
     * Ӣ�ļ��.
     */
    private String enShortName;
    
    /**
     * ��˾��������.
     */
    private String allName;
    
    /**
     * ��˾Ӣ������.
     */
    private String enName;
    
    /**
     * ��˾��ַ.
     */
    private String address;
    
    /**
     * ��˾Ӣ�ĵ�ַ.
     */
    private String enAddress;
    
    /**
     * ���ʣ���̨�ʣ����ʵ�.
     */
    private String commType;
    
    /**
     * ��ģ��1-500�ˣ�����ҵ��.
     */
    private String commScale;
    
    /**
     * ��˾��ַ.
     */
    private String webSite;
    
    /**
     * ���˴���.
     */
    private String representative;
    
    /**
     * ��˰��ʶ���.
     */
    private String taxCode;
    
    /**
     * ��Ӧ�ķֹ�˾���֧���������ֵ��ѡȡ���ֹ�����.
     */
    private String companyBranch;
    
    /**
     * ���öȣ��μ��ֵ������.
     */
    private String creditRate;
    
    /**
     * ���õȼ�����.
     */
    private String creditDesc;
    
    /**
     * ��Ҫ�̶ȣ��μ��ֵ������.
     */
    private String importantCode;
    
    /**
     * ��Ҫ�̶�����.
     */
    private String importantDesc;
    
    /**
     * �������ͣ��μ��ֵ������.
     */
    private String settlementType;
    
    /**
     * ���㷽ʽ����.
     */
    private String settlementDesc;
    
    /**
     * �ͽ�����֣��μ��ֵ��.
     */
    private String currency;
    
    /**
     * �����������.
     */
    private String currencyDesc;
    
    /**
     * ������ԱID.
     */
    private String staffId;
    
    /**
     * ������Ա����.
     */
    private String staffName;
    
    /**
     * �ͻ���ע.
     */
    private String commDesc;
    
    /**
     * ��Ӧ��Ӧ�̱���.
     */
    private String vendorCode;
    
    /**
     * ��ͬ�ѣ��ٷ�ֵ.
     */
    private BigDecimal contactFee;
    
    /**
     * �������Ƿ���ʾ����0---����ʾ1---��ʾ.
     */
    private String isShowPrice;
    
    /**
     * ����˰�ʣ��ٷ�ֵ.
     */
    private BigDecimal taxRate;


    
    /**
     * ��ϵ�б�
     */
    private List<ContactInfo> listContactInfo;
    /**
     * �����б�
     */
    private List<BankInfo> listBankInfo;
    /**
     * ������Ϣ
     */
    private AccountPreiod accountPreiod;
    /**
     * Ԥ������Ϣ
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
    
    /**
     * ��ȡ��˾���֧��������
     * @return
     */
    public String getCompanyBranchDesc(){
    	String retStr ="δ����";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getCompanyBranch());
    	ceParam.setEncodeType("COMPANY_BRANCH");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeDesc();
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
     * ��ȡ�Ƿ���ʾ��������
     * @return
     */
    public String getIsShowPriceDesc(){
    	String retStr ="δ����";
    	ConfigEncode ceParam = new ConfigEncode();
    	ceParam.setEncodeKey(getIsShowPrice());
    	ceParam.setEncodeType("IS_SHOW_PRICE");
    	ConfigEncode cEncode = ConfigEncodeMgr.getConfigEncode(ceParam);
    	if(null != cEncode){
    		retStr = cEncode.getEncodeDesc();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("baseSeqId=").append(this.baseSeqId).append(" ");
		sb.append("commCode=").append(this.commCode).append(" ");
		sb.append("state=").append(this.state).append(" ");
		sb.append("shortName=").append(this.shortName).append(" ");
		sb.append("enShortName=").append(this.enShortName).append(" ");
		sb.append("allName=").append(this.allName).append(" ");
		sb.append("enName=").append(this.enName).append(" ");
		sb.append("address=").append(this.address).append(" ");
		sb.append("enAddress=").append(this.enAddress).append(" ");
		sb.append("commType=").append(this.commType).append(" ");
		sb.append("commScale=").append(this.commScale).append(" ");
		sb.append("webSite=").append(this.webSite).append(" ");
		sb.append("representative=").append(this.representative).append(" ");
		sb.append("taxCode=").append(this.taxCode).append(" ");
		sb.append("companyBranch=").append(this.companyBranch).append(" ");
		sb.append("creditRate=").append(this.creditRate).append(" ");
		sb.append("creditDesc=").append(this.creditDesc).append(" ");
		sb.append("importantCode=").append(this.importantCode).append(" ");
		sb.append("importantDesc=").append(this.importantDesc).append(" ");
		sb.append("settlementType=").append(this.settlementType).append(" ");
		sb.append("settlementDesc=").append(this.settlementDesc).append(" ");
		sb.append("currency=").append(this.currency).append(" ");
		sb.append("currencyDesc=").append(this.currencyDesc).append(" ");
		sb.append("staffId=").append(this.staffId).append(" ");
		sb.append("staffName=").append(this.staffName).append(" ");
		sb.append("commDesc=").append(this.commDesc).append(" ");
		sb.append("vendorCode=").append(this.vendorCode).append(" ");
		sb.append("contactFee=").append(this.contactFee == null ? null : this.contactFee.floatValue()).append(" ");
		sb.append("isShowPrice=").append(this.isShowPrice).append(" ");
		sb.append("taxRate=").append(this.taxRate == null ? null : this.taxRate.floatValue()).append(" ");
		sb.append("listContactInfo=").append(this.listContactInfo == null ? null : this.listContactInfo.size()).append(" ");
		sb.append("listBankInfo=").append(this.listBankInfo == null ? null : this.listBankInfo.size()).append(" ");
		sb.append("accountPreiod=").append(this.accountPreiod == null ? null : this.accountPreiod.toString()).append(" ");
		sb.append("prePaidInfo=").append(this.prePaidInfo == null ? null : this.prePaidInfo.toString()).append(" ");
		
		return sb.toString();
	}
	
}