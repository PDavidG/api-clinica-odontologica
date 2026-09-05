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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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

    @Test
    @DisplayName("Post /api/v1/horarios -- Debe crear un horario y retornar 201 created")
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void shouldCreatedScheduleSuccessfully() throws Exception {

        HorarioRequestDto horaRequest = new HorarioRequestDto();
        horaRequest.setHorarioInicio("11:30");
        horaRequest.setHorarioFin("15:00");

        Horario saveHorario = new Horario();
        saveHorario.setIdHorario(9L);
        saveHorario.setHorarioInicio("11:30");
        saveHorario.setHorarioFin("15:00");

        when(horarioService.save(any(HorarioRequestDto.class))).thenReturn(saveHorario);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/horarios")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horaRequest)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idHorario").value(9))
                .andExpect(jsonPath("$.horarioInicio").value("11:30"))
                .andExpect(jsonPath("$.horarioFin").value("15:00"));

        // Verificación de interacciones con mocks
        verify(horarioService, times(1)).save(any(HorarioRequestDto.class));

        verify(horarioService, never()).findAll();
        verify(horarioService, never()).findById(anyLong());
    }

    @Test
    @DisplayName("PUT /api/v1/horarios/{id} -- Debe de actualizar un horario existente y retornar 200 OK")
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void shouldUpdateScheduleSuccessfully() throws Exception{

        Long idSchedule = 9L;

        HorarioRequestDto horaRequest = new HorarioRequestDto();
        horaRequest.setHorarioInicio("08:00");
        horaRequest.setHorarioFin("21:00");

        Horario horaInstance = new Horario();
        horaInstance.setIdHorario(idSchedule);
        horaInstance.setHorarioInicio("08:00");
        horaInstance.setHorarioFin("21:00");

        when(horarioService.update(eq(idSchedule), any(HorarioRequestDto.class))).thenReturn(horaInstance);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/horarios/{id}", idSchedule)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(horaRequest)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idHorario").value(9))
                .andExpect(jsonPath("$.horarioInicio").value("08:00"))
                .andExpect(jsonPath("$.horarioFin").value("21:00"));

        verify(horarioService, times(1)).update(anyLong(), any(HorarioRequestDto.class));

        verify(horarioService, never()).save(any(HorarioRequestDto.class));
        verify(horarioService, never()).findById(anyLong());
        verify(horarioService, never()).findAll();
    }

    @Test
    @DisplayName("Delete /api/v1/horarios{id} -- Debe eliminar un horario y retornar 204 No content")
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void shouldDeleteScheduleSuccessfully() throws Exception {

        final Long scheduleIdToDelete = 5L;

        doNothing().when(horarioService).deleteById(scheduleIdToDelete);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/horarios/{id}", scheduleIdToDelete)
                .with(csrf()))
                .andExpect(status().isNoContent());

        verify(horarioService, times(1)).deleteById(scheduleIdToDelete);

        verify(horarioService, never()).findById(anyLong());
        verify(horarioService, never()).findAll();
        verify(horarioService, never()).save(any(HorarioRequestDto.class));
        verify(horarioService, never()).update(anyLong(), any(HorarioRequestDto.class));
    }

    @Test
    @DisplayName("Delete /api/v1/horarios/{id} -- Debe retornar 404 Not Found si el horario a eliminar no existe")
    @WithMockUser(username = "adminUser", roles = "ADMIN")
    void shoulReturnNotFoundWhenDeletingNoExistsSchedule() throws Exception {

        final Long scheduleIdNotExist = 99L;

        doThrow(new ResourceNotFoundException("No se encontro un horario con el id: " + scheduleIdNotExist))
                .when(horarioService).deleteById(scheduleIdNotExist);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/horarios/{id}", scheduleIdNotExist)
                .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not found"))
                .andExpect(jsonPath("$.message").value("No se encontro un horario con el id: " + scheduleIdNotExist));

        verify(horarioService, times(1)).deleteById(scheduleIdNotExist);
    }
}