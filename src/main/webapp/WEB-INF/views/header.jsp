<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catálogo de Medios</title>
    <!-- Actualizado a tu archivo de estilos -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <div class="app-contenedor">
        
        <!-- SIDEBAR (Menú Lateral) -->
        <!-- SIDEBAR (Menú Lateral) -->
        <aside class="sidebar">
            <div class="sidebar-header">
               <!-- Sistema de catalogación<br>de medios aeroespaciales -->
                SisCaM
            </div>
            
            <div class="sidebar-menu">
                <!-- Ahora usa la clase compartida y no tiene estilos en línea -->
                <a href="${pageContext.request.contextPath}/app/dashboard" class="enlace-modulo">Inicio</a>
                
                <details class="menu-modulo">
                    <summary>Aeronaves</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/aereo">Ver Aeronaves</a>
                        <a href="${pageContext.request.contextPath}/app/medios/nuevo">Registrar Aeronave</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>Radares</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/radar">Ver Radares</a>
                    </div>
                </details>

                <!-- AGREGADO: Menú Armamento -->
                <details class="menu-modulo">
                    <summary>Armamento</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/armamento">Ver Armamento</a>
                    </div>
                </details>

                <details class="menu-modulo">
                    <summary>EW</summary>
                    <div class="submenu">
                        <a href="${pageContext.request.contextPath}/app/medios/ew">Ver Sistemas EW</a>
                    </div>
                </details>
                
                <!-- Ahora usa la clase enlace-modulo para verse idéntico a las categorías -->
                <a href="${pageContext.request.contextPath}/app/medios/listado" class="enlace-modulo">Totalidad de medios</a>
            </div>
        </aside>

        <!-- COLUMNA PRINCIPAL -->
        <div class="columna-principal">
            
            <header class="topbar">
                <div class="buscador-global">
                    <input type="text" placeholder="Buscar matrícula, nombre o modelo...">
                </div>
                <div class="topbar-usuario">
                    <!-- Acá inyectamos la variable exacta de tu código anterior -->
                    <span>Operador: ${usuarioSession.usuarioActual.username}</span> 
                    <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">[ SALIR ]</a>
                </div>
            </header>

            <main class="contenido">