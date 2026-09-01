<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Catalogo de medios aeroespaciales</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>

<body>

    <div class="pantalla-inicio">
        <!-- Mitad Izquierda: Título -->
        <div class="inicio-titulo">
            <h1>Sistema de catalogacion<br>de medios aeroespaciales</h1>
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

/**
 <form action="${pageContext.request.contextPath}/app/auth/login" method="POST">
                    <div class="form-group">
                        <label for="username">USUARIO</label>
                        <input type="text" id="username" name="username" class="form-control" placeholder="Ej: admin" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">CONTRASEÑA</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    
                    <button type="submit" class="btn-primary">INGRESAR</button>
                </form>

                <div style="text-align: center; margin-top: 15px; font-size: 0.85rem;">
                    <a href="${pageContext.request.contextPath}/app/auth/registro" style="color: #333;">Crear cuenta nueva</a><br><br>
                    <a href="${pageContext.request.contextPath}/app/auth/recuperar" style="color: #666;">¿Olvidaste tu contraseña?</a>
                </div>
            </div>

**/

       

                
                
        
</body>
</html>