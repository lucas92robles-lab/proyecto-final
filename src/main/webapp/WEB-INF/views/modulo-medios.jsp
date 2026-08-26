<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // ${tituloModulo}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <div class="navbar">
        <div>
            <strong>SITA //</strong>
            <a href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/app/medios/aereo">Módulo Aéreo</a>
            <a href="${pageContext.request.contextPath}/app/medios/radar">Módulo Radar</a>
            <a href="${pageContext.request.contextPath}/app/medios/ew">Módulo EW</a>
            <a href="${pageContext.request.contextPath}/app/medios/orbat">ORBAT</a>
            
            <!-- Renderizado Condicional: Estos botones solo los ve el ADMIN -->
            <c:if test="${usuarioSession.usuarioActual.rol == 'ADMIN'}">
                <span style="color: #ccc; margin: 0 10px;">|</span>
                <a href="${pageContext.request.contextPath}/app/categorias">Categorías</a>
                <a href="${pageContext.request.contextPath}/app/fabricantes">Fabricantes</a>
                <a href="${pageContext.request.contextPath}/app/paises">Países</a>
            </c:if>
        </div>
        <div>
            <span>Operador: <strong>${usuarioSession.usuarioActual.username}</strong></span>
            <a href="${pageContext.request.contextPath}/app/auth/logout" style="background-color: #c0392b; margin-left: 1rem;">Salir</a>
        </div>
    </div>

    <div class="container">
        <h2>${tituloModulo}</h2>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Iteramos la lista de DTOs que mandó el controlador -->
                <c:forEach var="medio" items="${medios}">
                    <tr>
                        <td>${medio.id}</td>
                        <td>${medio.nombre}</td>
                        <!-- <td>${medio.estadoOperativo}</td> -->
                        <td>
                            <a href="#">Ver Detalles</a>
                            <c:if test="${usuarioSession.usuarioActual.rol == 'ADMIN'}">
                                | <a href="#" style="color: red;">Eliminar</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>