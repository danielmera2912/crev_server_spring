package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.modelo.UsuarioEvento;
import com.example.crev_server_spring.repos.UsuarioEventoRepository;
import com.example.crev_server_spring.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioEventoService {
    @Autowired
    private UsuarioEventoRepository usuarioEventoRepository;

    public List<UsuarioEvento> findAll() {
        return usuarioEventoRepository.findAll();
    }


    public Optional<UsuarioEvento> findById(Long id) {
        return usuarioEventoRepository.findById(id);
    }


    public UsuarioEvento save(UsuarioEvento usuarioEvento) {
        return usuarioEventoRepository.save(usuarioEvento);
    }


    public void deleteById(Long id) {
        usuarioEventoRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return usuarioEventoRepository.existsById(id);
    }

}
