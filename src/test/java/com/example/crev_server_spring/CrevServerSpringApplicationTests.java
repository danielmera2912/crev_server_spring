package com.example.crev_server_spring;

import com.example.crev_server_spring.controller.CiudadController;
import com.example.crev_server_spring.controller.DeporteController;
import com.example.crev_server_spring.controller.EventoController;
import com.example.crev_server_spring.controller.UsuarioController;
import com.example.crev_server_spring.dto.CreateUserRequest;
import com.example.crev_server_spring.dto.GetUserDto;
import com.example.crev_server_spring.dto.UserDtoConverter;
import com.example.crev_server_spring.modelo.Ciudad;
import com.example.crev_server_spring.modelo.Deporte;
import com.example.crev_server_spring.modelo.Evento;
import com.example.crev_server_spring.modelo.Usuario;
import com.example.crev_server_spring.repos.UsuarioRepository;
import com.example.crev_server_spring.service.CiudadService;
import com.example.crev_server_spring.service.DeporteService;
import com.example.crev_server_spring.service.EventoService;
import com.example.crev_server_spring.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CrevServerSpringApplicationTests {
    // Crear, obtener y verificación de un usuario
    @Test
    public void testNewUsuario() {
        // Crear instancias de las clases necesarias
        UsuarioService usuarioService = Mockito.mock(UsuarioService.class);
        UserDtoConverter userDtoConverter = Mockito.mock(UserDtoConverter.class);
        UsuarioController usuarioController = new UsuarioController(usuarioService, userDtoConverter, null);

        // Crear el objeto CreateUserRequest de prueba
        CreateUserRequest newUser = CreateUserRequest.builder()
                .username("testuser")
                .password("password123")
                .avatar("avatar.jpg")
                .correo("test@example.com")
                .fechaNacimiento("1990-01-01")
                .build();

        // Crear un objeto Usuario de prueba
        Usuario usuario = Usuario.builder()
                .id(1L)
                .username("testuser")
                .password("password123")
                .avatar("avatar.jpg")
                .correo("test@example.com")
                .fechaNacimiento(LocalDate.parse("1990-01-01"))
                .build();

        // Configurar el comportamiento esperado del servicio de usuario
        Mockito.when(usuarioService.save(Mockito.any(CreateUserRequest.class))).thenReturn(usuario);

        // Configurar el comportamiento esperado del convertidor de DTO
        GetUserDto userDto = GetUserDto.builder()
                .username("testuser")
                .avatar("avatar.jpg")
                .correo("test@example.com")
                .id(1L)
                .fechaNacimiento(LocalDate.parse("1990-01-01"))
                .build();
        Mockito.when(userDtoConverter.convertUsuarioToGetUserDto(usuario)).thenReturn(userDto);

        // Ejecutar el método a probar
        ResponseEntity<GetUserDto> response = usuarioController.newUsuario(newUser);

        // Verificar el resultado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
    // Crear, obtener y verificación de un evento
    @Test
    public void testObtenerEvento() {
        // Crear una instancia del servicio de evento simulado
        EventoService eventoService = Mockito.mock(EventoService.class);

        // Crear un objeto Evento de prueba
        Evento evento = Evento.builder()
                .id(1L)
                .hora("18:00")
                .fecha(LocalDate.parse("2023-05-25"))
                .build();

        // Configurar el comportamiento esperado del servicio de evento
        Mockito.when(eventoService.findById(Mockito.anyLong())).thenReturn(Optional.of(evento));

        // Crear una instancia del controlador de evento con el servicio simulado
        EventoController eventoController = new EventoController(eventoService);

        // Ejecutar el método a probar
        ResponseEntity<Evento> response = eventoController.obtenerUno(1L);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(evento, response.getBody());
    }
    // Crear, obtener y verificación de un deporte
    @Test
    public void testObtenerDeporte() {
        // Crear una instancia del servicio de deporte simulado
        DeporteService deporteService = Mockito.mock(DeporteService.class);

        // Crear un objeto Deporte de prueba
        Deporte deporte = Deporte.builder()
                .id(1L)
                .nombre("Fútbol")
                .build();

        // Configurar el comportamiento esperado del servicio de deporte
        Mockito.when(deporteService.findById(Mockito.anyLong())).thenReturn(Optional.of(deporte));

        // Crear una instancia del controlador de deporte con el servicio simulado
        DeporteController deporteController = new DeporteController(deporteService);

        // Ejecutar el método a probar
        Deporte response = deporteController.obtenerUno(1L);

        // Verificar el resultado
        assertEquals(deporte, response);
    }
    // Crear, obtener y verificación de una ciudad
    @Test
    public void testObtenerCiudad() {
        // Crear una instancia del servicio de ciudad simulado
        CiudadService ciudadService = Mockito.mock(CiudadService.class);

        // Crear un objeto Ciudad de prueba
        Ciudad ciudad = Ciudad.builder()
                .id(1L)
                .nombre("Ciudad Ejemplo")
                .build();

        // Configurar el comportamiento esperado del servicio de ciudad
        Mockito.when(ciudadService.findById(Mockito.anyLong())).thenReturn(Optional.of(ciudad));

        // Crear una instancia del controlador de ciudad con el servicio simulado
        CiudadController ciudadController = new CiudadController(ciudadService);

        // Ejecutar el método a probar
        Ciudad response = ciudadController.obtenerUno(1L);

        // Verificar el resultado
        assertEquals(ciudad, response);
    }


}
