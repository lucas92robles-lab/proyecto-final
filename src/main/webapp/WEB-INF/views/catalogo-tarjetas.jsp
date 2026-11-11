<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

<div class="main-content">
    <div class="catalog-header">
        <h2 class="catalog-title">${tituloSeccion}</h2>
        <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="btn-registrar">
            + Registrar Medio
        </a>
    </div>

    <!-- Validar si hay elementos -->
    <c:if test="${empty medios}">
        <div class="empty-message">
            No se encontraron registros en la base de datos.
        </div>
    </c:if>

    <!-- GRILLA DE TARJETAS (CARDS) -->
    <div class="cards-grid">
        <c:forEach var="m" items="${medios}">
            <div class="tactical-card">
                
                <!-- Imagen Principal -->
                <div class="card-img-container">
                    <c:choose>
                        <c:when test="${not empty m.imagenUrl}">
                            <img src="${pageContext.request.contextPath}${m.imagenUrl}" alt="${m.nombre}">
                        </c:when>
                        <c:otherwise>
                            <span class="card-placeholder">Sin Imagen</span>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- Contenido de la Tarjeta -->
                <div class="card-content">
                    <div>
                        <span class="card-category">${m.categoria}</span>
                        <h3 class="card-title">${m.nombre}</h3>
                        <p class="card-model">Modelo: ${empty m.modelo ? 'N/D' : m.modelo}</p>
                    </div>

                    <!-- Botón de Detalle -->
                    <div>
                        <a href="${pageContext.request.contextPath}/app/medios/detalle-medio/${m.id}" class="card-btn">
                            Ver Detalle
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="footer.jsp" %>