package org.lito.jakarta.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "especificaciones_aereo")
public class EspecificacionesAereo {
    @Id
    @Column(name = "medio_id")
    private Integer id;

    @OneToOne
    @MapsId                          // El PK de esta tabla ES la FK al padre
    @JoinColumn(name = "medio_id")
    private Medio medio;

    @Column(name = "velocidad_max_mach")
    private BigDecimal velocidadMaxMach;

    @Column(name = "techo_servicio_pies")
    private Integer techoServicioPies;

    @Column(name = "radio_combate_millas")
    private Integer radioCombateMillas;

    @Column(name = "carga_g_maxima")
    private BigDecimal cargaGMaxima;

    @Column(name = "peso_max_despegue_lb")
    private Integer pesoMaxDespegueLb;

    @Column(name = "rcs_m2")
    private BigDecimal rcsM2;

    @Column(name = "envergadura_pies")
    private BigDecimal envergaduraPies;

    @Column(name = "longitud_pies")
    private BigDecimal longitudPies;

    public EspecificacionesAereo(Integer id, Medio medio, BigDecimal velocidadMaxMach, Integer techoServicioPies,
            Integer radioCombateMillas, BigDecimal cargaGMaxima, Integer pesoMaxDespegueLb, BigDecimal rcsM2,
            BigDecimal envergaduraPies, BigDecimal longitudPies) {
        this.id = id;
        this.medio = medio;
        this.velocidadMaxMach = velocidadMaxMach;
        this.techoServicioPies = techoServicioPies;
        this.radioCombateMillas = radioCombateMillas;
        this.cargaGMaxima = cargaGMaxima;
        this.pesoMaxDespegueLb = pesoMaxDespegueLb;
        this.rcsM2 = rcsM2;
        this.envergaduraPies = envergaduraPies;
        this.longitudPies = longitudPies;
    }

    public EspecificacionesAereo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medio getMedio() {
        return medio;
    }

    public void setMedio(Medio medio) {
        this.medio = medio;
    }

    public BigDecimal getVelocidadMaxMach() {
        return velocidadMaxMach;
    }

    public void setVelocidadMaxMach(BigDecimal velocidadMaxMach) {
        this.velocidadMaxMach = velocidadMaxMach;
    }

    public Integer getTechoServicioPies() {
        return techoServicioPies;
    }

    public void setTechoServicioPies(Integer techoServicioPies) {
        this.techoServicioPies = techoServicioPies;
    }

    public Integer getRadioCombateMillas() {
        return radioCombateMillas;
    }

    public void setRadioCombateMillas(Integer radioCombateMillas) {
        this.radioCombateMillas = radioCombateMillas;
    }

    public BigDecimal getCargaGMaxima() {
        return cargaGMaxima;
    }

    public void setCargaGMaxima(BigDecimal cargaGMaxima) {
        this.cargaGMaxima = cargaGMaxima;
    }

    public Integer getPesoMaxDespegueLb() {
        return pesoMaxDespegueLb;
    }

    public void setPesoMaxDespegueLb(Integer pesoMaxDespegueLb) {
        this.pesoMaxDespegueLb = pesoMaxDespegueLb;
    }

    public BigDecimal getRcsM2() {
        return rcsM2;
    }

    public void setRcsM2(BigDecimal rcsM2) {
        this.rcsM2 = rcsM2;
    }

    public BigDecimal getEnvergaduraPies() {
        return envergaduraPies;
    }

    public void setEnvergaduraPies(BigDecimal envergaduraPies) {
        this.envergaduraPies = envergaduraPies;
    }

    public BigDecimal getLongitudPies() {
        return longitudPies;
    }

    public void setLongitudPies(BigDecimal longitudPies) {
        this.longitudPies = longitudPies;
    }
    
    // getters/setter
    // 
   
}