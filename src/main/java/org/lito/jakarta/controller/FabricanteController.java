package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.session.UsuarioSession;
// Importá acá tus Servicios y DTOs...

@Path("/fabricantes")
@Controller
public class FabricanteController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models; // Para pasarle datos a la vista

    // @Inject
    // private FabricanteService fabricanteService;

    @GET
    public String administrarFabricantes() {
        // SEGURIDAD: Solo ingresa si está logueado Y su rol es ADMIN
        if (!usuarioSession.isLogueado() || !"ADMIN".equals(usuarioSession.getUsuarioActual().getRol())) {
            return "redirect:dashboard"; // Si no es admin, vuelve al inicio
        }
        
        // List<FabricanteDTO> lista = fabricanteService.obtenerTodos();
        // models.put("listaItems", lista);
        
        models.put("tituloAdmin", "Gestión de Fabricantes");
        return "panel-admin.jsp"; 
    }
}