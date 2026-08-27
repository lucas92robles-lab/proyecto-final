<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Detalle del Medio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <div class="navbar">
        <div>
            <strong>SISTEMA TÁCTICO // ISR</strong>
            <a href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/app/medios/orbat">ORBAT</a>
        </div>
        <div>
            <span>OP: ${usuarioSession.usuarioActual.username}</span>
            <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">Cerrar Sesión</a>
        </div>
    </div>

    <div class="container">
        <div class="panel-base">
            <h2 style="border-bottom: 2px solid #2c3e50; padding-bottom: 10px;">DETALLE DE ACTIVO: ${medio.nombre}</h2>
            
            <p><strong>ID de Sistema:</strong> ${medio.id}</p>
            <p><strong>Nombre/Designación:</strong> ${medio.nombre}</p>
            <!-- <p><strong>Estado Operativo:</strong> ${medio.estadoOperativo}</p> -->
            
            <h3 style="margin-top: 30px; color: #666;">Especificaciones Técnicas</h3>
            <p style="color: #999;"><em>(No hay especificaciones cargadas en la base de datos para este medio).</em></p>
            
            <div style="margin-top: 30px;">
                <a href="javascript:history.back()" style="padding: 10px 20px; background-color: #34495e; color: white; text-decoration: none; border-radius: 4px;">Volver al Listado</a>
            </div>
        </div>
    </div>

</body>
</html>