package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Evento;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.repos.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }


    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }


    public Evento save(Evento evento) {

        evento.setFechaCreacion(LocalDate.from(LocalDateTime.now()));
        return eventoRepository.save(evento);
    }


    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return eventoRepository.existsById(id);
    }

    public List<Evento> findByCiudadAndDeporte(String ciudad, String deporte) {
        return eventoRepository.findByCiudadNombreContainingIgnoreCaseAndDeporteNombreContainingIgnoreCase(ciudad, deporte);
    }

    public List<Evento> findByCiudad(String ciudad) {
        return eventoRepository.findByCiudadNombreContainingIgnoreCase(ciudad);
    }

    public List<Evento> findByDeporte(String deporte) {
        return eventoRepository.findByDeporteNombreContainingIgnoreCase(deporte);
    }

    public Page<Evento> findAllPaginated(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventoRepository.findAll(pageable);
    }
}
