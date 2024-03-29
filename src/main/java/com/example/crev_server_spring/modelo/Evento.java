package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento {
    // Modelo de evento
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
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private Set<UsuarioEvento> eventoUsuarios = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Equipo> equipos;
    @Column(name = "puntos_local")
    private Integer puntosLocal;

    @Column(name = "puntos_visitante")
    private Integer puntosVisitante;

    @Column(name = "estado", columnDefinition = "varchar(20) default 'EN CURSO'")
    private String estado;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    public List<UsuarioEventoDTO> getUsuarios() {
        List<UsuarioEvento> eventoUsuariosOrdenados = eventoUsuarios.stream()
                .sorted(Comparator.comparingLong(UsuarioEvento::getId))
                .collect(Collectors.toList());
        List<UsuarioEventoDTO> usuarios = new ArrayList<>();
        for (UsuarioEvento eu : eventoUsuariosOrdenados) {
            usuarios.add(new UsuarioEventoDTO(eu.getUsuario()));
        }
        return usuarios;
    }



    public List<Equipo> getEquipos() {
        return equipos;
    }
}
