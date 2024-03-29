package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.service.EquipoService;
import com.example.crev_server_spring.error.EquipoNotFoundException;
import com.example.crev_server_spring.modelo.Equipo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.crev_server_spring.modelo.UsuarioEquipo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoService equipoService;
    // Obtener equipos
    @GetMapping("/equipo")
    public List<Equipo> obtenerTodos() {
        List<Equipo> result =  equipoService.findAll();
        if(result.isEmpty()){
            throw new EquipoNotFoundException();
        }
        return result;
    }
    // Obtener un equipo
    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) {
        return equipoService.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
    }
    // Obtener los usuarios de un equipo
    @GetMapping("/equipo/{id}/usuarios")
    public List<Usuario> obtenerUsuariosDeEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
        return equipoService.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id))
                .getUsuarios().stream()
                .map(ue -> ue.getUsuario())
                .collect(Collectors.toList());
    }
    // Obtener de un equipo, la colección de ids de UsuarioEquipo
    @GetMapping("/equipo/{id}/usuario-equipo-ids")
    public List<Long> obtenerUsuarioEquipoIdsDeEquipo(@PathVariable Long id) {
        Equipo equipo = equipoService.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
        return equipo.getUsuarioEquipoIds();
    }

    // Crear un nuevo equipo
    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return equipoService.save(newEquipo);
    }
    // Modificar un equipo
    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        if (equipoService.existsById(id)) {
            updateEquipo.setId(id);
            return equipoService.save(updateEquipo);
        } else {
            throw new EquipoNotFoundException(id);
        }
    }
    // Eliminar un equipo
    /*
    @DeleteMapping("/equipo/{id}")
    public Equipo deleteEquipo(@PathVariable Long id) {
        if(equipoService.existsById(id)){
            Equipo result = equipoService.findById(id).get();
            equipoService.deleteById(id);
            return result;
        }else{
            throw new EquipoNotFoundException(id);
        }
    }
     */
}
