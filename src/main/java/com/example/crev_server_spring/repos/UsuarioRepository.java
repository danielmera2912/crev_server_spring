package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Repositorio de usuario
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByUsername(String username);

    List<Usuario> findByUsernameContainingAndIdNot(String username, Long id);
}
