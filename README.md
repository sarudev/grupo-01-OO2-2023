# Sensores para la UNLa

Trabajo Práctico para la materia "Orientación a Objetos II" en el tercer año de la carrera Sistemas en la Universidad Nacional de Lanús.

# Dependencias

Como dependencias principales, el programa requiere tener instalados [`Java 17`](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) y [`Node 16`](https://nodejs.org/es/download/releases) (o superior), así como sus variables de entorno correctamente condiguradas.

`Java` por su parte también requiere tener instalado [`Maven`](https://maven.apache.org/download.cgi).

Se debe tener instalado `MySQL server`, que se puede obtener junto a [`MySQL Worckbench`](https://www.mysql.com/products/workbench/) durante su instalación.

# Antes de empezar...

En la carpeta raíz se puede encontrar el archivo `database.sql` que al ejecutarlo en `MySQL` genera la base de datos necesaria para ejecutar el programa.

En el directorio `backend/src/main/resources` se debe crear el archivo `application.properties` con la siguiente configuración:
```properties
# MODIFICAR ESTOS VALORES
# Database url (e.g. localhost)
enviroment.db.host.url = 
# Database port (e.g. 3306)
enviroment.db.host.port = 
# Database username (e.g. root)
enviroment.db.host.username = 
# Database password (e.g. root)
enviroment.db.host.password = 


# NO MODIFICAR ESTOS VALORES
spring.jpa.hibernate.ddl-auto = update
spring.datasource.url = jdbc:mysql://${enviroment.db_host_url}:${enviroment.db_host_port}/smart-campus?serverTimezone=GMT-3
spring.datasource.username = ${enviroment.db.host.username}
spring.datasource.password = ${enviroment.db.host.password}
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.sql.init.mode = never
spring.jpa.database-platform = org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.dialect : org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql : true
server.port = 5500
logging.level.org.hibernate = ERROR
logging.level.org.springframework.web = DEBUG
```
Después del símbolo `=` se deben agregar los datos requeridos para una correcta conexión con la base de datos.

Se puede encontrar una preview del programa completo en este [link](test.com).

# Como iniciar el proyecto

Para empezar, se recomienda ejecutar el `backend` desde un IDE como `Eclipse` o `Spring Tool Suite`, y el frontend desde la terminal del sistema operativo.

Para poner en marcha el `frontend`, se debe acceder a la ubicación raíz (`frontend/`) y ejecutar en la terminal el siguiente comando: `npm start`
<br/>
Si se desea cambiar el puerto al `frontend`, se puede ejecutar el siguiente comando: `npm start --port` seguido del número del puerto.

# Inicialización del contenido

Cuando se ejecute por primera vez el `backend`, en la base de datos `campus`, se crearán las tablas necesarias, pero no tendrán contenido.
<br/>
Dicho contenido inicial se cargará cuando se acceda el `frontend` por primera vez, y cada vez que se vuelva a la ruta inicial (`/`) se hará la validación para comprobar que los datos están correctamente cargados.
<br/>
En el caso de que los datos no estén cargados y se acceda a otra ruta, se redireccionará automáticamente a la ruta inicial para cargar los datos necesarios.

