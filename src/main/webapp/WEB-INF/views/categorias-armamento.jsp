<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>


<div class="header-categorias">
    <h2>Categorías de Armamento</h2>
</div>

<div class="carrusel-categorias">
    
    <!-- Categoría 1: AIRE-AIRE -->
    <a href="${pageContext.request.contextPath}/app/medios/armamento/aire-aire" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">AIRE-AIRE</div>
        <img src="${pageContext.request.contextPath}/img/cat-arm-aire.jpg" alt="Misiles Aire-Aire">
    </a>

    <!-- Categoría 2: AIRE-SUPERFICIE -->
    <a href="${pageContext.request.contextPath}/app/medios/armamento/aire-superficie" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">AIRE-SUPERFICIE</div>
        <img src="${pageContext.request.contextPath}/img/cat-arm-superficie.jpg" alt="Misiles Aire-Superficie">
    </a>

    <!-- Categoría 3: LANZABLE -->
    <a href="${pageContext.request.contextPath}/app/medios/armamento/lanzable" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">LANZABLE</div>
        <img src="${pageContext.request.contextPath}/img/cat-arm-lanzable.jpg" alt="Armamento Lanzable (Bombas)">
    </a>

    <!-- Categoría 4: COHETES-CAÑONES -->
    <a href="${pageContext.request.contextPath}/app/medios/armamento/cohetes-canones" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">COHETES<br>CAÑONES</div>
        <img src="${pageContext.request.contextPath}/img/cat-arm-canones.jpg" alt="Cohetes y Cañones">
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