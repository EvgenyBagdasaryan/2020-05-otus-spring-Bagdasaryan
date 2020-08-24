--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-books
insert into books (id, name, genre_id, author_id)
values (1, 'Английский Для Вас', 3, 6),
       (2, 'Рассказы о пилоте Пирксе', 1, 2),
       (3, 'Десять негритят', 2, 4),
       (4, 'Я, Робот', 1, 3),
       (5, 'Основание', 1, 3)