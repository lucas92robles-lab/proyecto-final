package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.lito.jakarta.model.Usuario;
import org.lito.jakarta.service.AdminService; 
import org.lito.jakarta.session.UsuarioSession;

@Path("/admin")
@Controller
public class AdminController {

    @Inject
    private UsuarioSession usuarioSession; // Memoria de la sesión actual

    @Inject
    private AdminService adminService; // Servicio para operaciones de administración

    @Inject
    private Models models; // Para pasar datos a las vistas JSP

    // Mostrar el panel de control del administrador
    @GET
    @Path("/panel")
    public String mostrarPanel() {
        // Validar si hay un usuario logueado y si es ADMIN
        Usuario usuarioActual = usuarioSession.getUsuarioActual();
        
        if (usuarioActual == null || !"ADMIN".equals(usuarioActual.getRol())) {
            models.put("error", "Acceso no autorizado. Se requieren privilegios de Administrador.");
            return "login.jsp";
        }

        // Cargar el listado de usuarios para mostrarlos en la tabla
        models.put("listaUsuarios", adminService.obtenerTodosLosUsuarios());
        return "admin-panel.jsp";
    }

    // Procesar el cambio de rol de un usuario desde la tabla
    @POST
    @Path("/actualizar-rol")
    public String actualizarRol(
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("nuevoRol") String nuevoRol
    ) {
        // Doble validación de seguridad de rol
        Usuario usuarioActual = usuarioSession.getUsuarioActual();
        if (usuarioActual == null || !"ADMIN".equals(usuarioActual.getRol())) {
            return "redirect:auth/login";
        }

        // Actualizar el rol en la base de datos
        adminService.cambiarRolUsuario(idUsuario, nuevoRol);

        return "redirect:admin/panel";
    }

    // Eliminar un usuario del sistema
    @GET
    @Path("/eliminar-usuario")
    public String eliminarUsuario(@QueryParam("id") Integer id) {
        // Doble validación de seguridad de rol
        Usuario usuarioActual = usuarioSession.getUsuarioActual();
        if (usuarioActual == null || !"ADMIN".equals(usuarioActual.getRol())) {
            return "redirect:auth/login";
        }

        // Opcional: Evitar que el admin se elimine a sí mismo por error
        if (usuarioActual.getId().equals(id)) {
            return "redirect:admin/panel";
        }

        // Ejecutar eliminación
        adminService.eliminarUsuario(id);

        return "redirect:admin/panel";
    }
}