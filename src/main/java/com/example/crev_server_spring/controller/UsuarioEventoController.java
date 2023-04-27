package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.UsuarioEventoNotFoundException;
import com.example.crev_server_spring.error.UsuarioNotFoundException;
import com.example.crev_server_spring.modelo.Evento;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.modelo.UsuarioEvento;
import com.example.crev_server_spring.service.EventoService;
import com.example.crev_server_spring.service.UsuarioEventoService;
import com.example.crev_server_spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UsuarioEventoController {
    private final UsuarioEventoService usuarioEventoService;
    private final UsuarioService usuarioService;
    private final EventoService eventoService;

    @GetMapping("/usuario_evento")
    public List<UsuarioEvento> obtenerTodos() {
        List<UsuarioEvento> result = usuarioEventoService.findAll();
        if (result.isEmpty()) {
            throw new UsuarioEventoNotFoundException();
        }
        return result;
    }

    @GetMapping("/usuario_evento/{id}")
    public UsuarioEvento obtenerUno(@PathVariable Long id) {
        return usuarioEventoService.findById(id).orElseThrow(() -> new UsuarioEventoNotFoundException(id));
    }

    @GetMapping("/usuario_evento/evento/{id}")
    public List<UsuarioEvento> obtenerPorEventoId(@PathVariable Long id) {
        List<UsuarioEvento> result = usuarioEventoService.findByEventoId(id);
        if (result.isEmpty()) {
            throw new UsuarioEventoNotFoundException();
        }
        return result;
    }

    @PostMapping("/usuario_evento")
    public UsuarioEvento newUsuarioEvento(@RequestBody UsuarioEvento newUsuarioEvento) {
        return usuarioEventoService.save(newUsuarioEvento);
    }

    @PutMapping("/usuario_evento/{id}")
    public UsuarioEvento updateUsuario(@RequestBody UsuarioEvento updateUsuarioEvento, @PathVariable Long id) {
        if (usuarioEventoService.existsById(id)) {
            updateUsuarioEvento.setId(id);
            return usuarioEventoService.save(updateUsuarioEvento);
        } else {
            throw new UsuarioEventoNotFoundException(id);
        }
    }

    @DeleteMapping("/usuario_evento/{id}")
    public UsuarioEvento deleteUsuario(@PathVariable Long id) {
        if (usuarioEventoService.existsById(id)) {
            UsuarioEvento result = usuarioEventoService.findById(id).get();
            usuarioEventoService.deleteById(id);
            return result;
        } else {
            throw new UsuarioEventoNotFoundException(id);
        }
    }

    @GetMapping("/usuarios/{usuarioId}/eventos")
    public List<Evento> obtenerEventosPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

        List<Evento> eventos = new ArrayList<>();

        // Obtener eventos en los que el usuario participe directamente
        eventos.addAll(usuario.getEventosUsuario().stream()
                .map(UsuarioEvento::getEvento)
                .collect(Collectors.toList()));

        // Obtener eventos en los que el usuario participe a través de sus equipos
        eventos.addAll(usuario.getEquiposUsuario().stream()
                .flatMap(ue -> ue.getEquipo().getEventoUsuarios().stream())
                .map(UsuarioEvento::getEvento)
                .collect(Collectors.toList()));

        // Eliminar eventos duplicados
        eventos = eventos.stream()
                .distinct()
                .sorted(Comparator.comparingLong(Evento::getId)) // Ordenar por ID ascendente
                .collect(Collectors.toList());

        return eventos;
    }



}
