package com.example.crev_server_spring.config;

import com.example.crev_server_spring.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll() // Permite el acceso sin autenticación a todas las solicitudes
                .and()
                .httpBasic().disable() // Deshabilita la autenticación básica
                .formLogin().disable() // Deshabilita el formulario de inicio de sesión
                .csrf().disable(); // Deshabilita la protección CSRF
    }
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/usuario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/usuario").authenticated()
                .antMatchers(HttpMethod.DELETE, "/usuario/**").authenticated()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll() // Permitir acceso al endpoint POST /usuario
                .anyRequest().permitAll()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();
    }
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}





