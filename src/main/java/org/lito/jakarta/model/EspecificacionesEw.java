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
@Table(name = "especificaciones_ew")
public class EspecificacionesEw {
    @Id
    @Column(name = "medio_id")
    private Integer id;

    @OneToOne
    @MapsId                          // El PK de esta tabla ES la FK al padre
    
    @JoinColumn(name = "medio_id")
    private Medio medio;

    /* Aquí irían los atributos específicos de guerra electrónica, por ejemplo:
      */

    @Column(name = "rango_frecuencia_min_mhz")
    private BigDecimal rangoFrecuenciaMinMhz;

    @Column(name = "rango_frecuencia_max_mhz")
    private BigDecimal rangoFrecuenciaMaxMhz;

    @Column(name = "modos_operacion")
    private String modosOperacion;

    @Column(name = "potencia_emision_erp_kw")
    private BigDecimal potenciaEmisionErpKw;

    @Column(name = "capacidad_drfm")
    private boolean capacidadDrfm;

    @Column(name = "tecnicas_jamming")
    private String tecnicasJamming;

    @Column(name = "numero_objetivos_simultaneos")
    private Integer numeroObjetivosSimultaneos;

    public EspecificacionesEw(Integer id, Medio medio, BigDecimal rangoFrecuenciaMinMhz,
            BigDecimal rangoFrecuenciaMaxMhz, String modosOperacion, BigDecimal potenciaEmisionErpKw,
            boolean capacidadDrfm, String tecnicasJamming, Integer numeroObjetivosSimultaneos) {
        this.id = id;
        this.medio = medio;
        this.rangoFrecuenciaMinMhz = rangoFrecuenciaMinMhz;
        this.rangoFrecuenciaMaxMhz = rangoFrecuenciaMaxMhz;
        this.modosOperacion = modosOperacion;
        this.potenciaEmisionErpKw = potenciaEmisionErpKw;
        this.capacidadDrfm = capacidadDrfm;
        this.tecnicasJamming = tecnicasJamming;
        this.numeroObjetivosSimultaneos = numeroObjetivosSimultaneos;
    }

    public EspecificacionesEw() {
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

    public BigDecimal getRangoFrecuenciaMinMhz() {
        return rangoFrecuenciaMinMhz;
    }

    public void setRangoFrecuenciaMinMhz(BigDecimal rangoFrecuenciaMinMhz) {
        this.rangoFrecuenciaMinMhz = rangoFrecuenciaMinMhz;
    }

    public BigDecimal getRangoFrecuenciaMaxMhz() {
        return rangoFrecuenciaMaxMhz;
    }

    public void setRangoFrecuenciaMaxMhz(BigDecimal rangoFrecuenciaMaxMhz) {
        this.rangoFrecuenciaMaxMhz = rangoFrecuenciaMaxMhz;
    }

    public String getModosOperacion() {
        return modosOperacion;
    }

    public void setModosOperacion(String modosOperacion) {
        this.modosOperacion = modosOperacion;
    }

    public BigDecimal getPotenciaEmisionErpKw() {
        return potenciaEmisionErpKw;
    }

    public void setPotenciaEmisionErpKw(BigDecimal potenciaEmisionErpKw) {
        this.potenciaEmisionErpKw = potenciaEmisionErpKw;
    }

    public boolean isCapacidadDrfm() {
        return capacidadDrfm;
    }

    public void setCapacidadDrfm(boolean capacidadDrfm) {
        this.capacidadDrfm = capacidadDrfm;
    }

    public String getTecnicasJamming() {
        return tecnicasJamming;
    }

    public void setTecnicasJamming(String tecnicasJamming) {
        this.tecnicasJamming = tecnicasJamming;
    }

    public Integer getNumeroObjetivosSimultaneos() {
        return numeroObjetivosSimultaneos;
    }

    public void setNumeroObjetivosSimultaneos(Integer numeroObjetivosSimultaneos) {
        this.numeroObjetivosSimultaneos = numeroObjetivosSimultaneos;
    }

    
}