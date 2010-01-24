package com.hbs.domain.common.pojo.baseinfo;

/**
 * ContactInfo����.
 * 
 * @author hbs
 * 
 */
public class ContactInfo {

	/**
	 * ���к�.
	 */
	private String seqId;

	/**
	 * ���õĻ�����ϢID
	 */
	private String baseSeqId;

	/**
	 * �ͻ�/��Ӧ�̱���.
	 */
	private String commCode;

	/**
	 * ״̬0----��ʽ����1---��ʱ���ݣ�û���ύ������2---����������3---������ͨ��4---��������.
	 */
	private String state;

	/**
	 * ���1----��ϵ��2----�ջ���.
	 */
	private String conType;

	/**
	 * ��ϵ������.
	 */
	private String conName;

	/**
	 * ��ϵ��ְ��.
	 */
	private String conDuty;

	/**
	 * ��ϵ�˹̶��绰.
	 */
	private String conTel;

	/**
	 * ��ϵ���ƶ��绰.
	 */
	private String conMobile;

	/**
	 * ��ϵ�˴���.
	 */
	private String conFax;

	/**
	 * ��ϵ������.
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
	 * ��ϵ��������Ϣ.
	 */
	private String conOther;

	/**
	 * �ջ���ַ�����ջ�����Ч.
	 */
	private String conAddress;

	/**
	 * �ջ��ʱ࣬���ջ�����Ч.
	 */
	private String conZip;

	/**
	 * �Ƿ�������ϵ��0----��1---����.
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
