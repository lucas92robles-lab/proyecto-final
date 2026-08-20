package org.lito.jakarta.dto;

public class MedioListDTO {
    private Integer id;
    private String nombre;
    private String modelo;
    private String categoria;
    private String paisOrigen;
    private String fabricante;
    private String imagenUrl;
    private Integer añoIntroduccion;

    public MedioListDTO() {}

    public MedioListDTO(Integer id, String nombre, String modelo,
                        String categoria, String paisOrigen,
                        String fabricante, String imagenUrl,
                        Integer añoIntroduccion) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.categoria = categoria;
        this.paisOrigen = paisOrigen;
        this.fabricante = fabricante;
        this.imagenUrl = imagenUrl;
        this.añoIntroduccion = añoIntroduccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getAñoIntroduccion() {
        return añoIntroduccion;
    }

    public void setAñoIntroduccion(Integer añoIntroduccion) {
        this.añoIntroduccion = añoIntroduccion;
    }

    
}