ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID';
ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '����������';
ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '���㷽ʽ';

ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID';
ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '����������';
ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '���㷽ʽ';


