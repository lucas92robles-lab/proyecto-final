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
        
     <!-- Mensaje Flash de Éxito al Eliminar -->
        <c:if test="${param.exito == 'eliminado'}">
            <div style="background-color: #d4edda; color: #155724; padding: 12px; border: 1px solid #c3e6cb; border-radius: 4px; margin-bottom: 15px; font-weight: bold;">
                El activo táctico fue dado de baja correctamente del inventario.
            </div>
        </c:if>   
        <table>
            <div style="margin-bottom: 15px;">
                <a href="${pageContext.request.contextPath}/app/medios/nuevo" style="padding: 10px 15px; background-color: #27ae60; color: white; text-decoration: none; border-radius: 4px; font-weight: bold;">+ NUEVO ACTIVO</a>
            </div>
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
                            <a href="${pageContext.request.contextPath}/app/medios/detalle/${medio.id}">Ver Detalles</a>
                            <a href="${pageContext.request.contextPath}/app/medios/editar/${medio.id}" style="color: #f39c12;">Editar</a>
                            <c:if test="${usuarioSession.usuarioActual.rol == 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/app/medios/eliminar/${medio.id}" style="color: #e74c3c; text-decoration: none; font-weight: bold;"  onclick="return confirm('ATENCIÓN: ¿Estás seguro de que deseás dar de baja este activo táctico? Esta acción no se puede deshacer.');">Eliminar</a>
                            </c:if>
                        </td>
                    </tr>      
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>