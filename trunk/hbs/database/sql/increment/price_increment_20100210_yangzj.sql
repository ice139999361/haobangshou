ALTER TABLE `t_cust_order_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '��ͬ��';

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '��ͬ��';

ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_vendor_order_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '��ͬ��';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `C_PRICE_TAX` float(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `CONTACT_FEE` float(6,4) NULL DEFAULT NULL COMMENT '��ͬ��';


ALTER TABLE `t_cust_prepaid`  CHANGE COLUMN `REMINDER_DAY` `REMINDER_DAY` varchar(4) NULL DEFAULT NULL COMMENT '�Ի�������������г���Ա�߿�';



ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `SELF_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�ض��ͻ��������γ���������ͬ�ͻ�������Ӧ';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `COMM_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�����汾�γ���������ͬ�ͻ�������Ӧ';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�';

ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `SELF_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�ض��ͻ��������γ���������ͬ�ͻ�������Ӧ';
                                    
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `COMM_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�����汾�γ���������ͬ�ͻ�������Ӧ';
                                   
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `HOUSE_TYPE` varchar(2) NOT NULL DEFAULT '1' COMMENT '�ֿ����ͣ�ȱʡΪ��˾�ܿ�';

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '���ͻ�����������Ŀ' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail`  ADD COLUMN `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�����������Ŀ' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `SELF_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '���ͻ�����������Ŀ' AFTER `DELIVERY_AMOUNT`;

ALTER TABLE `t_cust_order_detail_his`  ADD COLUMN `COMM_DELIVERY_AMOUNT` int(11) NOT NULL DEFAULT 0 COMMENT '�����������Ŀ' AFTER `DELIVERY_AMOUNT`;