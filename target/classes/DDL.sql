CREATE DATABASE `sms`;
USE `sms`;

CREATE TABLE student(
	email VARCHAR(50) NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL
);

CREATE TABLE course(
	id int NOT NULL PRIMARY KEY,
	NAME VARCHAR(50) NOT NULL,
	instructor VARCHAR(50) NOT NULL
);