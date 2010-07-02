create or replace view v_sales_benefit_detail as 
select  t.staff_id as sales_id,t.staff_Name as sales_name   , w.create_time,w.send_po_no,w.c_code as cust_code , w.short_name as cust_short_name,
w.part_no as part_no, v.pn_desc, w.c_part_no as cust_part_no , w.c_price as cust_price , w.tax_rate as cust_tax_rate,
w.amount , w.cur_money, (w.cur_money - w.amount * v.price) as benefit,
 v.c_part_no as vendor_part_no  ,v.price as vendor_price , v.c_code as vendor_code  , v.c_short_name as vender_short_name
from t_warehouse_send_detail w left join t_vendor_part_no_info v on (w.vendor_code = v.c_code and w.part_no = v.part_no) 
,t_customer_info t 
where t.c_code = w.c_code and t.state =0 ;

create or replace view v_sales_benefit_total as 
select   sales_id , sales_name , EXTRACT(YEAR_MONTH FROM create_time)as create_time,
        cust_code ,cust_short_name , part_no , pn_desc ,cust_part_no , cust_price , vendor_code , vender_short_name ,vendor_part_no, sum(amount) as amount, sum(cur_money) as cur_money,sum(benefit)as benefit
from v_sales_benefit_detail

group by sales_id , sales_name , create_time ,cust_code ,cust_short_name ,part_no , pn_desc , cust_part_no , cust_price , vendor_code,vender_short_name,vendor_part_no;