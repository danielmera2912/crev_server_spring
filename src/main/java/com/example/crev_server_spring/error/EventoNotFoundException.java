package com.example.crev_server_spring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventoNotFoundException extends RuntimeException{
    // Errores de evento
    public EventoNotFoundException(){
        super("No se pudo encontrar ningun evento");
    }
    public EventoNotFoundException(Long id){
        super("No se puede encontrar el evento con la ID: " + id);
    }

    public EventoNotFoundException(String s) {
    }
}