create
materialized view students_snapshot as
select s.name, s.surname, sub.subject_name, er.mark
from students s
         inner join exam_result er on s.id = er.student_id
         inner join subjects sub on sub.id = er.subject_id

select *
from students_snapshot;

update students
set name = 'Updated name'
where name = 'name2';

select s.name, s.surname, sub.subject_name, er.mark
from students s
         inner join exam_result er on s.id = er.student_id
         inner join subjects sub on sub.id = er.subject_id