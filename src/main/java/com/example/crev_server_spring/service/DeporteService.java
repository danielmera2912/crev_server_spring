package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Deporte;
import com.example.crev_server_spring.repos.DeporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeporteService {
    @Autowired
    private DeporteRepository deporteRepository;

    public List<Deporte> findAll() {
        return deporteRepository.findAll();
    }


    public Optional<Deporte> findById(Long id) {
        return deporteRepository.findById(id);
    }


    public Deporte save(Deporte deporte) {
        return deporteRepository.save(deporte);
    }


    public void deleteById(Long id) {
        deporteRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return deporteRepository.existsById(id);
    }

}
