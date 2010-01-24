/*
Created		2009-12-7
Modified		2010-1-24
Project		
Model		
Company		
Author		
Version		
Database		mySQL 5 
*/




Create table T_CUST_ORDER_DETAIL (
	ORDER_SEQID Int NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��seqid�����кţ�',
	C_CODE Varchar(32) NOT NULL COMMENT '�ͻ����',
	SHORT_NAME Varchar(32) COMMENT '���',
	PO_NO_TYPE Varchar(2) NOT NULL COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
	PO_NO Varchar(16) NOT NULL COMMENT '�ͻ�������',
	C_PART_NO Varchar(32) NOT NULL COMMENT '�ͻ����ϱ�ţ�����T_CUST_PART_NO_INFO',
	PART_NO Varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
	PN_DESC Varchar(64) NOT NULL COMMENT '��������������T_CUST_PART_NO_INFO',
	C_PRICE Float(8,4) COMMENT '����',
	C_PRICE_TAX Float(6,4) COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
	IS_TAX Varchar(2) COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
	TAX_RATE Float(4,2) COMMENT '˰��',
	SPEC_DESC Varchar(32) COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
	COMM_DESC Varchar(128) COMMENT 'һ�㱸ע',
	AMOUNT Int NOT NULL COMMENT '��������',
	MONEY Float(9,2) COMMENT '�ܽ��',
	DELIVERY_AMOUNT Int DEFAULT 0 COMMENT '�Ѿ���������',
	LOCK_AMOUNT Int DEFAULT 0 COMMENT '�ֿ���������',
	SELF_LOCK_AMOUNT Int DEFAULT 0 COMMENT ' ���ͻ���������',
	COMM_LOCK_AMOUNT Int DEFAULT 0 COMMENT 'ͨ�ÿ����������',
	ORG_DELIVERY_DATE Date COMMENT '����ԭʼ��������',
	PRE_DELIVERY_DATE Date COMMENT 'ҵ�����������Ľ�������',
	VER_DELIVERY_DATE Date COMMENT '�ɹ�������ȷ�Ͻ�������',
	PERIOD Varchar(8) COMMENT '������ϸ��������',
	RLT_ORDER_PO_NO Varchar(128) COMMENT '�����Ĳɹ����ţ�����ɹ�������,�ָ�',
	VENDOR_CODE Varchar(32) COMMENT '��Ӧ�̱���',
	STATE Varchar(8) COMMENT '������ϸ״̬  01---ҵ�񴴽��ͻ�������ϸ��ҵ����ʱ���涩����ϸ���������޷��鿴��  02---ҵ��ȷ�Ͽͻ�������ϸ��������ʽ�����̣������˿��Բ鿴��  03---ҵ��ȡ���ͻ���������ǰ������ϸȡ���������ڶ�����ȡ��ֻ���ڲɹ�ȷ��֮ǰ����Ԥ����ȡ��ֻ���ڲ����յ�Ԥ����֮ǰ)  21 ---������ �����ڶ������ɹ�ȷ�϶�����������ϸΪ���������������״̬���䣩  22----������ȷ�Ϸ�����Ԥ��X%��ʣ��������  60----���ֳ���  61----ȫ������',
	ACTIVE_STATE Varchar(8) COMMENT ' ������ϸ�״̬  PAUSE---ҵ����ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---ҵ�񼤻����������ͣ�Ķ�����ҵ�������',
	RLT_SEND_PO_NO Varchar(128) COMMENT '�����ĳ������ţ����Զ������,�ָ�',
	SETTLEMENT_TYPE Varchar(32) COMMENT '���㷽ʽ',
	STAFF_ID Varchar(20) COMMENT '������ԱID',
	STAFF_NAME Varchar(32) COMMENT '������Ա����',
	SALES_ID Varchar(20) COMMENT '������ԱID',
	SALES Varchar(32) COMMENT '������Ա  ',
 Primary Key (ORDER_SEQID),
 Foreign Key (C_CODE,PO_NO) references T_CUST_ORDER (C_CODE,PO_NO) on delete  restrict on update  restrict
) ENGINE = innodb
COMMENT = '�ͻ�����������ϸ������������';












