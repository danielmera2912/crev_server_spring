package com.example.crev_server_spring.service;

import com.example.crev_server_spring.modelo.Ciudad;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.repos.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CiudadService {
    // Servicios de ciudad
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }


    public Optional<Ciudad> findById(Long id) {
        return ciudadRepository.findById(id);
    }


    public Ciudad save(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }


    public void deleteById(Long id) {
        ciudadRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return ciudadRepository.existsById(id);
    }

}
