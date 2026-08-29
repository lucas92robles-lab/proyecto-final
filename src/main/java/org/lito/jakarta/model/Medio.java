package org.lito.jakarta.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "medio")
public class Medio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "pais_origen_id")
    private Pais paisOrigen;

    @Column(name = "costo_adquisicion_m_usd")
    private BigDecimal costoAdquisicionMUsd;

    @Column(name = "costo_operativo_hora_usd")
    private Integer costoOperativoHoraUsd;

    @Column(name = "vida_util_horas")
    private Integer vidaUtilHoras;

    @Column(name = "año_introduccion")
    private Integer añoIntroduccion;

    @Column(name = "tripulacion_dotacion")
    private String tripulacionDotacion;

    @Column(name = "inventario_estimado")
    private String inventarioEstimado;

    @Column(name = "capacidad_produccion_anual")
    private String capacidadProduccionAnual;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    // Relaciones con especificaciones (opcionales, pueden ser null)
    @OneToOne(mappedBy = "medio", cascade = CascadeType.ALL, optional = true)
    private EspecificacionesAereo especificacionesAereo;

    @OneToOne(mappedBy = "medio", cascade = CascadeType.ALL, optional = true)
    private EspecificacionesEw especificacionesEw;

    @OneToOne(mappedBy = "medio", cascade = CascadeType.ALL, optional = true)
    private EspecificacionesRadar especificacionesRadar;

    @OneToOne(mappedBy = "medio", cascade = CascadeType.ALL, optional = true)
    private EspecificacionesArmamento especificacionesMisil;

    @OneToMany(mappedBy = "plataforma")
    private List<IntegracionArmamento> armasIntegradas;  // misiles que puede usar

    @OneToMany(mappedBy = "misil")
    private List<IntegracionArmamento> plataformasCompatibles;  // plataformas que lo usan

    @OneToMany(mappedBy = "medio")
    private List<OperadoresMedios> operadores;  // países que lo operan

   
    public Medio(Integer id, String nombre, String modelo, Fabricante fabricante, Categoria categoria, Pais paisOrigen,
            BigDecimal costoAdquisicionMUsd, Integer costoOperativoHoraUsd, Integer vidaUtilHoras,
            Integer añoIntroduccion, String tripulacionDotacion, String inventarioEstimado,
            String capacidadProduccionAnual, String descripcion, String imagenUrl,
            EspecificacionesAereo especificacionesAereo, EspecificacionesEw especificacionesEw,
            EspecificacionesRadar especificacionesRadar, EspecificacionesArmamento especificacionesMisil,
            List<IntegracionArmamento> armasIntegradas, List<IntegracionArmamento> plataformasCompatibles,
            List<OperadoresMedios> operadores) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.categoria = categoria;
        this.paisOrigen = paisOrigen;
        this.costoAdquisicionMUsd = costoAdquisicionMUsd;
        this.costoOperativoHoraUsd = costoOperativoHoraUsd;
        this.vidaUtilHoras = vidaUtilHoras;
        this.añoIntroduccion = añoIntroduccion;
        this.tripulacionDotacion = tripulacionDotacion;
        this.inventarioEstimado = inventarioEstimado;
        this.capacidadProduccionAnual = capacidadProduccionAnual;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.especificacionesAereo = especificacionesAereo;
        this.especificacionesEw = especificacionesEw;
        this.especificacionesRadar = especificacionesRadar;
        this.especificacionesMisil = especificacionesMisil;
        this.armasIntegradas = armasIntegradas;
        this.plataformasCompatibles = plataformasCompatibles;
        this.operadores = operadores;
    }

    public Medio() {
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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
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

    public EspecificacionesAereo getEspecificacionesAereo() {
        return especificacionesAereo;
    }

    public void setEspecificacionesAereo(EspecificacionesAereo especificacionesAereo) {
        this.especificacionesAereo = especificacionesAereo;
    }

    public EspecificacionesEw getEspecificacionesEw() {
        return especificacionesEw;
    }

    public void setEspecificacionesEw(EspecificacionesEw especificacionesEw) {
        this.especificacionesEw = especificacionesEw;
    }

    public EspecificacionesRadar getEspecificacionesRadar() {
        return especificacionesRadar;
    }

    public void setEspecificacionesRadar(EspecificacionesRadar especificacionesRadar) {
        this.especificacionesRadar = especificacionesRadar;
    }

    public EspecificacionesArmamento getEspecificacionesMisil() {
        return especificacionesMisil;
    }

    public void setEspecificacionesMisil(EspecificacionesArmamento especificacionesMisil) {
        this.especificacionesMisil = especificacionesMisil;
    }

    public List<IntegracionArmamento> getArmasIntegradas() {
        return armasIntegradas;
    }

    public void setArmasIntegradas(List<IntegracionArmamento> armasIntegradas) {
        this.armasIntegradas = armasIntegradas;
    }

    public List<IntegracionArmamento> getPlataformasCompatibles() {
        return plataformasCompatibles;
    }

    public void setPlataformasCompatibles(List<IntegracionArmamento> plataformasCompatibles) {
        this.plataformasCompatibles = plataformasCompatibles;
    }

    public List<OperadoresMedios> getOperadores() {
        return operadores;
    }

    public void setOperadores(List<OperadoresMedios> operadores) {
        this.operadores = operadores;
    }
    

}