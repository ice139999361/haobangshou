package com.hbs.domain.common.pojo.baseinfo;

/**
 * ContactInfo对象.
 * 
 * @author hbs
 * 
 */
public class ContactInfo {

	/**
	 * 序列号.
	 */
	private String seqId;

	/**
	 * 引用的基本信息ID
	 */
	private String baseSeqId;

	/**
	 * 客户/供应商编码.
	 */
	private String commCode;

	/**
	 * 状态0----正式数据1---临时数据（没有提交审批）2---待审批数据3---审批不通过4---废弃数据.
	 */
	private String state;

	/**
	 * 类别1----联系人2----收货人.
	 */
	private String conType;

	/**
	 * 联系人姓名.
	 */
	private String conName;

	/**
	 * 联系人职务.
	 */
	private String conDuty;

	/**
	 * 联系人固定电话.
	 */
	private String conTel;

	/**
	 * 联系人移动电话.
	 */
	private String conMobile;

	/**
	 * 联系人传真.
	 */
	private String conFax;

	/**
	 * 联系人邮箱.
	 */
	private String conMail;

	/**
	 * QQ.
	 */
	private String conQq;

	/**
	 * MSN.
	 */
	private String conMsn;

	/**
	 * 联系人其他信息.
	 */
	private String conOther;

	/**
	 * 收货地址，对收货人有效.
	 */
	private String conAddress;

	/**
	 * 收货邮编，对收货人有效.
	 */
	private String conZip;

	/**
	 * 是否是主联系人0----是1---不是.
	 */
	private String isPrimary;

	public String getSeqId() {
		return this.seqId;
	}

	public void setSeqId(String SEQID) {
		this.seqId = SEQID;
	}

	public String getCommCode() {
		return this.commCode;
	}

	public String getBaseSeqId() {
		return baseSeqId;
	}

	public void setBaseSeqId(String baseSeqId) {
		this.baseSeqId = baseSeqId;
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

	public String getConType() {
		return this.conType;
	}

	public void setConType(String conType) {
		this.conType = conType;
	}

	public String getConName() {
		return this.conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getConDuty() {
		return this.conDuty;
	}

	public void setConDuty(String conDuty) {
		this.conDuty = conDuty;
	}

	public String getConTel() {
		return this.conTel;
	}

	public void setConTel(String conTel) {
		this.conTel = conTel;
	}

	public String getConMobile() {
		return this.conMobile;
	}

	public void setConMobile(String conMobile) {
		this.conMobile = conMobile;
	}

	public String getConFax() {
		return this.conFax;
	}

	public void setConFax(String conFax) {
		this.conFax = conFax;
	}

	public String getConMail() {
		return this.conMail;
	}

	public void setConMail(String conMail) {
		this.conMail = conMail;
	}

	public String getConQq() {
		return this.conQq;
	}

	public void setConQq(String conQq) {
		this.conQq = conQq;
	}

	public String getConMsn() {
		return this.conMsn;
	}

	public void setConMsn(String conMsn) {
		this.conMsn = conMsn;
	}

	public String getConOther() {
		return this.conOther;
	}

	public void setConOther(String conOther) {
		this.conOther = conOther;
	}

	public String getConAddress() {
		return this.conAddress;
	}

	public void setConAddress(String conAddress) {
		this.conAddress = conAddress;
	}

	public String getConZip() {
		return this.conZip;
	}

	public void setConZip(String conZip) {
		this.conZip = conZip;
	}

	public String getIsPrimary() {
		return this.isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(baseSeqId).append(";");
		sb.append(commCode).append(";");
		sb.append(state).append(";");
		sb.append(conType).append(";");
		sb.append(conName).append(";");
		sb.append(conDuty).append(";");
		sb.append(conTel).append(";");
		sb.append(conMobile).append(";");
		sb.append(conFax).append(";");
		sb.append(conMail).append(";");
		sb.append(conQq).append(";");
		sb.append(conMsn).append(";");
		sb.append(conOther).append(";");
		sb.append(conAddress).append(";");
		sb.append(conZip);
		return sb.toString();
	}

}
