drop table if exists students;
create table students
(
    id               serial       not null primary key,
    name             varchar(255) not null,
    surname          varchar(255) not null,
    date_of_birth    date         not null,
    phone_number     varchar(255),
    primary_skill    text,
    created_datetime timestamp default current_timestamp,
    updated_datetime timestamp default current_timestamp
);

drop table if exists subjects;
create table subjects
(
    id           serial       not null primary key,
    subject_name varchar(255) not null,
    tutor        varchar(255) not null
);

drop table if exists exam_result;
create table exam_result
(
    id         serial not null primary key,
    mark       integer,
    student_id integer references students (id),
    subject_id integer references subjects (id)
);