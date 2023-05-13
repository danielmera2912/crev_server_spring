package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.EventoNotFoundException;
import com.example.crev_server_spring.modelo.Equipo;
import com.example.crev_server_spring.modelo.Evento;
import com.example.crev_server_spring.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EventoController {
    private final EventoService eventoService;

    @GetMapping("/evento")
    public ResponseEntity<Map<String, Object>> obtenerTodos(
            @RequestParam(defaultValue = "0") Integer page) {
        int size = 10;

        Page<Evento> eventosPage = eventoService.findAllPaginated(page, size);
        List<Evento> eventos = eventosPage.getContent();

        if (eventos.isEmpty()) {
            throw new EventoNotFoundException();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("eventos", eventos);
        response.put("totalPages", eventosPage.getTotalPages());
        response.put("currentPage", eventosPage.getNumber());

        return ResponseEntity.ok().body(response);
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
    @GetMapping("/evento/{id}/equipos")
    public ResponseEntity<List<Equipo>> getEquiposPorEvento(@PathVariable Long id) {
        Evento evento = eventoService.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));

        List<Equipo> equipos = evento.getEquipos();

        return ResponseEntity.ok().body(equipos);
    }

    @PutMapping("/evento/{id}")
    public Evento updateEvento(@RequestBody Evento updateEvento, @PathVariable Long id) {
        if (eventoService.existsById(id)) {
            Evento existingEvento = eventoService.findById(id).orElseThrow(() -> new EventoNotFoundException(id));

            // Verifica si se proporcionó un valor para el campo "estado"
            if (updateEvento.getEstado() == null) {
                // Si no se proporcionó un valor, mantén el valor existente del estado
                updateEvento.setEstado(existingEvento.getEstado());
            }

            // Copiar el valor existente del campo "deporte" al objeto actualizado
            updateEvento.setDeporte(existingEvento.getDeporte());

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
