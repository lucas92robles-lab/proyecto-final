<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SITA // Detalle del Medio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>

    <div class="navbar">
        <div>
            <strong>SISTEMA TÁCTICO // ISR</strong>
            <a href="${pageContext.request.contextPath}/app/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/app/medios/orbat">ORBAT</a>
        </div>
        <div>
            <span>OP: ${usuarioSession.usuarioActual.username}</span>
            <a href="${pageContext.request.contextPath}/app/auth/logout" class="btn-salir">Cerrar Sesión</a>
        </div>
    </div>

    <div class="container">
        <div class="panel-base">
            <h2 class="detalle-header">DETALLE DE ACTIVO: ${medio.nombre}</h2>
            
            <p><strong>ID de Sistema:</strong> ${medio.id}</p>
            <p><strong>Designación:</strong> ${medio.nombre}</p>
            

            <h3 class="detalle-titulo-seccion">Especificaciones Técnicas</h3>

            <!-- ========================================== -->
            <!-- 1. MÓDULO AÉREO                            -->
            <!-- ========================================== -->
            <!-- Evaluamos explícitamente si el objeto es distinto de nulo -->
            <c:if test="${medio.especificacionesAereo != null}">
                <div class="caja-especificaciones">
                    <p><strong>Velocidad Máxima:</strong> ${medio.especificacionesAereo.velocidadMaxMach} Mach</p>
                    <p><strong>Techo de Servicio:</strong> ${medio.especificacionesAereo.techoServicioPies} ft</p>
                    <p><strong>Radio de Combate:</strong> ${medio.especificacionesAereo.radioCombateMillas} mn</p>
                    <p><strong>Carga G Máxima:</strong> ${medio.especificacionesAereo.cargaGMaxima} G</p>
                    <p><strong>Peso Máx. Despegue:</strong> ${medio.especificacionesAereo.pesoMaxDespegueLb} lb</p>
                    <p><strong>Firma Radar (RCS):</strong> ${medio.especificacionesAereo.rcsM2} m²</p>
                    <p><strong>Dimensiones:</strong> ${medio.especificacionesAereo.envergaduraPies} ft (Env) x ${medio.especificacionesAereo.longitudPies} ft (Lon)</p>
                </div>
            </c:if>

            <!-- ========================================== -->
            <!-- 2. MÓDULO RADAR                            -->
            <!-- ========================================== -->
            <!-- Evaluamos explícitamente si el objeto es distinto de nulo -->
            <c:if test="${medio.especificacionesRadar != null}">
                <div class="caja-especificaciones">
                    <p><strong>Banda de Frecuencia:</strong> ${medio.especificacionesRadar.bandaFrecuencia}</p>
                    <p><strong>Alcance de Detección:</strong> ${medio.especificacionesRadar.alcanceDeteccionKm} km</p>
                    <p><strong>Tipo de Antena:</strong> ${medio.especificacionesRadar.tipoAntena}</p>
                    <p><strong>Resolución de Distancia:</strong> ${medio.especificacionesRadar.resolucionDistanciaM} m</p>
                    <p><strong>Potencia Pico:</strong> ${medio.especificacionesRadar.potenciaPicoKw} kW</p>
                </div>
            </c:if>

            <!-- ========================================== -->
            <!-- 3. MÓDULO GUERRA ELECTRÓNICA (EW)          -->
            <!-- ========================================== -->
            <c:if test="${medio.especificacionesEw != null}">
                <div class="caja-especificaciones">
                    <p><strong>Rango de Frecuencias:</strong> ${medio.especificacionesEw.rangoFrecuenciaMinMhz} - ${medio.especificacionesEw.rangoFrecuenciaMaxMhz} MHz</p>
                    <p><strong>Modos de Operación:</strong> ${medio.especificacionesEw.modosOperacion}</p>
                    <p><strong>Potencia Emisión (ERP):</strong> ${medio.especificacionesEw.potenciaEmisionErpKw} kW</p>
                    <p><strong>Capacidad DRFM:</strong> ${medio.especificacionesEw.capacidadDrfm ? 'Sí' : 'No'}</p>
                    <p><strong>Técnicas de Jamming:</strong> ${medio.especificacionesEw.tecnicasJamming}</p>
                    <p><strong>Objetivos Simultáneos:</strong> ${medio.especificacionesEw.numeroObjetivosSimultaneos}</p>
                </div>
            </c:if>

            <!-- ========================================== -->
            <!-- 4. MÓDULO ARMAMENTO                        -->
            <!-- ========================================== -->
            <c:if test="${medio.especificacionesArmamento != null}">
                <div class="caja-especificaciones">
                    <p><strong>Tipo de Guía:</strong> ${medio.especificacionesArmamento.tipoGuia}</p>
                    <p><strong>Tipo de Objetivo:</strong> ${medio.especificacionesArmamento.tipoObjetivo}</p>
                    <p><strong>Alcance Máximo:</strong> ${medio.especificacionesArmamento.alcanceMaxKm} km</p>
                    <p><strong>Velocidad Máxima:</strong> ${medio.especificacionesArmamento.velocidadMaxMach} Mach</p>
                    <p><strong>Peso de la Ojiva:</strong> ${medio.especificacionesArmamento.pesoOjivaKg} kg</p>
                    <p><strong>Tecnología del Buscador:</strong> ${medio.especificacionesArmamento.tecnologiaBuscador}</p>
                </div>
            </c:if>

            <!-- ========================================== -->
            <!-- MENSAJE SI NO HAY NINGUNA FICHA TÉCNICA    -->
            <!-- ========================================== -->
            <!-- Comprobamos si TODOS los objetos de especificaciones son nulos -->
            <c:if test="${medio.especificacionesAereo == null and medio.especificacionesRadar == null and medio.especificacionesEw == null and medio.especificacionesArmamento == null}">
                <p class="detalle-vacio">(No hay especificaciones técnicas cargadas para este activo en la base de datos).</p>
            </c:if>

            <div>
                <a href="javascript:history.back()" class="btn-volver">Volver al Listado</a>
            </div>
        </div>
    </div>
</body>
</html>