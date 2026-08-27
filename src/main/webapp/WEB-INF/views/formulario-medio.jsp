<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Formulario</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body class="login-body"> <!-- Reusamos tu clase centrada -->

    <div class="login-card" style="max-width: 500px;">
        <h2>${tituloFormulario}</h2>
        
        <form action="${pageContext.request.contextPath}/app/medios/guardar" method="POST">
            
            <!-- ID Oculto (Si está vacío, crea. Si tiene número, edita) -->
            <input type="hidden" name="id" value="${medio.id}">
            
            <div class="form-group">
                <label for="nombre">Nombre / Designación:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" value="${medio.nombre}" required>
            </div>
            
            <button type="submit" class="btn-primary" style="margin-top: 20px;">GUARDAR CAMBIOS</button>
            <a href="javascript:history.back()" style="display: block; text-align: center; margin-top: 15px; color: #666; text-decoration: none;">Cancelar y volver</a>
        </form>
    </div>
      <!-- nota agregar un desplegable al formulario de agregar o editar para seleccionar la categoría -->

</body>
</html>