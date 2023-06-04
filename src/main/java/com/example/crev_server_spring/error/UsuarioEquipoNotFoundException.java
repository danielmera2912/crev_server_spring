package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioEquipoNotFoundException extends RuntimeException{
    // Errores de la relación de Usuario con Equipo
    public UsuarioEquipoNotFoundException(){
        super("No se pudo encontrar ninguna relación para usuario con equipo");
    }
    public UsuarioEquipoNotFoundException(Long id){
        super("No se puede encontrar la relación usuario con equipo con la ID: " + id);
    }
}