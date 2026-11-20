<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catalogo de medios aeroespaciales</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/image.png">
    <script src="${pageContext.request.contextPath}/js/scripts.js" defer></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <div class="pantalla-inicio">
        <!-- Mitad Izquierda: Título -->
        <div class="inicio-titulo">
            <h1>Sistema de catalogación<br>de medios aeroespaciales</h1>
    </div>

        <!-- Mitad Derecha: Formulario -->
        <div class="caja-login">
            <form action="${pageContext.request.contextPath}/app/auth/login" method="POST">
                
                <input type="text" name="username" placeholder="usuario" class="input-tactico" required>
                <input type="password" name="password" placeholder="contraseña" class="input-tactico" required>
                
                <button type="submit" class="btn-ingresar">Ingresar</button>
            
            </form>
            <div class="login-links">
                <a href="${pageContext.request.contextPath}/app/auth/registro">Nuevo usuario</a>
                <a href="${pageContext.request.contextPath}/app/auth/recuperar">Recuperar usuario</a>
            </div>
        </div>

    </div>  
    

                
                
        
</body>
</html>