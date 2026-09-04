package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.session.UsuarioSession;

@Path("/dashboard")
@Controller 
public class DashboardController {

    @Inject
    private UsuarioSession usuarioSession; 

    @GET
    public String mostrarDashboard() {
        // Validación de seguridad si no está logueado, al login
        if (usuarioSession.getUsuarioActual() == null || !usuarioSession.isLogueado()) {
            return "redirect:auth/login";
        }
        
        // Si está logueado, le mostramos la pantalla del dashboard
        return "dashboard.jsp"; 
    }
}