<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

<div class="grid-detalle">
    
    <!-- COLUMNA IZQUIERDA: TEXTO -->
    <div class="columna-texto">
        
        <!-- Cabecera -->
        <div class="cabecera-detalle">
            <h1>${medio.nombre}</h1>
            <h2>${medio.modelo != null ? medio.modelo : 'SIN MODELO'}</h2>
        </div>

        <!-- Bloque 1: Especificaciones Específicas -->
        <div class="bloque-specs">
            <!-- 1. MÓDULO AÉREO -->
            <c:if test="${medio.especificacionesAereo != null}">
                <p><strong>velocidad Max (Mach):</strong> <span>${medio.especificacionesAereo.velocidadMaxMach}</span></p>
                <p><strong>techo Servicio (Pies):</strong> <span>${medio.especificacionesAereo.techoServicioPies}</span></p>
                <p><strong>radio Combate (Millas):</strong> <span>${medio.especificacionesAereo.radioCombateMillas}</span></p>
                <p><strong>carga G Maxima:</strong> <span>${medio.especificacionesAereo.cargaGMaxima}</span></p>
                <p><strong>peso Max Despegue (Lb):</strong> <span>${medio.especificacionesAereo.pesoMaxDespegueLb}</span></p>
                <p><strong>rcs (M2):</strong> <span>${medio.especificacionesAereo.rcsM2}</span></p>
                <p><strong>envergadura (Pies):</strong> <span>${medio.especificacionesAereo.envergaduraPies}</span></p>
                <p><strong>longitud (Pies):</strong> <span>${medio.especificacionesAereo.longitudPies}</span></p>
            </c:if>

            <!-- 2. MÓDULO RADAR -->
            <c:if test="${medio.especificacionesRadar != null}">
                <p><strong>Banda de Frecuencia:</strong> <span>${medio.especificacionesRadar.bandaFrecuencia}</span></p>
                <p><strong>Alcance Deteccion (Km):</strong> <span>${medio.especificacionesRadar.alcanceDeteccionKm}</span></p>
                <p><strong>Tipo de Antena:</strong> <span>${medio.especificacionesRadar.tipoAntena}</span></p>
                <p><strong>Resolucion Dist. (m):</strong> <span>${medio.especificacionesRadar.resolucionDistanciaM}</span></p>
                <p><strong>Potencia Pico (kW):</strong> <span>${medio.especificacionesRadar.potenciaPicoKw}</span></p>
            </c:if>

            <!-- 3. MÓDULO EW -->
            <c:if test="${medio.especificacionesEw != null}">
                <p><strong>Rango Frec. Min (MHz):</strong> <span>${medio.especificacionesEw.rangoFrecuenciaMinMhz}</span></p>
                <p><strong>Rango Frec. Max (MHz):</strong> <span>${medio.especificacionesEw.rangoFrecuenciaMaxMhz}</span></p>
                <p><strong>Modos Operacion:</strong> <span>${medio.especificacionesEw.modosOperacion}</span></p>
                <p><strong>Potencia Emision (kW):</strong> <span>${medio.especificacionesEw.potenciaEmisionErpKw}</span></p>
                <p><strong>Capacidad DRFM:</strong> <span>${medio.especificacionesEw.capacidadDrfm ? 'Sí' : 'No'}</span></p>
                <p><strong>Tecnicas Jamming:</strong> <span>${medio.especificacionesEw.tecnicasJamming}</span></p>
                <p><strong>Objetivos Simultaneos:</strong> <span>${medio.especificacionesEw.numeroObjetivosSimultaneos}</span></p>
            </c:if>

            <!-- 4. MÓDULO ARMAMENTO -->
            <c:if test="${medio.especificacionesArmamento != null}">
                <p><strong>Tipo Guia:</strong> <span>${medio.especificacionesArmamento.tipoGuia}</span></p>
                <p><strong>Tipo Objetivo:</strong> <span>${medio.especificacionesArmamento.tipoObjetivo}</span></p>
                <p><strong>Alcance Maximo (Km):</strong> <span>${medio.especificacionesArmamento.alcanceMaxKm}</span></p>
                <p><strong>Velocidad Max (Mach):</strong> <span>${medio.especificacionesArmamento.velocidadMaxMach}</span></p>
                <p><strong>Peso Ojiva (Kg):</strong> <span>${medio.especificacionesArmamento.pesoOjivaKg}</span></p>
                <p><strong>Buscador:</strong> <span>${medio.especificacionesArmamento.tecnologiaBuscador}</span></p>
            </c:if>
        </div>

        <!-- Bloque 2: Especificaciones Generales -->
        <div class="bloque-specs">
            <p><strong>pais Origen:</strong> <span>${medio.paisOrigen != null ? medio.paisOrigen : '-'}</span></p>
            <p><strong>fabricante:</strong> <span>${medio.fabricante != null ? medio.fabricante : '-'}</span></p>
            <p><strong>costo Adquisicion (MUsd):</strong> <span>${medio.costoAdquisicionMUsd != null ? medio.costoAdquisicionMUsd : '-'}</span></p>
            <p><strong>costo Operativo Hora (Usd):</strong> <span>${medio.costoOperativoHoraUsd != null ? medio.costoOperativoHoraUsd : '-'}</span></p>
            <p><strong>vida Util Horas:</strong> <span>${medio.vidaUtilHoras != null ? medio.vidaUtilHoras : '-'}</span></p>
            <p><strong>año Introduccion:</strong> <span>${medio.añoIntroduccion != null ? medio.añoIntroduccion : '-'}</span></p>
            <p><strong>tripulacion Dotacion:</strong> <span>${medio.tripulacionDotacion != null ? medio.tripulacionDotacion : '-'}</span></p>
            <p><strong>inventario Estimado:</strong> <span>${medio.inventarioEstimado != null ? medio.inventarioEstimado : '-'}</span></p>
            <p><strong>capacidad Prod. Anual:</strong> <span>${medio.capacidadProduccionAnual != null ? medio.capacidadProduccionAnual : '-'}</span></p>
        </div>

        <!-- BLOQUE EXCLUSIVO PARA DESCRIPCIÓN -->
        <div class="bloque-descripcion">
            <strong>descripción_general:</strong>
            <p>${medio.descripcion != null ? medio.descripcion : 'Sin descripción detallada.'}</p>
        </div>

        <!-- Bloque 3: Integraciones Futuras -->
        <div class="bloque-specs">
            <p><strong>armasIntegradas:</strong> <span>(para agregar despues)</span></p>
            <p><strong>plataformasCompatibles:</strong> <span>(para agregar despues)</span></p>
            <p><strong>operadores paises:</strong> <span>(para agregar despues)</span></p>
        </div>

        <!-- BARRA DE ACCIONES INFERIOR -->
        <div style="margin-top: 40px; margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #9ca3af; padding-top: 20px;">
            
            <!-- Izquierda: Volver -->
            <a href="${pageContext.request.contextPath}/app/medios/listado" class="btn" style="background: #9ca3af; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold; font-family: var(--fuente-tactica);">
                Volver al Listado
            </a>
            
            <!-- Derecha: Editar y Eliminar -->
            <div style="display: flex; gap: 10px;">
                <a href="${pageContext.request.contextPath}/app/medios/editar/${medio.id}" class="btn" style="background: var(--color-secundario); color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold; font-family: var(--fuente-tactica);">
                    Editar Activo
                </a>
                
                <!-- Solo mostramos el botón de eliminar si el usuario actual es ADMIN -->
                <c:if test="${usuarioSession.usuarioActual.rol == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/app/medios/eliminar/${medio.id}" 
                       onclick="return confirm('ATENCIÓN: ¿Está seguro que desea ELIMINAR permanentemente este activo táctico? Esta acción no se puede deshacer.');" 
                       class="btn" style="background: #dc2626; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold; font-family: var(--fuente-tactica);">
                        Eliminar
                    </a>
                </c:if>
            </div>
        </div>
    </div>

    <!-- COLUMNA DERECHA: IMAGEN -->
    <div class="columna-imagen">
        <c:choose>
            <c:when test="${not empty medio.imagenUrl}">
                <img src="${medio.imagenUrl}" alt="Imagen de ${medio.nombre}">
            </c:when>
            <c:otherwise>
                <div class="sin-imagen">SIN IMAGEN DISPONIBLE</div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

<%@ include file="footer.jsp" %>