package com.example.crev_server_spring.dto;

import com.example.crev_server_spring.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    // Conversión de un usuario para DTO
    public GetUserDto convertUsuarioToGetUserDto(Usuario usuario) {
        return GetUserDto.builder()
                .username(usuario.getUsername())
                .avatar(usuario.getAvatar())
                .correo(usuario.getCorreo())
                .id(usuario.getId())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .build();
    }
}