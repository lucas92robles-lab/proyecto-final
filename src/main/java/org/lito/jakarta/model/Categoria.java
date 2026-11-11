package org.lito.jakarta.model;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    // Asegurate de tener la anotación @Column con el nombre exacto de la BD
    @Column(name = "bloque_principal")
    private String bloquePrincipal;

    // --- Getters y Setters ---
    
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

    public String getBloquePrincipal() {
        return bloquePrincipal;
    }

    public void setBloquePrincipal(String bloquePrincipal) {
        this.bloquePrincipal = bloquePrincipal;
    }
}