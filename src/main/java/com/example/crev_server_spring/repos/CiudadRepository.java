package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    // Repositorio de Ciudad

}
