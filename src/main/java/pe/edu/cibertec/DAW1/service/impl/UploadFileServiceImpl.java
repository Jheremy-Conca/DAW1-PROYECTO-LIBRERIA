package pe.edu.cibertec.DAW1.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.DAW1.service.IUploadFileService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final static String UPLOADS_FOLDER = "uploads";

    public UploadFileServiceImpl() {
        // Verificar si el directorio de uploads existe, si no, crearlo
        File directory = new File(UPLOADS_FOLDER);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path path = getPath(filename);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Error: No se puede leer el archivo " + filename);
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);
        Files.copy(file.getInputStream(), rootPath);
        return uniqueFilename;
    }
    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File file = rootPath.toFile();

        // Verificar si el archivo existe y si se puede leer
        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;  // El archivo se eliminó correctamente
            } else {
                throw new RuntimeException("No se pudo eliminar el archivo: " + filename);
            }
        }
        return false;  // Si no existe o no se puede leer
    }

    // Método para obtener la ruta completa del archivo
    private Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }
}
