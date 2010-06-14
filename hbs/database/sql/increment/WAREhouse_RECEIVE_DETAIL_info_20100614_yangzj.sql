ALTER TABLE `t_warehouse_receive_detail`
  ADD COLUMN `ORDER_SEQID` varchar(12) NULL DEFAULT NULL COMMENT '对应供应商订单明细seqid';
