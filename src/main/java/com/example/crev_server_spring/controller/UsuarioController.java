package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.UsuarioNotFoundException;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @GetMapping("/usuario")
    public List<Usuario> obtenerTodos() {
        List<Usuario> result =  usuarioService.findAll();
        if(result.isEmpty()){
            throw new UsuarioNotFoundException();
        }
        return result;
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUno(@PathVariable Long id) {
        return usuarioService.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @PostMapping("/usuario")
    public Usuario newUsuario(@RequestBody Usuario newUsuario){
        return usuarioService.save(newUsuario);
    }

    @PutMapping("/usuario/{id}")
    public Usuario updateUsuario(@RequestBody Usuario updateUsuario, @PathVariable Long id){
        if (usuarioService.existsById(id)) {
            updateUsuario.setId(id);
            return usuarioService.save(updateUsuario);
        } else {
            throw new UsuarioNotFoundException(id);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public Usuario deleteUsuario(@PathVariable Long id) {
        if(usuarioService.existsById(id)){
            Usuario result = usuarioService.findById(id).get();
            usuarioService.deleteById(id);
            return result;
        }else{
            throw new UsuarioNotFoundException(id);
        }
    }
}
