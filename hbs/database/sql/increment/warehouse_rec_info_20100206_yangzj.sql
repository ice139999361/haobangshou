ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `SPEC_DESC` varchar(32) NULL DEFAULT NULL COMMENT '特殊描述，针对客户订单中有批次的批次号';

ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `SPEC_DESC` varchar(32) NULL DEFAULT NULL COMMENT '特殊描述，针对客户订单中有批次的批次号';
