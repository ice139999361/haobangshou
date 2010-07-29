-- MySQL dump 10.13  Distrib 5.1.46, for Win32 (ia32)
--
-- Host: 192.168.1.250    Database: hbs
-- ------------------------------------------------------
-- Server version	5.1.46-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `STAFF_ID` int(11) NOT NULL,
  `ACCOUNT` varchar(16) NOT NULL COMMENT '�û��˺�(UNIQUE)',
  `PASSWORD` varchar(32) NOT NULL COMMENT '����',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `LOGOUT_TIME` datetime DEFAULT NULL COMMENT '�ǳ�ʱ��',
  `ENABLED` tinyint(4) NOT NULL DEFAULT '1' COMMENT '�Ƿ���Ч�� ��Ч��0�� ��Ч0��',
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `ACCOUNT` (`ACCOUNT`),
  CONSTRAINT `RLT_FK_STAFF_ID` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='�û��˺ű�';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `action` (
  `ACTIONS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���в���ID(PK,�Զ�����)',
  `ACTION_ID` varchar(32) NOT NULL COMMENT '����ID',
  `ACTION_NAME` varchar(128) NOT NULL COMMENT '������',
  `BUTTON_ID` varchar(32) NOT NULL COMMENT '��Ӧ��ҳ�水ť��ID��',
  `DESCRIPTION` varchar(32) NOT NULL,
  UNIQUE KEY `ACTIONS_ID` (`ACTIONS_ID`,`ACTION_ID`(1))
) ENGINE=InnoDB AUTO_INCREMENT=10201 DEFAULT CHARSET=gbk COMMENT='������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '��ԴID(PK,�Զ�����)',
  `ACTIONS_ID` int(11) DEFAULT NULL COMMENT '����Դҳ������в���ID��FK->ACTION.ACTIONS_ID)',
  `RESOURCE_NAME` varchar(64) NOT NULL COMMENT '��Դ��',
  `DESCRIPTION` varchar(64) DEFAULT NULL COMMENT '����',
  `URL_ADDRESS` varchar(256) DEFAULT NULL COMMENT '��Ӧ��url��ַ',
  `PARENT` int(11) NOT NULL DEFAULT '0' COMMENT '��Դ�ĸ��ӹ�ϵ������Ϊ0',
  `IS_MENU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`),
  KEY `RLT_FK_ACTIONS_ID` (`ACTIONS_ID`),
  CONSTRAINT `RLT_FK_ACTIONS_ID` FOREIGN KEY (`ACTIONS_ID`) REFERENCES `action` (`ACTIONS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10201 DEFAULT CHARSET=gbk COMMENT='��Դ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '��ɫID(PK,�Զ�����)',
  `ROLE_NAME` varchar(64) NOT NULL COMMENT '��ɫ��(UNIQUE)',
  `MEMO` varchar(256) DEFAULT NULL COMMENT 'ע��',
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME` (`ROLE_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk COMMENT='��ɫ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_resource`
--

DROP TABLE IF EXISTS `role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_resource` (
  `ROLE_ID` int(11) NOT NULL COMMENT '��ɫID(FK->ROLE.ROLE_ID)',
  `RESOURCE_ID` int(11) NOT NULL COMMENT '��ԴID(FK->RESOURCE.ID)',
  `OPERATIONS` varchar(1024) NOT NULL COMMENT '������OPERATION_ID���ж�������ð�Ƕ��Ÿ���.����ACTION��',
  KEY `RLT_ROLE_ID` (`ROLE_ID`),
  KEY `RLT_RESOURCE_ID` (`RESOURCE_ID`),
  CONSTRAINT `RLT_RESOURCE_ID` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `resource` (`RESOURCE_ID`),
  CONSTRAINT `RLT_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��ɫȨ�޶�Ӧ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `STAFF_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�û�ID(PK,�Զ�����)',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '�û���(UNIQUE)',
  `IDENTITY_NUMBER` varchar(32) DEFAULT NULL COMMENT '���֤����',
  `GENDER` varchar(4) DEFAULT NULL COMMENT '�Ա�',
  `BIRTH_DATE` varchar(16) DEFAULT NULL COMMENT '��������',
  `PHONE` varchar(32) DEFAULT NULL COMMENT '�绰',
  `MOBILE` varchar(12) DEFAULT NULL COMMENT '�ֻ�',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '����',
  `DUTY` varchar(32) DEFAULT NULL COMMENT 'ְ��',
  `MEMO` varchar(256) DEFAULT NULL COMMENT 'ע��',
  PRIMARY KEY (`STAFF_ID`),
  UNIQUE KEY `STAFF_NAME` (`STAFF_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk COMMENT='�û�������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_adjust_info`
--

DROP TABLE IF EXISTS `t_adjust_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_adjust_info` (
  `APPLY_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�������к�',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������',
  `APPLY_DATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '���ϵĶ����ţ���Ӧ�̵Ĳɹ�����,ĿǰΪNULL',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '����Ĺ�˾����PART_NO',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '�������ϵ�����',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�λ��',
  `APPLY_AMOUNT` int(11) DEFAULT NULL COMMENT '����ĵ�������',
  `VENDOR_CODE` varchar(32) DEFAULT NULL COMMENT '���ϵĹ�Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '��Ӧ�̼��',
  `APPLY_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `FROM_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '���Ǹ��ͻ��������',
  `FROM_C_NAME` varchar(64) DEFAULT NULL COMMENT '�ӿͻ����',
  `TO_CUST_CODE` varchar(32) DEFAULT NULL COMMENT '�����Ǹ��ͻ�code��',
  `TO_C_NAME` varchar(64) DEFAULT NULL COMMENT '���ͻ����',
  `AUDIT_STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `AUDIT_STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����������',
  `AUDIT_DATE` datetime DEFAULT NULL COMMENT '����ʱ��',
  `AUDIT_AGREE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ�ͬ��  0---ͬ��  1---��ͬ��',
  `AUDIT_CONTENT` varchar(128) DEFAULT NULL COMMENT '�����ľ������',
  PRIMARY KEY (`APPLY_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='���������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_company_part_no`
--

DROP TABLE IF EXISTS `t_company_part_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_company_part_no` (
  `PART_NO` varchar(32) NOT NULL COMMENT '���ϱ��',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` float(8,4) DEFAULT '0.0000' COMMENT '�۸�',
  `TAX_PRICE` float(8,4) DEFAULT '0.0000' COMMENT '��˰�۸�',
  `CREATE_TIME` date NOT NULL COMMENT '����ʱ��',
  `P_CLS_CODE` varchar(20) DEFAULT NULL COMMENT '������Ʒ���',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`PART_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='��˾������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_config_encode`
--

DROP TABLE IF EXISTS `t_config_encode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_config_encode` (
  `ENCODE_TYPE` varchar(128) NOT NULL COMMENT '��������',
  `ENCODE_KEY` varchar(128) NOT NULL COMMENT '������',
  `ENCODE_VALUE` varchar(1024) NOT NULL COMMENT '����ֵ',
  `ENCODE_DESC` varchar(256) DEFAULT NULL COMMENT '����',
  `IS_VALID` varchar(1) NOT NULL DEFAULT '1' COMMENT '�Ƿ���Ч  1����Ч  2����Ч',
  `SORT_ID` varchar(3) NOT NULL COMMENT '�����  ͬһ�������ֵ����ֵ���������',
  PRIMARY KEY (`ENCODE_TYPE`,`ENCODE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='�ֵ�����';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_account_period`
--

DROP TABLE IF EXISTS `t_cust_account_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '��������  1---�½�  2---���',
  `ACCOUNT_PERIOD` varchar(20) NOT NULL COMMENT '�ͻ����������ã����ڽ�����Ч������½ᣬ��������������������',
  `PERIOD_START` varchar(8) NOT NULL COMMENT '���ڵ���ʼ�գ����ڽ�����Ч',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ�����ĵڼ��գ����ڽ�����Ч',
  `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч',
  `MAX_MONEY` float(12,2) DEFAULT NULL COMMENT '�ͻ����ڵ�����׽����ڽ�����Ч',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '�������ã������պͽ�������ǰ��������',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_account_period_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=gb2312 COMMENT='�ͻ��������ñ������ͻ��Ľ�����Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_bank`
--

DROP TABLE IF EXISTS `t_cust_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ACCOUNT_NAME` varchar(256) NOT NULL DEFAULT '' COMMENT '��������',
  `ACCOUNT_BANK` varchar(256) DEFAULT NULL COMMENT '������',
  `ACCOUNT` varchar(128) DEFAULT NULL COMMENT '�ʺ�',
  `BASE_SEQID` int(11) NOT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`),
  CONSTRAINT `t_cust_bank_ibfk_2` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=gb2312 COMMENT='�ͻ�������Ϣ���������ͻ���������Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order`
--

DROP TABLE IF EXISTS `t_cust_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order` (
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '�ͻ��������',
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
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '�½����ͣ����½���Ч',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '�½�����',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_detail`
--

DROP TABLE IF EXISTS `t_cust_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��SEQID�����кţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
  `PO_NO` varchar(50) NOT NULL DEFAULT '' COMMENT '�ͻ�������',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������������T_CUST_PART_NO_INFO',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��������',
  `MONEY` decimal(12,4) DEFAULT NULL COMMENT '�ܽ��',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ���������',
  `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����������Ŀ',
  `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���ͻ�����������Ŀ',
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
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '��ͬ��',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `ORDERAMOUNT` int(11) DEFAULT '0' COMMENT '���¶�������',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `C_CODE` (`C_CODE`,`PO_NO`),
  CONSTRAINT `t_cust_order_detail_ibfk_1` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_cust_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�����������ϸ������������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_detail_his`
--

DROP TABLE IF EXISTS `t_cust_order_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT 'Ψһ��SEQID�����кţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `PO_NO` varchar(16) NOT NULL COMMENT '�ͻ�������',
  `PO_NO_TYPE` varchar(2) DEFAULT NULL COMMENT '�ͻ���������  0----�ͻ�����  1---�ͻ��˻���',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������������T_CUST_PART_NO_INFO',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` decimal(12,4) DEFAULT NULL COMMENT '�ܽ��',
  `DELIVERY_AMOUNT` int(11) DEFAULT '0' COMMENT '�Ѿ���������',
  `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����������Ŀ',
  `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '���ͻ�����������Ŀ',
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
  `SALES` varchar(32) DEFAULT NULL COMMENT '������Ա������ҵ��Ա��',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '��ͬ��',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `ORDERAMOUNT` int(11) DEFAULT '0' COMMENT '���¶�������'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ�����������ϸ��ʷ������������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_order_his`
--

DROP TABLE IF EXISTS `t_cust_order_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '�½����ͣ����½���Ч',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '�½�����',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������ʷ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_part_no_info`
--

DROP TABLE IF EXISTS `t_cust_part_no_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '���ϱ��',
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `STATE` varchar(8) NOT NULL COMMENT '״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '����',
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
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�۸��Ƿ�䶯��  0--û��  1--��',
  `C_SHORT_NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=gb2312 COMMENT='�ͻ����ϱ��ͬ����˾���ϱ�Ŷ�Ӧ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_prepaid`
--

DROP TABLE IF EXISTS `t_cust_prepaid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT 'Ԥ���ٷֱ�',
  `REMINDER_DAY` int(11) DEFAULT NULL COMMENT '�Ի�������������г���Ա�߿�',
  PRIMARY KEY (`SEQID`),
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_cust_prepaid_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=gb2312 COMMENT='�ͻ�Ԥ���ѱ������ͻ��Ľ�����Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_send_invoice`
--

DROP TABLE IF EXISTS `t_cust_send_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_send_invoice` (
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_cust_send_invoice_his`
--

DROP TABLE IF EXISTS `t_cust_send_invoice_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_cust_send_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_contact_info`
--

DROP TABLE IF EXISTS `t_customer_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_contact_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `CON_TYPE` varchar(2) DEFAULT NULL COMMENT '���  1----��ϵ��  2----�ջ���',
  `CON_NAME` varchar(32) NOT NULL COMMENT '��ϵ������',
  `CON_DUTY` varchar(32) NOT NULL COMMENT '��ϵ��ְ��',
  `CON_TEL` varchar(64) DEFAULT NULL COMMENT '��ϵ�˹̶��绰',
  `CON_MOBILE` varchar(32) DEFAULT NULL COMMENT '��ϵ���ƶ��绰',
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
  KEY `BASE_SEQID` (`BASE_SEQID`),
  CONSTRAINT `t_customer_contact_info_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_customer_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=776 DEFAULT CHARSET=gb2312 COMMENT='�ͻ���ϵ����Ϣ���ͻ��ջ�����Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_info`
--

DROP TABLE IF EXISTS `t_customer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_info` (
  `BASE_SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����,��ʽGC0001',
  `STATE` varchar(2) NOT NULL COMMENT '�ͻ�״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������  5--ϵͳ����',
  `SHORT_NAME` varchar(32) NOT NULL COMMENT '�ͻ����',
  `EN_SHORT_NAME` varchar(32) DEFAULT NULL COMMENT 'Ӣ�ļ��',
  `ALL_NAME` varchar(256) NOT NULL COMMENT '��˾��������',
  `EN_NAME` varchar(256) DEFAULT NULL COMMENT '��˾Ӣ������',
  `ADDRESS` varchar(256) DEFAULT NULL COMMENT '�ͻ���˾��ַ',
  `EN_ADDRESS` varchar(256) DEFAULT NULL COMMENT '��˾Ӣ�ĵ�ַ',
  `C_TYPE` varchar(32) DEFAULT NULL COMMENT '���ʣ���̨�ʣ����ʵ�',
  `C_SCALE` varchar(32) DEFAULT NULL COMMENT '��ģ��1-500�ˣ�����ҵ��',
  `WEBSITE` varchar(128) DEFAULT NULL COMMENT '�ͻ���˾��ַ',
  `REPRESENTATIVE` varchar(32) DEFAULT NULL COMMENT '�ͻ����˴���',
  `TAX_CODE` char(128) DEFAULT NULL COMMENT '�ͻ���˰��ʶ���',
  `COMPANY_BRANCH` varchar(64) DEFAULT NULL COMMENT '��Ӧ�ķֹ�˾���֧���������ֵ��ѡȡ���ֹ�����',
  `CREDIT_RATE` varchar(8) DEFAULT NULL COMMENT '�ͻ����öȣ��μ��ֵ����  ',
  `CREDIT_DESC` char(32) DEFAULT NULL COMMENT '���õȼ�����',
  `IMPORTANT_CODE` varchar(8) DEFAULT NULL COMMENT '�ͻ�����Ҫ�̶ȣ��μ��ֵ����',
  `IMPORTANT_DESC` char(32) DEFAULT NULL COMMENT '�ͻ���Ҫ�̶�����',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '�ͻ��������ͣ��μ��ֵ����  1---���ڽ���  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `SETTLEMENT_DESC` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ����',
  `CURRENCY` varchar(2) DEFAULT NULL COMMENT '�ͻ�������֣��μ��ֵ��  ',
  `CURRENCY_DESC` varchar(32) DEFAULT NULL COMMENT '�����������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `STAFF_NAME` varchar(20) DEFAULT NULL COMMENT '������Ա����',
  `COMM_DESC` varchar(512) DEFAULT NULL COMMENT '�ͻ���ע',
  `VENDOR_CODE` varchar(8) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '��ͬ�ѣ��ٷ�ֵ',
  `IS_SHOW_PRICE` varchar(2) NOT NULL COMMENT '�������Ƿ���ʾ����  0---����ʾ  1---��ʾ',
  `TAX_RATE` float(6,4) DEFAULT '0.0000' COMMENT '����˰�ʣ��ٷ�ֵ',
  `ASS_STAFF_ID` varchar(20) DEFAULT NULL COMMENT '��Ӧ��ҵ������ID',
  `ASS_STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '��Ӧ��ҵ����������',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '¼��ʱ��',
  `SALE_TYPE` varchar(32) DEFAULT NULL COMMENT '��ҵ����',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ����ʽ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_oper_log`
--

DROP TABLE IF EXISTS `t_customer_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL COMMENT 'MEMO',
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=716 DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ/���� ��־������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_oper_log_his`
--

DROP TABLE IF EXISTS `t_customer_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���Ϣ/���� ��־����   ��ʷ��  ����Ϊ����һ�����ʷ��Ϣ������ʷ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_customer_settlement`
--

DROP TABLE IF EXISTS `t_customer_settlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '���㷽ʽ   1--�½�  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `ABSTRACT` varchar(16) NOT NULL COMMENT 'ժҪ  ���½�Ϊ����  �Է��½�Ϊ������',
  `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '�ܽ��',
  `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '��������',
  `SET_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '�ѽ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '����״̬  1 �Ѷ���δ����  2���ֽ���  3�ѽ���',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `SALES_NAME` varchar(32) DEFAULT NULL COMMENT '��������',
  `ASS_ID` varchar(20) DEFAULT NULL COMMENT 'ҵ������ID',
  `ASS_NAME` varchar(32) DEFAULT NULL COMMENT 'ҵ����������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_period_spec_memo`
--

DROP TABLE IF EXISTS `t_period_spec_memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_period_spec_memo` (
  `COMM_CODE` varchar(32) NOT NULL COMMENT '�ͻ���Ӧ�̱���',
  `COMM_SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ���Ӧ�̼��',
  `COMM_TYPE` int(11) NOT NULL COMMENT '��ʾ��Ӧ�̻��ǿͻ�  0---�ͻ�  1---��Ӧ��',
  `CREATE_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `MEMO` varchar(512) NOT NULL COMMENT '���ڽ��㱸ע'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�������ڽ��㱸ע��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_product_class`
--

DROP TABLE IF EXISTS `t_product_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_class` (
  `P_CLS_CODE` int(11) NOT NULL AUTO_INCREMENT COMMENT '��Ʒ������',
  `P_CLS_NAME` varchar(256) NOT NULL COMMENT '��Ʒ�������',
  `P_CLS_DESC` varchar(256) DEFAULT NULL COMMENT '��Ʒ�����������',
  `PARENT_CLS_CODE` int(11) NOT NULL DEFAULT '0' COMMENT '�������������� ��Ϊ0��˵��û�и������������',
  `P_CLS_LEVEL` varchar(2) NOT NULL COMMENT '��Ʒ�ֲ㼶��һ������������Ϊ0  �����Ϊ��1---һ�� /2---����/3---����/4---�ļ�  ���趨�嵽�ֵ����',
  PRIMARY KEY (`P_CLS_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=1101 DEFAULT CHARSET=gbk COMMENT='��Ʒ�����';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sys_sequence`
--

DROP TABLE IF EXISTS `t_sys_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_sequence` (
  `SEQ_TYPE` varchar(16) NOT NULL COMMENT 'SEQENCE����',
  `SEQ_KEY` varchar(16) NOT NULL COMMENT 'KEYֵ',
  `SEQ_VALUE` int(11) NOT NULL DEFAULT '0' COMMENT '��ǰ����ֵ',
  `SEQ_LENGTH` int(11) NOT NULL DEFAULT '2' COMMENT '����ֵҪ�󳤶�',
  `SEQ_PREFIX` varchar(16) NOT NULL COMMENT 'ǰ׺',
  `SEQ_MIDDLE` varchar(16) NOT NULL COMMENT '�ж�'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='ϵͳʹ�õ�����sequenceid�Ļ�ȡ�ʹ洢';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_system_config`
--

DROP TABLE IF EXISTS `t_system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_system_config` (
  `CONFIG_NAME` varchar(128) NOT NULL COMMENT '��������',
  `CONFIG_VALUE` varchar(256) NOT NULL COMMENT '����ֵ',
  `VALUE_TYPE` int(11) NOT NULL COMMENT 'ȡֵ���ͣ�  0��ʾ���ͣ�  1��ʾ�ַ����ͣ�  2��ʾ�����ͣ�  3��ʾ�����ͣ�',
  `VALUE_RANGE` varchar(128) DEFAULT NULL COMMENT 'ȡֵ��Χ',
  `CONFIG_DESC` varchar(128) DEFAULT NULL COMMENT '��������',
  `VISIBLE_FLAG` varchar(1) DEFAULT 'Y' COMMENT '�Ƿ�ɼ���     N�����ɼ�     Y���ɼ�',
  `CONFIG_REGION` varchar(128) DEFAULT NULL COMMENT '����վ�㣬ALL',
  `SUB_SYSTEM` varbinary(32) DEFAULT NULL COMMENT '������ϵͳ����',
  `LAST_UPDATE` datetime DEFAULT NULL COMMENT '����޸�ʱ��',
  PRIMARY KEY (`CONFIG_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='ϵͳ�������ñ�';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_account_period`
--

DROP TABLE IF EXISTS `t_vendor_account_period`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_account_period` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_TYPE` varchar(2) NOT NULL COMMENT '��������  1---�½�  2---���',
  `ACCOUNT_PERIOD` varchar(32) NOT NULL COMMENT '��Ӧ�̵��������ã����ڽ�����Ч������½ᣬ��������������������',
  `PERIOD_START` varchar(8) DEFAULT NULL COMMENT '���ڵ���ʼ�գ����ڽ�����Ч,��ʽΪ��YYYYMMDD',
  `ACCOUNT_DAY` varchar(2) NOT NULL COMMENT '�����գ����ڽ�����ĵڼ��գ����ڽ�����Ч',
  `SETTLEMENT_DAY` varchar(4) NOT NULL DEFAULT '' COMMENT '�����գ����ڽ����ĵڼ��գ�����ڶ����գ������ڽ�����Ч',
  `MAX_MONEY` int(11) NOT NULL DEFAULT '0' COMMENT '���ڵ�����׽����ڽ�����Ч,�Թ�Ӧ������',
  `REMINDER_DAY` varchar(20) NOT NULL COMMENT '�������ã������պͽ�������ǰ��������',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_ACCOUNT_PERIOD` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_account_period_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=gb2312 COMMENT='��Ӧ���������ñ�������Ӧ�̵Ľ�����Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_bank`
--

DROP TABLE IF EXISTS `t_vendor_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_bank` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(256) NOT NULL DEFAULT '' COMMENT '��������',
  `ACCOUNT_BANK` varchar(256) DEFAULT NULL COMMENT '������',
  `ACCOUNT` varchar(256) DEFAULT NULL COMMENT '�ʺ�',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_BANK` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_bank_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=gb2312 COMMENT='��Ӧ��������Ϣ����������Ӧ�̵�������Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_contact_info`
--

DROP TABLE IF EXISTS `t_vendor_contact_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `RLT_VENDOR_CONTACT_INFO` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_contact_info_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=gb2312 COMMENT='��ϵ����Ϣ����������Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_info`
--

DROP TABLE IF EXISTS `t_vendor_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_info` (
  `BASE_SEQID` int(11) NOT NULL AUTO_INCREMENT,
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
  `IMPORTANT_CODE` varchar(8) DEFAULT NULL COMMENT '��Ҫ�̶ȣ��μ��ֵ����',
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
  `TAX_RATE` decimal(6,4) DEFAULT '0.0000' COMMENT '˰�ʣ��ٷ�ֵ',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '¼��ʱ��',
  `SALE_TYPE` varchar(32) DEFAULT NULL COMMENT '��ҵ����',
  PRIMARY KEY (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=gb2312 COMMENT='��Ӧ����Ϣ����ʽ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_oper_log`
--

DROP TABLE IF EXISTS `t_vendor_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=gb2312 COMMENT='��־��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_oper_log_his`
--

DROP TABLE IF EXISTS `t_vendor_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(20) DEFAULT NULL,
  `STAFF_NAME` varchar(20) DEFAULT NULL,
  `OPER_TYPE` varchar(20) DEFAULT NULL COMMENT '��������  ADD  MOD  DEL  AUDIT',
  `OPER_OBJECT` varchar(128) DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER',
  `OPER_KEY` varchar(128) DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO',
  `OPER_CONTENT` varchar(256) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��־��ʷ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order`
--

DROP TABLE IF EXISTS `t_vendor_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '¼�붩������',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա  ����ɹ��Ĳɹ���Ա����Ӧ��Ӧ�̵Ĳɹ���Ա',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ���ʾ���ۣ���̨���ݹ�Ӧ����Ϣ�Զ�����',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������',
  `CUST_C_CODE` varchar(32) DEFAULT NULL COMMENT '���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч',
  `HASTEN_REMINDER` int(11) DEFAULT NULL COMMENT '׷������/��',
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '�½����ͣ����½���Ч',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '�½�����',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_detail`
--

DROP TABLE IF EXISTS `t_vendor_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_order_detail` (
  `ORDER_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Ψһ��seqid�����кţ�',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '��Ӧ�̶�������0----�ͻ��ɹ���1---�˻���2---���汸���ɹ���3--�ض��ͻ������ɹ���',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ�ţ�����T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '���',
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
  `HASTEN_REMINDER` int(11) DEFAULT NULL COMMENT '׷������/��',
  PRIMARY KEY (`ORDER_SEQID`),
  KEY `RLT_VENDOR_ORDER_PART_NO` (`C_CODE`,`PO_NO`),
  CONSTRAINT `RLT_VENDOR_ORDER_PART_NO` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`),
  CONSTRAINT `t_vendor_order_detail_ibfk_1` FOREIGN KEY (`C_CODE`, `PO_NO`) REFERENCES `t_vendor_order` (`C_CODE`, `PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����������ϸ������������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_detail_his`
--

DROP TABLE IF EXISTS `t_vendor_order_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_order_detail_his` (
  `ORDER_SEQID` int(11) NOT NULL COMMENT 'Ψһ��seqid�����кţ�',
  `C_CODE` varchar(32) NOT NULL,
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----����  1---�˻���',
  `PO_NO` varchar(16) NOT NULL,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ�ţ�����T_VENDOR_PART_NO_INFO',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ�ţ�����T_CUST_PART_NO_INFO',
  `PN_DESC` varchar(64) NOT NULL COMMENT '��������',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '����',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `COMM_DESC` varchar(128) DEFAULT NULL COMMENT 'һ�㱸ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '���',
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_order_his`
--

DROP TABLE IF EXISTS `t_vendor_order_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '¼�붩������',
  `SALES` varchar(32) DEFAULT NULL COMMENT '����ͻ���������Ա����Ӧ�ͻ���Ϣ��������Ա  ����ɹ��Ĳɹ���Ա����Ӧ��Ӧ�̵Ĳɹ���Ա',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�����Ƿ���ʾ���ۣ���̨���ݹ�Ӧ����Ϣ�Զ�����',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '��ʽ�γɶ������ڣ�ҵ��Ա��ʽ�ύ���ڣ�',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '������������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '����ҵ��״̬  01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��  02---�ɹ�ȷ�ϲɹ�������������ʽ�����̣������˿��Բ鿴��  03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)  04---�ɹ�ȷ�Ͻ���(����ȷ�Ϻ󣬲ɹ��������޸�)  00---�رղɹ�����  60----�������  61----ȫ�����',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������',
  `SETTLEMENT_DAY` varchar(16) DEFAULT NULL COMMENT '�½����ͣ����½���Ч',
  `SETTLEMENT_DAYDESC` varchar(32) DEFAULT NULL COMMENT '�½�����',
  PRIMARY KEY (`C_CODE`,`PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲ɹ�����ʷ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_part_no_info`
--

DROP TABLE IF EXISTS `t_vendor_part_no_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_part_no_info` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�����ϱ��',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `STATE` varchar(8) NOT NULL COMMENT '״̬  0----��ʽ����  1---��ʱ���ݣ�û���ύ������  2---����������  3---������ͨ��  4---��������',
  `PN_DESC` varchar(128) NOT NULL COMMENT '��������',
  `PRICE` decimal(12,10) NOT NULL DEFAULT '0.0000000000' COMMENT '����',
  `PRICE_TAX` decimal(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `CREATE_DATE` date DEFAULT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) DEFAULT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '������',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `P_CAT_CODE` varchar(32) DEFAULT NULL COMMENT '����˾�Ĵ���',
  `P_CLS_CODE` varchar(32) DEFAULT NULL COMMENT '����˾��С��',
  `MIN_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '��С����������ȱʡΪ0',
  `MIN_PACKAGE` int(11) DEFAULT NULL COMMENT '��С��װ��λ',
  `SAMPLE_CODE` varchar(32) DEFAULT NULL COMMENT '��Ʒ����',
  `IS_PRICECHANGE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�۸��Ƿ�䶯��  0--û��  1--��',
  `C_SHORT_NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=459 DEFAULT CHARSET=gb2312 COMMENT=' ��Ӧ�����ϱ��ͬ����˾���ϱ�Ŷ�Ӧ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_prepaid`
--

DROP TABLE IF EXISTS `t_vendor_prepaid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_prepaid` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT,
  `PRE_PAID` varchar(3) DEFAULT NULL COMMENT 'Ԥ���ٷֱ�',
  `REMINDER_DAY` varchar(3) DEFAULT NULL COMMENT '������,�Թ�Ӧ����Ч',
  `BASE_SEQID` int(11) NOT NULL,
  `C_CODE` varchar(32) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`SEQID`),
  KEY `RLT_VENDOR_PREPAID` (`BASE_SEQID`),
  CONSTRAINT `t_vendor_prepaid_ibfk_1` FOREIGN KEY (`BASE_SEQID`) REFERENCES `t_vendor_info` (`BASE_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=gb2312 COMMENT='��Ӧ��Ԥ���ѱ�������Ӧ�̵Ľ�����Ϣ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_receive_invoice`
--

DROP TABLE IF EXISTS `t_vendor_receive_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_receive_invoice` (
  `INVOICE_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_receive_invoice_his`
--

DROP TABLE IF EXISTS `t_vendor_receive_invoice_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_receive_invoice_his` (
  `INVOICE_SEQID` int(11) NOT NULL COMMENT '���к�',
  `STAFF_ID` varchar(20) NOT NULL COMMENT '������ID',
  `STAFF_NAME` varchar(32) NOT NULL COMMENT '����������',
  `CREATE_TIME` datetime NOT NULL COMMENT '����Ʊʱ��',
  `PO_NO` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `PO_NO_DATE` varchar(16) DEFAULT NULL COMMENT '�ͻ�����',
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '���',
  `PART_NO` varchar(32) DEFAULT NULL COMMENT '��˾���ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PART_NO` varchar(32) DEFAULT NULL COMMENT '��Ӧ�����ϱ��',
  `AMOUNT` int(11) DEFAULT NULL COMMENT '��������',
  `ALL_MONEY` decimal(10,2) DEFAULT NULL COMMENT '�ܺ�˰���',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���ο�Ʊ���',
  `LEFT_MONEY` decimal(10,2) DEFAULT NULL COMMENT 'δ��Ʊ���',
  `INVOICE_DESC` varchar(512) DEFAULT NULL COMMENT '��ע����Ҫ��д��Ʊ�ţ�Ҳ������д��������',
  PRIMARY KEY (`INVOICE_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̷�Ʊ��¼����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_vendor_settlement`
--

DROP TABLE IF EXISTS `t_vendor_settlement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_vendor_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '���㷽ʽ   1--�½�  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `ABSTRACT` varchar(16) NOT NULL COMMENT 'ժҪ  ���½�Ϊ����  �Է��½�Ϊ������',
  `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '�ܽ��',
  `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '��������',
  `SET_MONEY` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '�ѽ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '����״̬  1 �Ѷ���δ����  2���ֽ���  3�ѽ���',
  `PURCHASE_ID` varchar(20) DEFAULT NULL COMMENT '�ɹ�ԱID',
  `PURCHASE_NAME` varchar(32) DEFAULT NULL COMMENT '�ɹ�Ա����',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲�������';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_waittask_config_info`
--

DROP TABLE IF EXISTS `t_waittask_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_waittask_config_info` (
  `CONFIG_ID` varchar(20) NOT NULL COMMENT '���ñ��',
  `TASK_TYPE` varchar(3) NOT NULL COMMENT '��������:   0----ҵ�����  1---���Ѵ���  ',
  `BUSINESS_TYPE` varchar(64) NOT NULL COMMENT '�����ҵ������',
  `URL` varchar(512) NOT NULL COMMENT '���������URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '��������',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT '��ϵͳ����',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '���촦���˵Ľ�ɫID',
  PRIMARY KEY (`CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='������Ϣ���ñ�';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_waittask_info`
--

DROP TABLE IF EXISTS `t_waittask_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_waittask_info` (
  `TASK_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '������',
  `TASK_TYPE` varchar(30) NOT NULL COMMENT '��������:   0----ҵ�����  1---���Ѵ��� ',
  `BUSINESS_KEY` varchar(128) NOT NULL COMMENT 'ҵ��ؼ���',
  `BUSINESS_TYPE` varchar(30) NOT NULL COMMENT 'ҵ��������',
  `ROLE_ID` varchar(20) DEFAULT NULL COMMENT '���ܵĽ�ɫ',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '���ܵ���',
  `URL` varchar(512) NOT NULL COMMENT '���봦���URL',
  `COMMENTS` varchar(128) NOT NULL COMMENT '���촦������',
  `PARAM` varchar(64) DEFAULT NULL COMMENT '���ܵĲ���',
  `SYSTEM_NAME` varchar(32) DEFAULT NULL COMMENT 'ϵͳ����',
  `CREATE_TIME` datetime NOT NULL COMMENT '����ʱ��',
  `EXPIRE_TIME` datetime DEFAULT NULL COMMENT '���Ѵ������ʱ�䣬�������������Ч��������ϵͳ�Զ�������Ѵ���',
  PRIMARY KEY (`TASK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=596 DEFAULT CHARSET=gb2312 COMMENT='���������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_info`
--

DROP TABLE IF EXISTS `t_warehouse_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='�ֿ���Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_info_his`
--

DROP TABLE IF EXISTS `t_warehouse_info_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_oper_log`
--

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '������',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_oper_log_his`
--

DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '������',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive`
--

DROP TABLE IF EXISTS `t_warehouse_receive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ����ⵥ�ţ���Ӧ���ͻ����ţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date NOT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ�λ��',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '�ջ�����ע',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬  01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)  00---�ر���ⵥ  31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬  PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����',
  `FINANCE_STATE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '���㷽ʽ',
  PRIMARY KEY (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ���ջ���';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_detail`
--

DROP TABLE IF EXISTS `t_warehouse_receive_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_detail` (
  `REC_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�����ϸSEQID,���к�',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ���ͻ�����',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date DEFAULT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�λ��',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '�ͻ�����Ӧ�Ĳɹ�����',
  `SETTLEMENT_TYPE` varchar(8) NOT NULL DEFAULT '' COMMENT '���㷽ʽ',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾�����ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�̵����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '�ɹ��ĵ���',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `AMOUNT` int(11) NOT NULL COMMENT '�����ͻ�����',
  `CUR_MONEY` decimal(10,2) DEFAULT '0.00' COMMENT '�����ͻ����',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0---��ⵥ  1---�˻���',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '������������Կͻ������������ε����κ�',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `ORDER_SEQID` varchar(12) DEFAULT NULL COMMENT '��Ӧ��Ӧ�̶�����ϸseqid',
  PRIMARY KEY (`REC_DETAIL_SEQID`),
  KEY `RLT_RECEIVE_DETAIL` (`REC_PO_NO`),
  CONSTRAINT `RLT_RECEIVE_DETAIL` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`),
  CONSTRAINT `t_warehouse_receive_detail_ibfk_1` FOREIGN KEY (`REC_PO_NO`) REFERENCES `t_warehouse_receive` (`REC_PO_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COMMENT='��ⵥ��ϸ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_detail_his`
--

DROP TABLE IF EXISTS `t_warehouse_receive_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_detail_his` (
  `REC_DETAIL_SEQID` int(11) NOT NULL COMMENT '�����ϸSEQID,���к�',
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ���ͻ�����',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date DEFAULT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) DEFAULT NULL COMMENT '�ֿ�λ��',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '�ͻ�����Ӧ�Ĳɹ�����',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '���㷽ʽ',
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾�����ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '��Ӧ�̵����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `C_PRICE` decimal(12,10) DEFAULT '0.0000000000' COMMENT '�ɹ��ĵ���',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `AMOUNT` int(11) NOT NULL COMMENT '�����ͻ�����',
  `CUR_MONEY` decimal(10,2) DEFAULT '0.00' COMMENT '�����ͻ����',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����',
  `FINANCE_PERIOD` varchar(8) DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '������������Կͻ������������ε����κ�',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  PRIMARY KEY (`REC_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ��ϸ����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_receive_his`
--

DROP TABLE IF EXISTS `t_warehouse_receive_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_receive_his` (
  `REC_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ����ⵥ�ţ���Ӧ���ͻ����ţ�',
  `C_CODE` varchar(32) NOT NULL COMMENT '��Ӧ�̱���',
  `SHORT_NAME` varchar(64) NOT NULL COMMENT '��Ӧ�̼��',
  `PO_NO_DATE` date DEFAULT NULL COMMENT '��Ӧ�̵�������',
  `ARRAY_DATE` date NOT NULL COMMENT '���ﵽ������',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ�λ��',
  `OPER_ID` varchar(20) DEFAULT NULL COMMENT '������ID',
  `OPER_STAFF` varchar(32) NOT NULL COMMENT '����Ա',
  `OPER_TIME` datetime DEFAULT NULL COMMENT '����ʱ��',
  `RECEIVE_DESC` varchar(256) NOT NULL COMMENT '�ջ�����ע',
  `PERIOD` varchar(8) DEFAULT NULL COMMENT '��������',
  `STATE` varchar(8) DEFAULT NULL COMMENT '�ջ�������ⵥ��״̬  01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)  00---�ر���ⵥ  31---����ȷ�ϸ���',
  `ACTIVE_STATE` varchar(8) DEFAULT NULL COMMENT '�״̬  PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����  ',
  `FINALCE_STATE` varchar(2) DEFAULT NULL COMMENT '�������״̬  0----δ����  1---�Ѷ���',
  `PO_NO_TYPE` varchar(2) NOT NULL COMMENT '��Ӧ�̶�������  0----��ⵥ  1---�˻���',
  `SETTLEMENT_TYPE` varchar(8) DEFAULT NULL COMMENT '���㷽ʽ',
  PRIMARY KEY (`REC_PO_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��ⵥ���ջ�����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send`
--

DROP TABLE IF EXISTS `t_warehouse_send`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_detail`
--

DROP TABLE IF EXISTS `t_warehouse_send_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_send_detail` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '�굥���к�',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����������ε�',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '���ϵ���',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�Ƿ���ʾ����',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '��ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���γ������',
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
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ����',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '��ͬ��',
  `SELF_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�ض��ͻ��������γ���������ͬ�ͻ�������Ӧ',
  `COMM_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����汾�γ���������ͬ�ͻ�������Ӧ',
  `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  PRIMARY KEY (`SEND_DETAIL_SEQID`),
  KEY `RLT_SEND_DETAIL` (`SEND_PO_NO`),
  CONSTRAINT `RLT_SEND_DETAIL` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`),
  CONSTRAINT `t_warehouse_send_detail_ibfk_1` FOREIGN KEY (`SEND_PO_NO`) REFERENCES `t_warehouse_send` (`SEND_PO_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gb2312 COMMENT='�ͻ������굥��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_detail_his`
--

DROP TABLE IF EXISTS `t_warehouse_send_detail_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_warehouse_send_detail_his` (
  `SEND_DETAIL_SEQID` int(11) NOT NULL COMMENT '�굥���к�',
  `SEND_PO_NO` varchar(32) NOT NULL,
  `PART_NO` varchar(32) NOT NULL COMMENT '��˾���ϱ��',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ��',
  `PN_DESC` varchar(64) DEFAULT NULL COMMENT '��������',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����������ε�',
  `IS_TAX` varchar(2) DEFAULT NULL COMMENT '�Ƿ�˰  1--��  0--��  ��������Ǻ�˰�ģ���һ����1���������ѡ���Ƿ�˰����',
  `C_PRICE` decimal(12,10) DEFAULT NULL COMMENT '���ϵ���',
  `TAX_RATE` decimal(6,4) DEFAULT NULL COMMENT '˰��',
  `IS_SHOW_PRICE` varchar(2) DEFAULT NULL COMMENT '�Ƿ���ʾ����',
  `RLT_PO_NO` varchar(32) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `SETTLEMENT_TYPE` varchar(32) DEFAULT NULL COMMENT '���㷽ʽ',
  `COMM_DESC` char(128) DEFAULT NULL COMMENT '��ע',
  `AMOUNT` int(11) NOT NULL COMMENT '��������',
  `CUR_MONEY` decimal(10,2) DEFAULT NULL COMMENT '���γ������',
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
  `C_CODE` varchar(32) DEFAULT NULL COMMENT '�ͻ�����',
  `SHORT_NAME` varchar(64) DEFAULT NULL COMMENT '�ͻ����',
  `C_PRICE_TAX` float(6,4) DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰',
  `CONTACT_FEE` float(6,4) DEFAULT NULL COMMENT '��ͬ��',
  `SELF_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�ض��ͻ��������γ���������ͬ�ͻ�������Ӧ',
  `COMM_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����汾�γ���������ͬ�ͻ�������Ӧ',
  `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  PRIMARY KEY (`SEND_DETAIL_SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ������굥����ʷ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_warehouse_send_his`
--

DROP TABLE IF EXISTS `t_warehouse_send_his`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `STAFF_ID` int(11) NOT NULL COMMENT '�û�ID(FK->STAFF.STAFF_ID)',
  `ROLE_ID` int(11) NOT NULL COMMENT '��ɫID(FK->ROLE.ROLE_ID)',
  `MEMO` varchar(256) DEFAULT NULL COMMENT 'ע��',
  KEY `RLT_ROLE_STAFF_ID` (`STAFF_ID`),
  KEY `RLT_FK_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `RLT_FK_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`),
  CONSTRAINT `RLT_ROLE_STAFF_ID` FOREIGN KEY (`STAFF_ID`) REFERENCES `staff` (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='�û���ɫ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `v_customer_info`
--

DROP TABLE IF EXISTS `v_customer_info`;
/*!50001 DROP VIEW IF EXISTS `v_customer_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_info` (
  `BASE_SEQID` int(11),
  `C_CODE` varchar(32),
  `STATE` varchar(2),
  `SHORT_NAME` varchar(32),
  `EN_SHORT_NAME` varchar(32),
  `ALL_NAME` varchar(256),
  `EN_NAME` varchar(256),
  `ADDRESS` varchar(256),
  `EN_ADDRESS` varchar(256),
  `C_TYPE` varchar(32),
  `C_SCALE` varchar(32),
  `WEBSITE` varchar(128),
  `REPRESENTATIVE` varchar(32),
  `TAX_CODE` char(128),
  `COMPANY_BRANCH` varchar(64),
  `CREDIT_RATE` varchar(8),
  `CREDIT_DESC` char(32),
  `IMPORTANT_CODE` varchar(8),
  `IMPORTANT_DESC` char(32),
  `SETTLEMENT_TYPE` varchar(8),
  `SETTLEMENT_DESC` varchar(32),
  `CURRENCY` varchar(2),
  `CURRENCY_DESC` varchar(32),
  `STAFF_ID` varchar(20),
  `STAFF_NAME` varchar(20),
  `COMM_DESC` varchar(512),
  `VENDOR_CODE` varchar(8),
  `CONTACT_FEE` float(6,4),
  `IS_SHOW_PRICE` varchar(2),
  `TAX_RATE` float(6,4),
  `ASS_STAFF_ID` varchar(20),
  `ASS_STAFF_NAME` varchar(32),
  `CREATE_TIME` datetime,
  `SALE_TYPE` varchar(32),
  `settlement_day` varchar(4)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_customer_total_settlement`
--

DROP TABLE IF EXISTS `v_customer_total_settlement`;
/*!50001 DROP VIEW IF EXISTS `v_customer_total_settlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_total_settlement` (
  `C_CODE` varchar(32),
  `SHORT_NAME` varchar(32),
  `SETTLEMENT_TYPE` varchar(32),
  `ABSTRACT` varchar(32),
  `TOTAL_MONEY` decimal(32,2),
  `NEED_MONEY` decimal(32,2),
  `SET_MONEY` decimal(10,2),
  `FINANCE_STATE` varchar(2),
  `SALES_ID` varchar(20),
  `SALES_NAME` varchar(32),
  `ASS_ID` varchar(20),
  `ASS_NAME` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_customer_unsettlement`
--

DROP TABLE IF EXISTS `v_customer_unsettlement`;
/*!50001 DROP VIEW IF EXISTS `v_customer_unsettlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_customer_unsettlement` (
  `c_code` varchar(32),
  `short_name` varchar(32),
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `ass_id` varchar(20),
  `ass_name` varchar(32),
  `settlement_type` varchar(32),
  `abstract` varchar(32),
  `finance_state` varchar(2),
  `total_money` decimal(32,2),
  `need_money` decimal(32,2),
  `set_money` decimal(3,2)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_sales_benefit_detail`
--

DROP TABLE IF EXISTS `v_sales_benefit_detail`;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_detail`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_sales_benefit_detail` (
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `create_time` datetime,
  `send_po_no` varchar(32),
  `cust_code` varchar(32),
  `cust_short_name` varchar(64),
  `part_no` varchar(32),
  `pn_desc` varchar(128),
  `cust_part_no` varchar(32),
  `cust_price` decimal(12,10),
  `cust_tax_rate` decimal(6,4),
  `amount` int(11),
  `cur_money` decimal(10,2),
  `benefit` decimal(23,10),
  `vendor_part_no` varchar(32),
  `vendor_price` decimal(12,10),
  `vendor_code` varchar(32),
  `vender_short_name` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_sales_benefit_total`
--

DROP TABLE IF EXISTS `v_sales_benefit_total`;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_total`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_sales_benefit_total` (
  `sales_id` varchar(20),
  `sales_name` varchar(20),
  `create_time` int(6),
  `cust_code` varchar(32),
  `cust_short_name` varchar(64),
  `part_no` varchar(32),
  `pn_desc` varchar(128),
  `cust_part_no` varchar(32),
  `cust_price` decimal(12,10),
  `vendor_code` varchar(32),
  `vender_short_name` varchar(32),
  `vendor_part_no` varchar(32),
  `amount` decimal(32,0),
  `cur_money` decimal(32,2),
  `benefit` decimal(45,10)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_info`
--

DROP TABLE IF EXISTS `v_vendor_info`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_info` (
  `BASE_SEQID` int(11),
  `C_CODE` varchar(32),
  `STATE` varchar(2),
  `SHORT_NAME` varchar(32),
  `EN_SHORT_NAME` varchar(32),
  `ALL_NAME` varchar(256),
  `EN_NAME` varchar(256),
  `ADDRESS` varchar(256),
  `EN_ADDRESS` varchar(256),
  `C_TYPE` varchar(16),
  `C_SCALE` varchar(16),
  `WEBSITE` varchar(128),
  `REPRESENTATIVE` varchar(32),
  `TAX_CODE` varchar(20),
  `COMPANY_BRANCH` varchar(64),
  `CREDIT_RATE` varchar(8),
  `CREDIT_DESC` varchar(32),
  `IMPORTANT_CODE` varchar(8),
  `IMPORTANT_DESC` varchar(20),
  `SETTLEMENT_TYPE` varchar(8),
  `SETTLEMENT_DESC` varchar(32),
  `CURRENCY` varchar(8),
  `CURRENCY_DESC` varchar(32),
  `STAFF_ID` varchar(20),
  `STAFF_NAME` varchar(32),
  `COMM_DESC` varchar(512),
  `CONTACT_FEE` float(4,2),
  `IS_SHOW_PRICE` varchar(2),
  `TAX_RATE` decimal(6,4),
  `CREATE_TIME` datetime,
  `SALE_TYPE` varchar(32),
  `settlement_day` varchar(4)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_total_settlement`
--

DROP TABLE IF EXISTS `v_vendor_total_settlement`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_total_settlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_total_settlement` (
  `C_CODE` varchar(32),
  `SHORT_NAME` varchar(32),
  `SETTLEMENT_TYPE` varchar(8),
  `ABSTRACT` varchar(32),
  `TOTAL_MONEY` decimal(32,2),
  `NEED_MONEY` decimal(32,2),
  `SET_MONEY` decimal(10,2),
  `FINANCE_STATE` varchar(2),
  `PURCHASE_ID` varchar(20),
  `PURCHASE_NAME` varchar(32)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `v_vendor_unsettlement`
--

DROP TABLE IF EXISTS `v_vendor_unsettlement`;
/*!50001 DROP VIEW IF EXISTS `v_vendor_unsettlement`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_vendor_unsettlement` (
  `c_code` varchar(32),
  `short_name` varchar(32),
  `purchase_id` varchar(20),
  `purchase_name` varchar(32),
  `settlement_type` varchar(8),
  `abstract` varchar(32),
  `finance_state` varchar(2),
  `total_money` decimal(32,2),
  `need_money` decimal(32,2),
  `set_money` decimal(3,2)
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_customer_info`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_info`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_info` AS select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`VENDOR_CODE` AS `VENDOR_CODE`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`ASS_STAFF_ID` AS `ASS_STAFF_ID`,`info`.`ASS_STAFF_NAME` AS `ASS_STAFF_NAME`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`SETTLEMENT_DAY` AS `settlement_day` from (`t_customer_info` `info` join `t_cust_account_period` `p`) where ((`info`.`SETTLEMENT_TYPE` = '1') and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) union select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`VENDOR_CODE` AS `VENDOR_CODE`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`ASS_STAFF_ID` AS `ASS_STAFF_ID`,`info`.`ASS_STAFF_NAME` AS `ASS_STAFF_NAME`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`PRE_PAID` AS `settlement_day` from (`t_customer_info` `info` join `t_cust_prepaid` `p`) where (((`info`.`SETTLEMENT_TYPE` = '2') or (`info`.`SETTLEMENT_TYPE` = '3')) and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_customer_total_settlement`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_total_settlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_total_settlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_total_settlement` AS select `t_customer_settlement`.`C_CODE` AS `C_CODE`,`t_customer_settlement`.`SHORT_NAME` AS `SHORT_NAME`,`t_customer_settlement`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`t_customer_settlement`.`ABSTRACT` AS `ABSTRACT`,`t_customer_settlement`.`TOTAL_MONEY` AS `TOTAL_MONEY`,`t_customer_settlement`.`NEED_MONEY` AS `NEED_MONEY`,`t_customer_settlement`.`SET_MONEY` AS `SET_MONEY`,`t_customer_settlement`.`FINANCE_STATE` AS `FINANCE_STATE`,`t_customer_settlement`.`SALES_ID` AS `SALES_ID`,`t_customer_settlement`.`SALES_NAME` AS `SALES_NAME`,`t_customer_settlement`.`ASS_ID` AS `ASS_ID`,`t_customer_settlement`.`ASS_NAME` AS `ASS_NAME` from `t_customer_settlement` union select `v_customer_unsettlement`.`c_code` AS `C_CODE`,`v_customer_unsettlement`.`short_name` AS `SHORT_NAME`,`v_customer_unsettlement`.`settlement_type` AS `SETTLEMENT_TYPE`,`v_customer_unsettlement`.`abstract` AS `ABSTRACT`,`v_customer_unsettlement`.`total_money` AS `TOTAL_MONEY`,`v_customer_unsettlement`.`need_money` AS `NEED_MONEY`,`v_customer_unsettlement`.`set_money` AS `SET_MONEY`,`v_customer_unsettlement`.`finance_state` AS `FINANCE_STATE`,`v_customer_unsettlement`.`sales_id` AS `SALES_ID`,`v_customer_unsettlement`.`sales_name` AS `SALES_NAME`,`v_customer_unsettlement`.`ass_id` AS `ASS_ID`,`v_customer_unsettlement`.`ass_name` AS `ASS_NAME` from `v_customer_unsettlement` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_customer_unsettlement`
--

/*!50001 DROP TABLE IF EXISTS `v_customer_unsettlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_customer_unsettlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_customer_unsettlement` AS select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `sales_id`,`vinfo`.`STAFF_NAME` AS `sales_name`,`vinfo`.`ASS_STAFF_ID` AS `ass_id`,`vinfo`.`ASS_STAFF_NAME` AS `ass_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`FINANCE_PERIOD` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_customer_info` `vinfo` join `t_warehouse_send_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` = 1) and (`wdetail`.`FINANCE_STATE` = 1) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`vinfo`.`ASS_STAFF_ID`,`vinfo`.`ASS_STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`FINANCE_PERIOD`,`wdetail`.`FINANCE_STATE` union select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `sales_id`,`vinfo`.`STAFF_NAME` AS `sales_name`,`vinfo`.`ASS_STAFF_ID` AS `ass_id`,`vinfo`.`ASS_STAFF_NAME` AS `ass_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`SEND_PO_NO` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_customer_info` `vinfo` join `t_warehouse_send_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` <> 1) and ((`wdetail`.`FINANCE_STATE` = 0) or (`wdetail`.`FINANCE_STATE` = 1)) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`vinfo`.`ASS_STAFF_ID`,`vinfo`.`ASS_STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`SEND_PO_NO`,`wdetail`.`FINANCE_STATE` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_sales_benefit_detail`
--

/*!50001 DROP TABLE IF EXISTS `v_sales_benefit_detail`*/;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_detail`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_sales_benefit_detail` AS select `t`.`STAFF_ID` AS `sales_id`,`t`.`STAFF_NAME` AS `sales_name`,`w`.`CREATE_TIME` AS `create_time`,`w`.`SEND_PO_NO` AS `send_po_no`,`w`.`C_CODE` AS `cust_code`,`w`.`SHORT_NAME` AS `cust_short_name`,`w`.`PART_NO` AS `part_no`,`v`.`PN_DESC` AS `pn_desc`,`w`.`C_PART_NO` AS `cust_part_no`,`w`.`C_PRICE` AS `cust_price`,`w`.`TAX_RATE` AS `cust_tax_rate`,`w`.`AMOUNT` AS `amount`,`w`.`CUR_MONEY` AS `cur_money`,(`w`.`CUR_MONEY` - (`w`.`AMOUNT` * `v`.`PRICE`)) AS `benefit`,`v`.`C_PART_NO` AS `vendor_part_no`,`v`.`PRICE` AS `vendor_price`,`v`.`C_CODE` AS `vendor_code`,`v`.`C_SHORT_NAME` AS `vender_short_name` from ((`t_warehouse_send_detail` `w` left join `t_vendor_part_no_info` `v` on(((`w`.`VENDOR_CODE` = `v`.`C_CODE`) and (`w`.`PART_NO` = `v`.`PART_NO`)))) join `t_customer_info` `t`) where ((`t`.`C_CODE` = `w`.`C_CODE`) and (`t`.`STATE` = 0)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_sales_benefit_total`
--

/*!50001 DROP TABLE IF EXISTS `v_sales_benefit_total`*/;
/*!50001 DROP VIEW IF EXISTS `v_sales_benefit_total`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_sales_benefit_total` AS select `v_sales_benefit_detail`.`sales_id` AS `sales_id`,`v_sales_benefit_detail`.`sales_name` AS `sales_name`,extract(year_month from `v_sales_benefit_detail`.`create_time`) AS `create_time`,`v_sales_benefit_detail`.`cust_code` AS `cust_code`,`v_sales_benefit_detail`.`cust_short_name` AS `cust_short_name`,`v_sales_benefit_detail`.`part_no` AS `part_no`,`v_sales_benefit_detail`.`pn_desc` AS `pn_desc`,`v_sales_benefit_detail`.`cust_part_no` AS `cust_part_no`,`v_sales_benefit_detail`.`cust_price` AS `cust_price`,`v_sales_benefit_detail`.`vendor_code` AS `vendor_code`,`v_sales_benefit_detail`.`vender_short_name` AS `vender_short_name`,`v_sales_benefit_detail`.`vendor_part_no` AS `vendor_part_no`,sum(`v_sales_benefit_detail`.`amount`) AS `amount`,sum(`v_sales_benefit_detail`.`cur_money`) AS `cur_money`,sum(`v_sales_benefit_detail`.`benefit`) AS `benefit` from `v_sales_benefit_detail` group by `v_sales_benefit_detail`.`sales_id`,`v_sales_benefit_detail`.`sales_name`,`v_sales_benefit_detail`.`create_time`,`v_sales_benefit_detail`.`cust_code`,`v_sales_benefit_detail`.`cust_short_name`,`v_sales_benefit_detail`.`part_no`,`v_sales_benefit_detail`.`pn_desc`,`v_sales_benefit_detail`.`cust_part_no`,`v_sales_benefit_detail`.`cust_price`,`v_sales_benefit_detail`.`vendor_code`,`v_sales_benefit_detail`.`vender_short_name`,`v_sales_benefit_detail`.`vendor_part_no` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_info`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_info`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_info` AS select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`SETTLEMENT_DAY` AS `settlement_day` from (`t_vendor_info` `info` join `t_vendor_account_period` `p`) where ((`info`.`SETTLEMENT_TYPE` = '1') and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) union select `info`.`BASE_SEQID` AS `BASE_SEQID`,`info`.`C_CODE` AS `C_CODE`,`info`.`STATE` AS `STATE`,`info`.`SHORT_NAME` AS `SHORT_NAME`,`info`.`EN_SHORT_NAME` AS `EN_SHORT_NAME`,`info`.`ALL_NAME` AS `ALL_NAME`,`info`.`EN_NAME` AS `EN_NAME`,`info`.`ADDRESS` AS `ADDRESS`,`info`.`EN_ADDRESS` AS `EN_ADDRESS`,`info`.`C_TYPE` AS `C_TYPE`,`info`.`C_SCALE` AS `C_SCALE`,`info`.`WEBSITE` AS `WEBSITE`,`info`.`REPRESENTATIVE` AS `REPRESENTATIVE`,`info`.`TAX_CODE` AS `TAX_CODE`,`info`.`COMPANY_BRANCH` AS `COMPANY_BRANCH`,`info`.`CREDIT_RATE` AS `CREDIT_RATE`,`info`.`CREDIT_DESC` AS `CREDIT_DESC`,`info`.`IMPORTANT_CODE` AS `IMPORTANT_CODE`,`info`.`IMPORTANT_DESC` AS `IMPORTANT_DESC`,`info`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`info`.`SETTLEMENT_DESC` AS `SETTLEMENT_DESC`,`info`.`CURRENCY` AS `CURRENCY`,`info`.`CURRENCY_DESC` AS `CURRENCY_DESC`,`info`.`STAFF_ID` AS `STAFF_ID`,`info`.`STAFF_NAME` AS `STAFF_NAME`,`info`.`COMM_DESC` AS `COMM_DESC`,`info`.`CONTACT_FEE` AS `CONTACT_FEE`,`info`.`IS_SHOW_PRICE` AS `IS_SHOW_PRICE`,`info`.`TAX_RATE` AS `TAX_RATE`,`info`.`CREATE_TIME` AS `CREATE_TIME`,`info`.`SALE_TYPE` AS `SALE_TYPE`,`p`.`PRE_PAID` AS `settlement_day` from (`t_vendor_info` `info` join `t_vendor_prepaid` `p`) where (((`info`.`SETTLEMENT_TYPE` = '2') or (`info`.`SETTLEMENT_TYPE` = '3')) and (`info`.`BASE_SEQID` = `p`.`BASE_SEQID`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_total_settlement`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_total_settlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_total_settlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_total_settlement` AS select `t_vendor_settlement`.`C_CODE` AS `C_CODE`,`t_vendor_settlement`.`SHORT_NAME` AS `SHORT_NAME`,`t_vendor_settlement`.`SETTLEMENT_TYPE` AS `SETTLEMENT_TYPE`,`t_vendor_settlement`.`ABSTRACT` AS `ABSTRACT`,`t_vendor_settlement`.`TOTAL_MONEY` AS `TOTAL_MONEY`,`t_vendor_settlement`.`NEED_MONEY` AS `NEED_MONEY`,`t_vendor_settlement`.`SET_MONEY` AS `SET_MONEY`,`t_vendor_settlement`.`FINANCE_STATE` AS `FINANCE_STATE`,`t_vendor_settlement`.`PURCHASE_ID` AS `PURCHASE_ID`,`t_vendor_settlement`.`PURCHASE_NAME` AS `PURCHASE_NAME` from `t_vendor_settlement` union select `v_vendor_unsettlement`.`c_code` AS `C_CODE`,`v_vendor_unsettlement`.`short_name` AS `SHORT_NAME`,`v_vendor_unsettlement`.`settlement_type` AS `SETTLEMENT_TYPE`,`v_vendor_unsettlement`.`abstract` AS `ABSTRACT`,`v_vendor_unsettlement`.`total_money` AS `TOTAL_MONEY`,`v_vendor_unsettlement`.`need_money` AS `NEED_MONEY`,`v_vendor_unsettlement`.`set_money` AS `SET_MONEY`,`v_vendor_unsettlement`.`finance_state` AS `FINANCE_STATE`,`v_vendor_unsettlement`.`purchase_id` AS `PURCHASE_ID`,`v_vendor_unsettlement`.`purchase_name` AS `PURCHASE_NAME` from `v_vendor_unsettlement` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_vendor_unsettlement`
--

/*!50001 DROP TABLE IF EXISTS `v_vendor_unsettlement`*/;
/*!50001 DROP VIEW IF EXISTS `v_vendor_unsettlement`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = gbk */;
/*!50001 SET character_set_results     = gbk */;
/*!50001 SET collation_connection      = gbk_chinese_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_vendor_unsettlement` AS select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `purchase_id`,`vinfo`.`STAFF_NAME` AS `purchase_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`FINANCE_PERIOD` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_vendor_info` `vinfo` join `t_warehouse_receive_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` = 1) and (`wdetail`.`FINANCE_STATE` = 1) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`FINANCE_PERIOD`,`wdetail`.`FINANCE_STATE` union select `vinfo`.`C_CODE` AS `c_code`,`vinfo`.`SHORT_NAME` AS `short_name`,`vinfo`.`STAFF_ID` AS `purchase_id`,`vinfo`.`STAFF_NAME` AS `purchase_name`,`wdetail`.`SETTLEMENT_TYPE` AS `settlement_type`,`wdetail`.`REC_PO_NO` AS `abstract`,`wdetail`.`FINANCE_STATE` AS `finance_state`,sum(`wdetail`.`CUR_MONEY`) AS `total_money`,sum(`wdetail`.`CUR_MONEY`) AS `need_money`,0.00 AS `set_money` from (`t_vendor_info` `vinfo` join `t_warehouse_receive_detail` `wdetail`) where ((`vinfo`.`STATE` = 0) and (`wdetail`.`STATE` = '02') and (`wdetail`.`SETTLEMENT_TYPE` <> 1) and ((`wdetail`.`FINANCE_STATE` = 0) or (`wdetail`.`FINANCE_STATE` = 1)) and (`vinfo`.`C_CODE` = `wdetail`.`C_CODE`)) group by `vinfo`.`C_CODE`,`vinfo`.`SHORT_NAME`,`vinfo`.`STAFF_ID`,`vinfo`.`STAFF_NAME`,`wdetail`.`SETTLEMENT_TYPE`,`wdetail`.`REC_PO_NO`,`wdetail`.`FINANCE_STATE` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-07-12  8:05:00
