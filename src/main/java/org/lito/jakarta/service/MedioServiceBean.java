package org.lito.jakarta.service;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.lito.jakarta.exception.MedioNotFoundException;
import org.lito.jakarta.dto.MedioCreateDTO;
import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;
import org.lito.jakarta.model.Categoria;
import org.lito.jakarta.model.Fabricante;
import org.lito.jakarta.model.Medio;
import org.lito.jakarta.model.Pais;
import java.util.List;
import java.util.Optional;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MedioServiceBean implements MedioService {

    @PersistenceContext(unitName = "TacticaPU")
    private EntityManager em;

    // ─── MÉTODOS PÚBLICOS (la API del service) ────────────────────────────────

    @Override
    public List<MedioListDTO> findAll() {
        return em.createQuery(
                "SELECT m FROM Medio m " +
                "JOIN FETCH m.fabricante " +
                "JOIN FETCH m.categoria " +
                "JOIN FETCH m.paisOrigen", Medio.class)
                .getResultList()
                .stream()
                .map(this::toListDTO)
                .toList();
    }

    @Override
    public Optional<MedioDetalleDTO> findById(Integer id) {
        return Optional.ofNullable(em.find(Medio.class, id))
                       .map(this::toDetalleDTO);
    }

    @Override
    public List<MedioListDTO> findByCategoria(Integer categoriaId) {
        return em.createQuery(
                "SELECT m FROM Medio m " +
                "JOIN FETCH m.fabricante " +
                "JOIN FETCH m.categoria " +
                "JOIN FETCH m.paisOrigen " +
                "WHERE m.categoria.id = :catId", Medio.class)
                .setParameter("catId", categoriaId)
                .getResultList()
                .stream()
                .map(this::toListDTO)
                .toList();
    }

    @Override
    public MedioDetalleDTO create(MedioCreateDTO dto) {
        Medio medio = fromCreateDTO(dto);
        em.persist(medio);
        em.flush();
        return toDetalleDTO(medio);
    }

    @Override
    public MedioDetalleDTO update(Integer id, MedioCreateDTO dto) {
        if (em.find(Medio.class, id) == null)
            throw new MedioNotFoundException("Medio con id " + id + " no encontrado");
        Medio medio = fromCreateDTO(dto);
        medio.setId(id);
        return toDetalleDTO(em.merge(medio));
    }

    @Override
    public void delete(Integer id) {
        Medio medio = em.find(Medio.class, id);
        if (medio == null)
            throw new MedioNotFoundException("Medio con id " + id + " no encontrado");
        em.remove(medio);
    }

    // ─── MAPPERS PRIVADOS (conversión entity ↔ DTO) ──────────────────────────

    private MedioListDTO toListDTO(Medio m) {
        return new MedioListDTO(
            m.getId(),
            m.getNombre(),
            m.getModelo(),
            m.getCategoria()  != null ? m.getCategoria().getNombre()  : null,
            m.getPaisOrigen() != null ? m.getPaisOrigen().getNombre() : null,
            m.getFabricante() != null ? m.getFabricante().getNombre() : null,
            m.getImagenUrl(),
            m.getAñoIntroduccion()
        );
    }

    private MedioDetalleDTO toDetalleDTO(Medio m) {
        MedioDetalleDTO dto = new MedioDetalleDTO();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setModelo(m.getModelo());
        dto.setCategoria(m.getCategoria()   != null ? m.getCategoria().getNombre()  : null);
        dto.setPaisOrigen(m.getPaisOrigen() != null ? m.getPaisOrigen().getNombre() : null);
        dto.setFabricante(m.getFabricante() != null ? m.getFabricante().getNombre() : null);
        dto.setImagenUrl(m.getImagenUrl());
        dto.setAñoIntroduccion(m.getAñoIntroduccion());
        dto.setCostoAdquisicionMUsd(m.getCostoAdquisicionMUsd());
        dto.setCostoOperativoHoraUsd(m.getCostoOperativoHoraUsd());
        dto.setVidaUtilHoras(m.getVidaUtilHoras());
        dto.setTripulacionDotacion(m.getTripulacionDotacion());
        dto.setInventarioEstimado(m.getInventarioEstimado());
        dto.setCapacidadProduccionAnual(m.getCapacidadProduccionAnual());
        dto.setDescripcion(m.getDescripcion());

        if (m.getEspecificacionesAereo() != null) {
            MedioDetalleDTO.EspecificacionesAereoDTO ea = new MedioDetalleDTO.EspecificacionesAereoDTO();
            ea.setVelocidadMaxMach(m.getEspecificacionesAereo().getVelocidadMaxMach());
            ea.setTechoServicioPies(m.getEspecificacionesAereo().getTechoServicioPies());
            ea.setRadioCombateMillas(m.getEspecificacionesAereo().getRadioCombateMillas());
            ea.setCargaGMaxima(m.getEspecificacionesAereo().getCargaGMaxima());
            ea.setPesoMaxDespegueLb(m.getEspecificacionesAereo().getPesoMaxDespegueLb());
            ea.setRcsM2(m.getEspecificacionesAereo().getRcsM2());
            ea.setEnvergaduraPies(m.getEspecificacionesAereo().getEnvergaduraPies());
            ea.setLongitudPies(m.getEspecificacionesAereo().getLongitudPies());
            dto.setEspecificacionesAereo(ea);
        }

        if (m.getEspecificacionesEw() != null) {
            MedioDetalleDTO.EspecificacionesEwDTO ew = new MedioDetalleDTO.EspecificacionesEwDTO();
            ew.setRangoFrecuenciaMinMhz(m.getEspecificacionesEw().getRangoFrecuenciaMinMhz());
            ew.setRangoFrecuenciaMaxMhz(m.getEspecificacionesEw().getRangoFrecuenciaMaxMhz());
            ew.setModosOperacion(m.getEspecificacionesEw().getModosOperacion());
            ew.setPotenciaEmisionErpKw(m.getEspecificacionesEw().getPotenciaEmisionErpKw());
            ew.setCapacidadDrfm(m.getEspecificacionesEw().isCapacidadDrfm());
            ew.setTecnicasJamming(m.getEspecificacionesEw().getTecnicasJamming());
            ew.setNumeroObjetivosSimultaneos(m.getEspecificacionesEw().getNumeroObjetivosSimultaneos());
            dto.setEspecificacionesEw(ew);
        }

        if (m.getEspecificacionesRadar() != null) {
            MedioDetalleDTO.EspecificacionesRadarDTO er = new MedioDetalleDTO.EspecificacionesRadarDTO();
            er.setBandaFrecuencia(m.getEspecificacionesRadar().getBandaFrecuencia());
            er.setAlcanceDeteccionKm(m.getEspecificacionesRadar().getAlcanceDeteccionKm());
            er.setTipoAntena(m.getEspecificacionesRadar().getTipoAntena());
            er.setResolucionDistanciaM(m.getEspecificacionesRadar().getResolucionDistanciaM());
            er.setPotenciaPicoKw(m.getEspecificacionesRadar().getPotenciaPicoKw());
            dto.setEspecificacionesRadar(er);
        }

        if (m.getEspecificacionesMisil() != null) {
            MedioDetalleDTO.EspecificacionesMisilDTO emi = new MedioDetalleDTO.EspecificacionesMisilDTO();
            emi.setTipoGuia(m.getEspecificacionesMisil().getTipoGuia());
            emi.setTipoObjetivo(m.getEspecificacionesMisil().getTipoObjetivo());
            emi.setAlcanceMaxKm(m.getEspecificacionesMisil().getAlcanceMaxKm());
            emi.setVelocidadMaxMach(m.getEspecificacionesMisil().getVelocidadMaxMach());
            emi.setPesoOjivaKg(m.getEspecificacionesMisil().getPesoOjivaKg());
            emi.setTecnologiaBuscador(m.getEspecificacionesMisil().getTecnologiaBuscador());
            dto.setEspecificacionesMisil(emi);
        }

        return dto;
    }

    private Medio fromCreateDTO(MedioCreateDTO dto) {
        Medio m = new Medio();
        m.setNombre(dto.getNombre());
        m.setModelo(dto.getModelo());
        m.setAñoIntroduccion(dto.getAñoIntroduccion());
        m.setCostoAdquisicionMUsd(dto.getCostoAdquisicionMUsd());
        m.setCostoOperativoHoraUsd(dto.getCostoOperativoHoraUsd());
        m.setVidaUtilHoras(dto.getVidaUtilHoras());
        m.setTripulacionDotacion(dto.getTripulacionDotacion());
        m.setInventarioEstimado(dto.getInventarioEstimado());
        m.setCapacidadProduccionAnual(dto.getCapacidadProduccionAnual());
        m.setDescripcion(dto.getDescripcion());
        m.setImagenUrl(dto.getImagenUrl());

        if (dto.getCategoriaId()  != null)
            m.setCategoria(em.find(Categoria.class, dto.getCategoriaId()));
        if (dto.getFabricanteId() != null)
            m.setFabricante(em.find(Fabricante.class, dto.getFabricanteId()));
        if (dto.getPaisOrigenId() != null)
            m.setPaisOrigen(em.find(Pais.class, dto.getPaisOrigenId()));

        return m;
    }
}