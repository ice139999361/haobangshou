ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32)  NOT NULL DEFAULT '' COMMENT '��������';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '����ԱID(ҵ������)';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `STAFF_NAME` varchar(32) NULL COMMENT '����Ա������ҵ������';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID(ҵ��Ա)';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SALES` varchar(32) NULL COMMENT '������Ա������ҵ��Ա��';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SETTLEMENT_TYPE` varchar(32)  NOT NULL DEFAULT '' COMMENT '��������';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `STAFF_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '����ԱID(ҵ������)';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `STAFF_NAME` varchar(32) NULL COMMENT '����Ա������ҵ������';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SALES_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '������ԱID(ҵ��Ա)';
ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SALES` varchar(32) NULL COMMENT '������Ա������ҵ��Ա��';