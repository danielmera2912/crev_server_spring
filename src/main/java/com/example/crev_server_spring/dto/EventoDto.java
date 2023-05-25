package com.example.crev_server_spring.dto;

import com.example.crev_server_spring.modelo.Ciudad;
import com.example.crev_server_spring.modelo.Deporte;
import com.example.crev_server_spring.modelo.Equipo;
import com.example.crev_server_spring.modelo.UsuarioEvento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDto {
    private Long id;
    private String hora;
    private LocalDate fecha;
    private Deporte deporte;
    private Ciudad ciudad;
    private Set<UsuarioEvento> eventoUsuarios;
    private List<Equipo> equipos;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private String estado;
    private LocalDate fechaCreacion;
}
