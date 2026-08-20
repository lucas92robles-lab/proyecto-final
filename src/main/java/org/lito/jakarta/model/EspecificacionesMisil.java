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
@Table(name = "especificaciones_misil")
public class EspecificacionesMisil {
    @Id
    @Column(name = "medio_id")
    private Integer id;

    @OneToOne
    @MapsId                          // El PK de esta tabla ES la FK al padre
    
    @JoinColumn(name = "medio_id")
    private Medio medio;

    @Column(name = "tipo_guia")
    private String tipoGuia;

    @Column(name = "tipo_objetivo")
    private String tipoObjetivo;

    @Column(name = "alcance_max_km")
    private BigDecimal alcanceMaxKm;

    @Column(name = "velocidad_max_mach")
    private BigDecimal velocidadMaxMach;

    @Column(name = "peso_ojiva_kg")
    private BigDecimal pesoOjivaKg;

    @Column(name = "tecnologia_buscador")
    private String tecnologiaBuscador;

    public EspecificacionesMisil(Integer id, Medio medio, String tipoGuia, String tipoObjetivo, BigDecimal alcanceMaxKm,
            BigDecimal velocidadMaxMach, BigDecimal pesoOjivaKg, String tecnologiaBuscador) {
        this.id = id;
        this.medio = medio;
        this.tipoGuia = tipoGuia;
        this.tipoObjetivo = tipoObjetivo;
        this.alcanceMaxKm = alcanceMaxKm;
        this.velocidadMaxMach = velocidadMaxMach;
        this.pesoOjivaKg = pesoOjivaKg;
        this.tecnologiaBuscador = tecnologiaBuscador;
    }

    public EspecificacionesMisil() {
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

    public String getTipoGuia() {
        return tipoGuia;
    }

    public void setTipoGuia(String tipoGuia) {
        this.tipoGuia = tipoGuia;
    }

    public String getTipoObjetivo() {
        return tipoObjetivo;
    }

    public void setTipoObjetivo(String tipoObjetivo) {
        this.tipoObjetivo = tipoObjetivo;
    }

    public BigDecimal getAlcanceMaxKm() {
        return alcanceMaxKm;
    }

    public void setAlcanceMaxKm(BigDecimal alcanceMaxKm) {
        this.alcanceMaxKm = alcanceMaxKm;
    }

    public BigDecimal getVelocidadMaxMach() {
        return velocidadMaxMach;
    }

    public void setVelocidadMaxMach(BigDecimal velocidadMaxMach) {
        this.velocidadMaxMach = velocidadMaxMach;
    }

    public BigDecimal getPesoOjivaKg() {
        return pesoOjivaKg;
    }

    public void setPesoOjivaKg(BigDecimal pesoOjivaKg) {
        this.pesoOjivaKg = pesoOjivaKg;
    }

    public String getTecnologiaBuscador() {
        return tecnologiaBuscador;
    }

    public void setTecnologiaBuscador(String tecnologiaBuscador) {
        this.tecnologiaBuscador = tecnologiaBuscador;
    }

}