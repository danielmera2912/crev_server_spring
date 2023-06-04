package com.example.crev_server_spring.security;

import com.example.crev_server_spring.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Configuración de seguridad
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/evento").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario_equipo").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario_evento").permitAll()
                .antMatchers(HttpMethod.GET, "/equipo").permitAll()
                .antMatchers(HttpMethod.GET, "/ciudad").permitAll()
                .antMatchers(HttpMethod.GET, "/deporte").permitAll()
                .antMatchers(HttpMethod.GET, "/media").permitAll()
                .antMatchers(HttpMethod.GET, "/media/{filename}").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/{id}/eventos").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/buscarPorNombre/{username}").permitAll()
                .antMatchers(HttpMethod.GET, "/evento/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/evento/{id}/equipos").permitAll()
                .antMatchers(HttpMethod.GET, "/equipo/{id}/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/existeNombre").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/existeCorreo").permitAll()
                .antMatchers(HttpMethod.GET, "/equipo/{id}/usuario-equipo-ids").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/{usuarioId}/eventos").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario_evento/evento/{id}").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}