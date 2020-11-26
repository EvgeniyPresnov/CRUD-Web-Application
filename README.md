# Project Title
This is a simple web application based on Java EE.

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes.

## Installing

For the beginning install [MySQL](https://dev.mysql.com/doc/refman/5.7/en/windows-installation.html)
on your operation sysytem

Add an user in MySQL
```
mysql -u root -p
CREATE USER 'YOUR_USER'@'localhost' IDENTIFIED BY 'YOUR_PASSWORD';
```
Clone the repository
```
git clone git@github.com:ZhekaPresnov/Simple-CRUD-Web-Application.git
cd Simple-CRUD-Web-Application
```
Edit in IntelliJ IDEA the file **db.properties** in */src/main/resources/db.properties*
by changing the username and password to the newly created now, i.e.
user = 'YOUR_PASSWORD' and password ='YOUR_PASSWORD'

Run in IntelliJ IDEA the script **db.sql** in */src/main/resources/db.sql*
and you will see like this
```
[2020-11-26 08:53:37] Connecting to crud_web_app@localhost... (crud_web_app)
[2020-11-26 08:53:48] Using batch mode (1000 insert/update/delete statements max)
DROP DATABASE IF EXISTS crud_web_app
[2020-11-26 08:53:50] 2 row(s) affected in 2 s 533 ms
CREATE DATABASE IF NOT EXISTS crud_web_app
[2020-11-26 08:53:50] 1 row(s) affected in 32 ms
USE crud_web_app
[2020-11-26 08:53:50] 0 row(s) affected in 2 ms
CREATE TABLE IF NOT EXISTS user (
name VARCHAR(15) NOT NULL,
password VARCHAR(20) NOT NULL
)
[2020-11-26 08:53:51] 0 row(s) affected in 416 ms
INSERT INTO user VALUES ('admin', '1234')
.
[2020-11-26 08:53:51] 2 row(s) affected in 738 ms

CREATE TABLE IF NOT EXISTS book (
book_id VARCHAR(100) NOT NULL,
title VARCHAR(50) NOT NULL,
author VARCHAR(30) NOT NULL
)
[2020-11-26 08:53:52] 0 row(s) affected in 910 ms
INSERT INTO book VALUES ('1','The Lord of the Rings', 'JRR Tolkien')
.
[2020-11-26 08:53:53] 8 row(s) affected in 214 ms
[2020-11-26 08:53:53] Summary: 15 of 15 statements executed in 16 s 301 ms (970 symbols in file)
```
Build the project
```
mvn clean install
```
Start the embedded Tomcat server
```
mvn tomcat7:run
```
Open the browser
```
http://localhost:8088/
```
## Running the tests
```
mvn test
```
# Build With
* [Maven](https://maven.apache.org/) - Dependency Management

# License
```
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 ```

