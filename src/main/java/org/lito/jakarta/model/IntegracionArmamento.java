package org.lito.jakarta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "integracion_armamento")
public class IntegracionArmamento {

    @Id
    private Integer id;

   
    @ManyToOne
    @JoinColumn(name = "medio_plataforma_id")
    private Medio plataforma;

   
    @ManyToOne
    @JoinColumn(name = "medio_misil_id")
    private Medio misil;

    public IntegracionArmamento(Integer id, Medio plataforma, Medio misil) {
        this.id = id;
        this.plataforma = plataforma;
        this.misil = misil;
    }

    public IntegracionArmamento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Medio getPlataforma() { return plataforma; }
    public void setPlataforma(Medio plataforma) { this.plataforma = plataforma; }

    public Medio getMisil() { return misil; }
    public void setMisil(Medio misil) { this.misil = misil; }

    
}