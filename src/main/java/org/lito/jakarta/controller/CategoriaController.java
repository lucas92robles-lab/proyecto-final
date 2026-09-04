
package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.session.UsuarioSession;

@Path("/categorias")
@Controller
public class CategoriaController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models;

    @GET
    public String administrarCategorias() {
        
        if (!usuarioSession.isLogueado() || !"ADMIN".equals(usuarioSession.getUsuarioActual().getRol())) {
            return "redirect:dashboard";
        }
        
        // models.put("listaCategorias", categoriaService.obtenerTodas()); 
        models.put("tituloAdmin", "Gestión de Categorías");
        return "panel-admin.jsp"; 
    }
}