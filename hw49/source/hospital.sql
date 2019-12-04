 -- Однострочный комент
 /* Создаем базу данных */
 create database if not exists hospital default charset utf8;
 
create table if not exists users
(
	id int primary key auto_increment,
    login varchar(30) not null unique,
    password varchar(30) not null,
    enable bool
);

INSERT INTO users(login, password, enable) VALUES ('admin', 'qwerty', 1);