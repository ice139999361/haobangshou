ALTER TABLE `t_warehouse_receive`
  CHANGE COLUMN `STATE` `STATE` varchar(8) NOT NULL DEFAULT '01' COMMENT '�ջ�������ⵥ��״̬  01---�ֹܴ�����ⵥ����ʱ������ⵥ���������޷��鿴��  02---�ֹ�ȷ����ⵥ���������֤ʵȷ�ϣ������˿��Բ鿴��  03---�ֹ�ȡ����ⵥ����Ҫ��ȷ����ⵥ֮ǰ������ȷ�ϵ���ⵥ���ܲ���)  00---�ر���ⵥ  31---����ȷ�ϸ���';
ALTER TABLE `t_warehouse_receive`
  CHANGE COLUMN `ACTIVE_STATE` `ACTIVE_STATE` varchar(8) NOT NULL DEFAULT 'ACTIVE' COMMENT '�״̬  PAUSE---�ֹ���ͣ��ⵥ����ͣ״̬����ⵥ��ҵ��״̬ͣ���ڵ�ǰ�����ֹ��⣬���ܼ�����ⵥ��  ACTIVE---�ֹܼ�����ⵥ��������ͣ����ⵥ���ֹܲ�����';
ALTER TABLE `testdb`.`t_warehouse_receive`
  CHANGE COLUMN `FINANCE_STATE` `FINANCE_STATE` varchar(2) NOT NULL DEFAULT '0' COMMENT '�������״̬  0----δ����  1---�Ѷ���';
