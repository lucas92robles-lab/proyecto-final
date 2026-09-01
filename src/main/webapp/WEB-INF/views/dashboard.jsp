<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 1. Cargamos toda la estructura (Menú y Topbar) -->
<%@ include file="header.jsp" %>

<!-- 2. Tu Panel de Bienvenida adaptado al nuevo CSS -->
<div class="tarjeta" style="margin-bottom: 2rem;">
    <h2 style="color: var(--color-primario); margin-bottom: 10px;">Panel de Control Principal (Dashboard)</h2>
    <p style="color: var(--texto-secundario); margin-bottom: 5px;">Bienvenido al sistema de catalogación de entidades aeroespaciales</p>
    <p style="color: var(--texto-secundario);">Seleccione un módulo en la barra lateral para gestionar el inventario.</p>
</div>

<!-- 3. Los KPIs que propusiste en tu diseño de Figma -->
<div class="kpi-grid">
    <div class="tarjeta-kpi">
        <h3>Total Aeronaves</h3>
        <div class="numero">0</div> <!-- Más adelante lo conectamos a la BD -->
    </div>
    <div class="tarjeta-kpi">
        <h3>Sistemas de Radar</h3>
        <div class="numero">0</div>
    </div>
    <div class="tarjeta-kpi">
        <h3>Última Actualización</h3>
        <div class="numero" style="font-size: 1.2rem; margin-top: 15px;">Sistema en línea</div>
    </div>
</div>

<!-- 4. Cerramos la estructura -->
<%@ include file="footer.jsp" %>