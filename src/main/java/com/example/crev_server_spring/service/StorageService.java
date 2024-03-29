package com.example.crev_server_spring.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    // Servicios de almacenamiento
    void init() throws IOException;
    String store(MultipartFile file);
    Resource loadAsResource(String filename);
}