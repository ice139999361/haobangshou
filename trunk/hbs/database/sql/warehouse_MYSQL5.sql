# MySQL-Front 3.2  (Build 6.11)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES 'gb2312' */;

# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_warehouse_info
#

DROP TABLE IF EXISTS `t_warehouse_info`;
CREATE TABLE `t_warehouse_info` (
  `HOUSE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '�ֿ������;  �������  �ض��ͻ�����',
  `VENDOR_CODE` varchar(32) NOT NULL COMMENT ' ��Ӧ�̱���',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�Ĳɹ�����,Ŀǰ����Ʒ�������',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ��',
  `CUST_CODE` varchar(32) DEFAULT NULL COMMENT '��HOUSE_USEΪ�ض��ͻ�����ʱ������дCUST_CODE',
  `TOTAL_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�ܿ�����������ͳ�����Ҫ����Ӧ�ļӼ�',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����Ŀ�棬�ɹ���Ա�ڴ�����ʱ����һ������������������ʱ���������ܿ��ͬʱ���������ɹ�����ʱ�����������ӣ����ÿ���',
  `USE_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���ÿ�棬���ÿ��+�������=�ܿ��  ���ʱ�����Կ����������ͬʱ�Ӳ���',
  `MAX_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���������0��ʾ������',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��С�������ȱʡΪ0',
  `STATE` varchar(2) NOT NULL COMMENT '���״̬  0---�п��ÿ��  1---�޿��ÿ��',
  PRIMARY KEY (`HOUSE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ���Ϣ��';

#
# Dumping data for table t_warehouse_info
#


#
# Table structure for table t_warehouse_info_his
#

DROP TABLE IF EXISTS `t_warehouse_info_his`;
CREATE TABLE `t_warehouse_info_his` (
  `HOUSE_SEQID` varchar(20) NOT NULL COMMENT '���к�',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '�ֿ������;  �������  �ض��ͻ�����',
  `VENDOR_CODE` varchar(32) NOT NULL COMMENT ' ��Ӧ�̱���',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�Ĳɹ�����',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ��',
  `CUST_CODE` varchar(32) DEFAULT NULL COMMENT '��HOUSE_USEΪ�ض��ͻ�����ʱ������дCUST_CODE',
  `TOTAL_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�ܿ�����������ͳ�����Ҫ����Ӧ�ļӼ�',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����Ŀ�棬�ɹ���Ա�ڴ�����ʱ����һ������������������ʱ���������ܿ��ͬʱ���������ɹ�����ʱ�����������ӣ����ÿ���',
  `USE_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���ÿ�棬���ÿ��+�������=�ܿ��  ���ʱ�����Կ����������ͬʱ�Ӳ���',
  `MAX_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���������0��ʾ������',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��С�������ȱʡΪ0',
  `STATE` varchar(2) NOT NULL COMMENT '���״̬  0---�п��ÿ��  1---�޿��ÿ��',
  PRIMARY KEY (`HOUSE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ���Ϣ��ʷ��';

#
# Dumping data for table t_warehouse_info_his
#


#
# Table structure for table t_warehouse_oper_log
#

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '������',
  `OPER_SUBJECT` varchar(32) NOT NULL COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־��';

#
# Dumping data for table t_warehouse_oper_log
#


#
# Table structure for table t_warehouse_oper_log_his
#

DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '������',
  `OPER_SUBJECT` varchar(32) NOT NULL COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־����ʷ';

#
# Dumping data for table t_warehouse_oper_log_his
#


#
# Table structure for table t_warehouse_receive
#

DROP TABLE IF EXISTS `t_warehouse_receive`;
CREATE TABLE `t_warehouse_receive` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ����ⵥ�ţ���Ӧ���ͻ����ţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date default NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date NOT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ�λ��',
  `OPER_ID` varchar(20) default NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `OPER_TIME` datetime default NULL COMMENT '����ʱ��',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '�ջ�����ע',
  `PERIOD` varchar(8) default NULL COMMENT '��������',
  `STATE` varchar(8) default NULL COMMENT '�ջ�������ⵥ��״̬  01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)  00---�ر���ⵥ  31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) default NULL COMMENT '�״̬  PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����  ',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '���㷽ʽ',
  PRIMARY KEY  (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ���ջ���';


#
# Dumping data for table t_warehouse_receive
#


#
# Table structure for table t_warehouse_receive_detail
#

DROP TABLE IF EXISTS `t_warehouse_receive_detail`;
CREATE TABLE `t_warehouse_receive_detail` (
  `REC_DETAIL_SEQID` int(11) NOT NULL auto_increment COMMENT '�����ϸSEQID,���к�',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ���ͻ�����',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(32) default NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date default NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date default NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) default NULL COMMENT '�ֿ�λ��',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '�ͻ�����Ӧ�Ĳɹ�����',
  `SETTLEMENT_TYPE` varchar(8) NOT NULL default '' COMMENT '���㷽ʽ',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾�����ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�̵����ϱ��',
  `PN_DESC` varchar(64) default NULL COMMENT '��������',
  `C_PRICE` float(8,4) default '0.0000' COMMENT '�ɹ��ĵ���',
  `IS_TAX` varchar(2) default NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) default NULL COMMENT '˰��',
  `AMOUNT` int(11) NOT NULL COMMENT '�����ͻ�����',
  `CUR_MONEY` float(8,2) default '0.00' COMMENT '�����ͻ����',
  `PERIOD` varchar(8) default NULL COMMENT '��������',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0---��ⵥ  1---�˻���',
  `STAFF_ID` varchar(20) default NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) default NULL COMMENT '����Ա����',
  `OPER_TIME` datetime default NULL COMMENT '����ʱ��',
  `STATE` varchar(8) NOT NULL default '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) NOT NULL default 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����',
  `FINANCE_PERIOD` varchar(8) default NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  PRIMARY KEY  (`REC_DETAIL_SEQID`),
  KEY `RLT_RECEIVE_DETAIL` (`REC_PO_NO`),
  CONSTRAINT `t_warehouse_receive_detail_ibfk_1` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ��ϸ��';




#
# Dumping data for table t_warehouse_receive_detail
#


#
# Table structure for table t_warehouse_receive_detail_his
#

DROP TABLE IF EXISTS `t_warehouse_receive_detail_his`;
CREATE TABLE `t_warehouse_receive_detail_his` (
  `REC_DETAIL_SEQID` int(11) NOT NULL COMMENT '�����ϸSEQID,���к�',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ���ͻ�����',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(32) default NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date default NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date default NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) default NULL COMMENT '�ֿ�λ��',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '�ͻ�����Ӧ�Ĳɹ�����',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '���㷽ʽ',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾�����ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�̵����ϱ��',
  `PN_DESC` varchar(64) default NULL COMMENT '��������',
  `C_PRICE` float(8,4) default '0.0000' COMMENT '�ɹ��ĵ���',
  `IS_TAX` varchar(2) default NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` float(4,2) default NULL COMMENT '˰��',
  `AMOUNT` int(11) NOT NULL COMMENT '�����ͻ�����',
  `CUR_MONEY` float(8,2) default '0.00' COMMENT '�����ͻ����',
  `PERIOD` varchar(8) default NULL COMMENT '��������',
  `FINANCE_STATE` varchar(2) default NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `STATE` varchar(8) NOT NULL default '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) NOT NULL default 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����',
  `FINANCE_PERIOD` varchar(8) default NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  PRIMARY KEY  (`REC_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ��ϸ����ʷ';




#
# Dumping data for table t_warehouse_receive_detail_his
#


#
# Table structure for table t_warehouse_receive_his
#

DROP TABLE IF EXISTS `t_warehouse_receive_his`;
CREATE TABLE `t_warehouse_receive_his` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ����ⵥ�ţ���Ӧ���ͻ����ţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date default NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date NOT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ�λ��',
  `OPER_ID` varchar(20) default NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `OPER_TIME` datetime default NULL COMMENT '����ʱ��',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '�ջ�����ע',
  `PERIOD` varchar(8) default NULL COMMENT '��������',
  `STATE` varchar(8) default NULL COMMENT '�ջ�������ⵥ��״̬  01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)  00---�ر���ⵥ  31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) default NULL COMMENT '�״̬  PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����  ',
  `FINALCE_STATE` varchar(2) default NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `SETTLEMENT_TYPE` varchar(8) default NULL COMMENT '���㷽ʽ',
  PRIMARY KEY  (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ���ջ�����ʷ';


#
# Dumping data for table t_warehouse_receive_his
#


#
# Table structure for table t_warehouse_send
#

DROP TABLE IF EXISTS `t_warehouse_send`;
CREATE TABLE `t_warehouse_send` (
  `SEND_PO_NO` varchar(32) NOT NULL COMMENT '�������ţ�ϵͳ�Զ�����������GSYYYYMMDDXXX',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ����',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ�������',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ͻ����ջ���ַ',
  `RECEIVE_ZIP` varchar(8) DEFAULT NULL COMMENT '�ͻ��ջ��ʱ�',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ��˵绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ��˴���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ�Ĺ����ֹ�˾���֧����',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�˵��',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `CREATE_DATE` datetime NOT NULL COMMENT '��������',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `SEND_DESC` varchar(256) DEFAULT NULL COMMENT '��������ע',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `STATE` varchar(16) DEFAULT NULL COMMENT '�ͻ���ҵ��״̬  01---�ֹܴ������ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ�ϳ��ⵥ�����ⵥ����֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ�����ⵥ����Ҫ��ȷ�ϳ��ⵥ֮ǰ������ȷ�ϵĳ��ⵥ���ܲ���)  00---�رճ��ⵥ  31---����ȷ���տ�',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '���ⵥ�״̬  PAUSE---�ֹ���ͣ���ⵥ����ͣ״̬�����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ�ĳ��ⵥ���ֹܲ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '�ͻ���������  0----���ⵥ  1---�˻���',
  PRIMARY KEY (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��������Ϣ�����ⵥ�򷢻�����';

#
# Dumping data for table t_warehouse_send
#


#
# Table structure for table t_warehouse_send_detail
#

DROP TABLE IF EXISTS `t_warehouse_send_detail`;
CREATE TABLE `t_warehouse_send_detail` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�굥���к�',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����������ε�',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '���ϵ���',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�Ƿ���ʾ����',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '��ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���γ������',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `VENDOR_PO_NO` varchar(128) DEFAULT NULL COMMENT '��Ӧ�̵Ĳɹ����ţ����Զ������,�ָ�',
  `STATE` varchar(16) DEFAULT NULL COMMENT 'ҵ��״̬',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '�״̬',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '�ͻ���������  0----���ⵥ  1---�˻���',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  PRIMARY KEY (`SEND_DETAIL_SEQID`),
  KEY `RLT_SEND_DETAIL` (`SEND_PO_NO`),
  CONSTRAINT `RLT_SEND_DETAIL` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ������굥��';



#
# Dumping data for table t_warehouse_send_detail
#


#
# Table structure for table t_warehouse_send_detail_his
#

DROP TABLE IF EXISTS `t_warehouse_send_detail_his`;
CREATE TABLE `t_warehouse_send_detail_his` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL COMMENT '�굥���к�',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����������ε�',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `C_PRICE` float(8,4) DEFAULT NULL COMMENT '���ϵ���',
  `TAX_RATE` float(4,2) DEFAULT NULL COMMENT '˰��',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�Ƿ���ʾ����',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '��ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `CUR_MONEY` float(9,2) DEFAULT NULL COMMENT '���γ������',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `VENDOR_PO_NO` varchar(128) DEFAULT NULL COMMENT '��Ӧ�̵Ĳɹ����ţ����Զ������,�ָ�',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '�ͻ���������  0----���ⵥ  1---�˻���',
  `STATE` varchar(16) DEFAULT NULL COMMENT 'ҵ��״̬',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '�״̬',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '��������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  PRIMARY KEY (`SEND_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ������굥����ʷ';



#
# Dumping data for table t_warehouse_send_detail_his
#


#
# Table structure for table t_warehouse_send_his
#

DROP TABLE IF EXISTS `t_warehouse_send_his`;
CREATE TABLE `t_warehouse_send_his` (
  `SEND_PO_NO` varchar(32) NOT NULL COMMENT '�������ţ�ϵͳ�Զ�����������GSYYYYMMDDXXX',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ����',
  `RECEIVE_NAME` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ�������',
  `RECEIVE_ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ͻ����ջ���ַ',
  `RECEIVE_ZIP` varchar(8) DEFAULT NULL COMMENT '�ͻ��ջ��ʱ�',
  `CON_TEL` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ��˵绰',
  `CON_FAX` varchar(32) DEFAULT NULL COMMENT '�ͻ��ջ��˴���',
  `COMPANY_BRANCH` varchar(128) DEFAULT NULL COMMENT '��Ӧ�Ĺ����ֹ�˾���֧����',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�˵��',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `CREATE_DATE` datetime NOT NULL COMMENT '��������',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `SEND_DESC` varchar(256) DEFAULT NULL COMMENT '��������ע',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `STATE` varchar(16) DEFAULT NULL COMMENT '�ͻ���ҵ��״̬  01---�ֹܴ������ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ�ϳ��ⵥ�����ⵥ����֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ�����ⵥ����Ҫ��ȷ�ϳ��ⵥ֮ǰ������ȷ�ϵĳ��ⵥ���ܲ���)  00---�رճ��ⵥ  31---����ȷ���տ�',
  `ACTIVE_STATE` varchar(8) DEFAULT 'ACTIVE' COMMENT '���ⵥ�״̬  PAUSE---�ֹ���ͣ���ⵥ����ͣ״̬�����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ�ĳ��ⵥ���ֹܲ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_YPE` varchar(2) DEFAULT NULL COMMENT '�ͻ���������  0----���ⵥ  1---�˻���',
  PRIMARY KEY (`SEND_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��������Ϣ�����ⵥ�򷢻�������ʷ';

#
# Dumping data for table t_warehouse_send_his
#


#
#  Foreign keys for table t_warehouse_receive_detail
#

ALTER TABLE `t_warehouse_receive_detail`
  ADD FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`);

#
#  Foreign keys for table t_warehouse_send_detail
#

ALTER TABLE `t_warehouse_send_detail`
  ADD FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
