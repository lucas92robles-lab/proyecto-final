<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%@ include file="header.jsp" %>

<style>
    .pantalla-ppal {
        height: 100vh;
        overflow: hidden;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        padding-top: 30px;
        background-attachment: fixed;
        
    }

    /* .cabecera{
    style="width: 90%; max-width: 1100px; display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;
    } */
</style>

<div class="pantalla-ppal">

    <!-- Cabecera del Panel -->
    <div class="cabecera">
        <h2 style="color: var(--color-primario); text-transform: uppercase; margin: 0; font-size: 1.4rem;">
            Panel de Control Administrador
        </h2>
        
    <!-- Mensaje de Error si lo hubiera -->
    <c:if test="${not empty error}">
        <div style="width: 90%; max-width: 1100px; background: rgba(220, 38, 38, 0.85); color: white; padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; font-size: 0.9rem;">
            ⚠️ ${error}
        </div>
    </c:if>

    <!-- Contenedor Principal del Panel con Scroll Interno -->
    <div>
        
        <h3 style="color: #191a1b; border-bottom: 1px solid rgba(255,255,255,0.1); padding-bottom: 10px; margin-top: 0;">
            Gestión de Usuarios y Roles
        </h3> 

        <!-- Tabla de Listado de Usuarios -->
        <table class="tabla-tactica">
            <thead>
                <tr style="background: rgba(10, 25, 40, 0.95); text-align: left; border-bottom: 2px solid var(--color-primario);">
                    <th style="padding: 30px;">ID</th>
                    <th style="padding: 30px;">Usuario</th>
                    <th style="padding: 30px;">Email</th>
                    <th style="padding: 30px;">Dependencia</th>
                    <th style="padding: 30px;">Rol Actual</th>
                    <th style="padding: 30px; text-align: center;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${listaUsuarios}">
                    <tr style="border-bottom: 1px solid rgba(255,255,255,0.05);">
                        <td style="padding: 30px;">${u.id}</td>
                        <td style="padding: 30px;">${u.username}</td>
                        <td style="padding: 30px;">${u.email}</td>
                        <td style="padding: 30px;">${u.dependencia}</td>
                        <td style="padding: 30px;">
                            <!-- Formulario para cambiar el rol de forma inmediata (onchange) -->
                            <form action="${pageContext.request.contextPath}/app/admin/actualizar-rol" method="post" style="display:inline;">
                                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
                                <input type="hidden" name="idUsuario" value="${u.id}">
                                <select name="nuevoRol" onchange="this.form.submit()" style="background: #0f172a; color: #fff; border: 1px solid #334155; padding: 4px; border-radius: 4px; cursor: pointer;">
                                    <option value="LECTOR" ${u.rol == 'LECTOR' ? 'selected' : ''}>LECTOR</option>
                                    <option value="USUARIO" ${u.rol == 'USUARIO' ? 'selected' : ''}>USUARIO</option>
                                    <option value="ADMIN" ${u.rol == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
                                </select>
                            </form>
                        </td>
                        <td style="padding: 10px; text-align: center;">
                            <a href="${pageContext.request.contextPath}/app/admin/eliminar-usuario?id=${u.id}" 
                               onclick="return confirm('¿Estás seguro de eliminar al usuario ${u.username}?')" 
                               style="color: #ef4444; text-decoration: none; font-weight: bold;">🗑 Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>

<%@ include file="footer.jsp" %>