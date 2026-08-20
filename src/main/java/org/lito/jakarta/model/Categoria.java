package org.lito.jakarta.model;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    private Integer id;
    private String nombre;
    
    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria() {
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
   
    
}