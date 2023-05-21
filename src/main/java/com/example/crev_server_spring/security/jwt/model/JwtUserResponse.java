package com.example.crev_server_spring.security.jwt.model;

import com.example.crev_server_spring.dto.GetUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {
    private String token;

    @Builder(builderMethodName="jwtUserResponseBuilder")
    public JwtUserResponse(String username, String avatar, String correo, String token) {
        super(username, avatar, correo);
        this.token = token;
    }
}