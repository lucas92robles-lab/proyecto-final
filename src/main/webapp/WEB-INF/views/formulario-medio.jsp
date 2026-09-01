<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Cargamos el layout principal (Navbar y Sidebar) -->
<%@ include file="header.jsp" %>

<script>
    // Lógica para mostrar/ocultar las especificaciones según el tipo elegido
    function cambiarTipoMedio(esCargaInicial) {
        document.getElementById('bloqueAereo').style.display = 'none';
        document.getElementById('bloqueRadar').style.display = 'none';
        document.getElementById('bloqueEw').style.display = 'none';
        document.getElementById('bloqueArmamento').style.display = 'none';
        
        if (!esCargaInicial) {
            var radios = document.querySelectorAll('input[name="categoriaId"]');
            radios.forEach(function(radio) { radio.checked = false; });
        }

        var seleccion = document.getElementById('tipoSelect').value;
        if(seleccion !== "") {
            document.getElementById('bloque' + seleccion).style.display = 'block';
        }
    }

    // Ejecutar al cargar la página (para el modo Editar)
    window.onload = function() {
        if (document.getElementById('tipoSelect') && document.getElementById('tipoSelect').value !== "") {
            cambiarTipoMedio(true);
        }
    };
</script>

<div class="form-container">
    <div class="form-tarjeta">
        <h2 style="color: var(--color-primario); margin-top: 0; margin-bottom: 30px; text-transform: uppercase;">
            ${empty medio.id ? 'Alta de Nuevo' : 'Editar'} Activo Táctico
        </h2>

        <form action="${pageContext.request.contextPath}/app/medios/guardar" method="post">
            
            <!-- Token CSRF y campo oculto de ID para diferenciar Crear de Editar -->
            <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}">
            <input type="hidden" name="id" value="${medio.id}">
            
            <h3 class="titulo-seccion">Datos Generales</h3>
            <div class="inputs-grid">
                <label>Nombre/Designación (*):</label> 
                <input type="text" name="nombre" value="${medio.nombre}" required placeholder="Ej: F-16 Fighting Falcon">
                
                <label>Modelo/Variante:</label> 
                <input type="text" name="modelo" value="${medio.modelo}" placeholder="Ej: Block 50">
                
                <label>URL de Imagen:</label> 
                <input type="url" name="imagenUrl" value="${medio.imagenUrl}" placeholder="https://ejemplo.com/imagen.jpg">
                
                <label>País de Origen:</label> 
                <input type="text" name="paisOrigen" value="${medio.paisOrigen}" placeholder="Ej: Estados Unidos">
                
                <label>Año de Introducción:</label> 
                <input type="number" name="añoIntroduccion" value="${medio.añoIntroduccion}">
                
                <label>Costo Adquisición (MUsd):</label> 
                <input type="number" step="0.1" name="costoAdquisicion" value="${medio.costoAdquisicionMUsd}">
                
                <label>Costo Operativo/Hora (Usd):</label> 
                <input type="number" name="costoOperativo" value="${medio.costoOperativoHoraUsd}">
                
                <label>Descripción:</label> 
                <textarea name="descripcion" placeholder="Ingrese detalles técnicos e históricos...">${medio.descripcion}</textarea>
            </div>

            <!-- Lógica para pre-seleccionar el dropdown al Editar -->
            <c:set var="tipoSeleccionado" value="" />
            <c:if test="${not empty medio.especificacionesAereo}"><c:set var="tipoSeleccionado" value="Aereo" /></c:if>
            <c:if test="${not empty medio.especificacionesRadar}"><c:set var="tipoSeleccionado" value="Radar" /></c:if>
            <c:if test="${not empty medio.especificacionesEw}"><c:set var="tipoSeleccionado" value="Ew" /></c:if>
            <c:if test="${not empty medio.especificacionesArmamento}"><c:set var="tipoSeleccionado" value="Armamento" /></c:if>

            <h3 class="titulo-seccion">Clasificación y Especificaciones</h3>
            <div class="form-group inputs-grid">
                <label>Tipo Principal (*):</label>
                <select id="tipoSelect" onchange="cambiarTipoMedio(false)" required>
                    <option value="">-- Elija el tipo principal --</option>
                    <option value="Aereo" ${tipoSeleccionado == 'Aereo' ? 'selected' : ''}>Aéreo</option>
                    <option value="Radar" ${tipoSeleccionado == 'Radar' ? 'selected' : ''}>Radar</option>
                    <option value="Ew" ${tipoSeleccionado == 'Ew' ? 'selected' : ''}>Guerra Electrónica (EW)</option>
                    <option value="Armamento" ${tipoSeleccionado == 'Armamento' ? 'selected' : ''}>Armamento</option>
                </select>
            </div>

            <!-- BLOQUE AÉREO -->
            <div id="bloqueAereo" class="bloque-tipo">
                <h4>Categoría:</h4>
                <div class="categorias-container">
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="1" ${medio.categoria.id == 1 ? 'checked' : ''}><span>Caza</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="2" ${medio.categoria.id == 2 ? 'checked' : ''}><span>Cazabombardero</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="3" ${medio.categoria.id == 3 ? 'checked' : ''}><span>Transporte</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="4" ${medio.categoria.id == 4 ? 'checked' : ''}><span>Helicóptero</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="5" ${medio.categoria.id == 5 ? 'checked' : ''}><span>UAV</span></label>
                </div>

                <h4>Especificaciones:</h4>
                <div class="inputs-grid">
                    <label>Velocidad Máx (Mach):</label> <input type="number" step="0.1" name="velocidadMaxMach" value="${medio.especificacionesAereo.velocidadMaxMach}">
                    <label>Techo Servicio (ft):</label> <input type="number" name="techoServicioPies" value="${medio.especificacionesAereo.techoServicioPies}">
                    <label>Radio Combate (mn):</label> <input type="number" name="radioCombateMillas" value="${medio.especificacionesAereo.radioCombateMillas}">
                    <label>Carga G Máx:</label> <input type="number" step="0.1" name="cargaGMaxima" value="${medio.especificacionesAereo.cargaGMaxima}">
                    <label>Peso Máx Despegue (lb):</label> <input type="number" step="0.1" name="pesoMaxDespegueLb" value="${medio.especificacionesAereo.pesoMaxDespegueLb}">
                    <label>RCS (m2):</label> <input type="number" step="0.01" name="rcsM2" value="${medio.especificacionesAereo.rcsM2}">
                    <label>Envergadura (ft):</label> <input type="number" step="0.1" name="envergaduraPies" value="${medio.especificacionesAereo.envergaduraPies}">
                    <label>Longitud (ft):</label> <input type="number" step="0.1" name="longitudPies" value="${medio.especificacionesAereo.longitudPies}">
                </div>
            </div>

            <!-- BLOQUE RADAR -->
            <div id="bloqueRadar" class="bloque-tipo">
                <h4>Categoría:</h4>
                <div class="categorias-container">
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="90" ${medio.categoria.id == 90 ? 'checked' : ''}><span>Alerta Temprana</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="91" ${medio.categoria.id == 91 ? 'checked' : ''}><span>Embarcado</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="94" ${medio.categoria.id == 94 ? 'checked' : ''}><span>Control de Tiro</span></label>
                </div>

                <h4>Especificaciones:</h4>
                <div class="inputs-grid">
                    <label>Banda de Frecuencia:</label> <input type="text" name="bandaFrecuencia" value="${medio.especificacionesRadar.bandaFrecuencia}">
                    <label>Alcance Detección (km):</label> <input type="number" step="0.1" name="alcanceDeteccionKm" value="${medio.especificacionesRadar.alcanceDeteccionKm}">
                    <label>Potencia Pico (kW):</label> <input type="number" step="0.1" name="potenciaPicoKw" value="${medio.especificacionesRadar.potenciaPicoKw}">
                    <label>Tipo de Antena:</label> <input type="text" name="tipoAntena" value="${medio.especificacionesRadar.tipoAntena}">
                    <label>Resolución Dist. (m):</label> <input type="number" step="0.1" name="resolucionDistanciaM" value="${medio.especificacionesRadar.resolucionDistanciaM}">
                </div>
            </div>

            <!-- BLOQUE EW -->
            <div id="bloqueEw" class="bloque-tipo">
                <h4>Categoría:</h4>
                <div class="categorias-container">
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="101" ${medio.categoria.id == 101 ? 'checked' : ''}><span>Ataque Electrónico</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="102" ${medio.categoria.id == 102 ? 'checked' : ''}><span>Defensa Electrónica</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="103" ${medio.categoria.id == 103 ? 'checked' : ''}><span>Inteligencia de Señales</span></label>
                </div>

                <h4>Especificaciones:</h4>
                <div class="inputs-grid">
                    <label>Frecuencia Mín (MHz):</label> <input type="number" step="0.1" name="rangoFrecuenciaMinMhz" value="${medio.especificacionesEw.rangoFrecuenciaMinMhz}">
                    <label>Frecuencia Máx (MHz):</label> <input type="number" step="0.1" name="rangoFrecuenciaMaxMhz" value="${medio.especificacionesEw.rangoFrecuenciaMaxMhz}">
                    <label>Modos de Operación:</label> <input type="text" name="modosOperacion" value="${medio.especificacionesEw.modosOperacion}">
                    <label>Potencia Emisión (kW):</label> <input type="number" step="0.1" name="potenciaEmisionErpKw" value="${medio.especificacionesEw.potenciaEmisionErpKw}">
                    <label>Técnicas Jamming:</label> <input type="text" name="tecnicasJamming" value="${medio.especificacionesEw.tecnicasJamming}">
                    <label>Objs Simultáneos:</label> <input type="number" name="numeroObjetivosSimultaneos" value="${medio.especificacionesEw.numeroObjetivosSimultaneos}">
                    <label>Capacidad DRFM:</label> 
                    <select name="capacidadDrfm">
                        <option value="true" ${medio.especificacionesEw.capacidadDrfm == true ? 'selected' : ''}>Sí</option>
                        <option value="false" ${medio.especificacionesEw.capacidadDrfm == false ? 'selected' : ''}>No</option>
                    </select>
                </div>
            </div>

            <!-- BLOQUE ARMAMENTO -->
            <div id="bloqueArmamento" class="bloque-tipo">
                <h4>Categoría:</h4>
                <div class="categorias-container">
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="201" ${medio.categoria.id == 201 ? 'checked' : ''}><span>Aire-Aire</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="202" ${medio.categoria.id == 202 ? 'checked' : ''}><span>Aire-Superficie</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="203" ${medio.categoria.id == 203 ? 'checked' : ''}><span>Lanzable</span></label>
                    <label class="btn-categoria"><input type="radio" name="categoriaId" value="204" ${medio.categoria.id == 204 ? 'checked' : ''}><span>Cohetes-Cañones</span></label>
                </div>

                <h4>Especificaciones:</h4>
                <div class="inputs-grid">
                    <label>Tipo de Guía:</label> <input type="text" name="tipoGuia" value="${medio.especificacionesArmamento.tipoGuia}">
                    <label>Tipo Objetivo:</label> <input type="text" name="tipoObjetivo" value="${medio.especificacionesArmamento.tipoObjetivo}">
                    <label>Tecnología Buscador:</label> <input type="text" name="tecnologiaBuscador" value="${medio.especificacionesArmamento.tecnologiaBuscador}">
                    <label>Alcance Máx (km):</label> <input type="number" step="0.1" name="alcanceMaxKm" value="${medio.especificacionesArmamento.alcanceMaxKm}">
                    <label>Velocidad Máx (Mach):</label> <input type="number" step="0.1" name="velocidadMaxMachArm" value="${medio.especificacionesArmamento.velocidadMaxMach}">
                    <label>Peso Ojiva (kg):</label> <input type="number" step="0.1" name="pesoOjivaKg" value="${medio.especificacionesArmamento.pesoOjivaKg}">
                </div>
            </div>

            <div style="margin-top: 40px; border-top: 1px solid #e2e8f0; padding-top: 20px; display: flex; justify-content: space-between;">
                <a href="javascript:history.back()" class="btn" style="background: #9ca3af; color: white; text-decoration: none; padding: 10px 20px; border-radius: 4px;">Cancelar</a>
                <button type="submit" class="btn btn-primario" style="padding: 10px 30px; font-size: 1.1rem; border: none; border-radius: 4px; font-family: var(--fuente-tactica); font-weight: bold; cursor: pointer;">Guardar Activo Táctico</button>
            </div>
        </form>
    </div>
</div>

<!-- Cerramos el layout principal -->
<%@ include file="footer.jsp" %>