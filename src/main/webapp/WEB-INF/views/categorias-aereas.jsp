<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>


<div class="header-categorias">
    <h2>Categorías Aéreas</h2>
</div>

<div class="carrusel-categorias">
    
    <!-- Categoría 1: CAZA -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/caza" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">CAZA</div>
        <img src="${pageContext.request.contextPath}/img/cat-caza.jpg" alt="Aeronaves de Caza">
    </a>

    <!-- Categoría 2: ATAQUE -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/ataque" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">ATAQUE</div>
        <img src="${pageContext.request.contextPath}/img/cat-ataque.jpg" alt="Aeronaves de Ataque">
    </a>

    <!-- Categoría 3: TRANSPORTE -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/transporte" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">TRANSPORTE</div>
        <img src="${pageContext.request.contextPath}/img/cat-transporte.jpg" alt="Aeronaves de Transporte">
    </a>

    <!-- Categoría 4: HELICÓPTERO -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/helicoptero" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">HELICÓPTERO</div>
        <img src="${pageContext.request.contextPath}/img/cat-helicoptero.jpg" alt="Helicópteros">
    </a>

    <!-- Categoría 5: UAV -->
    <a href="${pageContext.request.contextPath}/app/medios/aereo/uav" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">UAV</div>
        <img src="${pageContext.request.contextPath}/img/cat-uav.jpg" alt="UAVs">
    </a>

</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const carrusel = document.querySelector('.carrusel-categorias');
        if (carrusel) {
            carrusel.addEventListener('wheel', function(e) {
                // Si el usuario mueve la ruedita, prevenimos que la página baje
                e.preventDefault();
                // Y en su lugar, movemos el scroll horizontalmente
                carrusel.scrollLeft += e.deltaY;
            });
        }
    });
</script>

<%@ include file="footer.jsp" %>