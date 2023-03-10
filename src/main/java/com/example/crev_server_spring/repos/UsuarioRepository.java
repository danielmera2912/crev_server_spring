package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
