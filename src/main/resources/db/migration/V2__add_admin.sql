INSERT INTO users (id, archive, isEnable, blockTime, email, name, password, role)
VALUES (1, false, true, null, 'mail@gmail.com', 'admin', '$2a$10$Cqp01JcRZ2UDXnYagfrIMefJBD7bOr0PpXh.AiZz3VD.qd6IYv16q', 'ADMIN');

INSERT INTO users (id, archive, isEnable, blockTime, email, name, password, role)
VALUES (2, false, true, null, 'mail1@gmail.com', 'user', '$2a$10$Cqp01JcRZ2UDXnYagfrIMefJBD7bOr0PpXh.AiZz3VD.qd6IYv16q', 'USER');

INSERT INTO users (id, archive, isEnable, blockTime, email, name, password, role)
VALUES (3, false, true, null, 'mail2@gmail.com', 'doctor', '$2a$10$Cqp01JcRZ2UDXnYagfrIMefJBD7bOr0PpXh.AiZz3VD.qd6IYv16q', 'DOCTOR');

ALTER SEQUENCE user_seq RESTART WITH 4;