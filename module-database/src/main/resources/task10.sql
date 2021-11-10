create function get_student_at_red_zone()
    returns table (student_name varchar, student_surname varchar)
    language 'plpgsql'
as $$

begin
return query

select s.name, s.surname
from students s
         inner join exam_result er on s.id = er.subject_id
where er.mark < 3
group by s.name, s.surname
having count(er.mark) >= 2;

end; $$

select *
from get_student_at_red_zone();