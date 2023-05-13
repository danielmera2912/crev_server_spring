package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Equipo;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.repos.EquipoRepository;
import com.example.crev_server_spring.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }


    public Usuario save(Usuario usuario)
    {
        usuario.setFechaCreacion(LocalDate.from(LocalDateTime.now()));
        return usuarioRepository.save(usuario);
    }


    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }


    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }
    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public List<Usuario> findByNombreContaining(String nombre) {
        return usuarioRepository.findByNombreContainingAndIdNot(nombre, 0L);
    }


    public Page<Usuario> findAllPaginated(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable);
    }
}
