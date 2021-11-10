drop table if exists student_address;
drop trigger if exists immutable_address_trig on student_address;
drop function if exists immutable_address_func();

create table student_address
(
    student_id int          not null references students (id),
    address    varchar(255) not null
);

create function immutable_address_func()
    returns trigger as $immutable_address_trig$

begin
if
new.student_id is distinct from old.student_id
then new.student_id = old.student_id;
end if;

if
new.address is distinct from old.address
then new.address = old.address;
end if;

return new;
end;
$immutable_address_trig$
language 'plpgsql';

create trigger immutable_address_trig
    before update
    on student_address
    for each row execute function immutable_address_func();

insert into student_address(student_id, address) values ('1', 'address1');
insert into student_address(student_id, address) values ('2', 'address2');
insert into student_address(student_id, address) values ('3', 'address3');
insert into student_address(student_id, address) values ('4', 'address4');
insert into student_address(student_id, address) values ('5', 'address5');
insert into student_address(student_id, address) values ('6', 'address6');
insert into student_address(student_id, address) values ('7', 'address7');
insert into student_address(student_id, address) values ('8', 'address8');

update student_address
set address = 'updated_address'
where student_id = 1;