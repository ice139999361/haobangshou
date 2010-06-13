ALTER TABLE `t_cust_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '����';

ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '����';

ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';

  ALTER TABLE `t_cust_order_detail`
  CHANGE COLUMN `MONEY` `MONEY` decimal(12,4) NULL DEFAULT NULL COMMENT '�ܽ��';



ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '����';

ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';

  ALTER TABLE `t_cust_order_detail_his`
  CHANGE COLUMN `MONEY` `MONEY` decimal(12,4) NULL DEFAULT NULL COMMENT '�ܽ��';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '�ܺ�˰���';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���ο�Ʊ���';

ALTER TABLE `t_cust_send_invoice`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT 'δ��Ʊ���';


  ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '�ܺ�˰���';

ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���ο�Ʊ���';

ALTER TABLE `t_cust_send_invoice_his`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT 'δ��Ʊ���';



ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `TOTAL_MONEY` `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '�ܽ��';

  ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `NEED_MONEY` `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '��������';

  ALTER TABLE `t_customer_settlement`
  CHANGE COLUMN `SET_MONEY` `SET_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '�ѽ�����';


  ALTER TABLE `t_vendor_info`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT .0000 COMMENT '˰�ʣ��ٷ�ֵ';


  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '����';
  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';
  ALTER TABLE `t_vendor_order_detail`
  CHANGE COLUMN `MONEY` `MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���';

  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '����';
  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';
  ALTER TABLE `t_vendor_order_detail_his`
  CHANGE COLUMN `MONEY` `MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���';

  ALTER TABLE `t_vendor_part_no_info`
  CHANGE COLUMN `PRICE` `PRICE` decimal(12,10) NOT NULL DEFAULT .00000 COMMENT '����';

  ALTER TABLE `t_vendor_part_no_info`
  CHANGE COLUMN `PRICE_TAX` `PRICE_TAX` decimal(6,4) NULL DEFAULT NULL COMMENT '����˰�ʣ��͵��۵Ĺ�ϵ��˰��Ϊ0������Ϊ����˰��˰�ʲ�Ϊ0������Ϊ��˰';


  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '�ܺ�˰���';
  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���ο�Ʊ���';
  ALTER TABLE `t_vendor_receive_invoice`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT 'δ��Ʊ���';

  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `ALL_MONEY` `ALL_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '�ܺ�˰���';
  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���ο�Ʊ���';
  ALTER TABLE `t_vendor_receive_invoice_his`
  CHANGE COLUMN `LEFT_MONEY` `LEFT_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT 'δ��Ʊ���';


ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `TOTAL_MONEY` `TOTAL_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '�ܽ��';
ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `NEED_MONEY` `NEED_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '��������';
ALTER TABLE `t_vendor_settlement`
  CHANGE COLUMN `SET_MONEY` `SET_MONEY` decimal(10,2) NOT NULL DEFAULT 0.00 COMMENT '�ѽ�����';


ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '�ɹ��ĵ���';
ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';
ALTER TABLE `t_warehouse_receive_detail`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT 0.00 COMMENT '�����ͻ����';

ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT .00000 COMMENT '�ɹ��ĵ���';
ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';
ALTER TABLE `t_warehouse_receive_detail_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT 0.00 COMMENT '�����ͻ����';

ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '���ϵ���';
ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';

ALTER TABLE `t_warehouse_send_detail`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���γ������';


  ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `C_PRICE` `C_PRICE` decimal(12,10) NULL DEFAULT NULL COMMENT '���ϵ���';
ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `TAX_RATE` `TAX_RATE` decimal(6,4) NULL DEFAULT NULL COMMENT '˰��';

ALTER TABLE `t_warehouse_send_detail_his`
  CHANGE COLUMN `CUR_MONEY` `CUR_MONEY` decimal(10,2) NULL DEFAULT NULL COMMENT '���γ������';


ALTER TABLE `t_customer_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�';
  ALTER TABLE `t_customer_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�';


  ALTER TABLE `t_vendor_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO';
  ALTER TABLE `t_vendor_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO';


  ALTER TABLE `t_warehouse_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��';
   ALTER TABLE `t_warehouse_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NOT NULL DEFAULT '' COMMENT '�ؼ��֣���������ⵥ����Ϊ��Ӧ���ͻ����ţ����ⵥ��Ϊ���ⵥ��';



















