INSERT INTO users (id, archive, email, name, password, role)
VALUES (1, false, 'mail@gmail.com', 'admin', 'pass', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;