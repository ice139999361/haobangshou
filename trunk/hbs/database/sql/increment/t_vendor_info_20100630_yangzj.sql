create or replace view v_vendor_info as 
select info.*, p.settlement_day from t_vendor_info  info , t_vendor_account_period p

where info.settlement_type='1'    and info.base_seqid = p.base_seqid 
union
select info.*, p.pre_paid as settlement_day from t_vendor_info  info , t_vendor_prepaid p

where (info.settlement_type='2' or info.settlement_type='3')     and info.base_seqid = p.base_seqid 