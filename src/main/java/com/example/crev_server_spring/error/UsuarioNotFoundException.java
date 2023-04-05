package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(){
        super("No se pudo encontrar ningun usuario");
    }
    public UsuarioNotFoundException(Long id){
        super("No se puede encontrar el usuario con la ID: " + id);
    }

    public UsuarioNotFoundException(String correo) {
        super("No se puede encontrar el usuario con el siguiente correo: " + correo);
    }
}