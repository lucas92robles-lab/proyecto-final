package org.lito.jakarta.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
// NOTA: Eliminamos 'import java.nio.file.Path;' para evitar el conflicto con JAX-RS @Path
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/imagenes")
@ApplicationScoped
public class ImagenController {

    private static final String DIRECTORIO_IMAGENES = "C:/tactica-subidas/imagenes";

    @GET
    @Path("/ver/{nombreArchivo}")
    public Response verImagen(@PathParam("nombreArchivo") String nombreArchivo) {
        File archivo = new File(DIRECTORIO_IMAGENES, nombreArchivo);

        if (!archivo.exists() || archivo.isDirectory()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            String mimeType = Files.probeContentType(archivo.toPath());
            if (mimeType == null) {
                mimeType = "image/jpeg";
            }

            return Response.ok(archivo)
                    .type(mimeType)
                    .header("Content-Disposition", "inline; filename=\"" + archivo.getName() + "\"")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirImagen(MultipartFormDataInput input) {
        try {
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");

            if (inputParts == null || inputParts.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"No se envió ningún archivo\"}").build();
            }

            InputPart inputPart = inputParts.get(0);
            InputStream inputStream = inputPart.getBody(InputStream.class, null);

            File uploadDir = new File(DIRECTORIO_IMAGENES);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = inputPart.getFileName();
            String extension = ".jpg";
            if (fileName != null && fileName.contains(".")) {
                extension = fileName.substring(fileName.lastIndexOf("."));
            }

            String nombreUnico = "img_" + UUID.randomUUID().toString().substring(0, 8) + extension;
            
            // Usamos el nombre completo de la clase para evitar colisiones con @Path
            java.nio.file.Path filePath = new File(uploadDir, nombreUnico).toPath();

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            String urlRelativa = "/app/imagenes/ver/" + nombreUnico;
            return Response.ok("{\"url\": \"" + urlRelativa + "\"}").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}