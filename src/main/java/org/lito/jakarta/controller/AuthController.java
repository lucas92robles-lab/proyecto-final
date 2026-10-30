    package org.lito.jakarta.controller;

    import jakarta.inject.Inject;
    import jakarta.mvc.Controller;
    import jakarta.mvc.Models;
    import jakarta.ws.rs.FormParam;
    import jakarta.ws.rs.GET;
    import jakarta.ws.rs.POST;
    import jakarta.ws.rs.Path;
    import org.lito.jakarta.model.Usuario;
    import org.lito.jakarta.service.AuthService;
    import org.lito.jakarta.session.UsuarioSession;

    @Path("/auth")
    @Controller 
    public class AuthController {

        @Inject
        private UsuarioSession usuarioSession; // La memoria de la sesión

        @Inject
        private AuthService authService; // Conexión a la base de datos

        @Inject
        private Models models; 

        // La pantalla del formulario
        @GET
        @Path("/login")
        public String mostrarLogin() {
        return "login.jsp";
        }

        // Procesa los datos del formulario HTML
        @POST
        @Path("/login")
        public String procesarLogin(@FormParam("username") String username,
                                    @FormParam("password") String password) {
            
            Usuario usuario = authService.validarUsuario(username, password);

            if (usuario != null) {
                
                usuarioSession.setLogueado(true);
                usuarioSession.setUsuarioActual(usuario);
                
                return "redirect:dashboard"; 
            } else {
                // Error: Las credenciales no coinciden
                models.put("error", "Usuario o contraseña incorrectos.");
                return "login.jsp"; // Volvemos a mostrar el formulario con el mensaje de error
            }

        }

        // Cierra la sesión
        @GET
        @Path("/logout")
        public String cerrarSesion() {
            usuarioSession.cerrarSesion();
            return "redirect:auth/login";
        }

        @GET
        @Path("/registro")
        public String mostrarRegistro() {
            return "registro.jsp";
        }


    @POST
        @Path("/registro")
        public String procesarRegistro(
                @FormParam("username") String username,
                @FormParam("password") String password,
                @FormParam("email") String email,
                @FormParam("edad") Integer edad,
                @FormParam("telefono") String telefono,
                @FormParam("direccion") String direccion,
                @FormParam("dependencia") String dependencia
        ) {
            try {
                // Validar si el usuario ya existe (asumiendo que tenés un método para buscar por username)
                Usuario usuarioExistente = authService.buscarPorUsername(username);
                if (usuarioExistente != null) {
                    models.put("error", "El nombre de usuario ya está en uso. Por favor, elige otro.");
                    return "registro.jsp";
                }
            } catch (Exception e) {
                models.put("error", "Ocurrió un error al verificar el usuario existente.");
                return "registro.jsp";
            }

            // Crear la entidad con todos los datos y el rol por defecto "LECTOR"
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setPassword(password); // Recordá hashearlo con BCrypt más adelante si lo deseás
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setEdad(edad);
            nuevoUsuario.setTelefono(telefono);
            nuevoUsuario.setDireccion(direccion);
            nuevoUsuario.setDependencia(dependencia);
            nuevoUsuario.setRol("LECTOR"); // Rol por defecto solicitado

            // Guardar a través del servicio
            authService.registrarUsuarioCompleto(username, password, email, edad, telefono, direccion, dependencia);

            models.put("mensaje", "Cuenta creada exitosamente. Ya podés iniciar sesión.");
            return "login.jsp";
        }

        @GET
        @Path("/recuperar")
        public String mostrarRecuperar() {
            return "recuperar.jsp";
        }
    }