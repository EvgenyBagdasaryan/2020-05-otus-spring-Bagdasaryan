--liquibase formatted sql

--changeset evgeny:2020-07-19-0001-books
create table books (
    id bigint auto_increment,
    name varchar(255) not null,
    genre_id bigint not null,
    author_id bigint not null,
    primary key (id)
)