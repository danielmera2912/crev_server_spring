package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioEventoNotFoundException extends RuntimeException{
    // Errores de la relación de usuario con Evento
    public UsuarioEventoNotFoundException(){
        super("No se pudo encontrar ninguna relación usuario con evento");
    }
    public UsuarioEventoNotFoundException(Long id){
        super("No se puede encontrar la relación usuario con evento con la ID: " + id);
    }
}