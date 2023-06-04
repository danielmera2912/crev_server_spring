package com.example.crev_server_spring.modelo;

import lombok.Data;
import java.util.List;


@Data
public class EventoConUsuariosDTO {
    // Modelo de la relación de evento con usuarios
    private Long id;
    private String hora;
    private String fecha;
    private String deporte;
    private String ciudad;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private String estado;
    private List<UsuarioEventoDTO> usuarios;

    public EventoConUsuariosDTO(Evento evento) {
        this.id = evento.getId();
        this.hora = evento.getHora();
        this.fecha = String.valueOf(evento.getFecha());
        this.deporte = evento.getDeporte().getNombre();
        this.ciudad = evento.getCiudad().getNombre();
        this.puntosLocal = evento.getPuntosLocal();
        this.puntosVisitante = evento.getPuntosVisitante();
        this.estado = evento.getEstado();
        this.usuarios = evento.getUsuarios();
    }
}