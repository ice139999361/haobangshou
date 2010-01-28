# MySQL-Front 5.0  (Build 1.133)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: testdb
# ------------------------------------------------------
# Server version 5.1.36-community

#
# Table structure for table t_sys_sequence
#

DROP TABLE IF EXISTS `t_sys_sequence`;
CREATE TABLE `t_sys_sequence` (
  `SEQ_TYPE` varchar(16) NOT NULL COMMENT 'SEQENCE����',
  `SEQ_KEY` varchar(16) NOT NULL COMMENT 'KEYֵ',
  `SEQ_VALUE` int(11) NOT NULL DEFAULT '0' COMMENT '��ǰ����ֵ',
  `SEQ_LENGTH` int(11) NOT NULL DEFAULT '2' COMMENT '����ֵҪ�󳤶�',
  `SEQ_PREFIX` varchar(16) NOT NULL COMMENT 'ǰ׺',
  `SEQ_MIDDLE` varchar(16) NOT NULL COMMENT '�ж�'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='ϵͳʹ�õ�����sequenceid�Ļ�ȡ�ʹ洢';

#
# Dumping data for table t_sys_sequence
#
LOCK TABLES `t_sys_sequence` WRITE;
/*!40000 ALTER TABLE `t_sys_sequence` DISABLE KEYS */;

INSERT INTO `t_sys_sequence` VALUES ('V_ORDER_PONO','yyMMdd',0,3,'P','100128');
INSERT INTO `t_sys_sequence` VALUES ('C_SEND_PONO','yyyyMMdd',0,3,'GS','20100128');
/*!40000 ALTER TABLE `t_sys_sequence` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
