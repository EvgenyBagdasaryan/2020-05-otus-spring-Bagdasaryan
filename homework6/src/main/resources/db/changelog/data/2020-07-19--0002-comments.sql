--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-comments
insert into comments (id, comment, book_id)
values (1, 'Увлекательно', 1),
       (2, 'Интересно', 2)