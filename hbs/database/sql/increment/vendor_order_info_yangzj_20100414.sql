ALTER TABLE `t_vendor_order`  CHANGE COLUMN `OPER_ID` `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '������ԱID';
ALTER TABLE `t_vendor_order`  CHANGE COLUMN `OPER_STAFF` `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '¼�붩������';
  
ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `OPER_ID` `STAFF_ID` varchar(20) NULL DEFAULT NULL COMMENT '������ԱID';

ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `OPER_STAFF` `STAFF_NAME` varchar(32) NULL DEFAULT NULL COMMENT '¼�붩������';

ALTER TABLE `t_vendor_order_his`  CHANGE COLUMN `ACTIVE_SATE` `ACTIVE_STATE` varchar(8) NULL DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������';

ALTER TABLE `t_vendor_order`  CHANGE COLUMN `ACTIVE_SATE` `ACTIVE_STATE` varchar(8) NULL DEFAULT NULL COMMENT '�����״̬��  PAUSE---�ɹ���ͣ��������ͣ״̬��������ҵ��״̬ͣ���ڵ�ǰ����ҵ���⣬���ܼ������  ACTIVE---�ɹ��������������ͣ�Ķ������ɹ�������';

