alter table if exists appointments
drop constraint if exists FKgdcpcx3yc2abu5oyb2078lc24;
alter table if exists appointments
drop constraint if exists FK6u6s6egu60m2cbdjno44jbipa;
alter table if exists appointments
drop constraint if exists FK886ced1atxgvnf1o3oxtj5m4s;
drop table if exists appointments cascade;
drop table if exists users cascade;
drop sequence if exists appointment_seq;
drop sequence if exists user_seq;
create sequence appointment_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;
create table appointments
(
    id        int8 not null,
    created   timestamp,
    status    varchar(255),
    updated   timestamp,
    description varchar(255),
    client_id int8,
    doctor_id int8,
--     user_id   int8,
    primary key (id)
);
create table users
(
    id       int8    not null,
    archive  boolean not null,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    role     varchar(255),
    primary key (id)
);
alter table if exists appointments
    add constraint FKgdcpcx3yc2abu5oyb2078lc24 foreign key (client_id) references users;
alter table if exists appointments
    add constraint FK6u6s6egu60m2cbdjno44jbipa foreign key (doctor_id) references users;

INSERT INTO USERS (id, archive, email, name, password, role)
VALUES (1, false, 'mail1@gmail.com', 'user', '$2a$10$iI7l7lV1Qzzx/XNAAzdPBud/eR0fL8pZV0mlS5kIU8q/5uvDnLmUy', 'CLIENT');

INSERT INTO users (id, archive, email, name, password, role)
VALUES (2, false, 'mail2@gmail.com', 'admin', '$2a$10$iI7l7lV1Qzzx/XNAAzdPBud/eR0fL8pZV0mlS5kIU8q/5uvDnLmUy', 'ADMIN');

INSERT INTO users (id, archive, email, name, password, role)
VALUES (3, false, 'mail3@gmail.com', 'doctor', '$2a$10$iI7l7lV1Qzzx/XNAAzdPBud/eR0fL8pZV0mlS5kIU8q/5uvDnLmUy', 'DOCTOR');

ALTER SEQUENCE user_seq RESTART WITH 4;