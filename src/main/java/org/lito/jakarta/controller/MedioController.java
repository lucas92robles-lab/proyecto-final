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


@Path("/medios")
@Controller // Convertimos la clase en un controlador MVC
public class MedioController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models; // Inyectamos Models para pasar datos a la vista[cite: 10]

    @Inject
    private MedioService medioService; // Tu servicio de base de datos

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
        
        List<MedioListDTO> lista = medioService.obtenerTodos(); // Acá traemos todo el inventario
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
        
        // Si alguien pone un ID que no existe en la URL, lo devolvemos al ORBAT
        if (detalle == null) {
            return "redirect:medios/orbat";
        }
        
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
        
        models.put("medio", detalle); // Pasamos los datos para pre-llenar los inputs
        models.put("tituloFormulario", "EDITAR ACTIVO: " + detalle.getNombre());
        return "formulario-medio.jsp";
    }

    // 3. Recibir los datos del formulario (POST)
    @POST
    @Path("/guardar")
    public String guardarMedio(@FormParam("id") Integer id, @FormParam("nombre") String nombre) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        // Llamamos al servicio pasando los datos del formulario
        medioService.guardarMedio(id, nombre);
        
        // Redirigimos al listado general para ver los cambios
        return "redirect:medios/orbat"; 
    }
}