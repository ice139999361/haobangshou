DROP TABLE IF EXISTS `t_cust_lock_info`;
CREATE TABLE `t_cust_lock_info` (
  `CUSTLOCK_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '�ֿ������;  2 �������  1 �ض��ͻ�����',
  `CUST_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `CUST_PO_NO` varchar(50) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `CUST_ORDER_SEQID` int(11) NOT NULL COMMENT '������ϸ���',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '�ͻ����ϱ��',
  `PART_NO` varchar(32) NOT NULL COMMENT '����˾�����ϱ��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����Ŀ��',
	`REC_PO_NO`  varchar(32) NULL COMMENT '������ⵥ��',
	`REC_DETAIL_SEQID` int(11) NULL COMMENT '���������ϸSEQID,���к�',
  `VENDOR_CODE` varchar(32) NULL COMMENT ' ��Ӧ�̱���',
  PRIMARY KEY (`CUSTLOCK_SEQID`),
  INDEX (`CUST_CODE`(32),`CUST_PO_NO`(50),`CUST_ORDER_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='�ͻ�������ϸ���������Ϣ��';
