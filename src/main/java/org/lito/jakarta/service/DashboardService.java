package org.lito.jakarta.service;

import java.util.List;

import org.lito.jakarta.model.Medio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

    @ApplicationScoped
    public class DashboardService {

    @PersistenceContext
    private EntityManager em;

    public long contarMedios() {
        return em.createQuery("SELECT COUNT(m) FROM Medio m", Long.class).getSingleResult();
    }

    public long contarAeronaves() {
        return em.createQuery("SELECT COUNT(e) FROM EspecificacionesAereo e", Long.class).getSingleResult();
    }

    public long contarRadares() {
        return em.createQuery("SELECT COUNT(e) FROM EspecificacionesRadar e", Long.class).getSingleResult();
    }

    public long contarEw() {
        return em.createQuery("SELECT COUNT(e) FROM EspecificacionesEw e", Long.class).getSingleResult();
    }

    public long contarArmamento() {
        return em.createQuery("SELECT COUNT(e) FROM EspecificacionesArmamento e", Long.class).getSingleResult();
    }

    public Medio obtenerUltimoMedio() {
        List<Medio> lista = em.createQuery("SELECT m FROM Medio m ORDER BY m.id DESC", Medio.class)
                             .setMaxResults(1)
                             .getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }
}

