package org.lito.jakarta.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.lito.jakarta.model.Categoria;
import org.lito.jakarta.model.Fabricante;
import org.lito.jakarta.model.Pais;

import java.util.List;

@Stateless
public class CatalogoServiceBean {

    @PersistenceContext(unitName = "TacticaPU")
    private EntityManager em;

    public List<Pais> findAllPaises() {
        return em.createQuery(
            "SELECT p FROM Pais p ORDER BY p.nombre", Pais.class)
            .getResultList();
    }

    public List<Categoria> findAllCategorias() {
        return em.createQuery(
            "SELECT c FROM Categoria c ORDER BY c.nombre", Categoria.class)
            .getResultList();
    }

    public List<Fabricante> findAllFabricantes() {
        return em.createQuery(
            "SELECT f FROM Fabricante f ORDER BY f.nombre", Fabricante.class)
            .getResultList();
    }
}