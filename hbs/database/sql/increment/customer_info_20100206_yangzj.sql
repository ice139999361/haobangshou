ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '������Ӧ�Ĳֿ��ܿ�/�����⣬ȱʡΪ��˾�ܿ�1';
ALTER TABLE `t_cust_order_detail`  ADD COLUMN `DELIVERY_HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '������Ӧ�Ĳֿ��ܿ�/�����⣬ȱʡΪ��˾�ܿ�1';