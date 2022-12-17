CREATE USER 'test'@'%' IDENTIFIED BY 'password';
GRANT ALL on *.* TO 'test'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON `northwind` . * TO 'test'@'%';
GRANT ALL PRIVILEGES ON `recommendations` . * TO 'test'@'%';
GRANT ALL PRIVILEGES ON `supportissues` . * TO 'test'@'%';