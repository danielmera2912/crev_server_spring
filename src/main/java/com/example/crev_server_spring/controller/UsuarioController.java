package com.example.crev_server_spring.controller;

import com.example.crev_server_spring.error.UsuarioNotFoundException;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @GetMapping("/usuario")
    public ResponseEntity<Map<String, Object>> obtenerTodos(@RequestParam(defaultValue = "0") Integer page) {
        int size = 9;

        // Obtiene todos los usuarios, incluido el usuario con ID 0
        List<Usuario> usuarios = usuarioService.findAll();

        // Elimina el usuario con ID 0 de la lista de usuarios
        usuarios.removeIf(usuario -> usuario.getId() == 0);

        // Calcula los índices de inicio y fin para la página solicitada
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, usuarios.size());

        // Si la página solicitada está fuera de rango, devuelve una excepción
        if (startIndex >= usuarios.size()) {
            throw new UsuarioNotFoundException();
        }

        // Obtiene los usuarios para la página solicitada
        List<Usuario> usuariosPaginados = usuarios.subList(startIndex, endIndex);

        // Crea la respuesta con los usuarios paginados y la información de paginación
        Map<String, Object> response = new HashMap<>();
        response.put("usuarios", usuariosPaginados);
        response.put("totalPages", (int) Math.ceil((double) usuarios.size() / size));
        response.put("currentPage", page);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUno(@PathVariable Long id) {
        return usuarioService.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }
    @GetMapping("/usuario/buscarPorCorreo/{correo}")
    public Usuario obtenerPorCorreo(@PathVariable String correo) {
        return usuarioService.findByCorreo(correo).orElseThrow(() -> new UsuarioNotFoundException(correo));
    }

    @GetMapping("/usuario/login")
    public boolean loginUsuario(@RequestParam String correo, @RequestParam String clave) {
        Optional<Usuario> usuarioOptional = usuarioService.findByCorreo(correo);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getClave().equals(clave);
        }

        return false;
    }
    @GetMapping("/usuario/existeCorreo")
    public boolean existeCorreo(@RequestParam String correo) {
        Optional<Usuario> usuarioOptional = usuarioService.findByCorreo(correo);
        return usuarioOptional.isPresent();
    }
    @GetMapping("/usuario/existeNombre")
    public boolean existeNombre(@RequestParam String nombre) {
        Optional<Usuario> usuarioOptional = usuarioService.findByNombre(nombre);
        return usuarioOptional.isPresent();
    }
    @GetMapping("/usuario/buscarPorNombre/{nombre}")
    public List<Usuario> buscarPorNombre(@PathVariable String nombre) {
        return usuarioService.findByNombreContaining(nombre);
    }
    @PostMapping("/usuario")
    public Usuario newUsuario(@RequestBody Usuario newUsuario){
        newUsuario.setRol(Usuario.Rol.USER);
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
