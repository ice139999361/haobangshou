ALTER TABLE `t_warehouse_receive`  ADD COLUMN `SETTLEMENT_TYPE` varchar(8) NULL DEFAULT NULL COMMENT '���㷽ʽ';

ALTER TABLE `t_warehouse_receive_his`  ADD COLUMN `SETTLEMENT_TYPE` varchar(8) NULL DEFAULT NULL COMMENT '���㷽ʽ';

ALTER TABLE `t_warehouse_receive_detail`  CHANGE COLUMN `SETTLEMENT_TYPE` `SETTLEMENT_TYPE` varchar(8) NOT NULL DEFAULT '' COMMENT '���㷽ʽ';

ALTER TABLE `t_warehouse_receive_detail_his`  CHANGE COLUMN `SETTLEMENT_TYPE` `SETTLEMENT_TYPE` varchar(8) NULL DEFAULT NULL COMMENT '���㷽ʽ';
