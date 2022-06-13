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
    isEnable  boolean,
    blockTime timestamp,
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
-- alter table if exists appointments
--     add constraint FK886ced1atxgvnf1o3oxtj5m4s foreign key (user_id) references users;