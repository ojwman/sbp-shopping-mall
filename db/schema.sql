drop table user if exists;
create table user (
	id varchar(20) primary key,
	name varchar(100) not null,
	password varchar(100) not null,
	auth varchar(20),
	phone varchar(11)
);

drop table item if exists;
create table item (
	id bigint primary key auto_increment,
	name varchar(100) not null,
	category varchar(100) not null,
	price int,
	thumbnail clob,
	hashtag varchar
);