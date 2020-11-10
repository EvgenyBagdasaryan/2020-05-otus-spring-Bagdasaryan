--liquibase formatted sql

drop table if exists comments;

--changeset evgeny:2020-07-19-0001-comments
create table comments (
                          id bigint auto_increment primary key,
                          comment varchar(255) not null,
                          book_id bigint references books(id)
);