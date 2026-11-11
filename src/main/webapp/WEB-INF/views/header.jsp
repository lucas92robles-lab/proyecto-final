<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Medios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/image.png">
</head>
<body>

    <div class="app-contenedor">
        
        <!-- SIDEBAR (Menú Lateral) -->
        <aside class="sidebar">
            <div class="sidebar-header">
               SisCaM
            </div>
            
            <div class="sidebar-menu">
                <a href="${pageContext.request.contextPath}/app/dashboard" class="enlace-modulo">Inicio</a>
                
                <details class="menu-modulo">
                    <summary>Aeronaves</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/aereo/general">Ver Aeronaves</a>
                        <a href="${pageContext.request.contextPath}/app/medios/aereo">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>Radares</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/radar/general">Ver Radares</a>
                        <a href="${pageContext.request.contextPath}/app/medios/radar">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>Armamento</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/armamento/general">Ver Armamento</a>
                        <a href="${pageContext.request.contextPath}/app/medios/armamento">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>EW</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/ew/general">Ver Sistemas EW</a>
                        <a href="${pageContext.request.contextPath}/app/medios/ew">Categorías</a>
                    </div>
                </details>
                
                <a href="${pageContext.request.contextPath}/app/medios/listado" class="enlace-modulo">Totalidad de medios</a>
                
                <c:if test="${usuarioSession.usuarioActual.rol != 'LECTOR'}">
                <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="enlace-modulo" style="margin-top: 15px;">Agregar nuevo elemento</a>
                </c:if>
            </div>
        </aside>

        <!-- COLUMNA PRINCIPAL -->
        <div class="columna-principal">
            
        <header class="topbar">
            <div class="buscador-global">
                <form action="${pageContext.request.contextPath}/app/medios/buscar" method="GET" style="display: flex; width: 100%;">
                <input type="text" name="q" placeholder="Buscar matrícula, nombre o modelo..." required style="width: 100%;">
            </form>
            </div>
            <div class="topbar-usuario">
                <!-- Muestra el usuario -->
                <span>Operador: ${usuarioSession.usuarioActual.username}</span> 

                <!-- Botón visible solo para administradores -->
                <c:if test="${not empty usuarioSession.usuarioActual && usuarioSession.usuarioActual.rol == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/app/admin/panel" 
                    style="background: #1e3a8a; color: #fff; padding: 8px 14px; border-radius: 4px; text-decoration: none; font-size: 0.85rem; font-weight: bold; text-transform: uppercase; display: inline-flex; align-items: center; gap: 6px; border: 1px solid #3b82f6;">
                         Panel de Administración
                    </a>
                </c:if>

                <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">[ SALIR ]</a>
            </div>
        </header>

        <main class="contenido">