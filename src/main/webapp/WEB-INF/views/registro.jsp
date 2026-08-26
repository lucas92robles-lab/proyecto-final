<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Ingreso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body class="login-body">

<div class="login-card">
    <h2>NUEVO USUARIO</h2>

    <!-- Evaluación de Expression Language (EL) y JSTL para mostrar el error si existe -->
    <c:if test="${not empty error}">
        <div class="error-msg">
            ${error}
        </div>
    </c:if>

    <!-- El formulario apunta a la ruta de tu controlador POST -->
    <form action="${pageContext.request.contextPath}/app/auth/registro" method="post">
        
        <!-- Token CSRF-->
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">

        <div class="form-group">
            <label for="username">USUARIO</label>
            <input type="text" id="username" name="username" required autocomplete="off" class="form-control" placeholder="Ej: admin">
        </div>

        <div class="form-group">
            <label for="password">CONTRASEÑA</label>
            <input type="password" id="password" name="password" required class="form-control" placeholder="••••••••">
        </div>

        <button type="submit" class="btn-primary">REGISTRAR NUEVO USUARIO</button>
    </form>

</div>
</div>

</body>
</html>