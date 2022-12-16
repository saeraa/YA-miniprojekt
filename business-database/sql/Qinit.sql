CREATE USER 'test'@'%' IDENTIFIED BY 'password';
GRANT ALL on *.* TO 'test'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `northwind` . * TO 'test'@'%';