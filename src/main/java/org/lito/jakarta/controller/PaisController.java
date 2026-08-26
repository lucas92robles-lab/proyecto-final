package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.session.UsuarioSession;
// Importá acá tus Servicios y DTOs...

@Path("/paises")
@Controller
public class PaisController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models;

    // @Inject
    // private PaisService paisService;

    @GET
    public String administrarPaises() {
        // SEGURIDAD: Validación estricta de sesión y rol
        if (!usuarioSession.isLogueado() || !"ADMIN".equals(usuarioSession.getUsuarioActual().getRol())) {
            return "redirect:dashboard"; 
        }
        
        // List<PaisDTO> lista = paisService.obtenerTodos();
        // models.put("listaItems", lista);
        
        models.put("tituloAdmin", "Gestión de Países de Origen");
        return "panel-admin.jsp"; 
    }
}