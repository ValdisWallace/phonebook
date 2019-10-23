DELETE FROM phone_numbers;
DELETE FROM employees;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO employees (name, age, email, sex) VALUES
('Novolotsky Vadim Alekseevich', 26, 'valdisvanwallace@gmail.com', TRUE),
('Ivanov Ivan Ivanovich', 55, 'ivanov@gmail.com', TRUE),
('Zinoveva Zinoida Zinoidovna', 60, 'zinaida007@gmail.com', FALSE),
('Mihail Ivanovich', 12, 'miha@gmail.com', TRUE),
('Ilya Vitalievich', 32, 'ilya@gmail.com', TRUE),
('Ludmila Sergeevna', 55, 'ludmila@gmail.com', FALSE),
('Semen Semenych', 23, 'sema@gmail.com', TRUE),
('Irina Sergeevna', 23, 'ira@gmail.com', FALSE),
('Dinis Kozlov', 25, 'kozlov@gmail.com', TRUE);

INSERT INTO phone_numbers (number, status, employee_id) VALUES
(89994624400, FALSE, 100000),
(89287514400, TRUE, 100000),
(89456667432, TRUE, 100001),
(89234475463, FALSE, 100002);