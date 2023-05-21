package com.example.crev_server_spring.service;

import com.example.crev_server_spring.dto.CreateUserRequest;
import com.example.crev_server_spring.modelo.UserRole;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }


    public Usuario save(CreateUserRequest createUserRequest) {
        LocalDate fechaNacimiento = LocalDate.parse(createUserRequest.getFechaNacimiento());
        Usuario usuario = Usuario.builder()
                .username(createUserRequest.getUsername())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .avatar(createUserRequest.getAvatar())
                .correo(createUserRequest.getCorreo())
                .fechaNacimiento(fechaNacimiento)
                .role(UserRole.USER)
                .build();
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del usuario ya existe");
        }
    }
    public Usuario modify(Usuario usuario){
        usuario.setUpdatedAt(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }


    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }


    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public List<Usuario> findByUsernameContaining(String username) {
        return usuarioRepository.findByUsernameContainingAndIdNot(username, 0L);
    }


    public Page<Usuario> findAllPaginated(Integer page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable);
    }

}
