<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>SITA // Recuperar Contraseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body class="login-body">
    <div class="recuperar-card">
        <h2>RECUPERACIÓN DE ACCESO</h2>
        <p>Por protocolos de seguridad, el reseteo de credenciales debe solicitarse al administrador del sistema.</p>
        <p>Por favor, envíe un correo a <strong>soporte-isr@tactica01.mil.ar</strong> indicando su nombre de usuario.</p>
        <a href="${pageContext.request.contextPath}/app/auth/login" class="btn-primary">VOLVER AL LOGIN</a>
    </div>
</body>
</html>