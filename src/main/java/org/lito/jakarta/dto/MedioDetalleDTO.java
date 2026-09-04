package org.lito.jakarta.dto;

import java.math.BigDecimal;

public class MedioDetalleDTO {
    

    private Integer id;
    private String nombre;
    private String modelo;
    private String categoria;
    private String paisOrigen;
    private String fabricante;
    private String imagenUrl;
    private Integer añoIntroduccion;
    private BigDecimal costoAdquisicionMUsd;
    private Integer costoOperativoHoraUsd;
    private Integer vidaUtilHoras;
    private String tripulacionDotacion;
    private String inventarioEstimado;
    private String capacidadProduccionAnual;
    private String descripcion;

    private EspecificacionesAereoDTO especificacionesAereo;
    private EspecificacionesEwDTO especificacionesEw;
    private EspecificacionesRadarDTO especificacionesRadar;
    private EspecificacionesArmamentoDTO especificacionesArmamento;

    public MedioDetalleDTO() {}

   
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public Integer getAñoIntroduccion() { return añoIntroduccion; }
    public void setAñoIntroduccion(Integer añoIntroduccion) { this.añoIntroduccion = añoIntroduccion; }

    public BigDecimal getCostoAdquisicionMUsd() { return costoAdquisicionMUsd; }
    public void setCostoAdquisicionMUsd(BigDecimal costoAdquisicionMUsd) { this.costoAdquisicionMUsd = costoAdquisicionMUsd; }

    public Integer getCostoOperativoHoraUsd() { return costoOperativoHoraUsd; }
    public void setCostoOperativoHoraUsd(Integer costoOperativoHoraUsd) { this.costoOperativoHoraUsd = costoOperativoHoraUsd; }

    public Integer getVidaUtilHoras() { return vidaUtilHoras; }
    public void setVidaUtilHoras(Integer vidaUtilHoras) { this.vidaUtilHoras = vidaUtilHoras; }

    public String getTripulacionDotacion() { return tripulacionDotacion; }
    public void setTripulacionDotacion(String tripulacionDotacion) { this.tripulacionDotacion = tripulacionDotacion; }

    public String getInventarioEstimado() { return inventarioEstimado; }
    public void setInventarioEstimado(String inventarioEstimado) { this.inventarioEstimado = inventarioEstimado; }

