Proyecto final - SISTEMA DE CATALOGACION DE MEDIOS AEROESPACIALES
Alumno: Lucas Robles
Entrega final - 80%

1. Descripción General
El SISTEMA DE CATALOGACION DE MEDIOS AEROESPACIALES permite la gestión integral de las especificaciones técnicas que poseen las principales entidades y sistemas que tienen incidencia en el poder aeroespacial actual.

2. Tecnologías Utilizadas
Lenguaje: Java 17+
Framework Backend: Jakarta EE / Jakarta MVC
Jakarta Persistence API (JPA) / Hibernate
Base de Datos: PostgreSQL
Frontend: JSP (JavaServer Pages) con JSTL y CSS3.
Servidor de Aplicaciones: WildFly

3. Arquitectura del Sistema
El proyecto sigue el patrón de diseño MVC (Modelo-Vista-Controlador), estructurado de la siguiente manera:
-Controladores (`@Controller`): Encargados exclusivamente de recibir las peticiones HTTP (GET/POST), capturar los parámetros del formulario (`@FormParam`) y redirigir a las vistas correspondientes.
-Entidades (`@Entity`): Clases que mapean  la estructura de la base de datos relacional (ej. `Medio`, `Pais`, `Fabricante`, y las extensiones como `EspecificacionesAereo`).
-Servicios (`@RequestScoped` / `@Transactional`): Clases intermedias que concentran la lógica de negocio y las -transacciones de base de datos usando el `EntityManager`.
-Vistas (JSP): Interfaces de usuario renderizadas del lado del servidor.

Si bien las guías iniciales de la materia introducían el uso del `EntityManager` directamente dentro de los Controladores para simplificar los primeros pasos, en este proyecto se optó por abstraer esa lógica hacia clases de Servicio (`MedioService`).

Esta decisión se alinea con el principio de Separación de Responsabilidades (Separation of Concerns). Al tener formularios complejos (con más de 30 campos y múltiples tablas relacionadas), colocar toda la lógica de persistencia en el Controlador lo volvería inmanejable y difícil de leer. El Controlador se mantiene limpio (solo gestiona rutas) y el Servicio centraliza la lógica de guardado, respetando los conceptos fundamentales de la arquitectura MVC.

Se utilizó JSP con JSTL (`<c:forEach>`, `<c:if>`) para manejar la lógica de presentación sin sobrecargar el HTML con código Java. El diseño se construyó utilizando CSS3 nativo (Grid, Flexbox) prescindiendo de librerías externas garantizando un control total sobre la estética del sistema.

4. Guía de Ejecución 

Atendiendo a la devolución de la entrega del 50%, se preparó este método de ejecución utilizando el plugin de Maven para WildFly. No es necesario descargar, instalar ni configurar una distribución completa de WildFly de forma manual.

Prerrequisitos:
Tener Java 17+.
Tener PostgreSQL corriendo en el puerto 5432.

Pasos para probar el sistema:

Base de Datos:
Crear una base de datos llamada `tactica01` en PostgreSQL.
Ejecutar el archivo `backup_tactica01.sql` (incluido en este ZIP) para generar las tablas y poblar los catálogos de Países y Fabricantes.
Si su usuario/contraseña de Postgres no es `postgres`/`admin`, actualice las credenciales en `src/main/resources/META-INF/persistence.xml`.

Ejecución:
Abrir una terminal en la raíz del proyecto.
Ejecutar el siguiente comando (en windows):

     ./mvnw clean package wildfly:dev

Tiempo esperado: La compilación y descarga del servidor embebido toma entre 1 y 3 minutos la primera vez. Las ejecuciones posteriores toman apenas unos segundos.

Acceso:
Una vez que la terminal indique que el servidor ha iniciado, ingresar en el navegador a: ("http://localhost:8080/tactica01/app/auth/login")

5. Próximos pasos (Faltante para el 100%)
Para la presentación final, se implementarán las siguientes funcionalidades que completarán el flujo del sistema:

Integraciones Avanzadas: Finalizar las relaciones en la ficha técnica (Armas Integradas, Plataformas Compatibles y Operadores por País).
Dashboard: Implementación de gráficos estadísticos en la pantalla de inicio de la tabla países para mostrar la distribución del ORBAT.
Filtros de Búsqueda: Incorporar un buscador funcional en el listado de medios para filtrar por categoría, país o fabricante.
Seguridad: Refinamiento del filtro de sesión (Filtro de Autenticación) para proteger todas las rutas privadas.