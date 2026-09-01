package org.lito.jakarta.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.lito.jakarta.model.Medio;
import org.lito.jakarta.model.Categoria;
import org.lito.jakarta.model.EspecificacionesAereo; 
import org.lito.jakarta.model.EspecificacionesRadar;
import org.lito.jakarta.model.EspecificacionesEw;
import org.lito.jakarta.model.EspecificacionesArmamento;
import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;

@ApplicationScoped
public class MedioService {

    @PersistenceContext(unitName = "TacticaPU") 
    private EntityManager em;

    @Transactional
    public List<MedioListDTO> obtenerPorCategoria(String nombreCategoria) {
        List<Medio> medios = em.createQuery(
                "SELECT m FROM Medio m WHERE m.categoria.nombre = :categoria", Medio.class)
                .setParameter("categoria", nombreCategoria)
                .getResultList();
        
        return medios.stream()
                .map(this::convertirAListDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MedioListDTO> obtenerPorFiltroEspecifico(String filtro) {
        List<Medio> medios = em.createQuery(
                "SELECT m FROM Medio m WHERE LOWER(m.categoria.nombre) = LOWER(:filtro)", Medio.class)
                .setParameter("filtro", filtro)
                .getResultList();
        
        return medios.stream()
                .map(this::convertirAListDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MedioListDTO> obtenerTodos() {
        List<Medio> medios = em.createQuery("SELECT m FROM Medio m", Medio.class)
                .getResultList();
                
        return medios.stream()
                .map(this::convertirAListDTO)
                .collect(Collectors.toList());
    }

    private MedioListDTO convertirAListDTO(Medio medio) {
        MedioListDTO dto = new MedioListDTO();
        dto.setId(medio.getId());
        dto.setNombre(medio.getNombre());
        
        if (medio.getModelo() != null) {
            dto.setModelo(medio.getModelo());
        }
        
        if (medio.getCategoria() != null) {
            dto.setCategoria(medio.getCategoria().getNombre());
        }
        
        // Si tenés estos atributos mapeados en tu entidad Medio.java, 
        // descomentalos para que lleguen completos a la tabla del ORBAT:
        // dto.setPaisOrigen(medio.getPaisOrigen());
        // if (medio.getFabricante() != null) {
        //     dto.setFabricante(medio.getFabricante().getNombre());
        // }
        // dto.setImagenUrl(medio.getImagenUrl());
        // dto.setAñoIntroduccion(medio.getAñoIntroduccion());
        
        return dto;
    }

    @Transactional
    public MedioDetalleDTO obtenerPorId(Integer id) {
        Medio medio = em.find(Medio.class, id);
        
        if (medio == null) {
            return null;
        }

        MedioDetalleDTO dto = new MedioDetalleDTO();
        dto.setId(medio.getId());
        dto.setNombre(medio.getNombre());

        // Mapeo de campos generales
        dto.setModelo(medio.getModelo());
        dto.setImagenUrl(medio.getImagenUrl());
        dto.setAñoIntroduccion(medio.getAñoIntroduccion());
        dto.setCostoAdquisicionMUsd(medio.getCostoAdquisicionMUsd());
        dto.setCostoOperativoHoraUsd(medio.getCostoOperativoHoraUsd());
        dto.setVidaUtilHoras(medio.getVidaUtilHoras());
        dto.setTripulacionDotacion(medio.getTripulacionDotacion());
        dto.setInventarioEstimado(medio.getInventarioEstimado());
        dto.setCapacidadProduccionAnual(medio.getCapacidadProduccionAnual());
        dto.setDescripcion(medio.getDescripcion());

        // Extraemos solo el String de los objetos relacionados
        if (medio.getPaisOrigen() != null) {
            dto.setPaisOrigen(medio.getPaisOrigen().getNombre()); // Asume que Pais tiene getNombre()
        }
        if (medio.getFabricante() != null) {
            dto.setFabricante(medio.getFabricante().getNombre()); // Asume que Fabricante tiene getNombre()
        }

        
        if (medio.getCategoria() != null) {
            // Guardamos el nombre para mostrarlo en pantalla
            dto.setCategoria(medio.getCategoria().getNombre());
            
            // Usamos el ID numérico para evaluar qué ficha cargar (Es 100% seguro)
            Integer catId = medio.getCategoria().getId();
            
            // 1. MÓDULO AÉREO
            if (catId >= 1 && catId <= 5) {
                EspecificacionesAereo specsEntity = em.find(EspecificacionesAereo.class, id);
                
                if (specsEntity != null) {
                    MedioDetalleDTO.EspecificacionesAereoDTO specsDTO = new MedioDetalleDTO.EspecificacionesAereoDTO();
                    specsDTO.setVelocidadMaxMach(specsEntity.getVelocidadMaxMach());
                    specsDTO.setTechoServicioPies(specsEntity.getTechoServicioPies());
                    specsDTO.setRadioCombateMillas(specsEntity.getRadioCombateMillas());
                    specsDTO.setCargaGMaxima(specsEntity.getCargaGMaxima());
                    specsDTO.setPesoMaxDespegueLb(specsEntity.getPesoMaxDespegueLb());
                    specsDTO.setRcsM2(specsEntity.getRcsM2());
                    specsDTO.setEnvergaduraPies(specsEntity.getEnvergaduraPies());
                    specsDTO.setLongitudPies(specsEntity.getLongitudPies());
                    dto.setEspecificacionesAereo(specsDTO);
                }
                
            // 2. MÓDULO RADAR
            } else if (catId >= 90 && catId <= 94 && catId != 92 && catId != 93) {
                EspecificacionesRadar specsEntity = em.find(EspecificacionesRadar.class, id);

                if (specsEntity != null) {
                    MedioDetalleDTO.EspecificacionesRadarDTO specsDTO = new MedioDetalleDTO.EspecificacionesRadarDTO();
                    specsDTO.setBandaFrecuencia(specsEntity.getBandaFrecuencia());
                    specsDTO.setAlcanceDeteccionKm(specsEntity.getAlcanceDeteccionKm());
                    specsDTO.setTipoAntena(specsEntity.getTipoAntena());
                    specsDTO.setResolucionDistanciaM(specsEntity.getResolucionDistanciaM());
                    specsDTO.setPotenciaPicoKw(specsEntity.getPotenciaPicoKw());
                    dto.setEspecificacionesRadar(specsDTO);
                }
                
            // 3. MÓDULO EW
            } else if (catId >= 101 && catId <= 103) {
                EspecificacionesEw specsEntity = em.find(EspecificacionesEw.class, id);

                if (specsEntity != null) {
                    MedioDetalleDTO.EspecificacionesEwDTO specsDTO = new MedioDetalleDTO.EspecificacionesEwDTO();
                    specsDTO.setRangoFrecuenciaMinMhz(specsEntity.getRangoFrecuenciaMinMhz());
                    specsDTO.setRangoFrecuenciaMaxMhz(specsEntity.getRangoFrecuenciaMaxMhz());
                    specsDTO.setModosOperacion(specsEntity.getModosOperacion());
                    specsDTO.setPotenciaEmisionErpKw(specsEntity.getPotenciaEmisionErpKw());
                    specsDTO.setCapacidadDrfm(specsEntity.isCapacidadDrfm());
                    specsDTO.setTecnicasJamming(specsEntity.getTecnicasJamming());
                    specsDTO.setNumeroObjetivosSimultaneos(specsEntity.getNumeroObjetivosSimultaneos());
                    dto.setEspecificacionesEw(specsDTO);
                }
                
            // 4. MÓDULO ARMAMENTO
            } else if (catId >= 201 && catId <= 204) {
                EspecificacionesArmamento specsEntity = em.find(EspecificacionesArmamento.class, id);

                if (specsEntity != null) {
                    MedioDetalleDTO.EspecificacionesArmamentoDTO specsDTO = new MedioDetalleDTO.EspecificacionesArmamentoDTO();
                    specsDTO.setTipoGuia(specsEntity.getTipoGuia());
                    specsDTO.setTipoObjetivo(specsEntity.getTipoObjetivo());
                    specsDTO.setAlcanceMaxKm(specsEntity.getAlcanceMaxKm());
                    specsDTO.setVelocidadMaxMach(specsEntity.getVelocidadMaxMach());
                    specsDTO.setPesoOjivaKg(specsEntity.getPesoOjivaKg());
                    specsDTO.setTecnologiaBuscador(specsEntity.getTecnologiaBuscador());
                    dto.setEspecificacionesArmamento(specsDTO);
                }
            }
        } 
        return dto;
    } 

    @Transactional
    public void guardarMedioCompleto(Integer id, String nombre, Integer categoriaId,    
                                String modelo, String imagenUrl, Integer añoIntro, Double costoAdq, Integer costoOp, String descripcion,
                                Double velocidadMaxMach, Integer techoServicioPies, Integer radioCombateMillas, Double cargaGMaxima, Double pesoMaxDespegueLb, Double rcsM2, Double envPies, Double lonPies,
                                String bandaFrecuencia, Double alcanceDeteccionKm, Double potenciaPicoKw, String tipoAntena, Double resolucionDist,
                                Double rangoFrecuenciaMinMhz, Double rangoFrecuenciaMaxMhz, String modosOperacion, Double potErp, String tecnicasJamming, Integer numObjs, Boolean capacidadDrfm,
                                String tipoGuia, String tipoObjetivo, String tecnologiaBuscador, Double alcanceMaxKm, Double velArm, Double pesoOjivaKg) {
    
    Medio medio;
    boolean esNuevoMedio = (id == null || id == 0);
    
    if (esNuevoMedio) {
        medio = new Medio();
    } else {
        medio = em.find(Medio.class, id);
    }
    
    // Asignación de datos generales
    medio.setNombre(nombre);
    medio.setModelo(modelo);
    medio.setImagenUrl(imagenUrl);
    medio.setAñoIntroduccion(añoIntro);
    medio.setCostoAdquisicionMUsd(costoAdq != null ? BigDecimal.valueOf(costoAdq) : null);
    medio.setCostoOperativoHoraUsd(costoOp);
    medio.setDescripcion(descripcion);
    
    if (categoriaId != null) {
        Categoria cat = em.find(Categoria.class, categoriaId);
        medio.setCategoria(cat);
    }

    if (esNuevoMedio) em.persist(medio); else em.merge(medio);
    
    if (categoriaId != null) {
        // AÉREO
        if (categoriaId >= 1 && categoriaId <= 5) { 
            EspecificacionesAereo aereo = em.find(EspecificacionesAereo.class, medio.getId());
            boolean esNuevaFicha = (aereo == null);
            if (esNuevaFicha) { aereo = new EspecificacionesAereo(); aereo.setMedio(medio); }
            
            aereo.setVelocidadMaxMach(velocidadMaxMach != null ? BigDecimal.valueOf(velocidadMaxMach) : null);
            aereo.setTechoServicioPies(techoServicioPies);
            aereo.setRadioCombateMillas(radioCombateMillas);
            aereo.setCargaGMaxima(cargaGMaxima != null ? BigDecimal.valueOf(cargaGMaxima) : null);
            aereo.setPesoMaxDespegueLb(pesoMaxDespegueLb != null ? pesoMaxDespegueLb.intValue() : null);
            aereo.setRcsM2(rcsM2 != null ? BigDecimal.valueOf(rcsM2) : null);
            aereo.setEnvergaduraPies(envPies != null ? BigDecimal.valueOf(envPies) : null);
            aereo.setLongitudPies(lonPies != null ? BigDecimal.valueOf(lonPies) : null);
            
            if (esNuevaFicha) em.persist(aereo); else em.merge(aereo);
            
        // RADAR
        } else if (categoriaId >= 90 && categoriaId <= 94 && categoriaId != 92 && categoriaId != 93) {
            EspecificacionesRadar radar = em.find(EspecificacionesRadar.class, medio.getId());
            boolean esNuevaFicha = (radar == null);
            if (esNuevaFicha) { radar = new EspecificacionesRadar(); radar.setMedio(medio); }
            
            radar.setBandaFrecuencia(bandaFrecuencia);
            radar.setAlcanceDeteccionKm(alcanceDeteccionKm != null ? alcanceDeteccionKm.intValue() : null);
            radar.setPotenciaPicoKw(potenciaPicoKw != null ? BigDecimal.valueOf(potenciaPicoKw) : null);
            radar.setTipoAntena(tipoAntena);
            radar.setResolucionDistanciaM(resolucionDist != null ? BigDecimal.valueOf(resolucionDist) : null);
            
            if (esNuevaFicha) em.persist(radar); else em.merge(radar);
            
        // EW
        } else if (categoriaId >= 101 && categoriaId <= 103) {
            EspecificacionesEw ew = em.find(EspecificacionesEw.class, medio.getId());
            boolean esNuevaFicha = (ew == null);
            if (esNuevaFicha) { ew = new EspecificacionesEw(); ew.setMedio(medio); }
            
            ew.setRangoFrecuenciaMinMhz(rangoFrecuenciaMinMhz != null ? BigDecimal.valueOf(rangoFrecuenciaMinMhz) : null);
            ew.setRangoFrecuenciaMaxMhz(rangoFrecuenciaMaxMhz != null ? BigDecimal.valueOf(rangoFrecuenciaMaxMhz) : null);
            ew.setModosOperacion(modosOperacion);
            ew.setPotenciaEmisionErpKw(potErp != null ? BigDecimal.valueOf(potErp) : null);
            ew.setTecnicasJamming(tecnicasJamming);
            ew.setNumeroObjetivosSimultaneos(numObjs);
            ew.setCapacidadDrfm(capacidadDrfm != null ? capacidadDrfm : false);
            
            if (esNuevaFicha) em.persist(ew); else em.merge(ew);
            
        // ARMAMENTO
        } else if (categoriaId >= 201 && categoriaId <= 204) {
            EspecificacionesArmamento armamento = em.find(EspecificacionesArmamento.class, medio.getId());
            boolean esNuevaFicha = (armamento == null);
            if (esNuevaFicha) { armamento = new EspecificacionesArmamento(); armamento.setMedio(medio); }
            
            armamento.setTipoGuia(tipoGuia);
            armamento.setTipoObjetivo(tipoObjetivo);
            armamento.setTecnologiaBuscador(tecnologiaBuscador);
            armamento.setAlcanceMaxKm(alcanceMaxKm != null ? BigDecimal.valueOf(alcanceMaxKm) : null);
            armamento.setVelocidadMaxMach(velArm != null ? BigDecimal.valueOf(velArm) : null);
            armamento.setPesoOjivaKg(pesoOjivaKg != null ? BigDecimal.valueOf(pesoOjivaKg) : null);
            
            if (esNuevaFicha) em.persist(armamento); else em.merge(armamento);
        }
    }
}

    @Transactional
    public void eliminarMedio(Integer id) {
        Medio medioAEliminar = em.find(Medio.class, id);
        if (medioAEliminar != null) {
            em.remove(medioAEliminar);
        }
    }
}