package com.example.crev_server_spring.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEventoDTO {
    // DTO de la relación de usuario con evento
    private Long id;
    private String username;
    private LocalDate fechaNacimiento;
    private String avatar;
    private String correo;
    private LocalDateTime fechaCreacion;

    private UserRole role;

    // Constructor adicional para convertir un objeto Usuario a UsuarioEventoDTO
    public UsuarioEventoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.avatar = usuario.getAvatar();
        this.correo = usuario.getCorreo();
        this.fechaCreacion = usuario.getCreatedAt();
        this.role = usuario.getRole();
    }
}
