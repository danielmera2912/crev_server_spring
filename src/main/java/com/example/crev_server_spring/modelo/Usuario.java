package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String avatar;

    private String clave;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<UsuarioEquipo> equiposUsuario = new HashSet<>();
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<UsuarioEvento> eventosUsuario = new HashSet<>();
}
