# API Cidades

## DataBase

### Postgres

```shell script
Criando DataBase
   # su – postgres
   bash-4.1$ psql
   postgres=# CREATE DATABASE  db_cities WITH ENCODING='UTF-8';   // Criar Database
   postgres=# CREATE USER user_cities WITH PASSWORD 'pgadmin';   // Criar User e Senha
   postgres=# GRANT ALL ON DATABASE db_cities TO user_cities; //Atribuir permissões do usuario a database
   postgres=# GRANT ALL PRIVILEGES ON DATABASE db_cities to postgres;  Atribuir permissões do usuario postgres a database
   postgres=# ALTER USER postgres WITH PASSWORD 'redhat'; Alterando a senha do usuario Postgres 
   postgres=# \q
   
   Alterar o arquivo pg_hba.conf
   # vi /var/lib/pgsql/data/pg_hba.conf
		local all all             password
		# IPv4 local connections:
		host all all 127.0.0.1/32 password
		# IPv6 local connections:
		host all all ::1/128      password
    Para permitir a conexão de endereços remotos adicionar uma linha correspondente, como no exemplo abaixo:
		host all all <IP>/32 password

```

### Populate

* [data](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL)

```shell script

psql -h localhost -U user_cities db_cities -f /tmp/pais.sql
psql -h localhost -U user_cities db_cities -f /tmp/estado.sql
psql -h localhost -U user_cities db_cities -f /tmp/cidade.sql

*****************

psql -h localhost -U user_cities db_cities

CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```

* [Postgres Earth distance](https://www.postgresql.org/docs/current/earthdistance.html)
* [earthdistance--1.0--1.1.sql](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.0--1.1.sql)
* [OPERATOR <@>](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.1.sql)
* [postgrescheatsheet](https://postgrescheatsheet.com/#/tables)
* [datatype-geometric](https://www.postgresql.org/docs/current/datatype-geometric.html)




### Query Earth Distance

Point
```roomsql
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

Cube
```roomsql
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;
```

## Spring Boot

* [https://start.spring.io/](https://start.spring.io/)

+ Java 8
+ Maven
+ Jar
+ Spring Web
+ Spring Data JPA
+ PostgreSQL Driver

### Spring Data

* [jpa.query-methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)

### Properties

* [appendix-application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html)
* [jdbc-database-connectio](https://www.codejava.net/java-se/jdbc/jdbc-database-connection-url-for-common-databases)

### Types

* [JsonTypes](https://github.com/vladmihalcea/hibernate-types)
* [UserType](https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/usertype/UserType.html)

