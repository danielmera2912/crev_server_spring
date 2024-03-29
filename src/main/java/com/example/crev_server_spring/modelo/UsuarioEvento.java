package com.example.crev_server_spring.modelo;

import com.example.crev_server_spring.dto.EventoDto;
import com.example.crev_server_spring.dto.GetUserDto;
import com.example.crev_server_spring.dto.UserEventoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario_has_evento")
public class UsuarioEvento {
    // Modelo de relación de usuario con evento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

}
