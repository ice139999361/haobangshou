
DROP TABLE IF EXISTS `t_config_encode`;
CREATE TABLE `t_config_encode` (
  `ENCODE_TYPE` varchar(128) NOT NULL COMMENT '编码类型',
  `ENCODE_KEY` varchar(128) NOT NULL COMMENT '编码项',
  `ENCODE_VALUE` varchar(1024) NOT NULL COMMENT '编码值',
  `ENCODE_DESC` varchar(256) DEFAULT NULL COMMENT '描述',
  `IS_VALID` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否有效  1：有效  2：无效',
  `SORT_ID` varchar(3) NOT NULL COMMENT '排序号  同一个配置字典项的值按这个排序',
  PRIMARY KEY (`ENCODE_TYPE`,`ENCODE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='字典编码表';

#
# Dumping data for table t_config_encode
#

/*!40101 SET NAMES gbk */;

INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','1','账期结算',NULL,'1','1');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','2','预付X%,剩余货到付款',NULL,'1','2');
INSERT INTO `t_config_encode` (`ENCODE_TYPE`,`ENCODE_KEY`,`ENCODE_VALUE`,`ENCODE_DESC`,`IS_VALID`,`SORT_ID`) VALUES ('SETTLEMENT','3','预付X%剩余款到发货',NULL,'1','3');

/*!40101 SET NAMES gb2312 */;


