
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

    // @Inject
    // private CategoriaService categoriaService;

    @GET
    public String administrarCategorias() {
        // SEGURIDAD: Solo ingresa si está logueado Y su rol es ADMIN
        if (!usuarioSession.isLogueado() || !"ADMIN".equals(usuarioSession.getUsuarioActual().getRol())) {
            return "redirect:dashboard"; // Si es visitante, lo mandamos al dashboard
        }
        
        // models.put("listaCategorias", categoriaService.obtenerTodas());
        models.put("tituloAdmin", "Gestión de Categorías");
        return "panel-admin.jsp"; 
    }
}