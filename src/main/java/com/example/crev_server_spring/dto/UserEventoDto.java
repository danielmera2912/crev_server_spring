package com.example.crev_server_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventoDto {
    // DTO de UsuarioEvento
    private Long id;
    private EventoDto evento;
    private String username;
    private String avatar;
    private String correo;
    private LocalDate fechaNacimiento;

}