    public String getCapacidadProduccionAnual() { return capacidadProduccionAnual; }
    public void setCapacidadProduccionAnual(String capacidadProduccionAnual) { this.capacidadProduccionAnual = capacidadProduccionAnual; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // —— Getters y Setters de los DTOs internos ——

    public EspecificacionesAereoDTO getEspecificacionesAereo() { return especificacionesAereo; }
    public void setEspecificacionesAereo(EspecificacionesAereoDTO especificacionesAereo) { this.especificacionesAereo = especificacionesAereo; }

    public EspecificacionesEwDTO getEspecificacionesEw() { return especificacionesEw; }
    public void setEspecificacionesEw(EspecificacionesEwDTO especificacionesEw) { this.especificacionesEw = especificacionesEw; }

    public EspecificacionesRadarDTO getEspecificacionesRadar() { return especificacionesRadar; }
    public void setEspecificacionesRadar(EspecificacionesRadarDTO especificacionesRadar) { this.especificacionesRadar = especificacionesRadar; }

    public EspecificacionesArmamentoDTO getEspecificacionesArmamento() { return especificacionesArmamento; }
    public void setEspecificacionesArmamento(EspecificacionesArmamentoDTO especificacionesArmamento) { this.especificacionesArmamento = especificacionesArmamento; }


    // —— DTOs internos (clases estáticas anidadas) ——

    public static class EspecificacionesAereoDTO {
        private BigDecimal velocidadMaxMach;
        private Integer techoServicioPies;
        private Integer radioCombateMillas;
        private BigDecimal cargaGMaxima;
        private Integer pesoMaxDespegueLb;
        private BigDecimal rcsM2;
        private BigDecimal envergaduraPies;
        private BigDecimal longitudPies;
        
        public BigDecimal getVelocidadMaxMach() { return velocidadMaxMach; }
        public void setVelocidadMaxMach(BigDecimal velocidadMaxMach) { this.velocidadMaxMach = velocidadMaxMach; }
        public Integer getTechoServicioPies() { return techoServicioPies; }
        public void setTechoServicioPies(Integer techoServicioPies) { this.techoServicioPies = techoServicioPies; }
        public Integer getRadioCombateMillas() { return radioCombateMillas; }
        public void setRadioCombateMillas(Integer radioCombateMillas) { this.radioCombateMillas = radioCombateMillas; }
        public BigDecimal getCargaGMaxima() { return cargaGMaxima; }
        public void setCargaGMaxima(BigDecimal cargaGMaxima) { this.cargaGMaxima = cargaGMaxima; }
        public Integer getPesoMaxDespegueLb() { return pesoMaxDespegueLb; }
        public void setPesoMaxDespegueLb(Integer pesoMaxDespegueLb) { this.pesoMaxDespegueLb = pesoMaxDespegueLb; }
        public BigDecimal getRcsM2() { return rcsM2; }
        public void setRcsM2(BigDecimal rcsM2) { this.rcsM2 = rcsM2; }
        public BigDecimal getEnvergaduraPies() { return envergaduraPies; }
        public void setEnvergaduraPies(BigDecimal envergaduraPies) { this.envergaduraPies = envergaduraPies; }
        public BigDecimal getLongitudPies() { return longitudPies; }
        public void setLongitudPies(BigDecimal longitudPies) { this.longitudPies = longitudPies; }
    }

    public static class EspecificacionesEwDTO {
        private BigDecimal rangoFrecuenciaMinMhz;
        private BigDecimal rangoFrecuenciaMaxMhz;
        private String modosOperacion;
        private BigDecimal potenciaEmisionErpKw;
        private Boolean capacidadDrfm;
        private String tecnicasJamming;
        private Integer numeroObjetivosSimultaneos;
        
        public BigDecimal getRangoFrecuenciaMinMhz() { return rangoFrecuenciaMinMhz; }
        public void setRangoFrecuenciaMinMhz(BigDecimal rangoFrecuenciaMinMhz) { this.rangoFrecuenciaMinMhz = rangoFrecuenciaMinMhz; }
        public BigDecimal getRangoFrecuenciaMaxMhz() { return rangoFrecuenciaMaxMhz; }
        public void setRangoFrecuenciaMaxMhz(BigDecimal rangoFrecuenciaMaxMhz) { this.rangoFrecuenciaMaxMhz = rangoFrecuenciaMaxMhz; }
        public String getModosOperacion() { return modosOperacion; }
        public void setModosOperacion(String modosOperacion) { this.modosOperacion = modosOperacion; }
        public BigDecimal getPotenciaEmisionErpKw() { return potenciaEmisionErpKw; }
        public void setPotenciaEmisionErpKw(BigDecimal potenciaEmisionErpKw) { this.potenciaEmisionErpKw = potenciaEmisionErpKw; }
        public Boolean getCapacidadDrfm() { return capacidadDrfm; }
        public void setCapacidadDrfm(Boolean capacidadDrfm) { this.capacidadDrfm = capacidadDrfm; }
        public String getTecnicasJamming() { return tecnicasJamming; }
        public void setTecnicasJamming(String tecnicasJamming) { this.tecnicasJamming = tecnicasJamming; }
        public Integer getNumeroObjetivosSimultaneos() { return numeroObjetivosSimultaneos; }
        public void setNumeroObjetivosSimultaneos(Integer numeroObjetivosSimultaneos) { this.numeroObjetivosSimultaneos = numeroObjetivosSimultaneos; }
    }

    public static class EspecificacionesRadarDTO {
        private String bandaFrecuencia;
        private Integer alcanceDeteccionKm;
        private String tipoAntena;
        private BigDecimal resolucionDistanciaM;
        private BigDecimal potenciaPicoKw;
        
        public String getBandaFrecuencia() { return bandaFrecuencia; }
        public void setBandaFrecuencia(String bandaFrecuencia) { this.bandaFrecuencia = bandaFrecuencia; }
        public Integer getAlcanceDeteccionKm() { return alcanceDeteccionKm; }
        public void setAlcanceDeteccionKm(Integer alcanceDeteccionKm) { this.alcanceDeteccionKm = alcanceDeteccionKm; }
        public String getTipoAntena() { return tipoAntena; }
        public void setTipoAntena(String tipoAntena) { this.tipoAntena = tipoAntena; }
        public BigDecimal getResolucionDistanciaM() { return resolucionDistanciaM; }
        public void setResolucionDistanciaM(BigDecimal resolucionDistanciaM) { this.resolucionDistanciaM = resolucionDistanciaM; }
        public BigDecimal getPotenciaPicoKw() { return potenciaPicoKw; }
        public void setPotenciaPicoKw(BigDecimal potenciaPicoKw) { this.potenciaPicoKw = potenciaPicoKw; }
    }

    public static class EspecificacionesArmamentoDTO {
        private String tipoGuia;
        private String tipoObjetivo;
        private BigDecimal alcanceMaxKm;
        private BigDecimal velocidadMaxMach;
        private BigDecimal pesoOjivaKg;
        private String tecnologiaBuscador;
        
        public String getTipoGuia() { return tipoGuia; }
        public void setTipoGuia(String tipoGuia) { this.tipoGuia = tipoGuia; }
        public String getTipoObjetivo() { return tipoObjetivo; }
        public void setTipoObjetivo(String tipoObjetivo) { this.tipoObjetivo = tipoObjetivo; }
        public BigDecimal getAlcanceMaxKm() { return alcanceMaxKm; }
        public void setAlcanceMaxKm(BigDecimal alcanceMaxKm) { this.alcanceMaxKm = alcanceMaxKm; }
        public BigDecimal getVelocidadMaxMach() { return velocidadMaxMach; }
        public void setVelocidadMaxMach(BigDecimal velocidadMaxMach) { this.velocidadMaxMach = velocidadMaxMach; }
        public BigDecimal getPesoOjivaKg() { return pesoOjivaKg; }
        public void setPesoOjivaKg(BigDecimal pesoOjivaKg) { this.pesoOjivaKg = pesoOjivaKg; }
        public String getTecnologiaBuscador() { return tecnologiaBuscador; }
        public void setTecnologiaBuscador(String tecnologiaBuscador) { this.tecnologiaBuscador = tecnologiaBuscador; }
    }
}