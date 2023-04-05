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
        // Aquí establecemos el valor por defecto para el campo "estado"
        newEvento.setEstado("EN CURSO");
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
    @GetMapping("/evento/busqueda")
    public List<Evento> buscarEventos(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String deporte) {

        if (ciudad != null && deporte != null) {
            List<Evento> eventos = eventoService.findByCiudadAndDeporte(ciudad, deporte);
            if (eventos.isEmpty()) {
                throw new EventoNotFoundException("No se encontraron eventos para los filtros proporcionados");
            }
            return eventos;
        } else if (ciudad != null) {
            List<Evento> eventos = eventoService.findByCiudad(ciudad);
            if (eventos.isEmpty()) {
                throw new EventoNotFoundException("No se encontraron eventos para los filtros proporcionados");
            }
            return eventos;
        } else if (deporte != null) {
            List<Evento> eventos = eventoService.findByDeporte(deporte);
            if (eventos.isEmpty()) {
                throw new EventoNotFoundException("No se encontraron eventos para los filtros proporcionados");
            }
            return eventos;
        } else {
            throw new IllegalArgumentException("Debe proporcionar al menos un parámetro de búsqueda");
        }
    }

}
