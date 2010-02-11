# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community


#
# Table structure for table t_vendor_info
#

DROP TABLE IF EXISTS `t_vendor_info`;
CREATE TABLE `t_vendor_info` (
  `BASE_SEQID` int(11) NOT NULL auto_increment COMMENT '   ',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱��룬��ʽGV0001',
  `STATE` varchar(2) NOT NULL COMMENT '״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������  5----����',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '��Ӧ�̼��',
  `EN_SHORT_NAME` varchar(32) DEFAULT NULL COMMENT 'Ӣ�ļ��',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '��������',
  `EN_NAME` varchar(256) DEFAULT NULL COMMENT 'Ӣ������',
  `ADDRESS` varchar(256) DEFAULT NULL COMMENT '��˾��ַ',
  `EN_ADDRESS` varchar(256) DEFAULT NULL COMMENT '��˾Ӣ�ĵ�ַ',
  `C_TYPE` varchar(16) DEFAULT NULL COMMENT '��Ӧ������,�磺̨�ʣ����ʵ�',
  `C_SCALE` varchar(16) DEFAULT NULL COMMENT '��Ӧ�̹�ģ����1-50�ˣ����ģ��',
  `WEBSITE` varchar(128) DEFAULT NULL COMMENT '��˾��ַ',
  `REPRESENTATIVE` varchar(32) DEFAULT NULL COMMENT '���˴���',
  `TAX_CODE` varchar(20) DEFAULT NULL COMMENT '��˰��ʶ���',
  `COMPANY_BRANCH` varchar(64) DEFAULT NULL COMMENT '��Ӧ�ķֹ�˾���֧���������ֵ��ѡȡ���ֹ�����',
  `CREDIT_RATE` varchar(8) DEFAULT NULL COMMENT '���öȣ��μ��ֵ����  ',
  `CREDIT_DESC` varchar(32) DEFAULT NULL COMMENT '���õȼ�����',
  `IMPORTORT_CODE` varchar(8) DEFAULT NULL COMMENT '��Ҫ�̶ȣ��μ��ֵ����',
  `IMPORTANT_DESC` varchar(20) DEFAULT NULL COMMENT '��Ҫ�̶�����',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '�������ͣ��μ��ֵ����  1---���ڽ���  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `SETTLEMENT_DESC` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����',
  `CURRENCY` varchar(8) DEFAULT NULL COMMENT '������֣��μ��ֵ��  ',
  `CURRENCY_DESC` varchar(32) DEFAULT NULL COMMENT '�����������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID(�ɹ�)',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������Ա����(�ɹ�)',
  `COMM_DESC` varchar(512) DEFAULT NULL COMMENT '��ע',
  `CONTACT_FEE` float(4,2) DEFAULT NULL COMMENT '��ͬ�ѣ��ٷ�ֵ',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '�Ƿ���ʾ����  0---����ʾ  1---��ʾ',
  `TAX_RATE` float(4,2) DEFAULT '0.00' COMMENT '˰�ʣ��ٷ�ֵ',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ����Ϣ����ʽ��';

#
# Dumping data for table t_vendor_info
#

#
# Table structure for table t_vendor_account_period
#

DROP TABLE IF EXISTS `t_vendor_account_period`;
CREATE TABLE `t_vendor_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '��������  1---�½�  2---���',
  `ACCOUNT_PERIOD` varchar(32) NOT NULL COMMENT '��Ӧ�̵��������ã����ڽ�����Ч������½ᣬ��������������������',
  `PERIOD_START` varchar(2) NOT NULL COMMENT '���ڵ���ʼ�գ����ڽ�����Ч',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ�����ĵڼ��գ����ڽ�����Ч',
  `SETTLEMENT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч',
  `MAX_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '���ڵ�����׽����ڽ�����Ч,�Թ�Ӧ������',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '�������ã������պͽ�������ǰ��������',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_ACCOUNT_PERIOD` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ���������ñ�������Ӧ�̵Ľ�����Ϣ';

#
# Dumping data for table t_vendor_account_period
#


#
# Table structure for table t_vendor_bank
#

DROP TABLE IF EXISTS `t_vendor_bank`;
CREATE TABLE `t_vendor_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(32) NOT NULL COMMENT '��������',
  `ACCOUNT_BANK` varchar(64) DEFAULT NULL COMMENT '������',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '�ʺ�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_BANK` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ��������Ϣ����������Ӧ�̵�������Ϣ';

#
# Dumping data for table t_vendor_bank
#


#
# Table structure for table t_vendor_contact_info
#

DROP TABLE IF EXISTS `t_vendor_contact_info`;
CREATE TABLE `t_vendor_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '���  1----��ϵ��  2----������',
  `CON_NAME` varchar(32) NOT NULL COMMENT '��ϵ������',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '��ϵ��ְ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '��ϵ�˹̶��绰',
  `CON_MOBILE` varchar(16) DEFAULT NULL COMMENT '��ϵ���ƶ��绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '��ϵ�˴���',
  `CON_MAIL` varchar(128) DEFAULT NULL COMMENT '��ϵ������',
  `CON_QQ` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `CON_MSN` varchar(32) DEFAULT NULL COMMENT 'MSN',
  `CON_OTHER` varchar(512) DEFAULT NULL COMMENT '��ϵ��������Ϣ',
  `CON_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ',
  `CON_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ�',
  `IS_PRIMARY` varchar(2) DEFAULT NULL COMMENT '�Ƿ�������ϵ��  0----��  1---����  ����ָ�Ƿ��ǹ�Ӧ�̵�������Ա',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_CONTACT_INFO` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ϵ����Ϣ����������Ϣ';

#
# Dumping data for table t_vendor_contact_info
#





#
# Table structure for table t_vendor_oper_log
#

DROP TABLE IF EXISTS `t_vendor_oper_log`;
CREATE TABLE `t_vendor_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(20) DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��־��';

#
# Dumping data for table t_vendor_oper_log
#


#
# Table structure for table t_vendor_oper_log_his
#

DROP TABLE IF EXISTS `t_vendor_oper_log_his`;
CREATE TABLE `t_vendor_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(20) DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(20) DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��־��ʷ��';

#
# Dumping data for table t_vendor_oper_log_his
#


#
# Table structure for table t_vendor_order
#

DROP TABLE IF EXISTS `t_vendor_order`;
CREATE TABLE `t_vendor_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `PO_NO` varchar(16) NOT NULL COMMENT '��Ӧ�̶������,ϵͳ�Զ�������ʽ��P������001��P091212001',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '��Ӧ�̶�������0----�ͻ��ɹ���1---�˻���2---���汸���ɹ���3--�ض��ͻ������ɹ���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '���',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��������',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵ�ˣ���Ӧ��Ӧ����ϵ���е�����ϵ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�绰����Ӧ��Ӧ������ϵ�˵ĵ绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '���棬��Ӧ��Ӧ������ϵ�˵Ĵ���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ��Ӧ����Ϣ�ж�Ӧ�ķֹ�˾���֧����',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����Ӧ��Ӧ����Ϣ�еĽ��㷽ʽ',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ջ���',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ�',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `OPER_STAFF` varchar(32) DEFAULT NULL COMMENT '¼�붩������',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա  ����ɹ��Ĳɹ���Ա����Ӧ��Ӧ�̵Ĳɹ���Ա',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ���ʾ���ۣ���̨���ݹ�Ӧ����Ϣ�Զ�����',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����',
  `ACTIVE_SATE` varchar(8) DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����';


#
# Dumping data for table t_vendor_order
#


#
# Table structure for table t_vendor_order_detail
#

DROP TABLE IF EXISTS `t_vendor_order_detail`;

CREATE TABLE `t_vendor_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��seqid�����кţ�',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '��Ӧ�̶�������0----�ͻ��ɹ���1---�˻���2---���汸���ɹ���3--�ض��ͻ������ɹ���',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ�ţ�����T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '���',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ��ջ�����',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ԭʼ��������',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ȷ�Ͻ�������',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����Ŀͻ������ţ������ж���ͻ��������ţ���,�ָ�',
  `STATE` varchar(8) DEFAULT '0' COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '������ϸ�Ļ״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������  ',
  `RLT_REC_PO_NO` varchar(128) DEFAULT NULL COMMENT '��������ⵥ�ţ������ж������,�ŷָ�',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID',
  `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '����������',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '���㷽ʽ',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `RLT_VENDOR_ORDER_PART_NO` (`C_CODE`,`PO_NO`),
  CONSTRAINT `RLT_VENDOR_ORDER_PART_NO` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����������ϸ������������';



#
# Dumping data for table t_vendor_order_detail
#


#
# Table structure for table t_vendor_order_detail_his
#

DROP TABLE IF EXISTS `t_vendor_order_detail_his`;
CREATE TABLE `t_vendor_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT 'Ψһ��seqid�����кţ�',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----����  1---�˻���',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ�ţ�����T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` float(9,2) DEFAULT NULL COMMENT '���',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ��ջ�����',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `ORG_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ԭʼ��������',
  `VER_DELIVERY_DATE` date DEFAULT NULL COMMENT 'ȷ�Ͻ�������',
  `RLT_ORDER_PO_NO` varchar(128) DEFAULT NULL COMMENT '�����Ŀͻ������ţ������ж���ͻ������ţ���,�ָ�',
  `STATE` varchar(8) DEFAULT '0' COMMENT 'ҵ��״̬��  01---�ɹ�����������ϸ���ɹ���ʱ���涩����ϸ���������޷��鿴��  02---�ɹ�ȷ�ϲɹ�������ϸ��������ʽ�����̣������˿��Բ鿴��  03---�ɹ�ȡ���ɹ���������ǰ������ϸȡ��)  04---���ջ����  00---�رղɹ�����  60----�������  61----ȫ�����',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '������ϸ�Ļ״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������  ',
  `RLT_REC_PO_NO` varchar(128) DEFAULT NULL COMMENT '��������ⵥ�ţ������ж������,�ŷָ�',
  `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID',
  `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '����������',
  `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '���㷽ʽ',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  PRIMARY KEY (`ORDER_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����������ϸ��ʷ������������';


#
# Dumping data for table t_vendor_order_detail_his
#


#
# Table structure for table t_vendor_order_his
#

DROP TABLE IF EXISTS `t_vendor_order_his`;
CREATE TABLE `t_vendor_order_his` (
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `PO_NO` varchar(16) NOT NULL COMMENT '��Ӧ�̶������,ϵͳ�Զ�������ʽ��P������001��P091212001',
  `PO_NO_TYPE` varchar(20) DEFAULT NULL COMMENT '��Ӧ�̶�������  0----����  1---�˻���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '���',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��������',
  `CON_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵ�ˣ���Ӧ��Ӧ����ϵ���е�����ϵ��',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�绰����Ӧ��Ӧ������ϵ�˵ĵ绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '���棬��Ӧ��Ӧ������ϵ�˵Ĵ���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ��Ӧ����Ϣ�ж�Ӧ�ķֹ�˾���֧����',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����Ӧ��Ӧ����Ϣ�еĽ��㷽ʽ',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ջ���',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ջ���ַ',
  `RECEIVE_ZIP` varchar(16) DEFAULT NULL COMMENT '�ջ��ʱ�',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `OPER_STAFF` varchar(32) DEFAULT NULL COMMENT '¼�붩������',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա  ����ɹ��Ĳɹ���Ա����Ӧ��Ӧ�̵Ĳɹ���Ա',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ���ʾ���ۣ���̨���ݹ�Ӧ����Ϣ�Զ�����',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '����ҵ��״̬  01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��  02---�ɹ�ȷ�ϲɹ�������������ʽ�����̣������˿��Բ鿴��  03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)  04---�ɹ�ȷ�Ͻ���(����ȷ�Ϻ󣬲ɹ��������޸�)  00---�رղɹ�����  60----�������  61----ȫ�����',
  `ACTIVE_SATE` varchar(8) DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����ʷ��';

#
# Dumping data for table t_vendor_order_his
#


#
# Table structure for table t_vendor_part_no_info
#

DROP TABLE IF EXISTS `t_vendor_part_no_info`;
CREATE TABLE `t_vendor_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ��',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `STATE` varchar(8) NOT NULL COMMENT '״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` float NOT NULL COMMENT '����',
  `PRICE_TAX` float(5,3) NOT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `CREATE_DATE` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '����˾�Ĵ���',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '����˾��С��',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��С����������ȱʡΪ0',
  `MIN_PACKAGE` int(11) DEFAULT NULL COMMENT '��С��װ��λ',
  `SIMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '��Ʒ����',
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�۸��Ƿ�䶯��  0--û��  1--��',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT=' ��Ӧ�����ϱ��ͬ����˾���ϱ�Ŷ�Ӧ��';


#
# Dumping data for table t_vendor_part_no_info
#


#
# Table structure for table t_vendor_prepaid
#

DROP TABLE IF EXISTS `t_vendor_prepaid`;
CREATE TABLE `t_vendor_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT 'Ԥ���ٷֱ�',
  `REMINDER_DAY` varchar(3) DEFAULT NULL COMMENT '������,�Թ�Ӧ����Ч',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_PREPAID` (`BASE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ��Ԥ���ѱ�������Ӧ�̵Ľ�����Ϣ';

#
# Dumping data for table t_vendor_prepaid
#


#
#  Foreign keys for table t_vendor_account_period
#

ALTER TABLE `t_vendor_account_period`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_bank
#

ALTER TABLE `t_vendor_bank`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_contact_info
#

ALTER TABLE `t_vendor_contact_info`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

#
#  Foreign keys for table t_vendor_order_detail
#

ALTER TABLE `t_vendor_order_detail`
  ADD FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`);

#
#  Foreign keys for table t_vendor_prepaid
#

ALTER TABLE `t_vendor_prepaid`
  ADD FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
