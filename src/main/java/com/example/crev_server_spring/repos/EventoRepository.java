package com.example.crev_server_spring.repos;

import com.example.crev_server_spring.modelo.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByCiudadNombreAndDeporteNombre(String ciudad, String deporte);

    List<Evento> findByCiudadNombre(String ciudad);

    List<Evento> findByDeporteNombre(String deporte);

    List<Evento> findByCiudadNombreContainingIgnoreCaseAndDeporteNombreContainingIgnoreCase(String ciudad, String deporte);

    List<Evento> findByCiudadNombreContainingIgnoreCase(String ciudad);

    List<Evento> findByDeporteNombreContainingIgnoreCase(String deporte);
}
