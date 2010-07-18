


update t_cust_order d,t_customer_info c set d.COMPANY_BRANCH = c.COMPANY_BRANCH, d.SETTLEMENT_TYPE=c.SETTLEMENT_TYPE, d.STAFF_ID=c.ASS_STAFF_ID, d.staff_name=c.ass_staff_name, 
       d.sales_id = c.staff_id, d.SALES=c.staff_name, d.IS_SHOW_PRICE=c.IS_SHOW_PRICE, d.state='02'
 where c.c_code = d.c_code and c.state='0' and (d.state is null or d.state = '');

update t_cust_order d, t_customer_contact_info c set d.con_name=c.con_name, d.con_tel=c.con_tel, d.con_fax=c.con_fax where d.c_code = c.c_code and c.state='0' and c.con_type='1' and c.is_primary='0' and d.con_name is null;
update t_cust_order d, t_customer_contact_info c set d.con_name=c.con_name, d.con_tel=c.con_tel, d.con_fax=c.con_fax where d.c_code = c.c_code and c.state='0' and c.con_type='1' and d.con_name is null;

update t_cust_order d, t_customer_contact_info c set d.RECEIVE_NAME=c.con_name, d.RECEIVE_ADDRESS=c.con_ADDRESS, d.RECEIVE_Zip=c.con_zip where d.c_code = c.c_code and c.state='0' and c.con_type='2' and c.is_primary='0' and d.RECEIVE_NAME is null;
update t_cust_order d, t_customer_contact_info c set d.RECEIVE_NAME=c.con_name, d.RECEIVE_ADDRESS=c.con_ADDRESS, d.RECEIVE_ZIP=c.con_zip where d.c_code = c.c_code and c.state='0' and c.con_type='2' and d.RECEIVE_NAME is null;

update t_cust_order_detail d, t_customer_info c set d.TAX_RATE=c.TAX_RATE, d.SETTLEMENT_TYPE=c.SETTLEMENT_TYPE, d.STAFF_ID=c.ASS_STAFF_ID, d.staff_name=c.ass_staff_name, 
       d.sales_id = c.staff_id, d.SALES=c.staff_name, d.state='02', d.CONTACT_FEE=c.CONTACT_FEE
 where c.c_code = d.c_code and c.state='0' and (d.SETTLEMENT_TYPE = '' or d.SETTLEMENT_TYPE is null);

update t_cust_order d, t_cust_account_period c set d.SETTLEMENT_DAY=c.SETTLEMENT_DAY where d.c_code=c.c_code and c.state='0' and d.SETTLEMENT_DAY is null;

update t_cust_order_detail set ACTIVE_STATE='ACTIVE' where ACTIVE_STATE is null;

