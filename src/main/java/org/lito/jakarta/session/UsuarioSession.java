package org.lito.jakarta.session;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import org.lito.jakarta.model.Usuario;

@Named
@SessionScoped
public class UsuarioSession implements Serializable {

    // Es importante implementar Serializable en alcances mayores a Request (como Session o Redirect)
    // para que el servidor pueda gestionar la memoria correctamente.
    
    private boolean logueado = false;
    private Usuario usuarioActual;

    public UsuarioSession() {
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    
    // Método de utilidad para limpiar la sesión al salir
    public void cerrarSesion() {
        this.logueado = false;
        this.usuarioActual = null;
    }
}