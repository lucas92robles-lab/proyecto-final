package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.session.UsuarioSession;

@Path("/dashboard")
@Controller // Le indica a Jakarta MVC que esta clase manejará vistas[cite: 8]
public class DashboardController {

    @Inject
    private UsuarioSession usuarioSession; // Inyectamos la sesión para verificar seguridad

    @GET
    public String mostrarDashboard() {
        // Validación de seguridad básica: si no está logueado, lo pateamos al login
        if (usuarioSession.getUsuarioActual() == null || !usuarioSession.isLogueado()) {
            return "redirect:auth/login";
        }
        
        // Si está logueado, le mostramos la pantalla del dashboard
        return "dashboard.jsp"; 
    }
}