ALTER TABLE `t_cust_order_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '合同费';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '合同费';

ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '合同费';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '单价税率，和单价的关系，税率为0，单价为不含税，税率不为0，单价为含税';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '合同费';


ALTER TABLE `t_cust_prepaid`  CHANGE COLUMN `REMINDER_DAY` `REMINDER_DAY` varchar(4) NULL DEFAULT NULL COMMENT '对货到付款，加提醒市场人员催款';



ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `SELF_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '特定客户备货本次出货数量，同客户订单对应';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `COMM_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '常规库存本次出货数量，同客户订单对应';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '仓库类型，缺省为公司总库';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `SELF_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '特定客户备货本次出货数量，同客户订单对应';
                                    
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `COMM_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '常规库存本次出货数量，同客户订单对应';
                                   
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '仓库类型，缺省为公司总库';

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '本客户备货发送数目' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '常规库存出货数目' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '本客户备货发送数目' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '常规库存出货数目' AFTER `DELIVERY_AMOUNT`;