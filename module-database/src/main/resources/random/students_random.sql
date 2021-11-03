insert into students(name, surname, date_of_birth, phone_number, primary_skill)
select
    left (md5(i::text), 10),
    left (md5(i::text), 10),
    DATE '2018-01-01' + (random() * 700):: integer,
    (random() * 700 + 100000):: integer,
    left (md5(i::text), 15)
from generate_series(1, 100000) s(i);