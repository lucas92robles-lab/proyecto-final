package org.lito.jakarta.dto;

import java.math.BigDecimal;

public class MedioCreateDTO {
    
    private String nombre;
    private String modelo;
    private Integer fabricanteId;
    private Integer categoriaId;
    private Integer paisOrigenId;
    private BigDecimal costoAdquisicionMUsd;
    private Integer costoOperativoHoraUsd;
    private Integer vidaUtilHoras;
    private Integer añoIntroduccion;
    private String tripulacionDotacion;
    private String inventarioEstimado;
    private String capacidadProduccionAnual;
    private String descripcion;
    private String imagenUrl;

    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public Integer getFabricanteId() {
        return fabricanteId;
    }
    public void setFabricanteId(Integer fabricanteId) {
        this.fabricanteId = fabricanteId;
    }
    public Integer getCategoriaId() {
        return categoriaId;
    }
    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }
    public Integer getPaisOrigenId() {
        return paisOrigenId;
    }
    public void setPaisOrigenId(Integer paisOrigenId) {
        this.paisOrigenId = paisOrigenId;
    }
    public BigDecimal getCostoAdquisicionMUsd() {
        return costoAdquisicionMUsd;
    }
    public void setCostoAdquisicionMUsd(BigDecimal costoAdquisicionMUsd) {
        this.costoAdquisicionMUsd = costoAdquisicionMUsd;
    }
    public Integer getCostoOperativoHoraUsd() {
        return costoOperativoHoraUsd;
    }
    public void setCostoOperativoHoraUsd(Integer costoOperativoHoraUsd) {
        this.costoOperativoHoraUsd = costoOperativoHoraUsd;
    }
    public Integer getVidaUtilHoras() {
        return vidaUtilHoras;
    }
    public void setVidaUtilHoras(Integer vidaUtilHoras) {
        this.vidaUtilHoras = vidaUtilHoras;
    }
    public Integer getAñoIntroduccion() {
        return añoIntroduccion;
    }
    public void setAñoIntroduccion(Integer añoIntroduccion) {
        this.añoIntroduccion = añoIntroduccion;
    }
    public String getTripulacionDotacion() {
        return tripulacionDotacion;
    }
    public void setTripulacionDotacion(String tripulacionDotacion) {
        this.tripulacionDotacion = tripulacionDotacion;
    }
    public String getInventarioEstimado() {
        return inventarioEstimado;
    }
    public void setInventarioEstimado(String inventarioEstimado) {
        this.inventarioEstimado = inventarioEstimado;
    }
    public String getCapacidadProduccionAnual() {
        return capacidadProduccionAnual;
    }
    public void setCapacidadProduccionAnual(String capacidadProduccionAnual) {
        this.capacidadProduccionAnual = capacidadProduccionAnual;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImagenUrl() {
        return imagenUrl;
    }
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    
}