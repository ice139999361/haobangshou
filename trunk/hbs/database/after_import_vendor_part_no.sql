update t_vendor_part_no_info p, t_company_part_no c set p.pn_desc = c.pn_desc, p.p_cls_code=c.p_cls_code where c.part_no = p.part_no and p.pn_desc = 'aa'

update t_vendor_part_no_info p, t_vendor_info c set p.staff_id = c.staff_id, p.staff_name = c.staff_name, p.c_short_name = c.short_name where p.c_code = c.c_code and p.staff_id is null

update t_vendor_part_no_info set create_date = now(), min_package=1 where create_date is null
