Proyecto final
Alumno: Lucas Robles

Entrega de codigo: 
Considero que en este momento el proyecto se encuentra en un considerable grado de avance: Al momento falta incluir sistema de usuarios, login, seguridad y mejorar el diseño e interfaz para presentar los datos.
Nota: el proyecto implementa conceptualmente el patrón MVC adaptado a una API REST, donde las entidades y servicios conforman el Modelo, las clases Resource actúan como Controladores, y las respuestas estructuradas en formato JSON representan la Vista, se inicializó el proyecto con wildfly. Se adjunta codigo fuente de la base de datos PostgreSQL.

Para poder ejecutar el proyecto de manera local, es necesario contar con:
1. Java SE 21
2. Docker y Docker Compose instalados para levantar el entorno de base de datos.

Instrucciones para ejecutar 

Iniciar base de datos

En la terminal del proyecto iniciamos la base de datos PostgreSQL con PGadmin
-----
docker compose up -d
---
pgAdmin :
    URL: http://localhost:5050
    Email: admin@tactica.com
    Contraseña: admin
PostgreSQL:
    Usuario: admin
    Contraseña: adminpassword
    Base de datos: tactica_db
    Puerto: 5432

Ejecutar servidor local wildfly
-----
./mvnw clean package wildfly:run
-------

Acceder a la aplicacion:
http://localhost:8080/tactica01

