CREATE DATABASE IF NOT EXISTS shop character set utf8;

create table shop.users
(
    id       int auto_increment
        primary key,
    login    varchar(50) not null unique,
    password varchar(50) not null
);
