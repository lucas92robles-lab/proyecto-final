<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <!-- BARRA DE NAVEGACIÓN PRINCIPAL (Esto cumple la exigencia de "módulos navegables") -->
    <div class="navbar">
        <div>
            <strong>SITA //</strong>
            <a href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/app/medios/aereo">Módulo Aéreo</a>
            <a href="${pageContext.request.contextPath}/app/medios/radar">Módulo Radar</a>
            <a href="${pageContext.request.contextPath}/app/medios/ew">Módulo EW</a>
            <a href="${pageContext.request.contextPath}/app/medios/orbat">ORBAT</a>
        </div>
        <div>
            <!-- Mostramos el nombre del usuario logueado usando Expression Language -->
            <span>Operador: <strong>${usuarioSession.usuarioActual.username}</strong></span>
            <a href="${pageContext.request.contextPath}/app/auth/logout" style="background-color: #c0392b; margin-left: 1rem;">Salir</a>
        </div>
    </div>

    <div class="container">
        <div class="welcome-panel">
            <h2>Panel de Control Principal (Dashboard)</h2>
            <p>Bienvenido al Sistema de Inteligencia, Vigilancia y Reconocimiento Táctico.</p>
            <p>Seleccione un módulo en la barra superior para gestionar el inventario.</p>
        </div>
    </div>

</body>
</html>