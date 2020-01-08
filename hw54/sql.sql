create database if not exists `household_store` character set utf8;

create table if not exists `household_store`.`products`(
`id` int auto_increment primary key ,
`name` varchar(50) not null check(length(`name`)>0),
`amount` int not null default 0 check(`amount`>=0)
);

create table if not exists `household_store`.`buyers`
(
`id` int auto_increment primary key,
`login`    varchar(50) not null unique,
`password` varchar(50) not null,
`firstName` varchar(50),
`lastName` varchar(50)
);

create table if not exists `household_store`.`sellers`
(
`id` int auto_increment primary key,
`login`    varchar(50) not null unique,
`password` varchar(50) not null,
`firstName` varchar(50),
`lastName` varchar(50)
);

create table if not exists `household_store`.`basket`(
`id` int auto_increment primary key,
`buyer_id` int not null,
`product_id` int not null,
`amount` int not null default 0 check(`amount`>=0),
constraint fk_buyer_basket foreign key (`buyer_id`) references  `household_store`.`buyers`(`id`),
constraint fk_product_basket foreign key (`product_id`) references  `household_store`.`products`(`id`)
);

create table if not exists `household_store`.`orders`(
`id` int auto_increment primary key,
`buyer_id` int not null,
`seller_id` int not null,
`product_id` int not null,
`amount` int not null default 0 check(`amount`>=0),
constraint fk_basket_orders foreign key (`buyer_id`) references  `household_store`.`buyers`(`id`),
constraint fk_seller_orders foreign key (`seller_id`) references  `household_store`.`sellers`(`id`)
);

create table if not exists `household_store`.`tokens`(
`id` int auto_increment primary key,
`buyer_id` int not null,
`token` text unique not null,
constraint fk_buyer_token foreign key (`buyer_id`) references  `household_store`.`buyers`(`id`)
);


insert into `household_store`.`products`(`name`, `amount`) values("Refrigerator", 5), ("Dishwasher", 10), ("Gas stove", 8), ("Washer", 3);

insert into `household_store`.`sellers`(`id`, `login`, `password`) values(1, "seller", "seller");

