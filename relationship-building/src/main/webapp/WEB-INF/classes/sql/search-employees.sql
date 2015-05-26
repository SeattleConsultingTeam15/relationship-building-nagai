select
    *
from 
    employees e
where 
    e.dlete_flag = 0
and (
    emp_name 
like /*searchWord*/
or 
    emp_frigana 
like /*searchWord*/
or 
    birthday 
like /*searchWord*/
or 
    telephone_number 
like /*searchWord*/
or 
    note 
like /*searchWord*/
)


