# Sensores para la UNLa

Trabajo Práctico para la materia "Orientación a Objetos II" en el tercer año de la carrera Sistemas en la Universidad Nacional de Lanús.

## Antes de empezar...

En la carpeta raíz se puede encontrar el archivo `database.sql` que es un script para ejecutarlo con MySQL, en caso de no ejecutarlo el proyecto no podrá funcionar.

En el directorio `backend/src/main/resources` se debe crear el archivo `aplication.properties` con la siguiente configuración:
```properties
# MODIFY THIS VALUES
enviroment.db.host.url = 
enviroment.db.host.port = 
enviroment.db.host.username = 
enviroment.db.host.password = 


# DON'T MODIFY UNLESS YOU KNOW WHAT ARE YOU DOING
spring.jpa.hibernate.ddl-auto = update
spring.datasource.url = jdbc:mysql://${enviroment.db_host_url}:${enviroment.db_host_port}/smart-campus?serverTimezone=GMT-3
spring.datasource.username = ${enviroment.db.host.username}
spring.datasource.password = ${enviroment.db.host.password}
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.sql.init.mode = never
spring.jpa.database-platform = org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.dialect : org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql : true
server.port = 3300
```
Después del símbolo `=` se deben agregar los datos requeridos para una correcta conexión con la database.

## Como iniciar el proyecto
