DROP TABLE IF EXISTS `t_cust_lock_info`;
CREATE TABLE `t_cust_lock_info` (
  `CUSTLOCK_SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `HOUSE_TYPE` varchar(32) NOT NULL COMMENT '仓库类型，缺省为公司总库',
  `HOUSE_USE` varchar(32) NOT NULL COMMENT '仓库货物用途  2 常规货物  1 特定客户货物',
  `CUST_CODE` varchar(32) NOT NULL COMMENT '客户编码',
  `CUST_PO_NO` varchar(50) NOT NULL COMMENT '对应的客户订单号',
  `CUST_ORDER_SEQID` int(11) NOT NULL COMMENT '订单明细序号',
  `C_PART_NO` varchar(32) NOT NULL COMMENT '客户物料编号',
  `PART_NO` varchar(32) NOT NULL COMMENT '本公司的物料编号',
  `SPEC_DESC` varchar(32) DEFAULT NULL COMMENT '特殊备注，如有的客户物料中有批次概念，可以填入该字段',
  `LOCK_AMOUNT` int(11) NOT NULL DEFAULT '0' COMMENT '锁定的库存',
	`REC_PO_NO`  varchar(32) NULL COMMENT '关联入库单号',
	`REC_DETAIL_SEQID` int(11) NULL COMMENT '关联入库明细SEQID,序列号',
  `VENDOR_CODE` varchar(32) NULL COMMENT ' 供应商编码',
  PRIMARY KEY (`CUSTLOCK_SEQID`),
  INDEX (`CUST_CODE`(32),`CUST_PO_NO`(50),`CUST_ORDER_SEQID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gb2312 COMMENT='客户订单明细库存锁定信息表';
