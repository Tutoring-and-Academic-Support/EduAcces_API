package com.upao.eduaccess.service;

//servicio de almacenamiento para manejar la subida y descarga de archivos.
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {
    void init();

    String store(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void delete(String filename);
}
