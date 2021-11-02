insert into students (name, surname, date_of_birth, phone_number, primary_skill)
values ('name2', 'surname2', '2000-01-02', '002-002-002', 'some skills 2'),
       ('name3', 'surname3', '2000-01-03', '003-003-003', 'some skills 3'),
       ('name4', 'surname4', '2000-01-04', '004-004-004', 'some skills 4'),
       ('name5', 'surname5', '2000-01-05', '005-005-005', 'some skills 5'),
       ('name6', 'surname6', '2000-01-06', '006-006-006', 'some skills 6'),
       ('name7', 'surname7', '2000-01-07', '007-007-007', 'some skills 7'),
       ('name8', 'surname8', '2000-01-08', '008-008-008', 'some skills 8'),
       ('name9', 'surname9', '2000-01-09', '009-009-009', 'some skills 9');

insert into subjects (subject_name, tutor)
values ('subject1', 'tutor1'),
       ('subject2', 'tutor2'),
       ('subject3', 'tutor3'),
       ('subject4', 'tutor4'),
       ('subject5', 'tutor5'),
       ('subject6', 'tutor6');


insert into exam_result (mark, student_id, subject_id)
values (5, 1, 1),
       (4, 1, 3),
       (4, 2, 4),
       (5, 3, 1);