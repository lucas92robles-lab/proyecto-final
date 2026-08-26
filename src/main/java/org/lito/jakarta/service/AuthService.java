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

    public Usuario validarUsuario(String username, String password) {
        try {
            // Buscamos un usuario que coincida exactamente con las credenciales ingresadas
            return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :user AND u.password = :pass", Usuario.class)
                     .setParameter("user", username)
                     .setParameter("pass", password)
                     .getSingleResult();
        } catch (NoResultException e) {
            // Si no encuentra ninguna coincidencia, devuelve null (credenciales incorrectas)
            return null; 
        }
    }
    public void registrarUsuario(String username, String password) {
    Usuario nuevo = new Usuario();
    nuevo.setUsername(username);
    nuevo.setPassword(password);
    nuevo.setRol("VISITANTE");
    em.persist(nuevo);
    }
}