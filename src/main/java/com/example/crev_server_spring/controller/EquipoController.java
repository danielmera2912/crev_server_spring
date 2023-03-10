package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.service.EquipoService;
import com.example.crev_server_spring.error.EquipoNotFoundException;
import com.example.crev_server_spring.modelo.Equipo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoService equipoService;
    @GetMapping("/equipo")
    public List<Equipo> obtenerTodos() {
        List<Equipo> result =  equipoService.findAll();
        if(result.isEmpty()){
            throw new EquipoNotFoundException();
        }
        return result;
    }

    @GetMapping("/equipo/{id}")
    public Equipo obtenerUno(@PathVariable Long id) {
        return equipoService.findById(id).orElseThrow(() -> new EquipoNotFoundException(id));
    }

    @PostMapping("/equipo")
    public Equipo newEquipo(@RequestBody Equipo newEquipo){
        return equipoService.save(newEquipo);
    }

    @PutMapping("/equipo/{id}")
    public Equipo updateEquipo(@RequestBody Equipo updateEquipo, @PathVariable Long id){
        if (equipoService.existsById(id)) {
            updateEquipo.setId(id);
            return equipoService.save(updateEquipo);
        } else {
            throw new EquipoNotFoundException(id);
        }
    }

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
}
