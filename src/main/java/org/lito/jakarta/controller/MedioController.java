package org.lito.jakarta.controller;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

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
}