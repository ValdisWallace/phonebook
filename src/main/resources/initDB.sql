DROP TABLE IF EXISTS phone_numbers;
DROP TABLE IF EXISTS employees;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE employees
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    age SMALLINT NOT NULL,
    email VARCHAR NOT NULL,
    sex BOOLEAN NOT NULL
);

CREATE UNIQUE INDEX employee_unique_email_idx ON employees (email);

CREATE TABLE phone_numbers
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    employee_id INTEGER NOT NULL,
    number BIGINT NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX phone_number_unique_number_idx ON phone_numbers (number);