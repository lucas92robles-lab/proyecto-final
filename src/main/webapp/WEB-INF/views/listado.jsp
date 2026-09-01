<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Cargamos el layout -->
<%@ include file="header.jsp" %>

<!-- Encabezado dinámico de la sección -->
<div class="tarjeta-header" style="display: flex; justify-content: space-between; align-items: center; padding: 20px 30px; background: white; border-bottom: 1px solid #e2e8f0;">
    <div>
        <h2 style="color: var(--color-primario); font-size: 1.5rem; margin-bottom: 5px;">
            ${tituloModulo}
        </h2>
    </div>
    
    <a href="${pageContext.request.contextPath}/app/medios/nuevo" class="btn btn-primario" style="text-decoration: none;">
        + Registrar Medio
    </a>
</div>

<!-- Contenedor de la Tabla -->
<div style="padding: 30px;">
    <div class="tarjeta" style="padding: 0; overflow: hidden;">
        <table class="tabla-tactica" style="width: 100%; border-collapse: collapse;">
            <thead>
                <tr style="background-color: #e2e8f0; border-bottom: 2px solid #cbd5e1; text-align: left;">
                    <th style="padding: 12px 15px;">Nombre / Matrícula</th>
                    <th style="padding: 12px 15px;">Modelo</th>
                    <th style="padding: 12px 15px;">Categoría</th>
                    <th style="padding: 12px 15px; text-align: right;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty medios}">
                        <tr>
                            <!-- Ajustado a colspan 4 porque quitamos el ID -->
                            <td colspan="4" style="text-align: center; padding: 30px; color: var(--texto-secundario);">
                                No se encontraron registros en la base de datos para este filtro.
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="medio" items="${medios}">
                            <!-- Fila con tonalidad más visible para prueba -->
                            <tr style="border-bottom: 1px solid #cbd5e1; background-color: #f8fafc;">
                                <td style="padding: 12px 15px; font-family: var(--fuente-tactica); font-weight: bold;">
                                    ${medio.nombre}
                                </td>
                                <td style="padding: 12px 15px;">
                                    ${medio.modelo != null ? medio.modelo : '-'}
                                </td>
                                <td style="padding: 12px 15px;">
                                    <span class="etiqueta" style="background-color: var(--color-secundario); color: white; padding: 4px 8px; border-radius: 4px; font-size: 0.8rem;">
                                        ${medio.categoria != null ? medio.categoria : 'SIN CATEGORÍA'}
                                    </span>
                                </td>
                                <td style="padding: 12px 15px; text-align: right;">
                                    <a href="${pageContext.request.contextPath}/app/medios/detalle-medio/${medio.id}" class="btn" style="background-color: #e2e8f0; color: var(--color-secundario); padding: 6px 12px; font-size: 0.8rem; text-decoration: none; border-radius: 4px; font-weight: bold;">
                                        Ver Detalles
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>

<!-- Cerramos el layout -->
<%@ include file="footer.jsp" %>