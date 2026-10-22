<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>


<div class="header-categorias">
    <h2>Categorías de Radares</h2>
</div>

<div class="carrusel-categorias">
    
    <!-- Categoría 1: ALERTA TEMPRANA -->
    <a href="${pageContext.request.contextPath}/app/medios/radar/alerta-temprana" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">ALERTA<br>TEMPRANA</div>
        <img src="${pageContext.request.contextPath}/img/cat-radar-alerta.jpg" alt="Radares de Alerta Temprana">
    </a>

    <!-- Categoría 2: EMBARCADO -->
    <a href="${pageContext.request.contextPath}/app/medios/radar/embarcado" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">EMBARCADO</div>
        <img src="${pageContext.request.contextPath}/img/cat-radar-embarcado.jpg" alt="Radares Embarcados">
    </a>

    <!-- Categoría 3: CONTROL DE TIRO -->
    <a href="${pageContext.request.contextPath}/app/medios/radar/control-tiro" class="tarjeta-deslizable">
        <div class="tarjeta-deslizable-titulo">CONTROL DE<br>TIRO</div>
        <img src="${pageContext.request.contextPath}/img/cat-radar-tiro.jpg" alt="Radares de Control de Tiro">
    </a>

</div>

<%@ include file="footer.jsp" %>