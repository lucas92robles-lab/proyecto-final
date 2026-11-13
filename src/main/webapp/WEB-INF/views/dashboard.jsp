<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="header.jsp" %>

<div class="dashboard-container">
    
    <h2 class="catalog-title">Panel de Control General</h2>

    <!-- Tarjetas de Métricas Principales (KPIs) -->
    <div class="dashboard-grid">
        
        <!-- Totalidad de Medios -->
        <div class="card-kpi">
            <span class="kpi-titulo">Total de Medios</span>
            <span class="kpi-valor">${totalMedios}</span>
        </div>

        <!-- Totalidad de Aeronaves -->
        <div class="card-kpi">
            <span class="kpi-titulo">Aeronaves</span>
            <span class="kpi-valor">${totalAeronaves}</span>
        </div>

        <!-- Totalidad de Radares -->
        <div class="card-kpi">
            <span class="kpi-titulo">Sistemas Radar</span>
            <span class="kpi-valor">${totalRadares}</span>
        </div>

        <!-- Totalidad de Guerra Electrónica (EW) -->
        <div class="card-kpi">
            <span class="kpi-titulo">Sistemas EW</span>
            <span class="kpi-valor">${totalEw}</span>
        </div>

        <!-- Totalidad de Armamento -->
        <div class="card-kpi">
            <span class="kpi-titulo">Armamento</span>
            <span class="kpi-valor">${totalArmamento}</span>
        </div>

    </div>

    <!-- Sección de Actividad y Último Registro -->
    <div class="dashboard-actividad" style="margin-top: 30px;">
        <div class="bloque-specs-dashboard">
            <h3>Último Activo Táctico Registrado</h3>
            <p><strong>Nombre / Diseño:</strong> <span>${ultimoMedio.nombre} (${ultimoMedio.modelo})</span></p>
            <p><strong>Categoría:</strong> <span>${ultimoMedio.categoria.nombre}</span></p>
            <p><strong>Fabricante:</strong> <span>${ultimoMedio.fabricante != null ? ultimoMedio.fabricante.nombre : '-'}</span></p>
            <p><strong>Acción:</strong> <span><a href="${pageContext.request.contextPath}/app/medios/detalle?id=${ultimoMedio.id}">Ver ficha técnica completa</a></span></p>
        </div>
    </div>

</div>

<%@ include file="footer.jsp" %>