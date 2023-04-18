package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.UsuarioEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioEquipoRepository extends JpaRepository<UsuarioEquipo, Long> {

}
