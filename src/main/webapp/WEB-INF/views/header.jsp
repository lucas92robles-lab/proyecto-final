<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Catálogo de Medios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/image.png">
    <script src="${pageContext.request.contextPath}/js/scripts.js" defer></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

    <div class="app-contenedor">
        
        <!-- SIDEBAR (Menú Lateral) -->
        <aside class="sidebar" id="menu-lateral">
            <div class="sidebar-header">
               SiCaM
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
                <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="enlace-modulo enlace-nuevo">Agregar nuevo elemento</a>
                </c:if>
            </div>
        </aside>

        <!-- Fondo oscuro que cierra el menú en pantallas chicas -->
        <div class="sidebar-overlay"></div>

        <!-- COLUMNA PRINCIPAL -->
        <div class="columna-principal">
            
        <header class="topbar">
            
            <button type="button" class="btn-menu-toggle" data-toggle-sidebar
            aria-controls="menu-lateral" aria-expanded="false"
            aria-label="Mostrar menú" title="Mostrar menú">&gt;
            </button>

            <div class="buscador-global">
                <form action="${pageContext.request.contextPath}/app/medios/buscar" method="GET" class="buscador-form">
                    <input type="text" name="q" placeholder="Buscar matrícula, nombre o modelo..." required>
                </form>
            </div>
            <div class="topbar-usuario">
                <!-- Muestra el usuario -->
                <span>Operador: ${usuarioSession.usuarioActual.username}</span> 

                <!-- Botón visible solo para administradores -->
                <c:if test="${not empty usuarioSession.usuarioActual && usuarioSession.usuarioActual.rol == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/app/admin/panel" class="btn-admin">
                        <span class="txt-largo">Panel de Administración</span>
                        <span class="txt-corto">Admin</span>
                    </a>
                </c:if>

                <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">[ SALIR ]</a>
            </div>
        </header>

        <main class="contenido">