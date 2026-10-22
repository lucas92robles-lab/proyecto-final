<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Medios</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
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
                        <a href="${pageContext.request.contextPath}/app/medios/aereo/listado">Ver Aeronaves</a>
                        <a href="${pageContext.request.contextPath}/app/medios/aereo">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>Radares</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/radar/listado">Ver Radares</a>
                        <a href="${pageContext.request.contextPath}/app/medios/radar">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>Armamento</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/armamento/listado">Ver Armamento</a>
                        <a href="${pageContext.request.contextPath}/app/medios/armamento">Categorías</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>EW</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/ew/listado">Ver Sistemas EW</a>
                        <a href="${pageContext.request.contextPath}/app/medios/ew">Categorías</a>
                    </div>
                </details>
                
                <a href="${pageContext.request.contextPath}/app/medios/listado" class="enlace-modulo">Totalidad de medios</a>
                
                <!-- AGREGADO: Opción para nuevo elemento al final -->
                <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="enlace-modulo" style="margin-top: 15px;">Agregar nuevo elemento</a>
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
                    <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">[ SALIR ]</a>
                </div>
            </header>

            <main class="contenido">