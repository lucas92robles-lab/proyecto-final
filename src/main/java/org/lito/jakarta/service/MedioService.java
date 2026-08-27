package org.lito.jakarta.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import org.lito.jakarta.model.Medio;
import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;

@ApplicationScoped
public class MedioService {

    @PersistenceContext(unitName = "TacticaPU") 
    private EntityManager em;

    // Método para los módulos Aéreo, Radar y EW
    @Transactional
    public List<MedioListDTO> obtenerPorCategoria(String nombreCategoria) {
        // Consulta JPQL que viaja por la relación Medio -> Categoria
        List<Medio> medios = em.createQuery(
                "SELECT m FROM Medio m WHERE m.categoria.nombre = :categoria", Medio.class)
                .setParameter("categoria", nombreCategoria)
                .getResultList();
        
        return medios.stream()
                .map(this::convertirAListDTO)
                .collect(Collectors.toList());
    }

    // Método para el ORBAT (trae todo el inventario)
    @Transactional
    public List<MedioListDTO> obtenerTodos() {
        List<Medio> medios = em.createQuery("SELECT m FROM Medio m", Medio.class)
                .getResultList();
                
        return medios.stream()
                .map(this::convertirAListDTO)
                .collect(Collectors.toList());
    }

    // Método privado auxiliar para limpiar los datos antes de mandarlos al JSP
    private MedioListDTO convertirAListDTO(Medio medio) {
        MedioListDTO dto = new MedioListDTO();
        
        dto.setId(medio.getId());
        dto.setNombre(medio.getNombre());
        // dto.setEstadoOperativo(medio.getEstadoOperativo()); 
        
        return dto;
    }

    // Método para buscar un medio específico por su ID
    @Transactional
    public MedioDetalleDTO obtenerPorId(Integer id) {
        // Usamos el método find de JPA para buscar por clave primaria
        Medio medio = em.find(Medio.class, id);
        
        if (medio == null) {
            return null;
        }

        // Mapeamos a DTO
        MedioDetalleDTO dto = new MedioDetalleDTO();
        dto.setId(medio.getId());
        dto.setNombre(medio.getNombre());
        // dto.setEstadoOperativo(medio.getEstadoOperativo()); // Descomentar a futuro
        
        // Acá a futuro se mapearan los datos específicos (bases, especificaciones, etc.)
        return dto;
    }

    //metodo para guardar un medio nuevo o modificar uno ya existente
    @Transactional
    public void guardarMedio(Integer id, String nombre) {
        if (id == null || id == 0) {
            // Es un registro NUEVO
            Medio nuevoMedio = new Medio();
            nuevoMedio.setNombre(nombre);
            // Nota: Si tu base de datos exige que la Categoría no sea nula, 
            // vas a tener que buscarla acá y setearla (ej: nuevoMedio.setCategoria(...))
            
            em.persist(nuevoMedio);
        } else {
            // Es una EDICIÓN de un registro existente
            Medio medioExistente = em.find(Medio.class, id);
            if (medioExistente != null) {
                medioExistente.setNombre(nombre);
                em.merge(medioExistente);
            }
        }
    }
}