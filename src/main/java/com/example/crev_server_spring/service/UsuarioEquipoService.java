package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.modelo.UsuarioEquipo;
import com.example.crev_server_spring.repos.UsuarioEquipoRepository;
import com.example.crev_server_spring.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioEquipoService {
    @Autowired
    private UsuarioEquipoRepository usuarioEquipoRepository;

    public List<UsuarioEquipo> findAll() {
        return usuarioEquipoRepository.findAll();
    }


    public Optional<UsuarioEquipo> findById(Long id) {
        return usuarioEquipoRepository.findById(id);
    }


    public UsuarioEquipo save(UsuarioEquipo usuarioEquipo) {
        return usuarioEquipoRepository.save(usuarioEquipo);
    }


    public void deleteById(Long id) {
        usuarioEquipoRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return usuarioEquipoRepository.existsById(id);
    }

}
