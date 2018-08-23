create table users(
	id varchar(32) primary key,
	name varchar(30),
	pwd varchar(32),
	email varchar(80),
	active char(1),
	acode varchar(32)
);