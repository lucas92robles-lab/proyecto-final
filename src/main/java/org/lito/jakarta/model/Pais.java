package org.lito.jakarta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    private Integer id;

    @Column(unique = true)
    private String nombre;

    private String continente;

    public Pais() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContinente() { return continente; }
    public void setContinente(String continente) { this.continente = continente; }
}