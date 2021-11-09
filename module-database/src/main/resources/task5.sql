drop trigger set_timestamp on students
drop function trigger_set_timestamp;

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_datetime = now();
RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER set_timestamp BEFORE UPDATE
    ON students FOR EACH ROW EXECUTE PROCEDURE
    trigger_set_timestamp();

update students set name = 'Updated name' where id = 2;