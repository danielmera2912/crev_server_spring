package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeporteNotFoundException extends RuntimeException{

    public DeporteNotFoundException(){
        super("No se pudo encontrar ningún deporte");
    }
    public DeporteNotFoundException(Long id){
        super("No se puede encontrar el deporte con la ID: " + id);
    }
}