package com.example.crev_server_spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    // Servicios de registro de usuario
    private final UsuarioService userService;

    public CustomUserDetailsService(UsuarioService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("No encontrado usuario "+username));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return userService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario con la id "+ id));
    }
}
