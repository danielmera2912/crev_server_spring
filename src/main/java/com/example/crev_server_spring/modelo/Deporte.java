package com.example.crev_server_spring.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deporte {
    // Modelo de deporte
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Boolean equipos;
}
