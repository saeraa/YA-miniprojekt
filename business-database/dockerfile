FROM mysql:5.7
COPY ./sql/ /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=password
EXPOSE 3306
CMD ["mysqld"]