ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����';

ALTER TABLE `t_warehouse_receive_detail`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������';

ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)00---�ر���ⵥ31---����ȷ�ϸ���';
                                       
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����';
                                      
ALTER TABLE `t_warehouse_receive_detail_his`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������';

ALTER TABLE `t_warehouse_send_detail`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������';
ALTER TABLE `t_warehouse_send_detail_his`  ADD COLUMN `FINANCE_PERIOD` varchar(8) NULL DEFAULT NULL COMMENT '����ȷ�ϵ����ڻ�������������';