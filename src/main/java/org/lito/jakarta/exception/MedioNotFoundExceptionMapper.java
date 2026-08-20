package org.lito.jakarta.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MedioNotFoundExceptionMapper implements ExceptionMapper<MedioNotFoundException> {

    @Override
    public Response toResponse(MedioNotFoundException exception) {
        // Devuelve un error 404 (Not Found) con un JSON indicando el mensaje
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("{\"error\": \"" + exception.getMessage() + "\"}")
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}