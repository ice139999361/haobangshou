DROP TABLE IF EXISTS `t_cust_lock_info`;
CREATE TABLE `t_cust_lock_info` (
  `CUST_CODE` varchar(32) NOT NULL COMMENT '�ͻ�����',
  `CUST_PO_NO` varchar(50) NOT NULL COMMENT '��Ӧ�Ŀͻ�������',
  `CUST_ORDER_SEQID` int(11) NOT NULL COMMENT '������ϸ���',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '�ֿ������;  2 �������  1 �ض��ͻ�����',
  `C_PART_NO` varchar(32) NULL COMMENT '�ͻ����ϱ��',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '�����Ŀ��',
  `PART_NO` varchar(32) NULL COMMENT '����˾�����ϱ��',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '���ⱸע�����еĿͻ������������θ������������ֶ�',
  `VENDOR_CODE` varchar(32) NULL COMMENT ' ��Ӧ�̱���',
  PRIMARY KEY (`CUST_CODE`,`CUST_PO_NO`,`CUST_ORDER_SEQID`,`HOUSE_TYPE`,`HOUSE_USE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='�ͻ�������ϸ���������Ϣ��';

DROP VIEW IF EXISTS `v_cust_lock_groupby_orderseqid`;
CREATE ALGORITHM=UNDEFINED SQL SECURITY DEFINER VIEW `hbs`.`v_cust_lock_groupby_orderseqid` AS select HOUSE_TYPE, CUST_CODE, CUST_PO_NO, CUST_ORDER_SEQID, sum(LOCK_AMOUNT) as LOCK_AMOUNT FROM t_cust_lock_info group by HOUSE_TYPE, CUST_CODE, CUST_PO_NO, CUST_ORDER_SEQID;
