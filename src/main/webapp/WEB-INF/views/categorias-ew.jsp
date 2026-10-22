<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<style>
    .contenido { padding: 0; }
    
    /* Margen para el título de la sección */
    .header-categorias {
        padding: 30px 40px 10px 40px;
    }
    
    .header-categorias h2 {
        color: var(--color-primario);
        margin: 0;
        text-transform: uppercase;
    }
</style>

<div class="header-categorias">
    <h2>Sistemas de Guerra Electrónica (EW)</h2>
</div>

<div class="carrusel-categorias">
    
    <!-- Categoría 1: ATAQUE ELECTRÓNICO -->
    <a href="${pageContext.request.contextPath}/app/medios/ew/ataque" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">ATAQUE<br>ELECTRONICO</div>
        <img src="${pageContext.request.contextPath}/img/cat-ew-ataque.jpg" alt="Sistemas de Ataque Electrónico">
    </a>

    <!-- Categoría 2: DEFENSA ELECTRÓNICA -->
    <a href="${pageContext.request.contextPath}/app/medios/ew/defensa" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">DEFENSA<br>ELECTRONICA</div>
        <img src="${pageContext.request.contextPath}/img/cat-ew-defensa.jpg" alt="Sistemas de Defensa Electrónica">
    </a>

    <!-- Categoría 3: ELINT -->
    <a href="${pageContext.request.contextPath}/app/medios/ew/elint" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">ELINT</div>
        <img src="${pageContext.request.contextPath}/img/cat-ew-elint.jpg" alt="Inteligencia Electrónica (ELINT)">
    </a>

</div>

<%@ include file="footer.jsp" %>