package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipo {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String escudo;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Set<UsuarioEquipo> equipoUsuarios = new HashSet<>();
}
