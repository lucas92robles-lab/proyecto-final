package org.lito.jakarta.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lito.jakarta.dto.MedioCreateDTO;
import org.lito.jakarta.service.MedioService;

@Path("/medios")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MedioResource {

    @Inject
    private MedioService service;

    // GET /api/v1/medios
    @GET
    public Response getAll() {
        return Response.ok(service.findAll()).build();
    }

    // GET /api/v1/medios/{id}
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return service.findById(id)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // GET /api/v1/medios?categoria=1
    @GET
    @Path("/categoria/{categoriaId}")
    public Response getByCategoria(@PathParam("categoriaId") Integer categoriaId) {
        return Response.ok(service.findByCategoria(categoriaId)).build();
    }

    // POST /api/v1/medios
    @POST
    public Response create(MedioCreateDTO dto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    // PUT /api/v1/medios/{id}
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, MedioCreateDTO dto) {
        return Response.ok(service.update(id, dto)).build();
    }

    // DELETE /api/v1/medios/{id}
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.noContent().build();
    }
}