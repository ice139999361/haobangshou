

DROP TABLE IF EXISTS `t_warehouse_oper_log`;
CREATE TABLE `t_warehouse_oper_log` (
  `SEQID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表';



DROP TABLE IF EXISTS `t_warehouse_oper_log_his`;
CREATE TABLE `t_warehouse_oper_log_his` (
  `SEQID` int(11) NOT NULL COMMENT '序列号',
  `OPER_TIME` date NOT NULL COMMENT '操作时间',
  `STAFF_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人',
  `OPER_OBJECT` varchar(32) NOT NULL DEFAULT '' COMMENT '操作的主题  入库单  出库单',
  `OPER_KEY` varchar(64) NOT NULL COMMENT '关键字，主题是入库单，则为供应商送货单号；出库单则为出库单号          ',
  `OPER_TYPE` varchar(16) NOT NULL COMMENT '操作类型  创建  修改  取消  终止  对于审批则为  同意  不同意  ',
  `OPER_CONTENT` varchar(512) DEFAULT NULL COMMENT '具体说明',
  `MEMO` varchar(512) DEFAULT NULL,
  `STAFF_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SEQID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='仓库操作日志表历史';

