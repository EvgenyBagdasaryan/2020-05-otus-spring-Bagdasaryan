--liquibase formatted sql

drop table if exists genres;

--changeset evgeny:2020-07-19-0001-genres
create table genres (
    id bigint auto_increment primary key ,
    name varchar(255) not null
);

