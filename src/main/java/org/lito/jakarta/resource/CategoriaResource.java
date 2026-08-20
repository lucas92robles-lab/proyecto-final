package org.lito.jakarta.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lito.jakarta.service.CatalogoServiceBean;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CategoriaResource {

    @Inject
    private CatalogoServiceBean service;

    @GET
    public Response getAll() {
        return Response.ok(service.findAllCategorias()).build();
    }
}