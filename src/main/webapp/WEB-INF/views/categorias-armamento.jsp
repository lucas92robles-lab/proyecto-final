<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Cargamos el layout mediante inclusión estática -->
<%@ include file="header.jsp" %>

<!-- Quitamos el padding para que la grilla ocupe toda la pantalla -->
<style>
    .contenido { padding: 0; }
</style>

<!-- Grilla asimétrica para Armamento -->
<div class="grilla-categorias grilla-armamento">
    
    <!-- Categoría 1 (Fila 1, Columna 1): AIRE-AIRE -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=aire-aire" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-arm-aire.jpg" alt="Misiles Aire-Aire">
        <div class="tarjeta-cat-titulo">AIRE-AIRE</div>
    </a>

    <!-- Categoría 2 (Fila 1, Columna 2): AIRE-SUPERFICIE -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=aire-superficie" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-arm-superficie.jpg" alt="Misiles Aire-Superficie">
        <div class="tarjeta-cat-titulo">AIRE-SUPERFICIE</div>
    </a>

    <!-- Categoría 3 (Columna 3, ocupa Fila 1 y 2): AGREGAR NUEVO -->
    <a href="${pageContext.request.contextPath}/app/medios/nuevo-armamento" class="tarjeta-cat span-2-filas">
        <img src="${pageContext.request.contextPath}/img/cat-arm-nuevo.jpg" alt="Agregar Nuevo Armamento">
        <div class="tarjeta-cat-titulo">AGREGAR<br>NUEVO</div>
    </a>

    <!-- Categoría 4 (Fila 2, Columna 1): LANZABLE -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=lanzable" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-arm-lanzable.jpg" alt="Armamento Lanzable (Bombas)">
        <div class="tarjeta-cat-titulo">LANZABLE</div>
    </a>

    <!-- Categoría 5 (Fila 2, Columna 2): COHETES-CAÑONES -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=cohetes-canones" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-arm-canones.jpg" alt="Cohetes y Cañones">
        <div class="tarjeta-cat-titulo">COHETES-CAÑONES</div>
    </a>

</div>

<!-- Cerramos el layout mediante inclusión estática -->
<%@ include file="footer.jsp" %>