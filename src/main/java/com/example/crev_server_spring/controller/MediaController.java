package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MediaController {
    private final StorageService storageService;

    @Autowired
    public MediaController(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping("/media/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        return ResponseEntity.ok().headers(headers).body(file);
    }


    @PostMapping("/media/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = storageService.store(file);
        String hostName = request.getServerName();
        int portNumber = request.getServerPort();
        String baseUrl = "http://" + hostName + ":" + portNumber;
        String resourceUrl = baseUrl + "/media/" + fileName;
        return resourceUrl;
    }

}
