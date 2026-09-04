<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="header.jsp" %>

<div class="tarjeta" style="margin-bottom: 2rem;">
    <h2 style="color: var(--color-primario); margin-bottom: 10px;">Panel de Control Principal (Dashboard)</h2>
    <p style="color: var(--texto-secundario); margin-bottom: 5px;">Bienvenido al sistema de catalogación de entidades aeroespaciales</p>
    <p style="color: var(--texto-secundario);">Seleccione un módulo en la barra lateral para gestionar el inventario.</p>
</div>


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

<%@ include file="footer.jsp" %>