DROP DATABASE IF EXISTS crud_web_app;

CREATE DATABASE IF NOT EXISTS crud_web_app;

USE crud_web_app;

CREATE TABLE IF NOT EXISTS user (
  name VARCHAR(15) NOT NULL,
  password VARCHAR(20) NOT NULL

);

INSERT INTO user VALUES ('admin', '1234');
INSERT INTO user VALUES ('temp', 'temp');

CREATE TABLE IF NOT EXISTS book (
  book_id VARCHAR(100) NOT NULL,
  title VARCHAR(50) NOT NULL,
  author VARCHAR(30) NOT NULL
);

INSERT INTO book VALUES ('1','The Lord of the Rings', 'JRR Tolkien');
INSERT INTO book VALUES ('2', 'Pride and Prejudice', 'Jane Austen');
INSERT INTO book VALUES ('3', 'His Dark Materials', 'Philip Pullman');
INSERT INTO book VALUES ('4', 'The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams');
INSERT INTO book VALUES ('5', 'To Kill a Mockingbird', 'Harper Lee');
INSERT INTO book VALUES ('6', 'Winnie the Pooh', 'AA Milne');
INSERT INTO book VALUES ('7', 'Nineteen Eighty-Four', 'George Orwell');
INSERT INTO book VALUES ('8', 'The Lion, the Witch and the Wardrobe', 'CS Lewis');