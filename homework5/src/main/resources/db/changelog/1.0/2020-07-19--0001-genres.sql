--liquibase formatted sql

--changeset evgeny:2020-07-19-0001-genres
create table genres (
       id bigint,
       name varchar(255),
       primary key (id)
)