<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 1. Cargamos el layout -->
<%@ include file="header.jsp" %>

<!-- 2. Quitamos el padding para que la grilla ocupe toda la pantalla -->
<style>
    .contenido { padding: 0; }
</style>

<!-- 3. Grilla de 4 columnas para los Radares -->
<div class="grilla-categorias grilla-4-columnas">
    
    <!-- Categoría 1: ALERTA TEMPRANA -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=alerta-temprana" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-radar-alerta.jpg" alt="Radares de Alerta Temprana">
        <div class="tarjeta-cat-titulo">ALERTA<br>TEMPRANA</div>
    </a>

    <!-- Categoría 2: EMBARCADO -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=embarcado" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-radar-embarcado.jpg" alt="Radares Embarcados">
        <div class="tarjeta-cat-titulo">EMBARCADO</div>
    </a>

    <!-- Categoría 3: CONTROL DE TIRO -->
    <a href="${pageContext.request.contextPath}/app/medios/orbat?categoria=control-tiro" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-radar-tiro.jpg" alt="Radares de Control de Tiro">
        <div class="tarjeta-cat-titulo">CONTROL DE<br>TIRO</div>
    </a>

    <!-- Categoría 4: AGREGAR NUEVO -->
    <a href="${pageContext.request.contextPath}/app/medios/nuevo-radar" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-radar-nuevo.jpg" alt="Agregar Nuevo Radar">
        <!-- Texto resaltado con el color secundario como en las aeronaves -->
        <div class="tarjeta-cat-titulo">AGREGAR<br>NUEVO</div>
    </a>

</div>

<!-- 4. Cerramos el layout -->
<%@ include file="footer.jsp" %>