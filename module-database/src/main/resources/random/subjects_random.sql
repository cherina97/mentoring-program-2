insert into subjects(subject_name, tutor)
select
    left (md5(i::text), 10),
    left (md5(i::text), 10)
from generate_series(1, 1000) s(i);