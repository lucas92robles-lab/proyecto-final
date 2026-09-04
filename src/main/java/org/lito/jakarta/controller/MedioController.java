package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;
import org.lito.jakarta.service.MedioService;
import org.lito.jakarta.session.UsuarioSession;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.QueryParam;

@Path("/medios")
@Controller 
public class MedioController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models;

    @Inject
    private MedioService medioService;

    // --- MÓDULO AÉREO ---
    @GET
    @Path("/aereo")
    public String moduloAereo() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Aereo");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO AÉREO");
        return "categorias-aereas.jsp"; 
    }

    // --- MÓDULO RADAR ---
    @GET
    @Path("/radar")
    public String moduloRadar() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Radar");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO RADAR");
        return "categorias-radares.jsp"; 
    }

    // --- MÓDULO EW (Guerra Electrónica) ---
    @GET
    @Path("/ew")
    public String moduloEw() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("EW");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO EW (Guerra Electrónica)");
        return "categorias-ew.jsp"; 
    }

    // --- MÓDULO armamento ---
    @GET
    @Path("/armamento")
    public String moduloArmamento() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Armamento");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO ARMAMENTO");
        return "categorias-armamento.jsp"; 
    }

    // --- LISTADO ---
    // --- MÓDULO ORBAT ---
    @GET
    @Path("/listado")
    public String listado(@QueryParam("categoria") String categoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista;
        String titulo;

        // Verificamos si viene el parámetro en la URL
        if (categoria != null && !categoria.trim().isEmpty()) {
            // Si hay filtro (ej: caza, ataque, elint), llamamos al servicio con ese filtro
            lista = medioService.obtenerPorFiltroEspecifico(categoria); 
            titulo = "ORBAT - FILTRO: " + categoria.toUpperCase();
        } else {
            // Si entra desde el menú principal (sin filtro), traemos todo
            lista = medioService.obtenerTodos(); 
            titulo = "Listado completo de medios";
        }
        
        models.put("medios", lista);
        models.put("tituloModulo", titulo);
        return "listado.jsp"; 
    }

    // --- VER DETALLES del medio ---
    @GET
    @Path("/detalle-medio/{id}")
    public String verDetalles(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        // Buscamos toda la ficha técnica usando el servicio
        MedioDetalleDTO detalle = medioService.obtenerPorId(id);
        
        // Si el usuario ingresa un ID que no existe en la URL, lo devolvemos al listado
        if (detalle == null) return "redirect:medios/listado"; 
        
        // Guardamos el objeto para que Expression Language (${medio.nombre}) lo lea en la vista
        models.put("medio", detalle);
        
        // Retornamos la vista correspondiente
        return "detalle-medio.jsp";
    }

    // nuevo medio (formulario)
    @GET
    @Path("/nuevo")
    public String nuevoMedio() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        models.put("tituloFormulario", "NUEVO ACTIVO TÁCTICO");
        models.put("paises", medioService.obtenerTodosLosPaises());
        models.put("fabricantes", medioService.obtenerTodosLosFabricantes());
        return "formulario-medio.jsp";
    }

    // 2. Mostrar formulario con datos (EDITAR)
    @GET
    @Path("/editar/{id}")
    public String editarMedio(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        MedioDetalleDTO detalle = medioService.obtenerPorId(id);
        if (detalle == null) return "redirect:medios/orbat";
        
        models.put("medio", detalle); 
        models.put("tituloFormulario", "EDITAR ACTIVO: " + detalle.getNombre());
        models.put("paises", medioService.obtenerTodosLosPaises());
        models.put("fabricantes", medioService.obtenerTodosLosFabricantes());
        return "formulario-medio.jsp";
    }

    // 3. Recibir los datos del formulario (POST)
    @POST
    @Path("/guardar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String guardarMedio(
            @FormParam("id") Integer id, 
            @FormParam("nombre") String nombre,
            @FormParam("categoriaId") Integer categoriaId,
            @FormParam("paisId") Integer paisId,
            @FormParam("fabricanteId") Integer fabricanteId,

            // Nuevos datos generales
            @FormParam("modelo") String modelo,
            @FormParam("imagenUrl") String imagenUrl,
            @FormParam("añoIntroduccion") String strAno,
            @FormParam("costoAdquisicion") String strCostoAdq,
            @FormParam("costoOperativo") String strCostoOp,
            @FormParam("descripcion") String descripcion,
            
            // Nota: Para PaisOrigen tendrías que recibir el ID y buscarlo, por ahora lo omitimos en la firma o lo manejás como String temporal
            
            // Aéreo
            @FormParam("velocidadMaxMach") String strVelocidad,
            @FormParam("techoServicioPies") String strTecho,
            @FormParam("radioCombateMillas") String strRadio,
            @FormParam("cargaGMaxima") String strCarga,
            @FormParam("pesoMaxDespegueLb") String strPeso,
            @FormParam("rcsM2") String strRcs,
            @FormParam("envergaduraPies") String strEnv,
            @FormParam("longitudPies") String strLon,

            // Radar
            @FormParam("bandaFrecuencia") String bandaFrecuencia,
            @FormParam("alcanceDeteccionKm") String strAlcanceDeteccion,
            @FormParam("potenciaPicoKw") String strPotencia,
            @FormParam("tipoAntena") String tipoAntena,
            @FormParam("resolucionDistanciaM") String strRes,

            // EW
            @FormParam("rangoFrecuenciaMinMhz") String strRangoMin,
            @FormParam("rangoFrecuenciaMaxMhz") String strRangoMax,
            @FormParam("modosOperacion") String modosOperacion,
            @FormParam("potenciaEmisionErpKw") String strErp,
            @FormParam("tecnicasJamming") String tecnicasJamming,
            @FormParam("numeroObjetivosSimultaneos") String strObjs,
            @FormParam("capacidadDrfm") String strDrfm,

            // Armamento
            @FormParam("tipoGuia") String tipoGuia,
            @FormParam("tipoObjetivo") String tipoObjetivo,
            @FormParam("tecnologiaBuscador") String tecnologiaBuscador,
            @FormParam("alcanceMaxKm") String strAlcanceArma,
            @FormParam("velocidadMaxMachArm") String strVelArm,
            @FormParam("pesoOjivaKg") String strOjiva
    ) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        // Conversiones Base
        Integer anoIntro = (strAno != null && !strAno.isBlank()) ? Integer.valueOf(strAno) : null;
        Double costoAdq = (strCostoAdq != null && !strCostoAdq.isBlank()) ? Double.valueOf(strCostoAdq) : null;
        Integer costoOp = (strCostoOp != null && !strCostoOp.isBlank()) ? Integer.valueOf(strCostoOp) : null;

        // Conversiones Aéreo
        Double velocidadMaxMach = (strVelocidad != null && !strVelocidad.isBlank()) ? Double.valueOf(strVelocidad) : null;
        Integer techoServicioPies = (strTecho != null && !strTecho.isBlank()) ? Integer.valueOf(strTecho) : null;
        Integer radioCombateMillas = (strRadio != null && !strRadio.isBlank()) ? Integer.valueOf(strRadio) : null;
        Double cargaGMaxima = (strCarga != null && !strCarga.isBlank()) ? Double.valueOf(strCarga) : null;
        Double pesoMaxDespegueLb = (strPeso != null && !strPeso.isBlank()) ? Double.valueOf(strPeso) : null;
        Double rcsM2 = (strRcs != null && !strRcs.isBlank()) ? Double.valueOf(strRcs) : null;
        Double envPies = (strEnv != null && !strEnv.isBlank()) ? Double.valueOf(strEnv) : null;
        Double lonPies = (strLon != null && !strLon.isBlank()) ? Double.valueOf(strLon) : null;

        // Conversiones Radar
        Double alcanceDeteccionKm = (strAlcanceDeteccion != null && !strAlcanceDeteccion.isBlank()) ? Double.valueOf(strAlcanceDeteccion) : null;
        Double potenciaPicoKw = (strPotencia != null && !strPotencia.isBlank()) ? Double.valueOf(strPotencia) : null;
        Double resolucionDist = (strRes != null && !strRes.isBlank()) ? Double.valueOf(strRes) : null;

        // Conversiones EW
        Double rangoFrecuenciaMinMhz = (strRangoMin != null && !strRangoMin.isBlank()) ? Double.valueOf(strRangoMin) : null;
        Double rangoFrecuenciaMaxMhz = (strRangoMax != null && !strRangoMax.isBlank()) ? Double.valueOf(strRangoMax) : null;
        Double potErp = (strErp != null && !strErp.isBlank()) ? Double.valueOf(strErp) : null;
        Integer numObjs = (strObjs != null && !strObjs.isBlank()) ? Integer.valueOf(strObjs) : null;
        Boolean capacidadDrfm = (strDrfm != null) ? Boolean.valueOf(strDrfm) : false;

        // Conversiones Armamento
        Double alcanceMaxKm = (strAlcanceArma != null && !strAlcanceArma.isBlank()) ? Double.valueOf(strAlcanceArma) : null;
        Double velArm = (strVelArm != null && !strVelArm.isBlank()) ? Double.valueOf(strVelArm) : null;
        Double pesoOjivaKg = (strOjiva != null && !strOjiva.isBlank()) ? Double.valueOf(strOjiva) : null;

        // Llamamos al servicio con todo
        medioService.guardarMedioCompleto(id, nombre, categoriaId, paisId, fabricanteId,
                modelo, imagenUrl, anoIntro, costoAdq, costoOp, descripcion,
                velocidadMaxMach, techoServicioPies, radioCombateMillas, cargaGMaxima, pesoMaxDespegueLb, rcsM2, envPies, lonPies,
                bandaFrecuencia, alcanceDeteccionKm, potenciaPicoKw, tipoAntena, resolucionDist,
                rangoFrecuenciaMinMhz, rangoFrecuenciaMaxMhz, modosOperacion, potErp, tecnicasJamming, numObjs, capacidadDrfm,
                tipoGuia, tipoObjetivo, tecnologiaBuscador, alcanceMaxKm, velArm, pesoOjivaKg);
        
        return "redirect:medios/listado"; 
    }

    // 4. Eliminar
    @GET
    @Path("/eliminar/{id}")
    public String eliminarMedio(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        String rolUsuario = usuarioSession.getUsuarioActual().getRol();
        if (!"ADMIN".equals(rolUsuario)) return "redirect:medios/orbat"; 
        
        medioService.eliminarMedio(id);
        return "redirect:medios/orbat?exito=eliminado"; 
    }
}