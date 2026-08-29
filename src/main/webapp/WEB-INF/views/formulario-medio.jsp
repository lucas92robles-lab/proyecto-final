<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>${empty medio.id ? 'Cargar Nuevo' : 'Editar'} Medio - Táctica01</title>
    
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; color: #333; }
        .form-group { margin-bottom: 15px; }
        .bloque-tipo { display: none; margin-top: 20px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; background-color: #f4f6f8; }
        .categorias-container { display: flex; gap: 10px; flex-wrap: wrap; margin-bottom: 20px; }
        .btn-categoria input[type="radio"] { display: none; }
        .btn-categoria span { display: inline-block; padding: 10px 18px; background-color: #e0e0e0; border: 1px solid #bbb; border-radius: 20px; cursor: pointer; transition: all 0.2s ease; }
        .btn-categoria span:hover { background-color: #d0d0d0; }
        .btn-categoria input[type="radio"]:checked + span { background-color: #0056b3; color: white; border-color: #004494; font-weight: bold; }
        .inputs-grid { display: grid; grid-template-columns: 180px 1fr; gap: 10px; align-items: center; }
        .inputs-grid input, .inputs-grid select { padding: 5px; width: 100%; max-width: 300px; box-sizing: border-box; }
    </style>
    
    <script>
        // Le pasamos un parámetro para saber si estamos cambiando a mano o si es la carga inicial de la página
        function cambiarTipoMedio(esCargaInicial) {
            document.getElementById('bloqueAereo').style.display = 'none';
            document.getElementById('bloqueRadar').style.display = 'none';
            document.getElementById('bloqueEw').style.display = 'none';
            document.getElementById('bloqueArmamento').style.display = 'none';
            
            // Si el usuario cambia el desplegable a mano, limpiamos los botones pre-seleccionados
            if (!esCargaInicial) {
                var radios = document.querySelectorAll('input[name="categoriaId"]');
                radios.forEach(function(radio) { radio.checked = false; });
            }

            var seleccion = document.getElementById('tipoSelect').value;
            if(seleccion !== "") {
                document.getElementById('bloque' + seleccion).style.display = 'block';
            }
        }

        // Auto-ejecutar al cargar la página (útil para el modo Editar)
        window.onload = function() {
            if (document.getElementById('tipoSelect').value !== "") {
                cambiarTipoMedio(true);
            }
        };
    </script>
</head>
<body>

    <!-- El título cambia dinámicamente si hay o no un ID -->
    <h2>${empty medio.id ? 'Alta de Nuevo' : 'Editar'} Activo Táctico</h2>

    <form action="${pageContext.request.contextPath}/app/medios/guardar" method="post">
       
        <!-- TOQUEN CRSF -->
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
        
        <!-- CAMPO OCULTO VITAL PARA EDITAR: Si existe un ID, se envía para actualizar; si no, va vacío y crea uno nuevo -->
        <input type="hidden" name="id" value="${medio.id}">
        
        <!-- DATOS BASE -->
        <div class="form-group">
            <label><strong>Nombre del Medio:</strong></label><br>
            <input type="text" name="nombre" value="${medio.nombre}" required placeholder="Ej: AN/ALQ-131" style="width: 300px; padding: 5px;">
        </div>
        
        <!-- LÓGICA PARA PRE-SELECCIONAR EL TIPO AL EDITAR -->
        <c:set var="tipoSeleccionado" value="" />
        <c:if test="${not empty medio.especificacionesAereo}"><c:set var="tipoSeleccionado" value="Aereo" /></c:if>
        <c:if test="${not empty medio.especificacionesRadar}"><c:set var="tipoSeleccionado" value="Radar" /></c:if>
        <c:if test="${not empty medio.especificacionesEw}"><c:set var="tipoSeleccionado" value="Ew" /></c:if>
        <c:if test="${not empty medio.especificacionesArmamento}"><c:set var="tipoSeleccionado" value="Armamento" /></c:if>

        <div class="form-group">
            <label><strong>1. Seleccionar Tipo de Medio:</strong></label><br>
            <select id="tipoSelect" onchange="cambiarTipoMedio(false)" style="padding: 5px; width: 314px;">
                <option value="">-- Elija el tipo principal --</option>
                <option value="Aereo" ${tipoSeleccionado == 'Aereo' ? 'selected' : ''}>Aéreo</option>
                <option value="Radar" ${tipoSeleccionado == 'Radar' ? 'selected' : ''}>Radar</option>
                <option value="Ew" ${tipoSeleccionado == 'Ew' ? 'selected' : ''}>Guerra Electrónica (EW)</option>
                <option value="Armamento" ${tipoSeleccionado == 'Armamento' ? 'selected' : ''}>Armamento</option>
            </select>
        </div>

        <!-- BLOQUE AÉREO -->
        <div id="bloqueAereo" class="bloque-tipo">
            <h4>2. Seleccione la Categoría:</h4>
            <!-- Usamos medio.categoria.id para pre-marcar el botón correcto al editar -->
            <div class="categorias-container">
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="1" ${medio.categoria.id == 1 ? 'checked' : ''}><span>Caza</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="2" ${medio.categoria.id == 2 ? 'checked' : ''}><span>Cazabombardero</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="3" ${medio.categoria.id == 3 ? 'checked' : ''}><span>Transporte</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="4" ${medio.categoria.id == 4 ? 'checked' : ''}><span>Helicóptero</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="5" ${medio.categoria.id == 5 ? 'checked' : ''}><span>UAV</span></label>
            </div>

            <h4>3. Completar Especificaciones:</h4>
            <div class="inputs-grid">
                <label>Velocidad Máx (Mach):</label> <input type="number" step="0.1" name="velocidadMaxMach" value="${medio.especificacionesAereo.velocidadMaxMach}">
                <label>Techo Servicio (ft):</label> <input type="number" name="techoServicioPies" value="${medio.especificacionesAereo.techoServicioPies}">
                <label>Radio Combate (mn):</label> <input type="number" name="radioCombateMillas" value="${medio.especificacionesAereo.radioCombateMillas}">
                <label>Carga G Máx:</label> <input type="number" step="0.1" name="cargaGMaxima" value="${medio.especificacionesAereo.cargaGMaxima}">
                <label>Peso Máx Despegue (lb):</label> <input type="number" step="0.1" name="pesoMaxDespegueLb" value="${medio.especificacionesAereo.pesoMaxDespegueLb}">
            </div>
        </div>

        <!-- BLOQUE RADAR -->
        <div id="bloqueRadar" class="bloque-tipo">
            <h4>2. Seleccione la Categoría:</h4>
            <div class="categorias-container">
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="90" ${medio.categoria.id == 90 ? 'checked' : ''}><span>Alerta Temprana</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="91" ${medio.categoria.id == 91 ? 'checked' : ''}><span>Embarcado</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="94" ${medio.categoria.id == 94 ? 'checked' : ''}><span>Control de Tiro</span></label>
            </div>

            <h4>3. Completar Especificaciones:</h4>
            <div class="inputs-grid">
                <label>Banda de Frecuencia:</label> <input type="text" name="bandaFrecuencia" value="${medio.especificacionesRadar.bandaFrecuencia}">
                <label>Alcance Detección (km):</label> <input type="number" step="0.1" name="alcanceDeteccionKm" value="${medio.especificacionesRadar.alcanceDeteccionKm}">
                <label>Potencia Pico (kW):</label> <input type="number" step="0.1" name="potenciaPicoKw" value="${medio.especificacionesRadar.potenciaPicoKw}">
            </div>
        </div>

        <!-- BLOQUE EW -->
        <div id="bloqueEw" class="bloque-tipo">
            <h4>2. Seleccione la Categoría:</h4>
            <div class="categorias-container">
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="101" ${medio.categoria.id == 101 ? 'checked' : ''}><span>Ataque Electrónico</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="102" ${medio.categoria.id == 102 ? 'checked' : ''}><span>Defensa Electrónica</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="103" ${medio.categoria.id == 103 ? 'checked' : ''}><span>Inteligencia de Señales</span></label>
            </div>

            <h4>3. Completar Especificaciones:</h4>
            <div class="inputs-grid">
                <label>Frecuencia Mín (MHz):</label> <input type="number" step="0.1" name="rangoFrecuenciaMinMhz" value="${medio.especificacionesEw.rangoFrecuenciaMinMhz}">
                <label>Frecuencia Máx (MHz):</label> <input type="number" step="0.1" name="rangoFrecuenciaMaxMhz" value="${medio.especificacionesEw.rangoFrecuenciaMaxMhz}">
                <label>Modos de Operación:</label> <input type="text" name="modosOperacion" value="${medio.especificacionesEw.modosOperacion}">
                <label>Capacidad DRFM:</label> 
                <select name="capacidadDrfm">
                    <option value="true" ${medio.especificacionesEw.capacidadDrfm == true ? 'selected' : ''}>Sí</option>
                    <option value="false" ${medio.especificacionesEw.capacidadDrfm == false ? 'selected' : ''}>No</option>
                </select>
            </div>
        </div>

        <!-- BLOQUE ARMAMENTO -->
        <div id="bloqueArmamento" class="bloque-tipo">
            <h4>2. Seleccione la Categoría:</h4>
            <div class="categorias-container">
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="201" ${medio.categoria.id == 201 ? 'checked' : ''}><span>Aire-Aire</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="202" ${medio.categoria.id == 202 ? 'checked' : ''}><span>Aire-Superficie</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="203" ${medio.categoria.id == 203 ? 'checked' : ''}><span>Lanzable</span></label>
                <label class="btn-categoria"><input type="radio" name="categoriaId" value="204" ${medio.categoria.id == 204 ? 'checked' : ''}><span>Cohetes-Cañones</span></label>
            </div>

            <h4>3. Completar Especificaciones:</h4>
            <div class="inputs-grid">
                <label>Tipo de Guía:</label> <input type="text" name="tipoGuia" value="${medio.especificacionesArmamento.tipoGuia}">
                <label>Alcance Máx (km):</label> <input type="number" step="0.1" name="alcanceMaxKm" value="${medio.especificacionesArmamento.alcanceMaxKm}">
                <label>Peso Ojiva (kg):</label> <input type="number" step="0.1" name="pesoOjivaKg" value="${medio.especificacionesArmamento.pesoOjivaKg}">
            </div>
        </div>

        <div style="margin-top: 30px;">
            <button type="submit" style="padding: 10px 20px; background-color: #28a745; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 16px;">Guardar Activo Táctico</button>
        </div>
    </form>

</body>
</html>