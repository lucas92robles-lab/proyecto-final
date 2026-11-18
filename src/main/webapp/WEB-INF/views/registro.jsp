<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/image.png">
    <script src="${pageContext.request.contextPath}/js/scripts.js" defer></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
    <div class="pantalla-inicio">
    
        <div class="inicio-titulo">
            <h1>Sistema de catalogacion<br>de medios aeroespaciales</h1>
        </div>

        <div class="caja-login">
            <!-- Mensaje de Error -->
                <c:if test="${not empty error}">
                    <div class="alerta alerta-error"> ${error}</div>
                </c:if>

                <!-- Mensaje de Éxito -->
                <c:if test="${not empty mensaje}">
                    <div class="alerta alerta-exito"> ${mensaje}</div>
                </c:if>


            <form action="${pageContext.request.contextPath}/app/auth/registro" method="post" data-validar-registro>

                <!-- Token CSRF-->
                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">

                <div class="registro">
                    <label for="username" class="label-tactico">Usuario</label>
                    <input type="text" id="username" name="username" required autocomplete="off" class="input-tactico" placeholder="Ej: usuario123">
                </div>

                <div class="registro">
                    <label for="password" class="label-tactico">Contraseña</label>
                    <input type="password" id="password" name="password" required class="input-tactico" placeholder="••••••••">
                </div>

                <div class="registro">
                    <label for="email" class="label-tactico">Correo Electrónico</label>
                    <input type="email" id="email" name="email" required class="input-tactico" placeholder="Ej: usuario@correo.com">
                </div>

                <div class="registro">
                    <label for="edad" class="label-tactico">Edad</label>
                    <input type="number" id="edad" name="edad" required min="18" max="100" class="input-tactico" placeholder="Ej: 30">
                </div>

                <div class="registro">
                    <label for="telefono" class="label-tactico">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" autocomplete="off" class="input-tactico" placeholder="Ej: 3434123456">
                </div>

                <div class="registro">
                    <label for="direccion" class="label-tactico">Dirección</label>
                    <input type="text" id="direccion" name="direccion" autocomplete="off" class="input-tactico" placeholder="Ej: Calle Principal 123">
                </div>

                <div class="registro">
                    <label for="dependencia" class="label-tactico">Dependencia / Unidad</label>
                    <input type="text" id="dependencia" name="dependencia" autocomplete="off" class="input-tactico" placeholder="Ej: Base Aérea Paraná">
                </div>

                <button type="submit" class="btn-ingresar">Registrar nuevo usuario</button>

                <a href="${pageContext.request.contextPath}/app/auth/login" class="enlace-volver">← Volver al Login</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>