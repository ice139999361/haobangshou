ALTER TABLE `t_vendor_info`
  ADD COLUMN `CREATE_TIME` datetime NULL DEFAULT NULL COMMENT '¼��ʱ��';
  
ALTER TABLE `t_vendor_oper_log`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER';

ALTER TABLE `t_vendor_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO';

ALTER TABLE `t_vendor_oper_log_his`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '��������  VENDOR_INFO  VENDOR_P/N  VENDOR_ORDER';

ALTER TABLE `t_vendor_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ľ������  �Թ�Ӧ����ϢΪC_CODE  �Թ�Ӧ������ΪC_PART_NO  �Թ�Ӧ�̶���ΪCPO_NO';


ALTER TABLE `t_customer_oper_log`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER';


ALTER TABLE `t_customer_oper_log`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�';

ALTER TABLE `t_customer_oper_log_his`
  CHANGE COLUMN `OPER_OBJECT` `OPER_OBJECT` varchar(128) NULL DEFAULT NULL COMMENT '��������  CUST_INFO  CUST_P/N  CUST_ORDER';

ALTER TABLE `t_customer_oper_log_his`
  CHANGE COLUMN `OPER_KEY` `OPER_KEY` varchar(128) NULL DEFAULT NULL COMMENT '�����ؼ���  �Կͻ���ϢΪC_CODE  ������ΪC_PART_NO  �Զ���Ϊ������  �Զ�����ϸΪ������ϸ���к�';
  
