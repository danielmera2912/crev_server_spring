package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.EventoNotFoundException;
import com.example.crev_server_spring.modelo.Evento;
import com.example.crev_server_spring.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventoController {
    private final EventoService eventoService;
    @GetMapping("/evento")
    public List<Evento> obtenerTodos() {
        List<Evento> result =  eventoService.findAll();
        if(result.isEmpty()){
            throw new EventoNotFoundException();
        }
        return result;
    }

    @GetMapping("/evento/{id}")
    public Evento obtenerUno(@PathVariable Long id) {
        return eventoService.findById(id).orElseThrow(() -> new EventoNotFoundException(id));
    }

    @PostMapping("/evento")
    public Evento newEvento(@RequestBody Evento newEvento){
        return eventoService.save(newEvento);
    }

    @PutMapping("/evento/{id}")
    public Evento updateEvento(@RequestBody Evento updateEvento, @PathVariable Long id){
        if (eventoService.existsById(id)) {
            updateEvento.setId(id);
            return eventoService.save(updateEvento);
        } else {
            throw new EventoNotFoundException(id);
        }
    }

    @DeleteMapping("/evento/{id}")
    public Evento deleteEvento(@PathVariable Long id) {
        if(eventoService.existsById(id)){
            Evento result = eventoService.findById(id).get();
            eventoService.deleteById(id);
            return result;
        }else{
            throw new EventoNotFoundException(id);
        }
    }
}
