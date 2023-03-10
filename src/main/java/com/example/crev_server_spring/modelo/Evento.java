package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento {
    @Id
    @GeneratedValue
    private Long id;
    private String hora;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "deporte_id")
    private Deporte deporte;
    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<UsuarioEvento> eventoUsuarios = new HashSet<>();
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
