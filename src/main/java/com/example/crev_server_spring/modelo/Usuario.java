package com.example.crev_server_spring.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "usuario")
@JsonIgnoreProperties(value = {"usuario"})
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;
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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;
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
    /*
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    @Column(nullable = false)
    private boolean enabled = true;
*/
    public Usuario getUsuario() {
        return this;
    }
    public enum Rol {
        ADMIN,
        USER
    }
}
