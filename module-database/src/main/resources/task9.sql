create function get_average_mark(func_subj_name text)
    returns float
    language 'plpgsql'
as $$
declare
average_mark float;

begin
select avg(er.mark)
into average_mark
from subjects s
         inner join exam_result er on s.id = er.subject_id
where s.subject_name = func_subj_name
group by s.id;

return average_mark;
end; $$

select get_average_mark('subject3')