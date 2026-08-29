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
        return "modulo-medios.jsp"; 
    }

    // --- MÓDULO RADAR ---
    @GET
    @Path("/radar")
    public String moduloRadar() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Radar");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO RADAR");
        return "modulo-medios.jsp"; 
    }

    // --- MÓDULO EW (Guerra Electrónica) ---
    @GET
    @Path("/ew")
    public String moduloEw() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("EW");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO EW (Guerra Electrónica)");
        return "modulo-medios.jsp"; 
    }

    // --- MÓDULO ORBAT ---
    @GET
    @Path("/orbat")
    public String moduloOrbat() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerTodos(); 
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO ORBAT (Orden de Batalla)");
        return "modulo-medios.jsp"; 
    }

    // --- VER DETALLES ---
    @GET
    @Path("/detalle/{id}")
    public String verDetalles(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        MedioDetalleDTO detalle = medioService.obtenerPorId(id);
        if (detalle == null) return "redirect:medios/orbat";
        
        models.put("medio", detalle);
        return "detalle-medio.jsp";
    }

    // 1. Mostrar formulario vacío (NUEVO)
    @GET
    @Path("/nuevo")
    public String nuevoMedio() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        models.put("tituloFormulario", "NUEVO ACTIVO TÁCTICO");
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
            
            // Recibimos los números como String para evitar que JAX-RS bloquee la petición
            @FormParam("velocidadMaxMach") String strVelocidad,
            @FormParam("techoServicioPies") String strTecho,
            @FormParam("radioCombateMillas") String strRadio,
            @FormParam("cargaGMaxima") String strCarga,
            @FormParam("pesoMaxDespegueLb") String strPeso,

            @FormParam("bandaFrecuencia") String bandaFrecuencia,
            @FormParam("alcanceDeteccionKm") String strAlcanceDeteccion,
            @FormParam("potenciaPicoKw") String strPotencia,

            @FormParam("rangoFrecuenciaMinMhz") String strRangoMin,
            @FormParam("rangoFrecuenciaMaxMhz") String strRangoMax,
            @FormParam("modosOperacion") String modosOperacion,
            @FormParam("capacidadDrfm") String strDrfm,

            @FormParam("tipoGuia") String tipoGuia,
            @FormParam("alcanceMaxKm") String strAlcanceArma,
            @FormParam("pesoOjivaKg") String strOjiva
    ) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        // Conversión segura: Si el usuario dejó la caja vacía, asignamos 'null' sin errores
        Double velocidadMaxMach = (strVelocidad != null && !strVelocidad.isBlank()) ? Double.valueOf(strVelocidad) : null;
        Integer techoServicioPies = (strTecho != null && !strTecho.isBlank()) ? Integer.valueOf(strTecho) : null;
        Integer radioCombateMillas = (strRadio != null && !strRadio.isBlank()) ? Integer.valueOf(strRadio) : null;
        Double cargaGMaxima = (strCarga != null && !strCarga.isBlank()) ? Double.valueOf(strCarga) : null;
        Double pesoMaxDespegueLb = (strPeso != null && !strPeso.isBlank()) ? Double.valueOf(strPeso) : null;

        Double alcanceDeteccionKm = (strAlcanceDeteccion != null && !strAlcanceDeteccion.isBlank()) ? Double.valueOf(strAlcanceDeteccion) : null;
        Double potenciaPicoKw = (strPotencia != null && !strPotencia.isBlank()) ? Double.valueOf(strPotencia) : null;

        Double rangoFrecuenciaMinMhz = (strRangoMin != null && !strRangoMin.isBlank()) ? Double.valueOf(strRangoMin) : null;
        Double rangoFrecuenciaMaxMhz = (strRangoMax != null && !strRangoMax.isBlank()) ? Double.valueOf(strRangoMax) : null;
        Boolean capacidadDrfm = (strDrfm != null) ? Boolean.valueOf(strDrfm) : false;

        Double alcanceMaxKm = (strAlcanceArma != null && !strAlcanceArma.isBlank()) ? Double.valueOf(strAlcanceArma) : null;
        Double pesoOjivaKg = (strOjiva != null && !strOjiva.isBlank()) ? Double.valueOf(strOjiva) : null;

        // Llamamos al servicio con los datos ya limpios
        medioService.guardarMedioCompleto(id, nombre, categoriaId, 
                velocidadMaxMach, techoServicioPies, radioCombateMillas, cargaGMaxima, pesoMaxDespegueLb,
                bandaFrecuencia, alcanceDeteccionKm, potenciaPicoKw,
                rangoFrecuenciaMinMhz, rangoFrecuenciaMaxMhz, modosOperacion, capacidadDrfm,
                tipoGuia, alcanceMaxKm, pesoOjivaKg);
        
        return "redirect:medios/orbat"; 
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