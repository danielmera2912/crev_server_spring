package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeporteRepository extends JpaRepository<Deporte, Long> {
    // Repositorio de deporte
}
