ALTER TABLE `t_vendor_order`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT NULL COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT '0' COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����';

ALTER TABLE `t_vendor_order`  ADD COLUMN `CUST_C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `STATE` `STATE` varchar(8) NULL DEFAULT '0' COMMENT '����ҵ��״̬01---�ɹ������ɹ��������ɹ���ʱ���涩�����������޷��鿴��02---�ɹ�ȷ�ϲɹ�����,���ջ���⣨������ʽ�����̣������˿��Բ鿴��03---�ɹ�ȡ���ɹ�����������ȡ�������ж�����ϸȫ��ȡ���������ڶ���)04---������ȷ��(���ڶ���)00---�رղɹ�����60----�������61----ȫ�����';

ALTER TABLE `t_vendor_order_detail`  ADD COLUMN `CUST_C_CODE` varchar(32) NULL DEFAULT NULL COMMENT '���0----�ͻ��ɹ�����3-- �ض��ͻ�������Ч';

ALTER TABLE `t_vendor_order_detail`  CHANGE COLUMN `PO_NO_TYPE` `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '' COMMENT '��Ӧ�̶�������0----�ͻ��ɹ���1---�˻���2---���汸���ɹ���3--�ض��ͻ������ɹ���';

ALTER TABLE `t_vendor_order`  CHANGE COLUMN `PO_NO_TYPE` `PO_NO_TYPE` varchar(2) NOT NULL DEFAULT '0' COMMENT '��Ӧ�̶�������0----�ͻ��ɹ���1---�˻���2---���汸���ɹ���3--�ض��ͻ������ɹ���';
C