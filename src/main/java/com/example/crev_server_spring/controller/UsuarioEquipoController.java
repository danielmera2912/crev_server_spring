package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.UsuarioEquipoNotFoundException;
import com.example.crev_server_spring.error.UsuarioEventoNotFoundException;
import com.example.crev_server_spring.error.UsuarioNotFoundException;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.modelo.UsuarioEquipo;
import com.example.crev_server_spring.modelo.UsuarioEvento;
import com.example.crev_server_spring.service.UsuarioEquipoService;
import com.example.crev_server_spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioEquipoController {
    private final UsuarioEquipoService usuarioEquipoService;
    // Obtener todas las relaciones de usuario con equipo
    @GetMapping("/usuario_equipo")
    public List<UsuarioEquipo> obtenerTodos() {
        List<UsuarioEquipo> result =  usuarioEquipoService.findAll();
        if(result.isEmpty()){
            throw new UsuarioEquipoNotFoundException();
        }
        return result;
    }
    // Obtener una relación de usuario con equipo
    @GetMapping("/usuario_equipo/{id}")
    public UsuarioEquipo obtenerUno(@PathVariable Long id) {
        return usuarioEquipoService.findById(id).orElseThrow(() -> new UsuarioEquipoNotFoundException(id));
    }
    // Crear una relación de usuario con equipo
    @PostMapping("/usuario_equipo")
    public UsuarioEquipo newUsuarioEquipo(@RequestBody UsuarioEquipo newUsuarioEquipo){
        return usuarioEquipoService.save(newUsuarioEquipo);
    }
    // Modificar una relación de usuario con equipo
    @PutMapping("/usuario_equipo/{id}")
    public UsuarioEquipo updateUsuario(@RequestBody UsuarioEquipo updateUsuario, @PathVariable Long id){
        if (usuarioEquipoService.existsById(id)) {
            updateUsuario.setId(id);
            return usuarioEquipoService.save(updateUsuario);
        } else {
            throw new UsuarioEquipoNotFoundException(id);
        }
    }
    // Eliminar una relación de usuario con equipo
    @DeleteMapping("/usuario_equipo/{id}")
    public UsuarioEquipo deleteUsuario(@PathVariable Long id) {
        if(usuarioEquipoService.existsById(id)){
            UsuarioEquipo result = usuarioEquipoService.findById(id).get();
            usuarioEquipoService.deleteById(id);
            return result;
        }else{
            throw new UsuarioEquipoNotFoundException(id);
        }
    }
}
