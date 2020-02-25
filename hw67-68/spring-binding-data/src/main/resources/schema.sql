/*drop database if exists academy;
create database if not exists academy default character set utf8;
use academy;*/
create table "groups"(
                                     id int primary key  auto_increment,
                                     "name" varchar(100) not null check ( length("name")>0)
);
create table students (
                          id int primary key auto_increment,
                          first_name varchar(255) not null,
                          last_name varchar(255) not null,
                          age int not null,
                          group_id int not null,
                          FOREIGN KEY (group_id)  REFERENCES "groups" (id)
);
