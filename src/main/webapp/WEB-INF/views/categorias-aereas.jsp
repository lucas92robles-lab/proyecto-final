<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 1. Cargamos el menú lateral y barra superior -->
<%@ include file="header.jsp" %>

<!-- 2. Quitamos el padding del main temporalmente para que la grilla toque los bordes como en tu Figma -->
<style>
    .contenido { padding: 0; } 
</style>

<div class="grilla-categorias">
    
    <!-- Categoría 1: CAZA -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/caza" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-caza.jpg" alt="Aeronaves de Caza">
        <div class="tarjeta-cat-titulo">CAZA</div>
    </a>

    <!-- Categoría 2: ATAQUE -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/ataque" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-ataque.jpg" alt="Aeronaves de Ataque">
        <div class="tarjeta-cat-titulo">ATAQUE</div>
    </a>

    <!-- Categoría 3: TRANSPORTE -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/transporte" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-transporte.jpg" alt="Aeronaves de Transporte">
        <div class="tarjeta-cat-titulo">TRANSPORTE</div>
    </a>

    <!-- Categoría 4: HELICÓPTERO -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/helicoptero" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-helicoptero.jpg" alt="Helicópteros">
        <div class="tarjeta-cat-titulo">HELICÓPTERO</div>
    </a>

    <!-- Categoría 5: UAV -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/uav" class="tarjeta-cat">
        <img src="${pageContext.request.contextPath}/img/cat-uav.jpg" alt="UAVs">
        <div class="tarjeta-cat-titulo">UAV</div>
    </a>

    <!-- Categoría 6: AGREGAR NUEVO -->
    <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="tarjeta-cat">
        <!-- Podes usar un gradiente o una foto de fábrica como tenés en Figma -->
        <img src="${pageContext.request.contextPath}/img/cat-fabrica.jpg" alt="Agregar Nuevo Medio">
        <div class="tarjeta-cat-titulo">AGREGAR NUEVO</div>
    </a>

</div>

<!-- 3. Cerramos la estructura -->
<%@ include file="footer.jsp" %>