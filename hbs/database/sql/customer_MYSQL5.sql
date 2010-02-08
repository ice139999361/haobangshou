# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_customer_info
#

DROP TABLE IF EXISTS `t_customer_info`;
CREATE TABLE `t_customer_info` (
  `BASE_SEQID` int(11) NOT NULL auto_increment,
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����,��ʽGC0001',
  `STATE` varchar(2) NOT NULL COMMENT '�ͻ�״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������  5--ϵͳ����',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '�ͻ����',
  `EN_SHORT_NAME` varchar(32) default NULL COMMENT 'Ӣ�ļ��',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '��˾��������',
  `EN_NAME` varchar(20) default NULL COMMENT '��˾Ӣ������',
  `ADDRESS` varchar(256) default NULL COMMENT '�ͻ���˾��ַ',
  `EN_ADDRESS` varchar(256) default NULL COMMENT '��˾Ӣ�ĵ�ַ',
  `C_TYPE` varchar(32) default NULL COMMENT '���ʣ���̨�ʣ����ʵ�',
  `C_SCALE` varchar(32) default NULL COMMENT '��ģ��1-500�ˣ�����ҵ��',
  `WEBSITE` varchar(128) default NULL COMMENT '�ͻ���˾��ַ',
  `REPRESENTATIVE` varchar(32) default NULL COMMENT '�ͻ����˴���',
  `TAX_CODE` char(128) default NULL COMMENT '�ͻ���˰��ʶ���',
  `COMPANY_BRANCH` varchar(64) default NULL COMMENT '��Ӧ�ķֹ�˾���֧���������ֵ��ѡȡ���ֹ�����',
  `CREDIT_RATE` varchar(8) default NULL COMMENT '�ͻ����öȣ��μ��ֵ����  ',
  `CREDIT_DESC` char(32) default NULL COMMENT '���õȼ�����',
  `IMPORTANT_CODE` varchar(8) default NULL COMMENT '�ͻ�����Ҫ�̶ȣ��μ��ֵ����',
  `IMPORTANT_DESC` char(32) default NULL COMMENT '�ͻ���Ҫ�̶�����',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '�ͻ��������ͣ��μ��ֵ����  1---���ڽ���  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `SETTLEMENT_DESC` varchar(32) default NULL COMMENT '���㷽ʽ����',
  `CURRENCY` varchar(2) default NULL COMMENT '�ͻ�������֣��μ��ֵ��  ',
  `CURRENCY_DESC` varchar(32) default NULL COMMENT '�����������',
  `STAFF_ID` varchar(20) default NULL COMMENT '������ԱID',
  `STAFF_NAME` varchar(20) default NULL COMMENT '������Ա����',
  `COMM_DESC` varchar(512) default NULL COMMENT '�ͻ���ע',
  `VENDOR_CODE` varchar(8) NOT NULL COMMENT '��Ӧ�̱���',
  `CONTACT_FEE` float(6,4) default NULL COMMENT '��ͬ�ѣ��ٷ�ֵ',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '�������Ƿ���ʾ����  0---����ʾ  1---��ʾ',
  `TAX_RATE` float(6,4) default '0.0000' COMMENT '����˰�ʣ��ٷ�ֵ',
  `ASS_STAFF_ID` varchar(20) default NULL COMMENT '��Ӧ��ҵ������ID',
  `ASS_STAFF_NAME` varchar(32) default NULL COMMENT '��Ӧ��ҵ����������',
  `CREATE_TIME` datetime default NULL COMMENT '¼��ʱ��',
  PRIMARY KEY  (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ����ʽ��';


#
# Dumping data for table t_customer_info
#


#
# Table structure for table t_cust_account_period
#

DROP TABLE IF EXISTS `t_cust_account_period`;
CREATE TABLE `t_cust_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '��������  1---�½�  2---���',
  `ACCOUNT_PERIOD` varchar(20) NOT NULL COMMENT '�ͻ����������ã����ڽ�����Ч������½ᣬ��������������������',
  `PERIOD_START` varchar(8) NOT NULL COMMENT '���ڵ���ʼ�գ����ڽ�����Ч',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ�����ĵڼ��գ����ڽ�����Ч',
  `SETTLEMENT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч',
  `MAX_MONEY` float(9,2) NOT NULL COMMENT '�ͻ����ڵ�����׽����ڽ�����Ч',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '�������ã������պͽ�������ǰ��������',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ��������ñ������ͻ��Ľ�����Ϣ';

#
# Dumping data for table t_cust_account_period
#


#
# Table structure for table t_cust_bank
#

DROP TABLE IF EXISTS `t_cust_bank`;
CREATE TABLE `t_cust_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(32) NOT NULL COMMENT '��������',
  `ACCOUNT_BANK` varchar(64) DEFAULT NULL COMMENT '������',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '�ʺ�',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�������Ϣ���������ͻ���������Ϣ';


#
# Dumping data for table t_cust_bank
#


#
# Table structure for table t_cust_order
#

DROP TABLE IF EXISTS `t_cust_order`;
CREATE TABLE `t_cust_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `PO_NO` varchar(16) NOT NULL COMMENT '�ͻ��������',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '�ͻ����',
  `ORDER_TIME` date DEFAULT NULL COMMENT '�ͻ���������',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵ�ˣ���Ӧ�ͻ���ϵ���е�����ϵ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�绰����Ӧ�ͻ�����ϵ�˵ĵ绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '���棬��Ӧ�ͻ�����ϵ�˵Ĵ���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ�ͻ���Ϣ�ж�Ӧ�ķֹ�˾���֧����',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����Ӧ�ͻ���Ϣ�еĽ��㷽ʽ',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ջ��ˣ���Ӧ�ͻ���Ϣ���ջ���',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ����Ӧ�ͻ���Ϣ���ջ���ַ',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ࣬��Ӧ�ͻ���Ϣ�е��ջ��ʱ�',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '¼����ID(ҵ������)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '¼�붩������(ҵ������)',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '����ID',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�ͻ������Ƿ���ʾ���ۣ���̨���ݿͻ���Ϣ�Զ�����',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱��룬�Խ�������',
  `FIRST_CREATE_TIME` datetime DEFAULT NULL COMMENT '����¼��ϵͳ������',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '������ҵ��״̬  01---ҵ�񴴽��ͻ�������ҵ����ʱ���涩�����������޷��鿴��  02---ҵ��ȷ�Ͽͻ�������������ʽ�����̣������˿��Բ鿴��  03---ҵ��ȡ���ͻ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ�����ȡ��ֻ���ڲɹ�ȷ��֮ǰ����Ԥ����ȡ��ֻ���ڲ����յ�Ԥ����֮ǰ)  04---��ҵ��ȷ�Ͻ���(ֻ�����ڶ�����Ч����״̬�ǲɹ��޸Ľ��ں���ʾ��״̬���ȴ�ҵ��ȷ�Ͻ��ڣ����ڵĸ�����Զ�����ϸ��ֻҪ��һ��������ϸ��Ҫȷ�Ͻ��ڣ�����״̬��Ϊ������ȷ��)  05---��ҵ��ȷ�Ϸ�������δ���룬��ҵ��',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '�����Ļ״̬  PAUSE---ҵ����ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---ҵ�񼤻����������ͣ�Ķ�����ҵ�������',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������';

#
# Dumping data for table t_cust_order
#


#
# Table structure for table t_cust_order_detail
#

DROP TABLE IF EXISTS `t_cust_order_detail`;
CREATE TABLE `t_cust_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��SEQID�����кţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
  `PO_NO` varchar(16) NOT NULL COMMENT '�ͻ�������',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������������T_CUST_PART_NO_INFO',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��������',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '�ܽ��',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ���������',
  `LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT '�ֿ���������',
  `SELF_LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT ' ���ͻ���������',
  `COMM_LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT 'ͨ�ÿ����������',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '����ԭʼ��������',
  `PRE_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ҵ�����������Ľ�������',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '�ɹ�������ȷ�Ͻ�������',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������ϸ��������',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����Ĳɹ����ţ�����ɹ�������,�ָ�',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `STATE` varchar(8) DEFAULT NULL COMMENT '������ϸ״̬  01---ҵ�񴴽��ͻ�������ϸ��ҵ����ʱ���涩����ϸ���������޷��鿴��  02---ҵ��ȷ�Ͽͻ�������ϸ��������ʽ�����̣������˿��Բ鿴��  03---ҵ��ȡ���ͻ���������ǰ������ϸȡ���������ڶ�����ȡ��ֻ���ڲɹ�ȷ��֮ǰ����Ԥ����ȡ��ֻ���ڲ����յ�Ԥ����֮ǰ)  21 ---������ �����ڶ������ɹ�ȷ�϶�����������ϸΪ���������������״̬���䣩  22----������ȷ�Ϸ�����Ԥ��X%��ʣ��������  60----���ֳ���  61----ȫ������',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT ' ������ϸ�״̬  PAUSE---ҵ����ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---ҵ�񼤻����������ͣ�Ķ�����ҵ�������',
  `RLT_SEND_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����ĳ������ţ����Զ������,�ָ�',
  `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '������Ӧ�Ĳֿ��ܿ�/�����⣬ȱʡΪ��˾�ܿ�1',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '��������',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '����ԱID(ҵ������)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա������ҵ������',
  `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID(ҵ��Ա)',
  `SALES` varchar(32) DEFAULT NULL COMMENT '������Ա������ҵ��Ա��',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `C_CODE` (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�����������ϸ������������';


#
# Dumping data for table t_cust_order_detail
#


#
# Table structure for table t_cust_order_detail_his
#

DROP TABLE IF EXISTS `t_cust_order_detail_his`;
CREATE TABLE `t_cust_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT 'Ψһ��SEQID�����кţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `PO_NO` varchar(16) NOT NULL COMMENT '�ͻ�������',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������������T_CUST_PART_NO_INFO',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '�ܽ��',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ���������',
  `LOCK_AMOUNT` int(11) DEFAULT '0' COMMENT '�ֿ���������',
  `SELF_LOCK_AMOUNT` int(11) DEFAULT NULL,
  `COMM_LOCK_AMOUNT` int(11) DEFAULT NULL,
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT '����ԭʼ��������',
  `PRE_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ҵ�����������Ľ�������',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT '�ɹ�������ȷ�Ͻ�������',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������ϸ��������',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����Ĳɹ����ţ�����ɹ�������,�ָ�',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `STATE` varchar(8) DEFAULT NULL COMMENT '������ϸ״̬  01---ҵ�񴴽��ͻ�������ϸ��ҵ����ʱ���涩����ϸ���������޷��鿴��  02---ҵ��ȷ�Ͽͻ�������ϸ��������ʽ�����̣������˿��Բ鿴��  03---ҵ��ȡ���ͻ���������ǰ������ϸȡ���������ڶ�����ȡ��ֻ���ڲɹ�ȷ��֮ǰ����Ԥ����ȡ��ֻ���ڲ����յ�Ԥ����֮ǰ)  21 ---������ �����ڶ������ɹ�ȷ�϶�����������ϸΪ���������������״̬���䣩  22----������ȷ�Ϸ�����Ԥ��X%��ʣ��������  60----���ֳ���  61----ȫ������',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT ' ������ϸ�״̬  PAUSE---ҵ����ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---ҵ�񼤻����������ͣ�Ķ�����ҵ�������',
  `RLT_SEND_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����ĳ������ţ����Զ������,�ŷָ�',
  `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '������Ӧ�Ĳֿ��ܿ�/�����⣬ȱʡΪ��˾�ܿ�1',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '��������',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '����ԱID(ҵ������)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա������ҵ������',
  `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID(ҵ��Ա)',
  `SALES` varchar(32) DEFAULT NULL COMMENT '������Ա������ҵ��Ա��'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�����������ϸ��ʷ������������';


#
# Dumping data for table t_cust_order_detail_his
#


#
# Table structure for table t_cust_order_his
#

DROP TABLE IF EXISTS `t_cust_order_his`;
CREATE TABLE `t_cust_order_his` (
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `PO_NO` varchar(16) NOT NULL COMMENT '�ͻ��������',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '�ͻ����',
  `ORDER_TIME` date DEFAULT NULL COMMENT '�ͻ���������',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵ�ˣ���Ӧ�ͻ���ϵ���е�����ϵ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�绰����Ӧ�ͻ�����ϵ�˵ĵ绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '���棬��Ӧ�ͻ�����ϵ�˵Ĵ���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ�ͻ���Ϣ�ж�Ӧ�ķֹ�˾���֧����',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����Ӧ�ͻ���Ϣ�еĽ��㷽ʽ',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ջ��ˣ���Ӧ�ͻ���Ϣ���ջ���',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ����Ӧ�ͻ���Ϣ���ջ���ַ',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ࣬��Ӧ�ͻ���Ϣ�е��ջ��ʱ�',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '¼����ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '¼�붩������',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա',
  `SALES_ID` char(20) DEFAULT NULL,
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�ͻ������Ƿ���ʾ���ۣ���̨���ݿͻ���Ϣ�Զ�����',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱��룬�Խ�������',
  `FIRST_CREATE_TIME` datetime DEFAULT NULL COMMENT '����¼��ϵͳ������',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '������ҵ��״̬  01---ҵ�񴴽��ͻ�������ҵ����ʱ���涩�����������޷��鿴��  02---ҵ��ȷ�Ͽͻ�������������ʽ�����̣������˿��Բ鿴��  03---ҵ��ȡ���ͻ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ�����ȡ��ֻ���ڲɹ�ȷ��֮ǰ����Ԥ����ȡ��ֻ���ڲ����յ�Ԥ����֮ǰ)  04---��ҵ��ȷ�Ͻ���(ֻ�����ڶ�����Ч����״̬�ǲɹ��޸Ľ��ں���ʾ��״̬���ȴ�ҵ��ȷ�Ͻ��ڣ����ڵĸ�����Զ�����ϸ��ֻҪ��һ��������ϸ��Ҫȷ�Ͻ��ڣ�����״̬��Ϊ������ȷ��)  05---��ҵ��ȷ�Ϸ�������δ���룬��ҵ��',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '�����Ļ״̬  PAUSE---ҵ����ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---ҵ�񼤻����������ͣ�Ķ�����ҵ�������',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������ʷ��';

#
# Dumping data for table t_cust_order_his
#


#
# Table structure for table t_cust_part_no_info
#

DROP TABLE IF EXISTS `t_cust_part_no_info`;
CREATE TABLE `t_cust_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '���ϱ��',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `STATE` varchar(8) NOT NULL COMMENT '״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` float(8,4) NOT NULL COMMENT '����',
  `PRICE_TAX` float(6,4) NOT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '����˾�Ĵ���',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '����˾��С��',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��С����������ȱʡΪ0',
  `SAMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '��Ʒ���',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ����ϱ��ͬ����˾���ϱ�Ŷ�Ӧ��';

#
# Dumping data for table t_cust_part_no_info
#


#
# Table structure for table t_cust_prepaid
#

DROP TABLE IF EXISTS `t_cust_prepaid`;
CREATE TABLE `t_cust_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT 'Ԥ���ٷֱ�',
  `REMINDER_DAY` int(11) DEFAULT NULL COMMENT '�Ի�������������г���Ա�߿�',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�Ԥ���ѱ������ͻ��Ľ�����Ϣ';

#
# Dumping data for table t_cust_prepaid
#


#
# Table structure for table t_customer_contact_info
#

DROP TABLE IF EXISTS `t_customer_contact_info`;
CREATE TABLE `t_customer_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '���  1----��ϵ��  2----�ջ���',
  `CON_NAME` varchar(32) NOT NULL COMMENT '��ϵ������',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '��ϵ��ְ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '��ϵ�˹̶��绰',
  `CON_MOBILE` varchar(16) DEFAULT NULL COMMENT '��ϵ���ƶ��绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '��ϵ�˴���',
  `CON_MAIL` varchar(128) DEFAULT NULL COMMENT '��ϵ������',
  `CON_QQ` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `CON_MSN` varchar(32) DEFAULT NULL COMMENT 'MSN',
  `CON_OTHER` varchar(512) DEFAULT NULL COMMENT '��ϵ��������Ϣ',
  `CON_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ�����ջ�����Ч',
  `CON_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ࣬���ջ�����Ч',
  `IS_PRIMARY` varchar(2) DEFAULT NULL COMMENT '�Ƿ�������ϵ�ˣ�����ָ�Ƿ��ǿͻ��Ĳɹ���Ա  0----��  1---����',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���ϵ����Ϣ���ͻ��ջ�����Ϣ';

#
# Dumping data for table t_customer_contact_info
#




#
# Table structure for table t_customer_oper_log
#

DROP TABLE IF EXISTS `t_customer_oper_log`;
CREATE TABLE `t_customer_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(32) DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL COMMENT 'MEMO',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ/���� ��־������';

#
# Dumping data for table t_customer_oper_log
#


#
# Table structure for table t_customer_oper_log_his
#

DROP TABLE IF EXISTS `t_customer_oper_log_his`;
CREATE TABLE `t_customer_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(32) DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ/���� ��־����   ��ʷ��  ����Ϊ����һ�����ʷ��Ϣ������ʷ��';

#
# Dumping data for table t_customer_oper_log_his
#


#
#  Foreign keys for table t_cust_account_period
#

ALTER TABLE `t_cust_account_period`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_cust_bank
#

ALTER TABLE `t_cust_bank`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_cust_order_detail
#

ALTER TABLE `t_cust_order_detail`
  ADD FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_cust_order` (`C_CODE`, `PO_NO`);

#
#  Foreign keys for table t_cust_prepaid
#

ALTER TABLE `t_cust_prepaid`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_customer_contact_info
#

ALTER TABLE `t_customer_contact_info`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
