package com.example.crev_server_spring.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {
    private String username;
    private String avatar;
    private String correo;
    private Long id;
    private LocalDate fechaNacimiento;
}