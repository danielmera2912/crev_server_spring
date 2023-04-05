package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndClave(String correo, String clave);

    boolean existsByCorreo(String correo);

    boolean existsByNombre(String nombre);

    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByNombre(String nombre);
    boolean existsByCorreoIgnoreCase(String correo);
    boolean existsByNombreIgnoreCase(String nombre);
    Optional<Usuario> findByCorreoIgnoreCase(String correo);
    List<Usuario> findByNombreIgnoreCase(String nombre);

    boolean existsByCorreoIgnoreCaseAndNombreIgnoreCase(String correo, String nombre);

}
