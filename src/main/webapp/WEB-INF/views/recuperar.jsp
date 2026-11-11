<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Recuperar Contraseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/image.png">
</head>

<body class="pantalla-inicio">
    <div class="caja-login caja-recuperacion">
        <h2>RECUPERACIÓN DE ACCESO</h2>
        <p>Ingresá tu correo electrónico registrado y te enviaremos las instrucciones para restablecer tu acceso.</p>
        <form action=""${pageContext.request.contextPath}/app/auth/recuperar" method="post" accept-charset="UTF-8">
            <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
            
            <label for="email" class="label-tactico">Correo Electrónico</label>
            <input type="email" id="email" name="email" required autocomplete="off" class="input-tactico" placeholder="usuario@correo.com">
            <button type="submit" class="btn-ingresar">Enviar</button>
        </form>
        
        <a href="${pageContext.request.contextPath}/app/auth/login" class="btn-ingresar">Volver al login</a>
    </div>

    <!-- NOTA : Este formulario actualmente no implementa la funcionalidad real de recuperación de contraseña.
      Para una implementación completa, se debería agregar la lógica del lado del servidor para manejar la solicitud de recuperación, enviar un correo electrónico con un enlace seguro para restablecer la contraseña y validar el token recibido. 
      falta agregar la logica de recuperaciooon
      
      ver la validacion dentro del controlador!!!-->
</body>
</html>