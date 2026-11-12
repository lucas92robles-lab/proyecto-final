<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

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
                <p><strong>Velocidad Max (Mach):</strong> <span>${medio.especificacionesAereo.velocidadMaxMach}</span></p>
                <p><strong>Techo de Servicio (Pies):</strong> <span>${medio.especificacionesAereo.techoServicioPies}</span></p>
                <p><strong>Radio de combate (Millas):</strong> <span>${medio.especificacionesAereo.radioCombateMillas}</span></p>
                <p><strong>Carga G Maxima:</strong> <span>${medio.especificacionesAereo.cargaGMaxima}</span></p>
                <p><strong>Peso Max Despegue (Lb):</strong> <span>${medio.especificacionesAereo.pesoMaxDespegueLb}</span></p>
                <p><strong>RCS (M2):</strong> <span>${medio.especificacionesAereo.rcsM2}</span></p>
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
            <p><strong>Pais de origen:</strong> <span>${medio.paisOrigen != null ? medio.paisOrigen : '-'}</span></p>
            <p><strong>Fabricante:</strong> <span>${medio.fabricante != null ? medio.fabricante : '-'}</span></p>
            <p><strong>Costo de adquisicion (MUsd):</strong> <span>${medio.costoAdquisicionMUsd != null ? medio.costoAdquisicionMUsd : '-'}</span></p>
            <p><strong>Costo operativo Hora (Usd):</strong> <span>${medio.costoOperativoHoraUsd != null ? medio.costoOperativoHoraUsd : '-'}</span></p>
            <p><strong>Vida util Horas:</strong> <span>${medio.vidaUtilHoras != null ? medio.vidaUtilHoras : '-'}</span></p>
            <p><strong>Año de introduccion:</strong> <span>${medio.añoIntroduccion != null ? medio.añoIntroduccion : '-'}</span></p>
            <p><strong>Tripulacion:</strong> <span>${medio.tripulacionDotacion != null ? medio.tripulacionDotacion : '-'}</span></p>
            <p><strong>Inventario estimado:</strong> <span>${medio.inventarioEstimado != null ? medio.inventarioEstimado : '-'}</span></p>
            <p><strong>Capacidad Prod. Anual:</strong> <span>${medio.capacidadProduccionAnual != null ? medio.capacidadProduccionAnual : '-'}</span></p>
        </div>

        <!-- BLOQUE EXCLUSIVO PARA DESCRIPCIÓN -->
        <div class="bloque-descripcion">
            <strong>Descripción general:</strong>
            <p>${medio.descripcion != null ? medio.descripcion : 'Sin descripción detallada.'}</p>
        </div>

        <!-- BloqueIntegraciones -->
        <div class="bloque-specs">
            <!-- Radares Compatibles -->
            <div>
                <h4 style="margin-top: 20px; margin-bottom: 20px;">Radares Compatibles </h4>
                <c:choose>
                    <c:when test="${not empty medio.radaresCompatibles}">
                        <ul style="list-style-type: none;">
                            <c:forEach var="radar" items="${medio.radaresCompatibles}">
                                <li style="margin-bottom: 4px;">${radar.nombre} (${empty radar.modelo ? 'Sin modelo' : radar.modelo})</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <p>No hay radares asociados a este medio.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- Sistemas EW Compatibles -->
            <div>
                <h4 style="margin-top: 20px; margin-bottom: 20px;">Sistemas de Guerra Electrónica (EW)</h4>
                <c:choose>
                    <c:when test="${not empty medio.sistemasEwCompatibles}">
                        <ul style="list-style-type: none;">
                            <c:forEach var="ew" items="${medio.sistemasEwCompatibles}">
                                <li style="margin-bottom: 4px;">${ew.nombre} (${empty ew.modelo ? 'Sin modelo' : ew.modelo})</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <p>No hay sistemas EW asociados a este medio.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <div>
                <h4 style="margin-top: 20px; margin-bottom: 20px;">Armamento Compatible</h4>
                <c:choose>
                    <c:when test="${not empty medio.armamentosCompatibles}">
                        <ul style="list-style-type: none;">
                            <c:forEach var="arm" items="${medio.armamentosCompatibles}">
                                <li style="margin-bottom: 4px;">${arm.nombre} (${empty arm.modelo ? 'Sin modelo' : arm.modelo})</li>
                            </c:forEach>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <p>No hay armamento asociado a este medio.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- <h4 style="margin-top: 20px; margin-bottom: 20px;">Paises operadores:</h4><span>(para agregar despues)</span> -->
        </div>
           
        <!-- BARRA DE ACCIONES INFERIOR -->
        <div style="margin-top: 40px; margin-bottom: 20px; display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #9ca3af; padding-top: 20px;">
            
            <!-- Izquierda: Volver -->
            <a href="${pageContext.request.contextPath}/app/medios/listado" class="btn" style="background: #9ca3af; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold; font-family: var(--fuente-tactica);">
                Volver al Listado
            </a>
            
            <!-- Derecha: Editar y Eliminar -->
            <div style="display: flex; gap: 10px;">
                <c:if test="${usuarioSession.usuarioActual.rol != 'LECTOR'}">
                    <a href="${pageContext.request.contextPath}/app/medios/editar/${medio.id}" class="btn" style="background: var(--color-secundario); color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold; font-family: var(--fuente-tactica);">
                        Editar Activo
                    </a>
                </c:if>
                
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

    <!-- COLUMNA DERECHA: IMAGENES -->
    <div class="columna-imagen" style="display: flex; flex-direction: column; gap: 15px;">
        <c:if test="${not empty medio.imagenUrl}">
            <c:set var="src1" value="${fn:startsWith(medio.imagenUrl, 'http') ? medio.imagenUrl : pageContext.request.contextPath.concat(medio.imagenUrl)}"/>
            <div>
                <img src="${src1}" alt="Imagen 1 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if>

        <c:if test="${not empty medio.imagenUrl}">
            <c:set var="src1" value="${fn:startsWith(medio.imagenUrl2, 'http') ? medio.imagenUrl2 : pageContext.request.contextPath.concat(medio.imagenUrl2)}"/>
            <div>
                <img src="${src1}" alt="Imagen 2 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if>

        <c:if test="${not empty medio.imagenUrl}">
            <c:set var="src1" value="${fn:startsWith(medio.imagenUrl3, 'http') ? medio.imagenUrl3 : pageContext.request.contextPath.concat(medio.imagenUrl3)}"/>
            <div>
                <img src="${src1}" alt="Imagen 3 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if>

        
        
        
        
        
        <!-- Imagen 1 (Principal) 
        <c:if test="${not empty medio.imagenUrl}">
            <div>
                <img src="${pageContext.request.contextPath}${medio.imagenUrl}" alt="Imagen 1 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if>-->

        <!-- Imagen 2 
        <c:if test="${not empty medio.imagenUrl2}">
            <div>
                <img src="${pageContext.request.contextPath}${medio.imagenUrl2}" alt="Imagen 2 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if>-->

        <!-- Imagen 3
        <c:if test="${not empty medio.imagenUrl3}">
            <div>
                <img src="${pageContext.request.contextPath}${medio.imagenUrl3}" alt="Imagen 3 de ${medio.nombre}" style="max-width: 100%; border-radius: 6px; border: 1px solid #cbd5e0;">
            </div>
        </c:if> -->

        <!-- Si ninguna tiene valor -->
        <c:if test="${empty medio.imagenUrl and empty medio.imagenUrl2 and empty medio.imagenUrl3}">
            <div class="sin-imagen" style="padding: 20px; text-align: center; background: #edf2f7; border-radius: 6px; color: #718096;">
                SIN IMÁGENES DISPONIBLES
            </div>
        </c:if>
    </div>


    <!--
    <div class="columna-imagen">
        <c:choose>
            <c:when test="${not empty medio.imagenUrl}">
                <img src="${pageContext.request.contextPath}${medio.imagenUrl}" alt="Imagen de ${medio.nombre}">
            </c:when>
            <c:otherwise>
                <div class="sin-imagen">SIN IMAGEN DISPONIBLE</div>
            </c:otherwise>
        </c:choose>
    </div>  -->


</div>

<%@ include file="footer.jsp" %>