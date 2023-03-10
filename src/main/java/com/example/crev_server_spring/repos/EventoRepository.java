package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
