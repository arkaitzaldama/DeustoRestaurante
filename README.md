deustoReventa
Base de datos
Crear una base de datos llamada productsDB en MySQL y dar permisos al usuario por defecto


DROP SCHEMA IF EXISTS productsDB;
DROP USER IF EXISTS 'spq'@'localhost';

CREATE SCHEMA productsDB;

CREATE USER 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON productsDB.* TO 'spq'@'localhost';
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
