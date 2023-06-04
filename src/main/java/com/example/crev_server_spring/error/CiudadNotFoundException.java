package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CiudadNotFoundException extends RuntimeException{
    // Errores de ciudad
    public CiudadNotFoundException(){
        super("No se pudo encontrar ninguna ciudad");
    }
    public CiudadNotFoundException(Long id){
        super("No se puede encontrar la ciudad con la ID: " + id);
    }
}