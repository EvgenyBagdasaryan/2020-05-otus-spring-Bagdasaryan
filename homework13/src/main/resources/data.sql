insert into genre (id,name) values (1,'Фантастика');
insert into genre (id,name) values (2,'Детектив');
insert into genre (id,name) values (3,'Самообучение');

insert into author (id, name)
values (1, 'Братья Стругатские'),
       (2, 'Станислав Лем'),
       (3, 'Айзек Азимов'),
       (4, 'Агата Кристи'),
       (5, 'Жорж Санд'),
       (6, 'Валентина Шах-Назарова');

insert into book (id, title, genre_id, author_id)
values (1, 'Английский Для Вас', 3, 6),
       (2, 'Рассказы о пилоте Пирксе', 1, 2),
       (3, 'Десять негритят', 2, 4),
       (4, 'Я, Робот', 1, 3),
       (5, 'Основание', 1, 3);

insert into comment (id,text,book_id,user_id) values(1,'Увлекательно',1,1);
insert into comment (id,text,book_id,user_id) values(2,'Интересно',1,1);
insert into comment (id,text,book_id,user_id) values(3,'Очень увлекательно',1,2);
insert into comment (id,text,book_id,user_id) values(4,'Очень интересно',1,2);

insert into user (id,username,password) values(1,'admin','admin');
insert into user (id,username,password) values(2,'user','user');
insert into user (id,username,password) values(3,'user2','user');

insert into user_role (user_id,roles) values (1,'ADMIN');
insert into user_role (user_id,roles) values (2,'USER');
insert into user_role (user_id,roles) values (3,'BANNED_USER');