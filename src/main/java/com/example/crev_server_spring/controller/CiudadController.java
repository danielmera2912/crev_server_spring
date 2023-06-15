package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.CiudadNotFoundException;
import com.example.crev_server_spring.modelo.Ciudad;
import com.example.crev_server_spring.service.CiudadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CiudadController {
    private final CiudadService ciudadService;
    // Obtener ciudades
    @GetMapping("/ciudad")
    public List<Ciudad> obtenerTodos() {
        List<Ciudad> result =  ciudadService.findAll();
        if(result.isEmpty()){
            throw new CiudadNotFoundException();
        }
        return result;
    }
    //Obtener ciudad por id
    @GetMapping("/ciudad/{id}")
    public Ciudad obtenerUno(@PathVariable Long id) {
        return ciudadService.findById(id).orElseThrow(() -> new CiudadNotFoundException(id));
    }
    // Crear una ciudad
    /*
    @PostMapping("/ciudad")
    public Ciudad newUCiudad(@RequestBody Ciudad newUsuario){
        return ciudadService.save(newUsuario);
    }

     */
    // Modificar una ciudad
    /*
    @PutMapping("/ciudad/{id}")
    public Ciudad updateCiudad(@RequestBody Ciudad updateUsuario, @PathVariable Long id){
        if (ciudadService.existsById(id)) {
            updateUsuario.setId(id);
            return ciudadService.save(updateUsuario);
        } else {
            throw new CiudadNotFoundException(id);
        }
    }

     */
    // Eliminar una ciudad
    /*
    @DeleteMapping("/ciudad/{id}")
    public Ciudad deleteCiudad(@PathVariable Long id) {
        if(ciudadService.existsById(id)){
            Ciudad result = ciudadService.findById(id).get();
            ciudadService.deleteById(id);
            return result;
        }else{
            throw new CiudadNotFoundException(id);
        }
    }
     */
}
