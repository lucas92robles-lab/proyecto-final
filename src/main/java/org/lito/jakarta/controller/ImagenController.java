package org.lito.jakarta.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/imagenes")
@ApplicationScoped
public class ImagenController {

    private static final Logger LOG = Logger.getLogger(ImagenController.class.getName());

    // Solo se usa en modo local (sin Cloudinary)
    private static final String DIRECTORIO_IMAGENES =
            System.getenv().getOrDefault("IMAGENES_DIR", "C:/tactica-subidas/imagenes");

    private static final Set<String> EXTENSIONES_PERMITIDAS =
            Set.of(".jpg", ".jpeg", ".png", ".webp", ".gif");
    private static final long MAX_BYTES = 5L * 1024 * 1024; // 5 MB

    private Cloudinary cloudinary; // null = modo disco local

    @PostConstruct
    void init() {
        String url = System.getenv("CLOUDINARY_URL");
        if (url != null && !url.isBlank()) {
            cloudinary = new Cloudinary(url);
            LOG.info("Imagenes: usando Cloudinary");
        } else {
            LOG.info("Imagenes: usando disco local en " + DIRECTORIO_IMAGENES);
        }
    }

    // Sirve imágenes guardadas en disco (modo local o imágenes viejas)
    @GET
    @Path("/ver/{nombreArchivo}")
    public Response verImagen(@PathParam("nombreArchivo") String nombreArchivo) {
        try {
            File base = new File(DIRECTORIO_IMAGENES).getCanonicalFile();
            File archivo = new File(base, nombreArchivo).getCanonicalFile();

            // Evita path traversal (../)
            if (!archivo.getPath().startsWith(base.getPath() + File.separator)
                    || !archivo.exists() || archivo.isDirectory()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            String mimeType = Files.probeContentType(archivo.toPath());
            if (mimeType == null) {
                mimeType = "image/jpeg";
            }
            return Response.ok(archivo)
                    .type(mimeType)
                    .header("Content-Disposition", "inline; filename=\"" + archivo.getName() + "\"")
                    .build();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Error sirviendo imagen", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @SuppressWarnings("rawtypes")
    public Response subirImagen(MultipartFormDataInput input) {
        try {
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");
            if (inputParts == null || inputParts.isEmpty()) {
                return error(Response.Status.BAD_REQUEST, "No se envió ningún archivo");
            }

            InputPart inputPart = inputParts.get(0);

            String fileName = inputPart.getFileName();
            String extension = ".jpg";
            if (fileName != null && fileName.contains(".")) {
                extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase(Locale.ROOT);
            }
            if (!EXTENSIONES_PERMITIDAS.contains(extension)) {
                return error(Response.Status.BAD_REQUEST, "Formato de imagen no permitido");
            }

            byte[] bytes;
            try (InputStream in = inputPart.getBody(InputStream.class, null)) {
                bytes = in.readAllBytes();
            }
            if (bytes.length == 0 || bytes.length > MAX_BYTES) {
                return error(Response.Status.BAD_REQUEST, "La imagen debe pesar entre 1 byte y 5 MB");
            }

            String url;
            if (cloudinary != null) {
                Map resultado = cloudinary.uploader().upload(bytes, ObjectUtils.asMap(
                        "folder", "tactica",
                        "resource_type", "image"));
                url = (String) resultado.get("secure_url");
            } else {
                File uploadDir = new File(DIRECTORIO_IMAGENES);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String nombreUnico = "img_" + UUID.randomUUID().toString().substring(0, 8) + extension;
                Files.write(new File(uploadDir, nombreUnico).toPath(), bytes);
                url = "/app/imagenes/ver/" + nombreUnico;
            }

            return Response.ok("{\"url\": \"" + url + "\"}").build();

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error subiendo imagen", e);
            return error(Response.Status.INTERNAL_SERVER_ERROR, "No se pudo subir la imagen");
        }
    }

    private Response error(Response.Status status, String mensaje) {
        return Response.status(status).entity("{\"error\": \"" + mensaje + "\"}").build();
    }
}