<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Ingreso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

<div class="pantalla-inicio">
    <!-- Evaluación de Expression Language (EL) y JSTL para mostrar el error si existe -->
    <c:if test="${not empty error}">
        <div class="error-msg">
            ${error}
        </div>
    </c:if>

    <div class="inicio-titulo">
            <h1>Sistema de catalogacion<br>de medios aeroespaciales</h1>
    </div>

    <!-- El formulario apunta a la ruta de tu controlador POST -->
    <div class="caja-login">
        <form action="${pageContext.request.contextPath}/app/auth/registro" method="post">
        
        <!-- Token CSRF-->
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">

        <div class="login-links">
            <label for="username" class="label-tactico">Usuario</label>
            <input type="text" id="username" name="username" required autocomplete="off" class="input-tactico" placeholder="Ej: admin">
        </div>

        <div class="login-links">
            <label for="password" class="label-tactico">Contraseña</label>
            <input type="password" id="password" name="password" required class="input-tactico" placeholder="••••••••">
        </div>

        <button type="submit" class="btn-ingresar">Registrar nuevo usuario</button>
        </form>
    </div>
</div>
</div>

</body>
</html>