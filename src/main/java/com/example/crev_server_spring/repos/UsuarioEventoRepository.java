package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Long> {
    // Repositorio de la relación de usuario con evento
    List<UsuarioEvento> findByEventoId(Long id);
}
