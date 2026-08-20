package org.lito.jakarta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "operadores_medios")
public class OperadoresMedios {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medio_id")
    private Medio medio;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    // Ej: "operador", "exportador", "en desarrollo", "retirado"
    @Column(name = "tipo_relacion")
    private String tipoRelacion;

    
    
    public OperadoresMedios(Integer id, Medio medio, Pais pais, String tipoRelacion) {
        this.id = id;
        this.medio = medio;
        this.pais = pais;
        this.tipoRelacion = tipoRelacion;
    }

    public OperadoresMedios() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Medio getMedio() { return medio; }
    public void setMedio(Medio medio) { this.medio = medio; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    public String getTipoRelacion() { return tipoRelacion; }
    public void setTipoRelacion(String tipoRelacion) { this.tipoRelacion = tipoRelacion; }
}