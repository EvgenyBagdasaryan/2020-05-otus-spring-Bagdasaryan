--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-authors
insert into authors (id, name)
values (1, 'Братья Стругатские'),
       (2, 'Станислав Лем'),
       (3, 'Айзек Азимов'),
       (4, 'Агата Кристи'),
       (5, 'Жорж Санд'),
       (6, 'Валентина Шах-Назарова');


--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-genres
insert into genres (id, name)
values (1, 'фантастика'),
       (2, 'детектив'),
       (3, 'самообучение');


--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-books
insert into books (id, name, genre_id, author_id)
values (1, 'Английский Для Вас', 3, 6),
       (2, 'Рассказы о пилоте Пирксе', 1, 2),
       (3, 'Десять негритят', 2, 4),
       (4, 'Я, Робот', 1, 3),
       (5, 'Основание', 1, 3);

--liquibase formatted sql

--changeset evgeny:2020-07-19-0002-comments
insert into comments (id, comment, book_id)
values (1, 'Увлекательно', 1),
       (2, 'Интересно', 2);

insert into user (id,username,password) values(1,'admin','password');