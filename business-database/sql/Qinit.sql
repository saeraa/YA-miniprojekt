CREATE USER 'test'@'%' IDENTIFIED BY 'mypassword';
GRANT ALL on *.* TO 'test'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `northwind` . * TO 'test'@'%';