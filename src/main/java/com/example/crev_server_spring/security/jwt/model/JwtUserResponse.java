package com.example.crev_server_spring.security.jwt.model;

import com.example.crev_server_spring.dto.GetUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {
    // Generación del token
    private String token;

    @Builder(builderMethodName="jwtUserResponseBuilder")
    public JwtUserResponse(String username, String avatar, String correo, Long id, LocalDate fechaNacimiento, String token) {
        super(username, avatar, correo, id, fechaNacimiento);
        this.token = token;
    }
}