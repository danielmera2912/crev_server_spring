package com.example.crev_server_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// DTO para la creación de un usuario
public class CreateUserRequest {
    private String username, password, avatar, correo, fechaNacimiento;
}
