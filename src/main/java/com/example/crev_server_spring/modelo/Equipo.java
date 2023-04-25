package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JsonIgnore
    @OneToMany(mappedBy = "equipo")
    private List<UsuarioEquipo> usuarioEquipos;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Set<UsuarioEquipo> equipoUsuarios = new HashSet<>();
    public List<Usuario> getUsuarios() {
        return usuarioEquipos == null ? new ArrayList<>() :
                usuarioEquipos.stream()
                        .map(ue -> ue == null ? null : ue.getUsuario())
                        .filter(u -> u != null)
                        .collect(Collectors.toList());
    }
    public List<Long> getUsuarioEquipoIds() {
        return equipoUsuarios.stream()
                .map(ue -> ue.getId())
                .sorted()
                .collect(Collectors.toList());
    }



}
