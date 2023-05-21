package com.example.crev_server_spring.dto;

import com.example.crev_server_spring.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public GetUserDto convertUsuarioToGetUserDto(Usuario usuario) {
        return GetUserDto.builder()
                .username(usuario.getUsername())
                .avatar(usuario.getAvatar())
                .correo(usuario.getCorreo())
                .build();
    }
}