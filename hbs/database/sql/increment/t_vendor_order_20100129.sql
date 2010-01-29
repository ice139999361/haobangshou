ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员ID';
ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人姓名';
ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算方式';

ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人员ID';
ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NOT NULL DEFAULT '' COMMENT '操作人姓名';
ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32) NOT NULL DEFAULT '' COMMENT '结算方式';


