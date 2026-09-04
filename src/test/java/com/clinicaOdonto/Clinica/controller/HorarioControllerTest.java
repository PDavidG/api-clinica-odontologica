package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.HorarioMapper;
import com.clinicaOdonto.Clinica.security.jwt.JwtAuthEntryPoint;
import com.clinicaOdonto.Clinica.security.jwt.JwtAuthenticationFilter;
import com.clinicaOdonto.Clinica.security.jwt.JwtGenerator;
import com.clinicaOdonto.Clinica.service.HorarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = HorarioController.class,
        excludeAutoConfiguration = {
                // Excluye la configuracion de seguridad principal y UserDetailsService
                SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class
        },
        // Se añade excludeFilters para evitar que Spring escanee y cree los beans de seguridad especificos
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                JwtAuthenticationFilter.class,
                JwtGenerator.class,
                JwtAuthEntryPoint.class
        })
)
class HorarioControllerTest {

    // Simular peticiones Http
    @Autowired
    private MockMvc mockMvc;

    private HorarioService horarioService;
    private HorarioMapper horarioMapper;

    //Serializar y deserializar JSON
    @Autowired
    private ObjectMapper objectMapper;

    private Horario horario;
    private HorarioRequestDto horarioRequestDto;


    // Proposito principal actuar como fabrica de beans para el test
    @TestConfiguration
    static class HorarioControllerTestConfig {
        @Bean
        @Primary
        HorarioService horarioService() {
            return mock(HorarioService.class);
        }

        @Bean
        @Primary
        HorarioMapper horarioMapper() {
            return mock(HorarioMapper.class);
        }
    }

    @BeforeEach
    void setUp(@Autowired HorarioService horarioServiceMock, @Autowired HorarioMapper horarioMapperMock) {
        this.horarioService = horarioServiceMock;
        this.horarioMapper = horarioMapperMock;

        reset(horarioService, horarioMapper);

        horario = new Horario();
        horario.setIdHorario(5L);
        horario.setHorarioInicio("11:00");
        horario.setHorarioFin("17:00");

        horarioRequestDto = new HorarioRequestDto();
        horarioRequestDto.setHorarioInicio("11:00");
        horarioRequestDto.setHorarioFin("17:00");

    }

    @Test
    @DisplayName("Get /api/v1/horarios/{id} - Debe retornar un horario por ID cuando existe")
    @WithMockUser(username = "testUser", roles = "USER")
    void shouldReturnHorarioById() throws Exception{

        when(horarioService.findById(anyLong())).thenReturn(horario);

        // Action
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/horarios/{id}", 5L)

                .accept(MediaType.APPLICATION_JSON)
        )
                // Assert
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idHorario").value(5))
                .andExpect(jsonPath("$.horarioInicio").value("11:00"))
                .andExpect(jsonPath("$.horarioFin").value("17:00"));

        verify(horarioService, times(1)).findById(5L);

    }

    @Test
    @DisplayName("Get /api/v1/horarios/{id} - Debe retornar 404 not found cuando el horario no existe")
    @WithMockUser(username = "testUser", roles = "USER")
    void shouldReturnNotFoundWhenScheduleDoesNotExists() throws Exception {

        when(horarioService.findById(anyLong())).thenThrow(
                new ResourceNotFoundException("No se encontro un horario con el id: 99")
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/horarios/{id}", 99L)

                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No se encontro un horario con el id: 99"));

        verify(horarioService, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Get /api/v1/horarios - Debe retornar todos los horarios")
    @WithMockUser(username = "testUser", roles = "USER")
    void shouldReturnAllSchedules() throws Exception {

        Horario horarioInstance = new Horario();
        horarioInstance.setIdHorario(10L);
        horarioInstance.setHorarioInicio("07:00");
        horarioInstance.setHorarioFin("14:00");

        List<Horario> horarioList = List.of(horario, horarioInstance);

        when(horarioService.findAll()).thenReturn(horarioList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/horarios")

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0]").exists())
                .andExpect(jsonPath("$.[1]").exists())
                .andExpect(jsonPath("$.[2]").doesNotExist())

                .andExpect(jsonPath("$.[0].idHorario").value(5))
                .andExpect(jsonPath("$.[0].horarioInicio").value("11:00"))
                .andExpect(jsonPath("$.[0].horarioFin").value("17:00"))

                .andExpect(jsonPath("$.[1].idHorario").value(10))
                .andExpect(jsonPath("$.[1].horarioInicio").value("07:00"))
                .andExpect(jsonPath("$.[1].horarioFin").value("14:00"));

        verify(horarioService, times(1)).findAll();
        // test Defensivo
        verify(horarioService, never()).findById(anyLong());
    }
}