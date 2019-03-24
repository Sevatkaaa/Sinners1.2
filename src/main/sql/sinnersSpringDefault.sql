-- execute this script as root for using basic rest functionality
-- it will be changed

CREATE DATABASE sinners12;

CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON sinners12.* TO 'demo'@'localhost';
FLUSH PRIVILEGES;
