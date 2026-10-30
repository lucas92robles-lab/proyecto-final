package org.lito.jakarta.service;

import java.util.List;

import org.lito.jakarta.model.Usuario;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless 
public class AdminService {
    @PersistenceContext(unitName = "TacticaPU")
    private EntityManager em;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return em.createQuery("SELECT u FROM Usuario u ORDER BY u.username", Usuario.class)
            .getResultList();
        }


    public Usuario buscarPorUsername(String username) {
            try {
                return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :user", Usuario.class)
                        .setParameter("user", username)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null; // No existe el usuario
            }
        }

    public void cambiarRolUsuario(Integer idUsuario, String nuevoRol) {
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                usuario.setRol(nuevoRol);
                em.merge(usuario);
            }
        }

    public void eliminarUsuario(Integer idUsuario) {
            Usuario usuario = em.find(Usuario.class, idUsuario);
            if (usuario != null) {
                em.remove(usuario);
            }
        }
    
}
