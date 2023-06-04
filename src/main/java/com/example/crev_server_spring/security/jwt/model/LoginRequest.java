package com.example.crev_server_spring.security.jwt.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    // Cuerpo para el login
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}