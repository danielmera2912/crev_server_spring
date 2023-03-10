package com.example.crev_server_spring.service;

import com.example.crev_server_spring.repos.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crev_server_spring.modelo.Equipo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoService{
    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }


    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }


    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }


    public void deleteById(Long id) {
        equipoRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return equipoRepository.existsById(id);
    }

}
