package org.lito.jakarta.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.lito.jakarta.model.Usuario;

@Stateless
public class AuthService {

    @PersistenceContext(unitName = "TacticaPU")
    private EntityManager em;

    // Método para validar el login de un usuario existente
    public Usuario validarUsuario(String username, String password) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :user AND u.password = :pass", Usuario.class)
                     .setParameter("user", username)
                     .setParameter("pass", password)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
        
    }

    // NUEVO: Método auxiliar para verificar si un username ya está registrado
    public Usuario buscarPorUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :user", Usuario.class)
                     .setParameter("user", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null; // No existe el usuario
        }
    }

    // Método de registro ampliado con todos los campos y rol por defecto "LECTOR"
    public void registrarUsuarioCompleto(String username, String password, String email, Integer edad, String telefono, String direccion, String dependencia) {
        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(password); // Más adelante se puede aplicar BCrypt acá
        nuevo.setEmail(email);
        nuevo.setEdad(edad);
        nuevo.setTelefono(telefono);
        nuevo.setDireccion(direccion);
        nuevo.setDependencia(dependencia);
        nuevo.setRol("LECTOR"); // Rol por defecto solicitado
        
        em.persist(nuevo);
    }
}