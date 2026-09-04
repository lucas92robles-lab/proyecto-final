<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="header.jsp" %>

<style>
    .contenido { padding: 0; }
</style>

<div class="grilla-categorias grilla-4-columnas">
    
    <!-- Categoría 1: ATAQUE ELECTRÓNICO -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=ataque-electronico" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-ew-ataque.jpg" alt="Sistemas de Ataque Electrónico">
        <div class="tarjeta-cat-titulo">ATAQUE<br>ELECTRONICO</div>
    </a>

    <!-- Categoría 2: DEFENSA ELECTRÓNICA -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=defensa-electronica" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-ew-defensa.jpg" alt="Sistemas de Defensa Electrónica">
        <div class="tarjeta-cat-titulo">DEFENSA<br>ELECTRONICA</div>
    </a>

    <!-- Categoría 3: ELINT -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=elint" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-ew-elint.jpg" alt="Inteligencia Electrónica (ELINT)">
        <div class="tarjeta-cat-titulo">ELINT</div>
    </a>

    <!-- Categoría 4: AGREGAR NUEVO -->
    <a href="${pageContext.request.contextPath}/app/medios/nuevo-ew" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-ew-nuevo.jpg" alt="Agregar Nuevo Sistema EW">
        <div class="tarjeta-cat-titulo">AGREGAR<br>NUEVO</div>
    </a>

</div>

<%@ include file="footer.jsp" %>