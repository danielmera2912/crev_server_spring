package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipoNotFoundException extends RuntimeException{
    // Errores de equipo
    public EquipoNotFoundException(){
        super("No se pudo encontrar ningun equipo");
    }
    public EquipoNotFoundException(Long id){
        super("No se puede encontrar el equipo con la ID: " + id);
    }
}