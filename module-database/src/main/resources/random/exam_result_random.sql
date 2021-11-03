INSERT INTO exam_result(mark, student_id, subject_id)
WITH expanded AS (
    SELECT random(), seq, stu.id AS student_id, sub.id AS subject_id
    FROM GENERATE_SERIES(1, 50) seq,
         students stu,
         subjects sub
),
     shuffled AS (
         SELECT e.*
         FROM expanded e
                  INNER JOIN (
             SELECT ei.seq, MIN(ei.random)
             FROM expanded ei
             GROUP BY ei.seq
         ) em ON (e.seq = em.seq AND e.random = em.min)
         ORDER BY e.seq
     )
SELECT (random() * (5 - 1) + 1)::integer, exam_result.student_id,
       exam_result.subject_id
FROM shuffled exam_result;