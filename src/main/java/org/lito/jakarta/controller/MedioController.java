package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

import org.lito.jakarta.dto.MedioDetalleDTO;
import org.lito.jakarta.dto.MedioListDTO;
import org.lito.jakarta.service.MedioService;
import org.lito.jakarta.session.UsuarioSession;

import jakarta.ws.rs.QueryParam;


import java.nio.charset.StandardCharsets;


@Path("/medios")
@Controller 
public class MedioController {

    @Inject
    private UsuarioSession usuarioSession;

    @Inject
    private Models models;

    @Inject
    private MedioService medioService;

    // --- MÓDULO AÉREO ---
    @GET
    @Path("/aereo")
    public String moduloAereo() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Aereo");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO AÉREO");
        return "categorias-aereas.jsp"; 
    }

    @GET
    @Path("/aereo/{subcategoria}")
    public String moduloAereoFiltrado(@PathParam("subcategoria") String subcategoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorFiltroEspecifico(subcategoria); 
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO AÉREO - " + subcategoria.toUpperCase());
        return "listado.jsp"; 
    }

    // --- MÓDULO RADAR ---
    @GET
    @Path("/radar")
    public String moduloRadar() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Radar");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO RADAR");
        return "categorias-radares.jsp"; 
    }

    @GET
    @Path("/radar/{subcategoria}")
    public String moduloRadarFiltrado(@PathParam("subcategoria") String subcategoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorFiltroEspecifico(subcategoria); 
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO RADAR - " + subcategoria.toUpperCase());
        return "listado.jsp"; 
    }

    // --- MÓDULO EW (Guerra Electrónica) ---
    @GET
    @Path("/ew")
    public String moduloEw() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("EW");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO EW (Guerra Electrónica)");
        return "categorias-ew.jsp"; 
    }

    @GET
    @Path("/ew/{subcategoria}")
    public String moduloEwFiltrado(@PathParam("subcategoria") String subcategoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorFiltroEspecifico(subcategoria); 
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO EW - " + subcategoria.toUpperCase());
        return "listado.jsp"; 
    }

    // --- MÓDULO ARMAMENTO ---
    @GET
    @Path("/armamento")
    public String moduloArmamento() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorCategoria("Armamento");
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO ARMAMENTO");
        return "categorias-armamento.jsp"; 
    }

    @GET
    @Path("/armamento/{subcategoria}")
    public String moduloArmamentoFiltrado(@PathParam("subcategoria") String subcategoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista = medioService.obtenerPorFiltroEspecifico(subcategoria); 
        models.put("medios", lista);
        models.put("tituloModulo", "MÓDULO ARMAMENTO - " + subcategoria.toUpperCase());
        return "listado.jsp"; 
    }

    // --- LISTADO ---
    @GET
    @Path("/listado")
    public String listado(@QueryParam("categoria") String categoria) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> lista;
        String titulo;

        if (categoria != null && !categoria.trim().isEmpty()) {
            lista = medioService.obtenerPorFiltroEspecifico(categoria); 
            titulo = "ORBAT - FILTRO: " + categoria.toUpperCase();
        } else {
            lista = medioService.obtenerTodos(); 
            titulo = "Listado completo de medios";
        }
        
        models.put("medios", lista);
        models.put("tituloModulo", titulo);
        return "listado.jsp"; 
    }

    // --- VER DETALLES del medio ---
    @GET
    @Path("/detalle-medio/{id}")
    public String verDetalles(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        MedioDetalleDTO detalle = medioService.obtenerPorId(id);
        
        if (detalle == null) return "redirect:medios/listado"; 
        
        models.put("medio", detalle);
        return "detalle-medio.jsp";
    }

    // nuevo medio (formulario)
    @GET
    @Path("/nuevo")
    public String nuevoMedio() {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        models.put("tituloFormulario", "NUEVO ACTIVO TÁCTICO");
        models.put("paises", medioService.obtenerTodosLosPaises());
        models.put("fabricantes", medioService.obtenerTodosLosFabricantes());
        return "formulario-medio.jsp";
    }

    // Mostrar formulario con datos (para editar un medio existente)
    @GET
    @Path("/editar/{id}")
    public String editarMedio(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        MedioDetalleDTO detalle = medioService.obtenerPorId(id);
        if (detalle == null) return "redirect:medios/listado"; 
        
        models.put("medio", detalle); 
        models.put("tituloFormulario", "EDITAR ACTIVO: " + detalle.getNombre());
        models.put("paises", medioService.obtenerTodosLosPaises());
        models.put("fabricantes", medioService.obtenerTodosLosFabricantes());
        return "formulario-medio.jsp";
    }

    // Recibir los datos del formulario
    @POST
    @Path("/guardar")
    //@Consumes(MediaType.MULTIPART_FORM_DATA)
    public String guardarMedio(
        
        @FormParam("id") String strId, 
        @FormParam("nombre") String nombre,
        @FormParam("categoriaId") String strCategoriaId,
        @FormParam("paisId") String strPaisId,
        @FormParam("fabricanteId") String strFabricanteId,

        @FormParam("modelo") String modelo,
        @FormParam("imagenUrl") String imagenUrl,
        @FormParam("imagenUrl2") String imagenUrl2,
        @FormParam("imagenUrl3") String imagenUrl3,
        @FormParam("añoIntroduccion") String strAno,
        @FormParam("costoAdquisicion") String strCostoAdq,
        @FormParam("costoOperativo") String strCostoOp,
        @FormParam("descripcion") String descripcion,
        
        @FormParam("velocidadMaxMach") String strVelocidad,
        @FormParam("techoServicioPies") String strTecho,
        @FormParam("radioCombateMillas") String strRadio,
        @FormParam("cargaGMaxima") String strCarga,
        @FormParam("pesoMaxDespegueLb") String strPeso,
        @FormParam("rcsM2") String strRcs,
        @FormParam("envergaduraPies") String strEnv,
        @FormParam("longitudPies") String strLon,

        @FormParam("bandaFrecuencia") String bandaFrecuencia,
        @FormParam("alcanceDeteccionKm") String strAlcanceDeteccion,
        @FormParam("potenciaPicoKw") String strPotencia,
        @FormParam("tipoAntena") String tipoAntena,
        @FormParam("resolucionDistanciaM") String strRes,

        @FormParam("rangoFrecuenciaMinMhz") String strRangoMin,
        @FormParam("rangoFrecuenciaMaxMhz") String strRangoMax,
        @FormParam("modosOperacion") String modosOperacion,
        @FormParam("potenciaEmisionErpKw") String strErp,
        @FormParam("tecnicasJamming") String tecnicasJamming,
        @FormParam("numeroObjetivosSimultaneos") String strObjs,
        @FormParam("capacidadDrfm") String strDrfm,

        @FormParam("tipoGuia") String tipoGuia,
        @FormParam("tipoObjetivo") String tipoObjetivo,
        @FormParam("tecnologiaBuscador") String tecnologiaBuscador,
        @FormParam("alcanceMaxKm") String strAlcanceArma,
        @FormParam("velocidadMaxMachArm") String strVelArm,
        @FormParam("pesoOjivaKg") String strOjiva
    ) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        //conversiones
        Integer id = (strId != null && !strId.isBlank()) ? Integer.valueOf(strId) : null;
        Integer categoriaId = (strCategoriaId != null && !strCategoriaId.isBlank()) ? Integer.valueOf(strCategoriaId) : null;
        Integer paisId = (strPaisId != null && !strPaisId.isBlank()) ? Integer.valueOf(strPaisId) : null;
        Integer fabricanteId = (strFabricanteId != null && !strFabricanteId.isBlank()) ? Integer.valueOf(strFabricanteId) : null;
        
        // Conversiones Base
        Integer anoIntro = (strAno != null && !strAno.isBlank()) ? Integer.valueOf(strAno) : null;
        Double costoAdq = (strCostoAdq != null && !strCostoAdq.isBlank()) ? Double.valueOf(strCostoAdq) : null;
        Integer costoOp = (strCostoOp != null && !strCostoOp.isBlank()) ? Integer.valueOf(strCostoOp) : null;

        // Conversiones Aéreo
        Double velocidadMaxMach = (strVelocidad != null && !strVelocidad.isBlank()) ? Double.valueOf(strVelocidad) : null;
        Integer techoServicioPies = (strTecho != null && !strTecho.isBlank()) ? Integer.valueOf(strTecho) : null;
        Integer radioCombateMillas = (strRadio != null && !strRadio.isBlank()) ? Integer.valueOf(strRadio) : null;
        Double cargaGMaxima = (strCarga != null && !strCarga.isBlank()) ? Double.valueOf(strCarga) : null;
        Double pesoMaxDespegueLb = (strPeso != null && !strPeso.isBlank()) ? Double.valueOf(strPeso) : null;
        Double rcsM2 = (strRcs != null && !strRcs.isBlank()) ? Double.valueOf(strRcs) : null;
        Double envPies = (strEnv != null && !strEnv.isBlank()) ? Double.valueOf(strEnv) : null;
        Double lonPies = (strLon != null && !strLon.isBlank()) ? Double.valueOf(strLon) : null;

        // Conversiones Radar
        Double alcanceDeteccionKm = (strAlcanceDeteccion != null && !strAlcanceDeteccion.isBlank()) ? Double.valueOf(strAlcanceDeteccion) : null;
        Double potenciaPicoKw = (strPotencia != null && !strPotencia.isBlank()) ? Double.valueOf(strPotencia) : null;
        Double resolucionDist = (strRes != null && !strRes.isBlank()) ? Double.valueOf(strRes) : null;

        // Conversiones EW
        Double rangoFrecuenciaMinMhz = (strRangoMin != null && !strRangoMin.isBlank()) ? Double.valueOf(strRangoMin) : null;
        Double rangoFrecuenciaMaxMhz = (strRangoMax != null && !strRangoMax.isBlank()) ? Double.valueOf(strRangoMax) : null;
        Double potErp = (strErp != null && !strErp.isBlank()) ? Double.valueOf(strErp) : null;
        Integer numObjs = (strObjs != null && !strObjs.isBlank()) ? Integer.valueOf(strObjs) : null;
        Boolean capacidadDrfm = (strDrfm != null) ? Boolean.valueOf(strDrfm) : false;

        // Conversiones Armamento
        Double alcanceMaxKm = (strAlcanceArma != null && !strAlcanceArma.isBlank()) ? Double.valueOf(strAlcanceArma) : null;
        Double velArm = (strVelArm != null && !strVelArm.isBlank()) ? Double.valueOf(strVelArm) : null;
        Double pesoOjivaKg = (strOjiva != null && !strOjiva.isBlank()) ? Double.valueOf(strOjiva) : null;

        // Corrección de codificación de acentos para campos de texto libre
        if (descripcion != null && !descripcion.isBlank()) {
            descripcion = new String(descripcion.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        if (modelo != null && !modelo.isBlank()) {
            modelo = new String(modelo.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        
            
    
        medioService.guardarMedioCompleto(id, nombre, categoriaId, paisId, fabricanteId,
                modelo, imagenUrl, imagenUrl2, imagenUrl3, anoIntro, costoAdq, costoOp, descripcion,
                velocidadMaxMach, techoServicioPies, radioCombateMillas, cargaGMaxima, pesoMaxDespegueLb, rcsM2, envPies, lonPies,
                bandaFrecuencia, alcanceDeteccionKm, potenciaPicoKw, tipoAntena, resolucionDist,
                rangoFrecuenciaMinMhz, rangoFrecuenciaMaxMhz, modosOperacion, potErp, tecnicasJamming, numObjs, capacidadDrfm,
                tipoGuia, tipoObjetivo, tecnologiaBuscador, alcanceMaxKm, velArm, pesoOjivaKg);
        
        return "redirect:medios/listado"; 
    }

    // 4. Eliminar
    @GET
    @Path("/eliminar/{id}")
    public String eliminarMedio(@PathParam("id") Integer id) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        String rolUsuario = usuarioSession.getUsuarioActual().getRol();
        if (!"ADMIN".equals(rolUsuario)) return "redirect:medios/listado"; 
        
        medioService.eliminarMedio(id);
        return "redirect:medios/listado?exito=eliminado"; 
    }

    @GET
    @Path("/buscar")
    public String buscarMedios(@QueryParam("q") String query) {
        if (!usuarioSession.isLogueado()) return "redirect:auth/login";
        
        List<MedioListDTO> resultados = medioService.buscarActivos(query);
        
        models.put("medios", resultados);
        models.put("terminoBusqueda", query);
        // Usamos tituloModulo para que listado.jsp lo muestre igual que en las categorías
        models.put("tituloModulo", "RESULTADOS DE BÚSQUEDA: " + query.toUpperCase()); 
        
        return "listado.jsp"; 
    }

    /*private String guardarImagen(String foto1Stream, String nombreBase) {
        if (foto1Stream == null) return null;
        
        try {
            if (foto1Stream.available() == 0) return null;

            String nombreUnico = "img_" + nombreBase.replaceAll("\\s+", "_") + "_" + UUID.randomUUID().toString().substring(0, 8) + ".jpg";
            
            // Usamos java.nio.file.Path de forma explícita para que no choque con @Path de Jakarta
            java.nio.file.Path directorioDestino = Paths.get("C:/SisCaM/imagenes/"); 
            
            if (!Files.exists(directorioDestino)) {
                Files.createDirectories(directorioDestino);
            }

            java.nio.file.Path rutaFinal = directorioDestino.resolve(nombreUnico);
            
            Files.copy(foto1Stream, rutaFinal, StandardCopyOption.REPLACE_EXISTING);

            return "/imagenes/" + nombreUnico; 
            
        } catch (Exception e) {
            System.out.println("Error al guardar la imagen: " + e.getMessage());
            return null;
        }
    }
        */
}
