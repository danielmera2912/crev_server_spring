package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.DeporteNotFoundException;
import com.example.crev_server_spring.modelo.Deporte;
import com.example.crev_server_spring.service.DeporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeporteController {
    private final DeporteService deporteService;
    // Obtener deportes
    @GetMapping("/deporte")
    public List<Deporte> obtenerTodos() {
        List<Deporte> result =  deporteService.findAll();
        if(result.isEmpty()){
            throw new DeporteNotFoundException();
        }
        return result;
    }
    // Obtener un deporte
    @GetMapping("/deporte/{id}")
    public Deporte obtenerUno(@PathVariable Long id) {
        return deporteService.findById(id).orElseThrow(() -> new DeporteNotFoundException(id));
    }
    // Crear un nuevo deporte
    @PostMapping("/deporte")
    public Deporte newDeporte(@RequestBody Deporte newDeporte){
        return deporteService.save(newDeporte);
    }
    // Modificar un deporte
    @PutMapping("/deporte/{id}")
    public Deporte updateDeporte(@RequestBody Deporte updateDeporte, @PathVariable Long id){
        if (deporteService.existsById(id)) {
            updateDeporte.setId(id);
            return deporteService.save(updateDeporte);
        } else {
            throw new DeporteNotFoundException(id);
        }
    }
    // Eliminar un deporte
    @DeleteMapping("/deporte/{id}")
    public Deporte deleteDeporte(@PathVariable Long id) {
        if(deporteService.existsById(id)){
            Deporte result = deporteService.findById(id).get();
            deporteService.deleteById(id);
            return result;
        }else{
            throw new DeporteNotFoundException(id);
        }
    }
}
