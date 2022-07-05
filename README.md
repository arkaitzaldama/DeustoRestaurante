<<<<<<< HEAD
# deustoReventa

## Base de datos
_Crear una base de datos llamada productsDB en MySQL y dar permisos al usuario por defecto_
```
=======
deustoReventa
Base de datos
Crear una base de datos llamada productsDB en MySQL y dar permisos al usuario por defecto

>>>>>>> f7af9e59c150922e3f7a0349d6194132121f6469

DROP SCHEMA IF EXISTS productsDB;
DROP USER IF EXISTS 'spq'@'localhost';

CREATE SCHEMA productsDB;

CREATE USER 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON productsDB.* TO 'spq'@'localhost';
<<<<<<< HEAD
```

_Lo primero que hay que realizar es compilar el código_

```
mvn clean compile
```

_Despues lanzar los test unitarios_
```
mvn test -Punit
```

_Despues realizar el enhance_
```
mvn datanucleus:enhance
```


_Despues crear las tablas en la base de datos_
```
mvn datanucleus:schema-create
```

_Rellenar Datos de prueba_
```
mvn exec:java -Pdatos
```
_Lanzar el servidor_

```
mvn jetty:run
```

_Si se desea lanzar los test de integracion, sino saltar este paso_

```
mvn test -Pintegration
```

_Si se desea lanzar los test de performance, sino saltar este paso_

```
mvn test -Pperformance
```

_Lanzar el cliente_

```
mvn exec:java -Pclient
```

_Para generar la documentacion_

```
mvn doxygen:report
```
```
mvn validate
```

=======
Lo primero que hay que realizar es compilar el código

mvn clean compile
Despues lanzar los test unitarios

mvn test -Punit
Despues realizar el enhance

mvn datanucleus:enhance
Despues crear las tablas en la base de datos

mvn datanucleus:schema-create
Rellenar Datos de prueba

mvn exec:java -Pdatos
Lanzar el servidor

mvn jetty:run
Si se desea lanzar los test de integracion, sino saltar este paso

mvn test -Pintegration
Si se desea lanzar los test de performance, sino saltar este paso

mvn test -Pperformance
Lanzar el cliente

mvn exec:java -Pclient
Para generar la documentacion

mvn doxygen:report
mvn validate
>>>>>>> f7af9e59c150922e3f7a0349d6194132121f6469
