--liquibase formatted sql

drop table if exists books;

--changeset evgeny:2020-07-19-0001-books
create table books (
    id bigint auto_increment primary key,
    name varchar(255) not null,
    genre_id bigint references genres(id),
    author_id bigint references authors(id)
)