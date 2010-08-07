update t_cust_part_no_info p, t_company_part_no c set p.pn_desc = c.pn_desc, p.p_cls_code=c.p_cls_code where c.part_no = p.part_no and p.pn_desc = 'aa'

update t_cust_part_no_info p, t_customer_info c set p.staff_id = c.staff_id, p.staff_name = c.staff_name where p.c_code = c.c_code and p.staff_id is null

update t_cust_part_no_info set create_date = now() where create_date is null

update t_cust_part_no_info p, t_company_part_no c set p.p_cat_code=c.p_cat_code where c.part_no = p.part_no and p.p_cat_code is null
