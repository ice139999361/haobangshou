
DROP TABLE IF EXISTS `t_config_encode`;
CREATE TABLE `t_config_encode` (
  `ENCODE_TYPE` varchar(128) NOT NULL COMMENT '��������',
  `ENCODE_KEY` varchar(128) NOT NULL COMMENT '������',
  `ENCODE_VALUE` varchar(1024) NOT NULL COMMENT '����ֵ',
  `ENCODE_DESC` varchar(256) DEFAULT NULL COMMENT '����',
  `IS_VALID` varchar(1) NOT NULL DEFAULT '1' COMMENT '�Ƿ���Ч  1����Ч  2����Ч',
  `SORT_ID` varchar(3) NOT NULL COMMENT '�����  ͬһ�������ֵ����ֵ���������',
  PRIMARY KEY (`ENCODE_TYPE`,`ENCODE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='�ֵ�����';

#
# Dumping data for table t_config_encode
#

/*!40101 SET NAMES gbk */;

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','1','���ڽ���',NULL,'1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','2','Ԥ��X%,ʣ���������',NULL,'1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','3','Ԥ��X%ʣ������',NULL,'1','3');

/*!40101 SET NAMES gb2312 */;


