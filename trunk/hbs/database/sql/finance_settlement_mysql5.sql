/** ������Ӧ��δ���˵���ͼ */
create or replace VIEW v_vendor_unsettlement as 
select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as purchase_id, 
vinfo.staff_name as purchase_name, wdetail.settlement_type as settlement_type,
wdetail.finance_period as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_vendor_info vinfo , t_warehouse_receive_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type =1
and wdetail.finance_state=1 and vinfo.c_code = wdetail.c_code
group by c_code , short_name , purchase_id , purchase_name, settlement_type,abstract, finance_state 

union 

select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as purchase_id, 
vinfo.staff_name as purchase_name, wdetail.settlement_type as settlement_type,
wdetail.rec_po_no as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_vendor_info vinfo , t_warehouse_receive_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type != 1
and (wdetail.finance_state=0 or wdetail.finance_state=1) and vinfo.c_code = wdetail.c_code
group by c_code , short_name , purchase_id , purchase_name, settlement_type,abstract, finance_state ;

/** �����ͻ�δ���˵���ͼ*/
create or replace VIEW v_customer_unsettlement as 
select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as sales_id, 
vinfo.staff_name as sales_name,vinfo.ass_staff_id as ass_id, 
vinfo.ass_staff_name as ass_name, wdetail.settlement_type as settlement_type,
wdetail.finance_period as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_customer_info vinfo , t_warehouse_send_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type =1
and wdetail.finance_state=1 and vinfo.c_code = wdetail.c_code
group by c_code , short_name , sales_id , sales_name, ass_id , ass_name, settlement_type,abstract, finance_state 

union 

select  vinfo.c_code as c_code , vinfo.short_name as short_name, vinfo.staff_id as sales_id, 
vinfo.staff_name as sales_name,vinfo.ass_staff_id as ass_id, 
vinfo.ass_staff_name as ass_name, wdetail.settlement_type as settlement_type,
wdetail.send_po_no as abstract,
 wdetail.finance_state as finance_state  , sum(wdetail.cur_money) as total_money
 ,sum(wdetail.cur_money) as need_money , 00000000.00 as set_money
from t_customer_info vinfo , t_warehouse_send_detail wdetail

where vinfo.state =0 and wdetail.state = '02'  and wdetail.settlement_type != 1
and (wdetail.finance_state=0 or wdetail.finance_state=1) and vinfo.c_code = wdetail.c_code
group by c_code , short_name , sales_id , sales_name, ass_id , ass_name, settlement_type,abstract, finance_state;

/** �����ͻ������ */
CREATE TABLE `t_customer_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '���㷽ʽ   1--�½�  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `ABSTRACT` varchar(16) NOT NULL COMMENT 'ժҪ  ���½�Ϊ����  �Է��½�Ϊ������',
  `TOTAL_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '�ܽ��',
  `NEED_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '��������',
  `SET_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '�ѽ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '����״̬  1 �Ѷ���δ����  2���ֽ���  3�ѽ���',
  `SALES_ID` varchar(20) DEFAULT NULL COMMENT '������ԱID',
  `SALES_NAME` varchar(32) DEFAULT NULL COMMENT '��������',
  `ASS_ID` varchar(20) DEFAULT NULL COMMENT 'ҵ������ID',
  `ASS_NAME` varchar(32) DEFAULT NULL COMMENT 'ҵ����������',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='�ͻ���������';

/** ������Ӧ�̽���� */

CREATE TABLE `t_vendor_settlement` (
  `C_CODE` varchar(8) DEFAULT NULL COMMENT '����',
  `SHORT_NAME` varchar(32) DEFAULT NULL COMMENT '���',
  `SETTLEMENT_TYPE` varchar(2) NOT NULL COMMENT '���㷽ʽ   1--�½�  2---Ԥ��X%,ʣ���������  3---Ԥ��X%ʣ������',
  `ABSTRACT` varchar(16) NOT NULL COMMENT 'ժҪ  ���½�Ϊ����  �Է��½�Ϊ������',
  `TOTAL_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '�ܽ��',
  `NEED_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '��������',
  `SET_MONEY` float(9,2) NOT NULL DEFAULT '0.00' COMMENT '�ѽ�����',
  `FINANCE_STATE` varchar(2) DEFAULT NULL COMMENT '����״̬  1 �Ѷ���δ����  2���ֽ���  3�ѽ���',
  `PURCHASE_ID` varchar(20) DEFAULT NULL COMMENT '�ɹ�ԱID',
  `PURCHASE_NAME` varchar(32) DEFAULT NULL COMMENT '�ɹ�Ա����',
  `STAFF_ID` varchar(20) DEFAULT NULL COMMENT '����ԱID',
  `STAFF_NAME` varchar(32) DEFAULT NULL COMMENT '����Ա����'
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='��Ӧ�̲�������';

/** �����ͻ��ܽ���� */
create or replace view v_customer_total_settlement as 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,SALES_ID,SALES_NAME,ASS_ID,ASS_NAME
from t_customer_settlement
union 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,SALES_ID,SALES_NAME,ASS_ID,ASS_NAME
from v_customer_unsettlement;

/** ������Ӧ���ܽ���� */
create or replace view v_vendor_total_settlement as 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,PURCHASE_ID,PURCHASE_NAME
from t_vendor_settlement
union 
select C_CODE,SHORT_NAME,SETTLEMENT_TYPE,ABSTRACT,TOTAL_MONEY,NEED_MONEY,SET_MONEY,FINANCE_STATE,PURCHASE_ID,PURCHASE_NAME
from v_vendor_unsettlement;

