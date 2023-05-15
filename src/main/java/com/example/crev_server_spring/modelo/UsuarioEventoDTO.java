package com.example.crev_server_spring.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEventoDTO {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String avatar;
    private String correo;
    private LocalDate fechaCreacion;
    private Usuario.Rol rol;

    public enum Rol {
        ADMIN,
        USER
    }

    // Constructor adicional para convertir un objeto Usuario a UsuarioEventoDTO
    public UsuarioEventoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.avatar = usuario.getAvatar();
        this.correo = usuario.getCorreo();
        this.fechaCreacion = usuario.getFechaCreacion();
        this.rol = usuario.getRol();
    }
}
