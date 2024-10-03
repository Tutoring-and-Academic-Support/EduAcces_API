package com.upao.eduaccess.service.impl;
/*
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;


//implementa almacen de archivos-aun no se implementara
@Service
public class FileSystemStorageService implements StorageService {

    @Value("${storage.location}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    @Override
    public void init() {
        if (storageLocation.trim().isEmpty()) {
            throw new RuntimeException("La ubicación de almacenamiento no puede estar vacía.");
        }

        rootLocation = Paths.get(storageLocation);

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar el almacenamiento", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("No se puede almacenar un archivo vacío.");
            }
            String originalFilename = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(originalFilename);
            String filename = UUID.randomUUID() + "." + extension;

            Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // Esta es una verificación de seguridad
                throw new RuntimeException("No se puede almacenar el archivo fuera del directorio actual.");
            }
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename; // Este nombre se asignará a filePath en la entidad Material
        } catch (IOException e) {
            throw new RuntimeException("No se pudo almacenar el archivo.", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResourceNotFoundException("No se pudo leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("No se pudo leer el archivo: " + filename);
        }
    }

    @Override
    public void delete(String filename) {
        Path file = load(filename);
        try {
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo eliminar el archivo.");
        }
    }
}
*/