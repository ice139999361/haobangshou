ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32)  NOT NULL DEFAULT '' COMMENT '结算类型';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作员ID(业务助理)';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NULL COMMENT '操作员姓名（业务助理）';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '销售人员ID(业务员)';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SALES` varchar(32) NULL COMMENT '销售人员姓名（业务员）';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32)  NOT NULL DEFAULT '' COMMENT '结算类型';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '操作员ID(业务助理)';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NULL COMMENT '操作员姓名（业务助理）';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '销售人员ID(业务员)';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SALES` varchar(32) NULL COMMENT '销售人员姓名（业务员）';