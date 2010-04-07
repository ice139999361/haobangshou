

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '������',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־��';



DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '���к�',
  `OPER_TIME` date NOT NULL COMMENT '����ʱ��',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '������',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '����������  ��ⵥ  ���ⵥ',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '��������  ����  �޸�  ȡ��  ��ֹ  ����������Ϊ  ͬ��  ��ͬ��  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '����˵��',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ֿ������־����ʷ';

