<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%@ include file="header.jsp" %>

<div class="pantalla-ppal">
    <div class="admin-contenido">

        <!-- Cabecera del Panel -->
        <h2 class="admin-titulo">Panel de Control Administrador</h2>

        <!-- Mensaje de Error si lo hubiera -->
        <c:if test="${not empty error}">
            <div class="alerta alerta-error"> ${error}</div>
        </c:if>

        <h3 class="admin-subtitulo">Gestión de Usuarios y Roles</h3>

        <!-- Tabla de Listado de Usuarios (en celulares cada fila se muestra como tarjeta) -->
        <div class="tarjeta tarjeta-tabla">
            <table class="tabla-tactica tabla-cards">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Usuario</th>
                        <th>Email</th>
                        <th>Dependencia</th>
                        <th>Rol Actual</th>
                        <th class="col-centro">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${listaUsuarios}">
                        <tr>
                            <td data-label="ID">${u.id}</td>
                            <td data-label="Usuario">${u.username}</td>
                            <td data-label="Email">${u.email}</td>
                            <td data-label="Dependencia">${u.dependencia}</td>
                            <td data-label="Rol Actual">
                                <!-- Formulario para cambiar el rol de forma inmediata (data-autosubmit) -->
                                <form action="${pageContext.request.contextPath}/app/admin/actualizar-rol" method="post" class="form-inline">
                                    <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
                                    <input type="hidden" name="idUsuario" value="${u.id}">
                                    <select name="nuevoRol" class="select-rol" data-autosubmit>
                                        <option value="LECTOR" ${u.rol == 'LECTOR' ? 'selected' : ''}>LECTOR</option>
                                        <option value="USUARIO" ${u.rol == 'USUARIO' ? 'selected' : ''}>USUARIO</option>
                                        <option value="ADMIN" ${u.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                    </select>
                                </form>
                            </td>
                            <td class="col-centro" data-label="Acciones">
                                <a href="${pageContext.request.contextPath}/app/admin/eliminar-usuario?id=${u.id}"
                                   class="enlace-eliminar"
                                   data-confirm="¿Estás seguro de eliminar al usuario <c:out value='${u.username}'/>?">🗑 Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>

<%@ include file="footer.jsp" %>
