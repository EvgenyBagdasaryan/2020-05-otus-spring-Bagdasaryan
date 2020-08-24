--liquibase formatted sql

--changeset evgeny:2020-07-19-0001-comments
create table comments (
    id bigint auto_increment,
    comment varchar(255) not null,
    book_id bigint not null,
    primary key (id)
);

