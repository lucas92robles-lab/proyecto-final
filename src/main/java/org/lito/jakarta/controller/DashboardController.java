package org.lito.jakarta.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models; // <-- Importante
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.lito.jakarta.service.DashboardService;

@Controller
@Path("/dashboard")
public class DashboardController {

    @Inject
    private DashboardService dashboardService;

    @Inject
    private Models models; // <-- Inyectamos Models de Jakarta MVC

    @GET
    @Path("/")
    public String verDashboard() {
        
        // Cargamos las variables directamente en el contenedor de modelos de MVC
        models.put("totalMedios", dashboardService.contarMedios());
        models.put("totalAeronaves", dashboardService.contarAeronaves());
        models.put("totalRadares", dashboardService.contarRadares());
        models.put("totalEw", dashboardService.contarEw());
        models.put("totalArmamento", dashboardService.contarArmamento());
        models.put("ultimoMedio", dashboardService.obtenerUltimoMedio());

        return "dashboard.jsp"; 
    }
}