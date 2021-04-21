--liquibase formatted sql

drop table if exists authors;

--changeset evgeny:2020-07-19-0001-authors
create table authors (
                         id bigint auto_increment primary key,
                         name varchar(255) not null
);


--liquibase formatted sql

drop table if exists genres;

--changeset evgeny:2020-07-19-0001-genres
create table genres (
                        id bigint auto_increment primary key ,
                        name varchar(255) not null
);

drop table if exists books;

--changeset evgeny:2020-07-19-0001-books
create table books (
                       id bigint auto_increment primary key,
                       name varchar(255) not null,
                       genre_id bigint,
                       author_id bigint,
                       foreign key (genre_id) references genres(id)  ON DELETE CASCADE,
                       foreign key (author_id) references authors(id)  ON DELETE CASCADE
);

--liquibase formatted sql

drop table if exists comments;

--changeset evgeny:2020-07-19-0001-comments
create table comments (
                          id bigint auto_increment primary key,
                          comment varchar(255) not null,
                          book_id bigint,
                          foreign key (book_id) references books(id) ON DELETE CASCADE
);


drop table if exists user;
create table user
(
    id bigint auto_increment primary key ,
    username  varchar(255) unique,
    password varchar(255)
);