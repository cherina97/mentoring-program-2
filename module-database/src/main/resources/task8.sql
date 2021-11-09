create function get_average_mark(func_name text, func_surname text)
    returns float
    language 'plpgsql'
as $$
declare
average_mark float;

begin
select avg(er.mark)
into average_mark
from students s
         inner join exam_result er on s.id = er.student_id
where s.name = func_name
  and s.surname = func_surname
group by s.id;

return average_mark;
end; $$

select get_average_mark('name8', 'surname8')