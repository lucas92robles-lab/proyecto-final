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
@Table(name = "especificaciones_radar")
public class EspecificacionesRadar {
    @Id
    @Column(name = "medio_id")
    private Integer id;

    @OneToOne
    @MapsId                          // El PK de esta tabla ES la FK al padre
    
    @JoinColumn(name = "medio_id")
    private Medio medio;

    @Column(name = "banda_frecuencia")
    private String bandaFrecuencia;

    @Column(name = "alcance_deteccion_km")
    private Integer alcanceDeteccionKm;

    @Column(name = "tipo_antena")
    private String tipoAntena;

    @Column(name = "resolucion_distancia_m")
    private BigDecimal resolucionDistanciaM;

    @Column(name = "potencia_pico_kw")
    private BigDecimal potenciaPicoKw;
    
    public EspecificacionesRadar(Integer id, Medio medio, String bandaFrecuencia, Integer alcanceDeteccionKm,
            String tipoAntena, BigDecimal resolucionDistanciaM, BigDecimal potenciaPicoKw) {
        this.id = id;
        this.medio = medio;
        this.bandaFrecuencia = bandaFrecuencia;
        this.alcanceDeteccionKm = alcanceDeteccionKm;
        this.tipoAntena = tipoAntena;
        this.resolucionDistanciaM = resolucionDistanciaM;
        this.potenciaPicoKw = potenciaPicoKw;
    }

    public EspecificacionesRadar() {
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

    public String getBandaFrecuencia() {
        return bandaFrecuencia;
    }

    public void setBandaFrecuencia(String bandaFrecuencia) {
        this.bandaFrecuencia = bandaFrecuencia;
    }

    public Integer getAlcanceDeteccionKm() {
        return alcanceDeteccionKm;
    }

    public void setAlcanceDeteccionKm(Integer alcanceDeteccionKm) {
        this.alcanceDeteccionKm = alcanceDeteccionKm;
    }

    public String getTipoAntena() {
        return tipoAntena;
    }

    public void setTipoAntena(String tipoAntena) {
        this.tipoAntena = tipoAntena;
    }

    public BigDecimal getResolucionDistanciaM() {
        return resolucionDistanciaM;
    }

    public void setResolucionDistanciaM(BigDecimal resolucionDistanciaM) {
        this.resolucionDistanciaM = resolucionDistanciaM;
    }

    public BigDecimal getPotenciaPicoKw() {
        return potenciaPicoKw;
    }

    public void setPotenciaPicoKw(BigDecimal potenciaPicoKw) {
        this.potenciaPicoKw = potenciaPicoKw;
    }

    

}