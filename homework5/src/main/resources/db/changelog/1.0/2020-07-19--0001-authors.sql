--liquibase formatted sql

--changeset evgeny:2020-07-19-0001-authors
create table authors (
    id bigint auto_increment,
    name varchar(255) not null,
    primary key (id)
)
