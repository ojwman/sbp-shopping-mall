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

CREATE TABLE IF NOT EXISTS MEMBER ( 
  MBR_NO BIGINT NOT NULL AUTO_INCREMENT
, ID VARCHAR(200)
, NAME VARCHAR(200)
, PRIMARY KEY(MBR_NO) /*AUTO_INCREMENT 컬럼 단일 PK */ 
);
--출처: https://goddaehee.tistory.com/209 [갓대희의 작은공간]
