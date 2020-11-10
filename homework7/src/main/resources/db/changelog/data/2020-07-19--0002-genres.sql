--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-genres
insert into genres (id, name)
values (1, 'фантастика'),
       (2, 'детектив'),
       (3, 'самообучение')
