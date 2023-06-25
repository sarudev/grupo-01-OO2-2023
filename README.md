# Sensores para la UNLa

Trabajo Práctico para la materia "Orientación a Objetos II" en el tercer año de la carrera Sistemas en la Universidad Nacional de Lanús.

# Dependencias

Como dependencias principales, el programa requiere tener instalados [`Java 17`](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) y [`Node 16`](https://nodejs.org/es/download/releases) (o superior), así como sus variables de entorno correctamente condiguradas.

`Java` por su parte también requiere tener instalado [`Maven`](https://maven.apache.org/download.cgi).

Se debe tener instalado `MySQL server`, que se puede obtener junto a [`MySQL Worckbench`](https://www.mysql.com/products/workbench/) durante su instalación.

# Antes de empezar...

Se debe ejecutar la siguiente petición a `MySQL` mediante `MySQL Workbench` (u otro):
```sql
CREATE DATABASE IF NOT EXISTS `campus`;
```

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
spring.datasource.url = jdbc:mysql://${enviroment.db_host_url}:${enviroment.db_host_port}/campus?serverTimezone=GMT-3
spring.datasource.username = ${enviroment.db.host.username}
spring.datasource.password = ${enviroment.db.host.password}
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
spring.sql.init.mode = never
spring.jpa.database-platform = org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.dialect : org.hibernate.dialect.MySQL5Dialect
spring.jpa.show-sql : false
server.port = 5282
logging.level.root=WARN
logging.level.org.springframework.web=WARN
logging.level.org.hibernate=ERROR
```
Después del símbolo `=` se deben agregar los datos requeridos para una correcta conexión con la base de datos.

# Como iniciar el proyecto

Para empezar, se recomienda ejecutar el `backend` desde un IDE como `Eclipse` o `Spring Tool Suite`, y el frontend desde la terminal del sistema operativo.

Para poner en marcha el `frontend`, se debe acceder a la ubicación raíz (`frontend/`) y ejecutar en la terminal el siguiente comando: `npm run dev`

# Inicialización del contenido

Cuando se ejecute por primera vez el `backend`, en la base de datos `campus`, se crearán las tablas necesarias, pero no tendrán contenido.
<br/>
Dicho contenido inicial se cargará cuando se acceda el `frontend` por primera vez, y cada vez que se vuelva a la ruta inicial (`/`) se hará la validación para comprobar que los datos están cargados.
<br/>
En el caso de que los datos no estén cargados y se acceda a otra ruta, se redireccionará automáticamente a la ruta inicial (`/`) para cargar los datos necesarios.

# Funcionamiento del Frontend

Al iniciar (ruta `/`) se verá un mapa interactivo de la UNLa, en dicho mapa se puede seleccionar: Edificio, Espacio Verde o Parking.
<br/>
En la parte superior del mapa interactivo, se puede visualizar un aviso, en el mismo se puede encontrar tres cosas:
- Servidor no funciona
- No haz iniciado sesión
- Haz iniciado sesión como [RolDeUsuario]

Para poder acceder a cualquier ruta ajena a `/` se debe iniciar sesión, en caso de no hacerlo e ingresar a otra ruta, se pedirá iniciar sesión, los datos se inicio de sesión son los siguiente:
### Para el rol usuario
- Nombre: `user`
- Contraseña: `user`
### Para el rol administrador
- Nombre: `admin`
- Contraseña: `admin`

Al hacer click en alguno, se redirige a otra página donde se puede ver información del lugar.
<br/>
Tomando de ejemplo al Edificio 1, José Hernández (ruta `/edificio/1`), se puede observar información del lugar visitado y un menú con pestañas que contiene lo siguiente:
<br/>
### Estado:
Aquí se puede ver el estado general del lugar, como luces, persianas, etc
<br/>
### Aulas o estacionamientos:
Aquí se pueden ver y visitar las diferentes aulas/estacionamientos. También se puede filtrar por nombre. Si se es administrador, se puede agregar una nueva.
<br/>
### Sensores:
Aquí se puede ver que tipos de sensores hay en el lugar, así como también su estado (activo o inactivo). Si se desea, se puede filtrar por sensores activos, inactivos o todos. Si se es administrador se puede encender o apagar un sensor.
### Historial:
Aquí se pueden ver los eventos generados por los sensores, dichos eventos son generados de manera pseudo aleatoria. También se puede filtrar de diferentes formas:
- Todos: muestra todos los eventos.
- Tipo del sensor: se puede filtrar por el tipo del sensor asociado al evento.
- Descripción: se puede filtrar por la descripción del evento.
- Fecha: se puede elegir entre que fechas filtrar los eventos.

 
