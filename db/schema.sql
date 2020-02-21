drop table user if exists;
create table user (
  id varchar(20) primary key,
  name varchar(100),
  password varchar(100),
  auth varchar(20),
  phone varchar(11)
);