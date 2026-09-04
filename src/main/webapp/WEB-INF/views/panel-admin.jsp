<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Panel de Administración</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <div class="navbar">
        <div>
            <strong> PANEL ADMIN //</strong>
            <a href="${pageContext.request.contextPath}/app/dashboard">Volver al Dashboard</a>
            <span style="color: #ccc; margin: 0 10px;">|</span>
            <a href="${pageContext.request.contextPath}/app/categorias">Categorías</a>
            <a href="${pageContext.request.contextPath}/app/fabricantes">Fabricantes</a>
            <a href="${pageContext.request.contextPath}/app/paises">Países</a>
        </div>
        <div>
            <span>Admin: <strong>${usuarioSession.usuarioActual.username}</strong></span>
            <a href="${pageContext.request.contextPath}/app/auth/logout" style="background-color: #c0392b; margin-left: 1rem;">Salir</a>
        </div>
    </div>

    <div class="container">
        <h2>${tituloAdmin}</h2>
        
        <button style="padding: 10px; background-color: #2c3e50; color: white; border: none; cursor: pointer; margin-bottom: 15px;">
            + Nuevo Registro
        </button>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descripción / Nombre</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listaItems}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.descripcion}</td>
                        <td>
                            <a href="#">Editar</a> | 
                            <a href="#" style="color: red;">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>