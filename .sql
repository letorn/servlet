create database if not exists servlet;
use servlet;
create table if not exists user(
  id int primary key auto_increment,
  name varchar(15) not null,
  password varchar(25) not null,
  nickname varchar(25),
  birth date
);

delete from user;

insert into user(id, name, password, nickname, birth) values(1, 'admin', 'admin', 'admin', '1990-06-10');
insert into user(id, name, password, nickname, birth) values(2, 'admin', 'admin', 'admin', '1990-06-10');
insert into user(id, name, password, nickname, birth) values(3, 'admin', 'admin', 'admin', '1990-06-10');
insert into user(id, name, password, nickname, birth) values(4, 'admin', 'admin', 'admin', '1990-06-10');
insert into user(id, name, password, nickname, birth) values(5, 'admin', 'admin', 'admin', '1990-06-10');
